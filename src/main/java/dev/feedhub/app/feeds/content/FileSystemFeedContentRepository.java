package dev.feedhub.app.feeds.content;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.util.Properties;
import org.springframework.stereotype.Component;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.SyndFeedOutput;

import dev.feedhub.app.feeds.FeedId;

@Component
public class FileSystemFeedContentRepository implements FeedContentRepository {

  private final File localStore;

  public FileSystemFeedContentRepository() {

    File workingDirectory = new File("").getAbsoluteFile();

    localStore = new File(workingDirectory, ".feeds");

    localStore.mkdirs();

  }

  @Override
  public FeedContent save(FeedContent feedContent) {

    try {

      // lookup the key
      String feedKey = FileSystemFeedContentRepositoryKey.fromFeedId(feedContent.feedId());

      System.out.println("Save: " + feedContent.feedId() + " using key: " + feedKey);

      File feedDirectory = new File(localStore, feedKey);

      if (!feedDirectory.exists()) {
        feedDirectory.mkdirs();
      }

      File feedProperties = new File(feedDirectory, "feed.properties");
      if (!feedProperties.exists()) {
        feedProperties.createNewFile();
        Properties props = new Properties();
        props.setProperty("feedId", feedContent.feedId().id());
        props.setProperty("feedUrl", feedContent.url().toString());
        try (FileWriter fw = new FileWriter(feedProperties)) {
          props.store(fw, "Feed Properties for " + feedContent.url().toString());
        }
      }

      File cachedFeed = new File(feedDirectory, "cached-feed.xml");
      if (!cachedFeed.exists()) {

        // Use try-with-resources to ensure the writer is closed.
        try (Writer writer = new FileWriter(cachedFeed)) {
          SyndFeedOutput output = new SyndFeedOutput();

          output.output(feedContent.syndFeed(), writer);

          System.out.println("RSS feed successfully written to: " + cachedFeed.getAbsolutePath());

        } catch (IOException e) {
          throw new IOException("Error writing RSS feed to file: " + cachedFeed.getAbsolutePath(), e); // Wrap for more
                                                                                                       // context
        } catch (FeedException e) {
          throw new FeedException("Error serializing RSS feed: " + e.getMessage(), e);
        } finally {
          // No need to close the writer here; try-with-resources handles it.
          // System.clearProperty("com.rometools.rome.xml.prettyPrint"); //cleanup
        }

      }

      return feedContent;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public FeedContent findByFeedId(FeedId id) {

    String feedKey = FileSystemFeedContentRepositoryKey.fromFeedId(id);

    File rssDirectoy = new File(localStore, feedKey);

    return readFeed(rssDirectoy);

  }

  private static FeedContent readFeed(File rssDirectory) {

    try {

      File cachedFile = new File(rssDirectory, "cached-feed.xml");

      if (!cachedFile.exists()) {
        return null;
      }

      SyndFeedInput input = new SyndFeedInput();
      SyndFeed feed = input.build(cachedFile);

      File propertiesFile = new File(rssDirectory, "feed.properties");

      Properties properties = new Properties();
      try (FileReader fr = new FileReader(propertiesFile)) {
        properties.load(fr);
      }

      FeedId feedId = new FeedId(properties.getProperty("feedId"));
      String feedUrl = properties.getProperty("feedUrl");

      return new FeedContent(feedId, URI.create(feedUrl).toURL(), feed);

    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (FeedException e) {
      throw new RuntimeException(e);
    }

  }

}
