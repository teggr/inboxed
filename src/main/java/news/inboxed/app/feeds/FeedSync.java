package news.inboxed.app.feeds;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndLink;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import news.inboxed.app.feeds.content.FeedContent;
import news.inboxed.app.feeds.content.FeedContentRepository;

/**
 * FeedSync
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class FeedSync {

  private final FeedContentRepository feedContentStore;

  public void refreshFeed(Feed feed) {

    try {

      log.info("Refresing feed {}", feed.feedId());

      SyndFeed syndFeed = new SyndFeedInput().build(new XmlReader(feed.url()));

      List<SyndEntry> entries = syndFeed.getEntries();

      List<SyndLink> links = syndFeed.getLinks();

      log.info("Feed found: {} with {} enties and {} links", syndFeed.getTitle(),entries.size() , links.size());

      FeedContent feedContent = feedContentStore.save(new FeedContent(feed.feedId(), feed.url(), syndFeed ));

      // TODO: now need to generate the refreshed event, so we can get the next set of items and publish to the inbox subscription

    } catch (Exception e) {

      throw new RuntimeException(e);

    }

  }

}
