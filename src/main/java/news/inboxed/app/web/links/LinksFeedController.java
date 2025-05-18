package news.inboxed.app.web.links;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import news.inboxed.app.domain.Links;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.*;

@Controller
@RequestMapping("/feed/links")
@RequiredArgsConstructor
public class LinksFeedController {

  private final Links links;

  @GetMapping(produces = "application/atom+xml")
  public String getLinksFeed(Model model) {
    String feedUrl = fromMethodName(LinksFeedController.class, "getLinksFeed", (Model) null).build()
        .toUriString();
    String homeUrl = fromMethodName(LinksController.class, "getLinks", (Model) null).build()
        .toUriString();
    model.addAttribute("feedUrl", feedUrl);
    model.addAttribute("homeUrl", homeUrl);
    model.addAttribute("links", links.getLinks());
    model.addAttribute("lastUpdated", links.getLastUpdated());
    return "linksFeedView";
  }

}
