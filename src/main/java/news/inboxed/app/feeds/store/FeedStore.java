package news.inboxed.app.feeds.store;

import news.inboxed.app.feeds.Feed;
import news.inboxed.app.feeds.FeedId;

public interface FeedStore {

    String save(Feed feed);

    Feed get(FeedId id);

}
