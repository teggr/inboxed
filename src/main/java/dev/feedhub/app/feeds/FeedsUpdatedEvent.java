package dev.feedhub.app.feeds;

import java.util.List;

import org.springframework.context.ApplicationEvent;

/**
 * FeedUpdatedEvent
 */
public class FeedsUpdatedEvent extends ApplicationEvent {

  public FeedsUpdatedEvent(List<Feed> updatedFeeds) {
    super(updatedFeeds);
  }

}
