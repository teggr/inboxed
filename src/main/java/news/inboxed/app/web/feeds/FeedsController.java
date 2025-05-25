package news.inboxed.app.web.feeds;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import news.inboxed.app.feeds.FeedId;
import news.inboxed.app.feeds.Feeds;
import news.inboxed.app.web.inbox.InboxController;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.*;

import java.net.URL;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/feeds")
@RequiredArgsConstructor
public class FeedsController {

  private final Feeds feeds;

  @GetMapping
  public String getFeeds(Pageable pageable, Model model) {

    String homeUrl = fromMethodName(InboxController.class, "getInbox", pageable, model).build().toUriString();
    model.addAttribute("homeUrl", homeUrl);

    String adminFeedsUrl = fromMethodName(FeedsController.class, "getFeeds", pageable, model).build().toUriString();
    model.addAttribute("adminFeedsUrl", adminFeedsUrl);
    model.addAttribute("refreshUrl", adminFeedsUrl);

    String addFeedUrl = fromMethodName(FeedsController.class, "postFeed", null, null).build().toUriString();
    model.addAttribute("addFeedUrl", addFeedUrl);

    String updateFeedsUrl = fromMethodName(UpdateFeedsController.class, "postUpdateFeeds").build().toUriString();
    model.addAttribute("updateFeedsUrl", updateFeedsUrl);

    model.addAttribute("feeds", feeds.getFeeds(pageable));
    return "feedsView";
  }

  @PostMapping
  public String postFeed(@RequestParam(value = "url", required = false) URL url,
      RedirectAttributes redirectAttributes) {

    FeedId feedId = feeds.add(url);

    // redirectAttributes.addAttribute("feedId", feedId.id());

    return "redirect:/feeds";

  }

}
