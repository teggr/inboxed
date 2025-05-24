package news.inboxed.app.feeds;

import java.net.URL;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Feeds {

  private final FeedRepository feedRepository;

  public Page<Feed> getFeeds(Pageable pageable) {
    return feedRepository.findAll(pageable);
  }

  public FeedId add(URL feedUrl) {

    try {

      // is this actually a feed url?
      SyndFeed syndFeed = new SyndFeedInput().build(new XmlReader(feedUrl));

      FeedId feedId = new FeedId(feedUrl.toString());

      Feed feed = new Feed(null, feedId, feedUrl, null);

      feed = feedRepository.save(feed);
      
      return feedId;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

}
