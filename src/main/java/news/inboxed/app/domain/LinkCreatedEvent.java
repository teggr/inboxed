package news.inboxed.app.domain;

import org.springframework.context.ApplicationEvent;

public class LinkCreatedEvent extends ApplicationEvent {

  public LinkCreatedEvent(Link savedLink) {
    super(savedLink);
  }

}