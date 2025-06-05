package dev.feedhub.app.subscriptions;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("FEEDHUB_SUBSCRIBERS")
public record FeedSubscriber ( @Id Long id, String subscriberId ) {

}
