package dev.webshares.app.webshares;

import org.springframework.context.ApplicationEvent;

public class WebShareDataUpdatedEvent extends ApplicationEvent {

  public WebShareDataUpdatedEvent(WebShare webShare) {
    super(webShare);
  }

}
