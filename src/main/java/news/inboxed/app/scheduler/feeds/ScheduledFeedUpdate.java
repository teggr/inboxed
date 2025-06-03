package news.inboxed.app.scheduler.feeds;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import dev.feedhub.app.fetch.FetchFeedJob;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ScheduledFeedUpdate {

  private final FetchFeedJob feedUpdateJob;

  @Scheduled(cron="0 0 * * * *") // every hour
  public void runFeedUpdateJob() {
    feedUpdateJob.run();
  }

}
