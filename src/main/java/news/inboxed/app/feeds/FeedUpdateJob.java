package news.inboxed.app.feeds;

import java.time.Instant;
import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class FeedUpdateJob {

  private final Feeds feeds;
  private final FeedSync feedSync;
  private final ApplicationEventPublisher applicationEventPublisher;

  // todo: given the scheduled feeds and id, do what needs doing
  public void run() {

    List<Feed> scheduledFeeds = feeds.getNextScheduledFeeds(Instant.now());

    for (Feed feed : scheduledFeeds) {

      Schedule current = feed.schedule();

      log.info("Updating feed {}", feed.id());

      try {

        feedSync.refreshFeed(feed);

        feed = feed.withSchedule(new Schedule(Instant.now().plus(feed.scheduler())))
            .withLastScheduledRun(new ScheduledRun(current.nextUpdate(), ScheduledRunResult.SUCCESS));

      } catch (Exception e) {

        feed = feed.withSchedule(new Schedule(Instant.now().plus(feed.scheduler())))
            .withLastScheduledRun(new ScheduledRun(current.nextUpdate(), ScheduledRunResult.FAILURE));

      }

      feed = feeds.update(feed);

    }

    applicationEventPublisher.publishEvent(new FeedsUpdatedEvent(scheduledFeeds));

  }

}
