package news.inboxed.app.webshares;

import org.springframework.context.ApplicationEvent;

/**
 * FetchedLinkDataCreated
 */
public class FetchedLinkDataCreatedEvent extends ApplicationEvent {

  public FetchedLinkDataCreatedEvent(Link updatedLink) {
    super(updatedLink);
  }

}
