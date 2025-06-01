package news.inboxed.app.subscriptions;

import java.net.URL;
import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Subscription
 */
@Table("SUBSCRIPTIONS")
public record Subscription( @Id Long id, String type, URL subscriptionUrl, @CreatedDate Instant createdDate ) {

}
