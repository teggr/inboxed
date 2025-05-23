package news.inboxed.app.feeds.store;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Properties;

import org.springframework.stereotype.Component;

import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedOutput;

import news.inboxed.app.feeds.Feed;

@Component
public class FileSystemFeedStore implements FeedStore {

    private final File localStore;

    public FileSystemFeedStore() {

        File workingDirectory = new File("").getAbsoluteFile();

        localStore = new File(workingDirectory, ".feeds");

        localStore.mkdirs();

    }

    @Override
    public String save(Feed feed) {

        try {

            // TODO: save the feed to the file system

            // lookup the key
            String feedKey = FileSystemFeedStoreKey.fromFeedId(feed.getId());

            System.out.println("Save: " + feed.getId() + " using key: " + feedKey);

            File feedDirectory = new File(localStore, feedKey);

            if (!feedDirectory.exists()) {
                feedDirectory.mkdirs();
            }

            File feedProperties = new File(feedDirectory,  "feed.properties");
            if(!feedProperties.exists()) {
                feedProperties.createNewFile();
                Properties props = new Properties();
                props.setProperty("feedId", feed.getId().id());
                props.setProperty("feedUrl", feed.getUrl());
                try(FileWriter fw = new FileWriter(feedProperties)) {
                    props.store(fw, "Feed Properties for " + feed.getUrl());
                }
            }

            File cachedFeed = new File(feedDirectory, "cached-feed.xml");
            if (!cachedFeed.exists()) {

                // Use try-with-resources to ensure the writer is closed.
                try (Writer writer = new FileWriter(cachedFeed)) {
                    SyndFeedOutput output = new SyndFeedOutput();

                    output.output(feed.getSyndFeed(), writer);

                    System.out.println("RSS feed successfully written to: " + cachedFeed.getAbsolutePath());

                } catch (IOException e) {
                    throw new IOException("Error writing RSS feed to file: " + cachedFeed.getAbsolutePath(), e); //Wrap for more context
                } catch (FeedException e) {
                    throw new FeedException("Error serializing RSS feed: " + e.getMessage(), e);
                } finally {
                    //  No need to close the writer here; try-with-resources handles it.
                    // System.clearProperty("com.rometools.rome.xml.prettyPrint"); //cleanup
                }

            }

            return feedKey;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Feed get(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

}
