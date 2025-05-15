package dev.rebelcraft.linksapp.web.links;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.feed.AbstractAtomFeedView;

import com.rometools.rome.feed.atom.Entry;

import dev.rebelcraft.linksapp.domain.Link;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("linksFeedView")
public class LinksFeedView extends AbstractAtomFeedView {

  // TODO: no view found
  // TODO: build list of links

  @Override
  protected List<Entry> buildFeedEntries(Map<String, Object> model, HttpServletRequest request,
      HttpServletResponse response) throws Exception {

    Page<Link> links = (Page<Link>) model.get("links");

    if (links != null) {
      return links.stream().map(link -> {
        Entry entry = new Entry();
        entry.setTitle(link.url().toString());
       
        return entry;
      }).toList();
    }

    return List.of();
    
  }

}
