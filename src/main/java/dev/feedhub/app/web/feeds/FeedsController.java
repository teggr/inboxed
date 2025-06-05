package dev.feedhub.app.web.feeds;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.feedhub.app.feeds.FeedId;
import dev.feedhub.app.feeds.Feeds;
import dev.feedhub.app.web.admin.feeds.FeedsAdminController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.*;

@Controller
@RequestMapping("/feedhub/feeds")
@RequiredArgsConstructor
public class FeedsController {

  private final Feeds feeds;

  @GetMapping
  public String getFeeds(Pageable pageable, Model model) {

    String feedsAdminUrl = fromMethodName(FeedsAdminController.class, "getFeeds", pageable, model).build().toUriString();
    model.addAttribute("feedsAdminUrl", feedsAdminUrl);

    String refreshUrl = fromMethodName(FeedsController.class, "getFeeds", pageable, model).build().toUriString();
    model.addAttribute("refreshUrl", refreshUrl);

    model.addAttribute("feedUrlBuilder", new FeedUrlBuilder() {
      @Override
      public String build(FeedId feedId) {
        return fromMethodName(FeedsController.class, "getFeed", feedId.id(), null, null).build().toUriString();
      }
    });

    model.addAttribute("feeds", feeds.getFeeds(pageable));

    return "feedsView";

  }

  @GetMapping("/{feedId}")
  public String getFeed(@PathVariable("feedId") String feedIdValue, Pageable pageable, Model model) {

    FeedId feedId = new FeedId(feedIdValue);

    String feedsUrl = fromMethodName(FeedsController.class, "getFeeds", null, null).build().toUriString();
    model.addAttribute("feedsUrl", feedsUrl);

    model.addAttribute("feed", feeds.getFeed(feedId));
    model.addAttribute("feedItems", feeds.getFeedItems(feedId, pageable));

    return "feedView";

  }

}
