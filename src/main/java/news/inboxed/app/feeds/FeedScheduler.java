package news.inboxed.app.feeds;

import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndLink;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

@Service
public class FeedScheduler {

    // scheduler for a feed
    // 1. need to add feeds
    // 2. need to fetch feeds
    // 3. need to store feeds
    // 4. need to read feeds
    // 5. is the feed updated? (feed last updated value)
    // 5. need need to work out what's new in entries (key last updated), 
    // previously published? subscription start date
    // 6. need to publish (to where?)

    @TransactionalEventListener(fallbackExecution = true)
    public void handleFeedAddedEvent(FeedAddedEvent addedEvent)  { 

        Feed feed = (Feed) addedEvent.getSource();

        // create a schedule for the feed
        // create an offline store for the feed

        // todo: keep a list of feeds that need fetching

        try {

            // FeedId feedId = new FeedId(feedUrl);

            // TODO: this could be done offline, queued list of things to load

            // SyndFeed syndFeed = new SyndFeedInput().build(new XmlReader(new URL(feedUrl)));

            // List<SyndEntry> entries = syndFeed.getEntries();

            // List<SyndLink> links = syndFeed.getLinks();

            // System.out.println(syndFeed.getTitle() + " " + entries.size() + " " + links.size());

            // Feed feed = new Feed(feedId, feedUrl, syndFeed);

            // return feedRepository.save( feed );

        } catch (Exception e) {

            throw new RuntimeException(e);

        }

    }

    // todo: given the scheduled feeds and id, do what needs doing
    public void doTheWork() {

        // if first time (fetch without last updated)
        // fetch the feed(up to last updated)
        // store the feed
        // publish feed updated event

    } 

}
