package news.inboxed.app.web.feeds;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import news.inboxed.app.feeds.FeedUpdateJob;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/update-feeds")
@RequiredArgsConstructor
public class UpdateFeedsController {

  private final FeedUpdateJob feedUpdateJob;

  @PostMapping
  public String postUpdateFeeds() {
    feedUpdateJob.run();
    return "redirect:/feeds";
  }

}
