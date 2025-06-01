package news.inboxed.app.feeds;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class FeedUpdateJob {

  private final Feeds feeds;

  // todo: given the scheduled feeds and id, do what needs doing
  public void run() {

    List<Feed> scheduleFeeds = feeds.getNextSchdeuledFeeds(Instant.now());

    for (Feed feed : scheduleFeeds) {

      Schedule current = feed.schedule();

      log.info("Updating feed {}", feed.id());

      feed = feed.withSchedule(new Schedule(Instant.now().plus(feed.scheduler())))
          .withLastScheduledRun(new ScheduledRun(current.nextUpdate(), ScheduledRunResult.SUCCESS));

      feed = feeds.update(feed);

    }

    // if first time (fetch without last updated)
    // fetch the feed(up to last updated)
    // store the feed
    // publish feed updated event

  }

  public void fetchFeed() {

    // todo: keep a listf feeds that need fetching

    try {

      // FeedId feedId = new FeedId(feedUrl);

      // TODO: this could be done offline, queued list of things to load

      // SyndFeed syndFeed = new SyndFeedInput().build(new XmlReader(new
      // URL(feedUrl)));

      // List<SyndEntry> entries = syndFeed.getEntries();

      // List<SyndLink> links = syndFeed.getLinks();

      // System.out.println(syndFeed.getTitle() + " " + entries.size() + " " +
      // links.size());

      // Feed feed = new Feed(feedId, feedUrl, syndFeed);

      // return feedRepository.save( feed );

    } catch (Exception e) {

      throw new RuntimeException(e);

    }

  }

}
