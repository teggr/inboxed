package news.inboxed.app.webshares;

import java.time.Instant;

public record WebShareData(String title, String html, Instant createdDate, String fetchError) {

  public WebShareData(String title, String html) {
    this(title, html, Instant.now(), null);
  }

  public WebShareData(String fetchError) {
    this(null, null, Instant.now(), fetchError);
  }

}
