package news.inboxed.app.webshares;

import org.springframework.context.ApplicationEvent;

public class WebShareMetaDataUpdatedEvent extends ApplicationEvent {

  public WebShareMetaDataUpdatedEvent(WebShare webShare) {
    super(webShare);
  }

}
