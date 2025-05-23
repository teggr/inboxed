package news.inboxed.app.feeds.store;

import news.inboxed.app.feeds.Feed;

public interface FeedStore {

    String save(Feed feed);

    Feed get(String id);

}
