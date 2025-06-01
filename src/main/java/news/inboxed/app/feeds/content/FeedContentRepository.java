package news.inboxed.app.feeds.content;

import news.inboxed.app.feeds.FeedId;

public interface FeedContentRepository {

    FeedContent save(FeedContent feedContent);

    FeedContent findByFeedId(FeedId feedId);

}
