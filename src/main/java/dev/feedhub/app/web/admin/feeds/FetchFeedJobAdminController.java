package dev.feedhub.app.web.admin.feeds;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.feedhub.app.fetch.FetchFeedJobScheduler;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/fetch-feeds")
@RequiredArgsConstructor
public class FetchFeedJobAdminController {

  private final FetchFeedJobScheduler fetchFeedJobScheduler;

  @PostMapping
  public String postRunJob() {
    fetchFeedJobScheduler.run();
    return "redirect:/feedhub/admin/feeds";
  }

}
