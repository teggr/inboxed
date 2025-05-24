package news.inboxed.app.feeds.store;

import news.inboxed.app.feeds.FeedContent;
import news.inboxed.app.feeds.FeedId;

public interface FeedContentStore {

    String save(FeedContent feed);

    FeedContent get(FeedId id);

}
