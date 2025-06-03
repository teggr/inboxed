package dev.feedhub.app.fetch;

import org.springframework.stereotype.Component;

import dev.feedhub.app.feeds.Feeds;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class FetchFeedJob {

  private final Feeds feeds;

  // todo: given the scheduled feeds and id, do what needs doing
  public void run() {

    // List<FeedConfig> scheduledFeeds = feeds.getNextScheduledFeeds(Instant.now());

    // for (FeedConfig feed : scheduledFeeds) {

    //   Schedule current = feed.schedule();

    //   log.info("Updating feed {}", feed.id());

    //   try {

    //     feedSync.refreshFeed(feed);

    //     feed = feed.withSchedule(new Schedule(Instant.now().plus(feed.scheduler())))
    //         .withLastScheduledRun(new ScheduledRun(current.nextUpdate(), ScheduledRunResult.SUCCESS));

    //   } catch (Exception e) {

    //     feed = feed.withSchedule(new Schedule(Instant.now().plus(feed.scheduler())))
    //         .withLastScheduledRun(new ScheduledRun(current.nextUpdate(), ScheduledRunResult.FAILURE));

    //   }

    //   feed = feeds.update(feed);

    // }

    // applicationEventPublisher.publishEvent(new FeedsUpdatedEvent(scheduledFeeds));

  }

}
