package news.inboxed.app.webshares;

import org.springframework.context.ApplicationEvent;

public class WebShareAddedEvent extends ApplicationEvent {

  public WebShareAddedEvent(WebShare webShare) {
    super(webShare);
  }

}