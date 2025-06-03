package dev.feedhub.app.web.feeds;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.feedhub.app.fetch.FetchFeedJob;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/update-feeds")
@RequiredArgsConstructor
public class FeedUpdateController {

  private final FetchFeedJob feedUpdateJob;

  @PostMapping
  public String postUpdateFeeds() {
    feedUpdateJob.run();
    return "redirect:/feeds";
  }

}
