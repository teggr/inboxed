package news.inboxed.app.scheduler.feeds;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import dev.feedhub.app.feeds.FeedUpdateJob;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ScheduledFeedUpdate {

  private final FeedUpdateJob feedUpdateJob;

  @Scheduled(cron="0 0 * * * *") // every hour
  public void runFeedUpdateJob() {
    feedUpdateJob.run();
  }

}
