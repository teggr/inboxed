package news.inboxed.app.domain;

import java.time.Instant;
import java.util.List;

public record ArticleMetaData(
    Instant publishedTime,
    Instant modifiedTime,
    Instant expirationTime,
    String author,
    String section,
    List<String> tags
) {}