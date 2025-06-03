package dev.feedhub.app.feeds.content;

import dev.feedhub.app.feeds.FeedId;

public interface FeedContentRepository {

    FeedContent save(FeedContent feedContent);

    FeedContent findByFeedId(FeedId feedId);

}
