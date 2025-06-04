package dev.feedhub.app.feeds;

import org.springframework.data.relational.core.mapping.Table;

@Table("FEED_HUB_FEED_ITEM_LINKS")
public record FeedItemLink(String href, String rel, String type, String title) {

}
