package news.inboxed.app.feeds.subscriptions;

import java.time.Instant;

import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Embedded.OnEmpty;

import news.inboxed.app.feeds.FeedId;

public record FeedSubscription( @Embedded(prefix = "FEED_", onEmpty = OnEmpty.USE_NULL) FeedId feedId, Instant lastUpdated ) {
}
