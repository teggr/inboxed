package news.inboxed.app.feeds;

import org.springframework.context.ApplicationEvent;

/**
 * FeedAddedEvent
 */
public class FeedAddedEvent extends ApplicationEvent {

  public FeedAddedEvent(Feed feed) {
    super(feed);
  }

}
