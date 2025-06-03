package dev.feedhub.app.fetch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndLink;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import dev.feedhub.app.feeds.Feed;
import dev.feedhub.app.feeds.FeedConfiguration;
import dev.feedhub.app.feeds.FeedConfigurations;
import dev.feedhub.app.feeds.FeedId;
import dev.feedhub.app.feeds.FeedItem;
import dev.feedhub.app.feeds.Feeds;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class FetchFeedJob {

  private final FeedConfigurations feedConfigurations;
  private final Feeds feeds;

  public void run(FeedId feedId) {

    log.info("Refresing feed {}", feedId);

    FeedConfiguration configuration = feedConfigurations.getFeedConfiguration(feedId);

    try {

      SyndFeed syndFeed = new SyndFeedInput().build(new XmlReader(configuration.url()));

      List<SyndEntry> entries = syndFeed.getEntries();

      List<SyndLink> links = syndFeed.getLinks();

      log.info("Feed found: {} with {} enties and {} links", syndFeed.getTitle(), entries.size(), links.size());

      Feed feed = new Feed(null, feedId, syndFeed.getTitle());

      List<FeedItem> feedItems = new ArrayList<>();
      entries.forEach( e -> {
        feedItems.add( new FeedItem(null, feedId, e.getTitle()) );
      });

      feeds.update(feed, feedItems);

    } catch (Exception e) {

      throw new RuntimeException(e);

    }

  }

}
