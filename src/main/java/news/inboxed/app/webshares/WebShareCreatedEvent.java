package news.inboxed.app.webshares;

import org.springframework.context.ApplicationEvent;

public class WebShareCreatedEvent extends ApplicationEvent {

  public WebShareCreatedEvent(WebShare webShare) {
    super(webShare);
  }

}