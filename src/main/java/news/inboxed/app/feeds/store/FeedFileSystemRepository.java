package news.inboxed.app.feeds.store;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndLink;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.SyndFeedOutput;

import news.inboxed.app.feeds.store.FileSystemFeedStoreKey;

@Repository
public class FeedFileSystemRepository {

    

    public List<FeedDetails> findAll() {

        File[] rssDirectories = localStore.listFiles();

        return Stream.of(rssDirectories)
                .map( FeedFileSystemRepository::readFeed)
                .filter(Objects::nonNull)
                .map( FeedFileSystemRepository::mapFeedDetails)
                .sorted(Comparator.comparing(FeedDetails::getPublishedDate).reversed())
                .collect(Collectors.toList());

    }

    private static FeedDetails mapFeedDetails(Feed feed) {
        return new FeedDetails() {

            @Override
            public String getId() {
                return feed.getId().id();
            }

            @Override
            public String getTitle() {
                return feed.getSyndFeed().getTitle();
            }

            @Override
            public Date getPublishedDate() {
                return feed.getSyndFeed().getPublishedDate();
            }

            @Override
            public FeedDetailsLinks getLinks() {
                Optional<SyndLink> feedLink = feed.getSyndFeed().getLinks().stream().filter(l -> l.getRel().equals("self")).findFirst();
                Optional<SyndLink> siteLink = feed.getSyndFeed().getLinks().stream().filter(l -> l.getRel().equals("alternative")).findFirst();
                return new FeedDetailsLinks() {

                    @Override
                    public String getSiteHref() {
                        return siteLink.map(SyndLink::getHref).orElse(feed.getSyndFeed().getLink());
                    }

                    @Override
                    public String getFeedHref() {
                        return feedLink.map(SyndLink::getHref).orElse(feed.getUrl());
                    }
                };
            }

        };
    }

    private static Feed readFeed(File rssDirectory) {

        try {

            File cachedFile = new File(rssDirectory, "cached-feed.xml");

            if (!cachedFile.exists()) {
                return null;
            }

            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(cachedFile);

            File propertiesFile = new File(rssDirectory, "feed.properties");

            Properties properties = new Properties();
            try(FileReader fr = new FileReader(propertiesFile)) {
            properties.load(fr);
            }

            FeedId feedId = new FeedId( properties.getProperty("feedId") );
            String feedUrl = properties.getProperty("feedUrl");

            return new Feed( feedId, feedUrl, feed );

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FeedException e) {
            throw new RuntimeException(e);
        }

    }

    public List<EntryDetails> findAllEntries() {

        File[] rssDirectories = localStore.listFiles();

        return Stream.of(rssDirectories)
                .map( FeedFileSystemRepository::readFeed )
                .filter(Objects::nonNull)
                .flatMap(FeedFileSystemRepository::mapFeedEntry)
                .sorted(Comparator.comparing(EntryDetails::getPublishedDate).reversed())
                .collect(Collectors.toList());
    }
    private static Stream<EntryDetails> mapFeedEntry(Feed feed) {
        return feed.getSyndFeed().getEntries().stream()
                .map(entry -> new EntryDetails() {

                    @Override
                    public String title() {
                        return entry.getTitle();
                    }

                    @Override
                    public Date getPublishedDate() {
                        return entry.getPublishedDate();
                    }

                });
    }

}
