package dev.feedhub.app.scheduler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import dev.feedhub.app.feeds.Feed;
import dev.feedhub.app.feeds.FeedAddedEvent;
import dev.feedhub.app.feeds.Feeds;
import dev.feedhub.app.fetch.FeedUpdateJob;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedScheduler {

  private final Feeds feeds;
  private final FeedUpdateJob feedUpdateJob;

  @TransactionalEventListener(fallbackExecution = true)
  public void handleFeedAddedEvent(FeedAddedEvent addedEvent) {

    Feed feed = (Feed) addedEvent.getSource();

    // given the created date, create next schedule for immediately]
    feed = feed.withSchedule(new Schedule(feed.createdDate()));

    // TODO: could create the job here (quartz/jobrunr), rather than just use spring

    feed = feeds.update(feed);

    // schedule an immediate fetch
    feedUpdateJob.run();

  }

}
