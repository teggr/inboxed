package dev.feedhub.app.feeds;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Embedded.OnEmpty;

@Table("FEEDHUB_FEED_ITEMS")
public record FeedItem(
  @Id Long id, 
  @Embedded(prefix = "FEED_", onEmpty = OnEmpty.USE_NULL) FeedId feedId, 
  String title) {

}
