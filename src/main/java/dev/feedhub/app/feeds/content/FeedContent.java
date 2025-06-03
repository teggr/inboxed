package dev.feedhub.app.feeds.content;

import java.net.URL;

import com.rometools.rome.feed.synd.SyndFeed;

import dev.feedhub.app.feeds.FeedId;

public record FeedContent(FeedId feedId, URL url, SyndFeed syndFeed) {
    
}
