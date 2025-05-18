package news.inboxed.app.web.share;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.feed.AbstractAtomFeedView;

import com.rometools.rome.feed.atom.Content;
import com.rometools.rome.feed.atom.Entry;
import com.rometools.rome.feed.atom.Feed;
import com.rometools.rome.feed.synd.SyndPersonImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import news.inboxed.app.webshares.Link;

@Component("linksFeedView")
public class LinksFeedView extends AbstractAtomFeedView {

  @Override
  protected void buildFeedMetadata(Map<String, Object> model, Feed feed, HttpServletRequest request) {

    // get from model
    String feedUrl = (String) model.get("feedUrl");
    String homeUrl = (String) model.get("homeUrl");
    Instant lastUpdated = (Instant) model.get("lastUpdated");

    // build the feed metadata
    feed.setTitle("Links Feed");
    com.rometools.rome.feed.atom.Link selfLink = new com.rometools.rome.feed.atom.Link();
    selfLink.setRel("self");
    selfLink.setHref(feedUrl);
    com.rometools.rome.feed.atom.Link alternativeLink = new com.rometools.rome.feed.atom.Link();
    alternativeLink.setRel("alternative");
    alternativeLink.setHref(homeUrl);
    feed.setAlternateLinks(List.of(
        selfLink,
        alternativeLink));
    feed.setId(feedUrl);

    feed.setUpdated(Date.from(lastUpdated));
    SyndPersonImpl author = new SyndPersonImpl();
    author.setName("RebelCraft");
    feed.setAuthors(List.of(author));

  }

  @SuppressWarnings("unchecked")
  @Override
  protected List<Entry> buildFeedEntries(Map<String, Object> model, HttpServletRequest request,
      HttpServletResponse response) throws Exception {

    // get from the model
    Page<Link> links = (Page<Link>) model.get("links");

    // build the feed entries
    if (links != null) {

      return links.stream().map(link -> {

        Entry entry = new Entry();
        entry.setTitle(link.url().toString());

        com.rometools.rome.feed.atom.Link alternativeLink = new com.rometools.rome.feed.atom.Link();
        alternativeLink.setHref(link.url().toString());

        entry.setId(link.url().toString());

        entry.setUpdated(Date.from(link.createdDate()));

        Content content = new Content();
        content.setValue(link.notes());

        entry.setSummary(content);

        entry.setCategories(link.tags().stream().map(tag -> {
          com.rometools.rome.feed.atom.Category category = new com.rometools.rome.feed.atom.Category();
          category.setLabel(tag);
          return category;
        }).toList());

        return entry;

      }).toList();

    }

    return List.of();

  }

}
