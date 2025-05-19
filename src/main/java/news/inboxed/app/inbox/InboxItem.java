package news.inboxed.app.inbox;

import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("INBOX_ITEMS")
public record InboxItem(
    @Id Long id,
    String subscriptionId,
    String subscriptionName,
    String canonicalUrl,
    String title,
    List<String> authors,
    String summary,
    Instant date,
    boolean starred,
    boolean read,
    String picture,
    List<String> tags
) {}
