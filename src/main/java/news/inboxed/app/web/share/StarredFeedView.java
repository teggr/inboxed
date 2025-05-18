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
import news.inboxed.app.webshares.WebShare;

@Component("starredFeedView")
public class StarredFeedView extends AbstractAtomFeedView {

  @Override
  protected void buildFeedMetadata(Map<String, Object> model, Feed feed, HttpServletRequest request) {

    // get from model
    String starredFeedUrl = (String) model.get("starredFeedUrl");
    String homeUrl = (String) model.get("homeUrl");
    Instant lastUpdated = (Instant) model.get("lastUpdated");

    // build the feed metadata
    feed.setTitle("Starred Feed");
    com.rometools.rome.feed.atom.Link selfLink = new com.rometools.rome.feed.atom.Link();
    selfLink.setRel("self");
    selfLink.setHref(starredFeedUrl);
    com.rometools.rome.feed.atom.Link alternativeLink = new com.rometools.rome.feed.atom.Link();
    alternativeLink.setRel("alternative");
    alternativeLink.setHref(homeUrl);
    feed.setAlternateLinks(List.of(
        selfLink,
        alternativeLink));
    feed.setId(starredFeedUrl);

    feed.setUpdated(Date.from(lastUpdated));
    SyndPersonImpl author = new SyndPersonImpl();
    author.setName("Inboxed");
    feed.setAuthors(List.of(author));

  }

  @SuppressWarnings("unchecked")
  @Override
  protected List<Entry> buildFeedEntries(Map<String, Object> model, HttpServletRequest request,
      HttpServletResponse response) throws Exception {

    // get from the model
    Page<WebShare> webShares = (Page<WebShare>) model.get("webShares");

    // build the feed entries
    if (webShares != null) {

      return webShares.stream().map(webShare -> {

        Entry entry = new Entry();
        entry.setTitle(webShare.url().toString());

        com.rometools.rome.feed.atom.Link alternativeLink = new com.rometools.rome.feed.atom.Link();
        alternativeLink.setHref(webShare.url().toString());

        entry.setId(webShare.url().toString());

        entry.setUpdated(Date.from(webShare.createdDate()));

        Content content = new Content();
        content.setValue(webShare.notes());

        entry.setSummary(content);

        entry.setCategories(webShare.tags().stream().map(tag -> {
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
