package news.inboxed.app.feeds.content;

import java.net.URL;

import com.rometools.rome.feed.synd.SyndFeed;

import news.inboxed.app.feeds.FeedId;

public record FeedContent(FeedId feedId, URL url, SyndFeed syndFeed) {
    
}
