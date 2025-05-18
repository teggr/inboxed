package news.inboxed.app.webshares;

import java.time.Instant;

public record FetchedLinkData(String title, String html, Instant createdDate, String fetchError) {

  public FetchedLinkData(String title, String html) {
    this(title, html, Instant.now(), null);
  }

  public FetchedLinkData(String fetchError) {
    this(null, null, Instant.now(), fetchError);
  }

  public boolean wasFetchedSuccessfully() {
    return this.fetchError == null;
  }

}
