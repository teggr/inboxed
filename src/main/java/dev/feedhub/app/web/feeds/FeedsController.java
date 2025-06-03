package dev.feedhub.app.web.feeds;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.feedhub.app.feeds.FeedConfigurations;
import dev.feedhub.app.feeds.FeedId;
import dev.feedhub.app.feeds.Feeds;
import dev.feedhub.app.fetch.FetchFeedJobScheduler;
import lombok.RequiredArgsConstructor;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.*;

import java.net.URL;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/feedhub/feeds")
@RequiredArgsConstructor
public class FeedsController {

  private final FeedConfigurations feedConfigurations;
  private final FetchFeedJobScheduler feedUpdateJobScheduler;
  private final Feeds feeds;

  @GetMapping
  public String getFeeds(Pageable pageable, Model model) {

    String refreshUrl = fromMethodName(FeedsController.class, "getFeeds", pageable, model).build().toUriString();
    model.addAttribute("refreshUrl", refreshUrl);

    String addFeedUrl = fromMethodName(FeedsController.class, "postFeed", null, null).build().toUriString();
    model.addAttribute("addFeedUrl", addFeedUrl);

    String runFetchFeedJobUrl = fromMethodName(FetchFeedJobController.class, "postRunJob").build().toUriString();
    model.addAttribute("runFetchFeedJobUrl", runFetchFeedJobUrl);

    model.addAttribute("feedConfigurations", feedConfigurations.getFeedConfigurations(pageable));
    model.addAttribute("scheduledFetchFeedJobs", feedUpdateJobScheduler.getScheduledFetchFeedJobs());
     model.addAttribute("feeds", feeds.getFeeds());
    
    return "feedsView";
  }

  @PostMapping
  public String postFeed(@RequestParam(value = "url", required = false) URL url,
      RedirectAttributes redirectAttributes) {

    FeedId feedId = feedConfigurations.createFeedConfiguration(url);

    // redirectAttributes.addAttribute("feedId", feedId.id());

    return "redirect:/feedhub/feeds";

  }

}
