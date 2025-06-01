package news.inboxed.app.subscriptions;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Subscription
 */
@Table("SUBSCRIPTIONS")
public record Subscription( @Id Long id, SubscriptionType type, String subscriptionId, @CreatedDate Instant createdDate ) {

}
