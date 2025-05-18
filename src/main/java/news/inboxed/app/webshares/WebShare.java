package news.inboxed.app.webshares;

import java.net.URL;
import java.time.Instant;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("WEB_SHARES")
public record WebShare(@Id Long id, URL url, String notes, Set<String> tags, @CreatedDate Instant createdDate,
    WebShareData webShareData, WebShareMetaData webShareMetaData) {

  public WebShare {
    if (notes == null) {
      notes = "";
    }
    if (tags == null) {
      tags = Set.of();
    }
  }

  public WebShare withWebShareData(WebShareData webShareData) {
    return new WebShare(this.id, this.url, this.notes, this.tags, this.createdDate, webShareData, this.webShareMetaData);
  }

  public WebShare withWebShareMetaData(WebShareMetaData webShareMetaData) {
    return new WebShare(this.id, this.url, this.notes, this.tags, this.createdDate, webShareData, webShareMetaData);
  }

  public boolean hasWebShareData() {
    return this.webShareData != null;
  }

  public boolean hasMetaData() {
    return this.webShareMetaData != null;
  }

}
