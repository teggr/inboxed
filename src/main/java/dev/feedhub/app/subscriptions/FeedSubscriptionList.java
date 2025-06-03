package dev.feedhub.app.subscriptions;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("FEED_SUBSCRIPTION_LIST")
public record FeedSubscriptionList(@Id Long id, Set<FeedSubscription> feedSubscriptions ) {

}
