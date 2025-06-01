package news.inboxed.app.feeds;

import org.springframework.context.ApplicationEvent;

/**
 * FeedUpdatedEvent
 */
public class FeedUpdatedEvent extends ApplicationEvent {

  public FeedUpdatedEvent(Feed feed) {
    super(feed);
  }

}
