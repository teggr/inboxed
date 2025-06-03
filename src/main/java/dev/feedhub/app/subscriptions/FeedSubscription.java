package dev.feedhub.app.subscriptions;

import java.time.Instant;

import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Embedded.OnEmpty;

import dev.feedhub.app.feeds.FeedId;

public record FeedSubscription( @Embedded(prefix = "FEED_", onEmpty = OnEmpty.USE_NULL) FeedId feedId, Instant lastUpdated ) {
}
