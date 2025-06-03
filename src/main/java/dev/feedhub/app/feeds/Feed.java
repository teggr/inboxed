package dev.feedhub.app.feeds;

import java.net.URL;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Embedded.OnEmpty;

@Table("FEEDHUB_FEEDS")
public record Feed( 
  @Id Long id, 
  @Embedded(prefix = "FEED_", onEmpty = OnEmpty.USE_NULL) FeedId feedId, 
  URL url,
  String title) {

}
