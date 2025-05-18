package news.inboxed.app.web.share;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import news.inboxed.app.web.inbox.InboxController;
import news.inboxed.app.webshares.WebShares;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.*;

@Controller
@RequestMapping("/feed/starred")
@RequiredArgsConstructor
public class StarredFeedController {

  private final WebShares webShares;

  @GetMapping(produces = "application/atom+xml")
  public String getStarredFeed(Model model) {
    String starredFeedUrl = fromMethodName(StarredFeedController.class, "getStarredFeed", (Model) null).build()
        .toUriString();
    String homeUrl = fromMethodName(InboxController.class, "getInbox", (Model) null).build()
        .toUriString();
    model.addAttribute("starredFeedUrl", starredFeedUrl);
    model.addAttribute("homeUrl", homeUrl);
    model.addAttribute("webShares", webShares.getWebShares());
    model.addAttribute("lastUpdated", webShares.getLastUpdated());
    return "starredFeedView";
  }

}
