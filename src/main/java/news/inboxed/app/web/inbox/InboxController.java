package news.inboxed.app.web.inbox;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import news.inboxed.app.web.share.StarredFeedController;
import news.inboxed.app.web.webshares.WebShareController;
import news.inboxed.app.webshares.WebShares;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.*;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class InboxController {

    private final WebShares webShares;

    @GetMapping
    public String getInbox(Model model) {
        
        String createWebShareUrl = fromMethodName(WebShareController.class, "getWebShare", null, (Model) null)
                .build()
                .toUriString();
        String starredFeedUrl = fromMethodName(StarredFeedController.class, "getStarredFeed", (Model) null).build().toUriString();
        
        model.addAttribute("createWebShareUrl", createWebShareUrl);
        model.addAttribute("starredFeedUrl", starredFeedUrl);
        model.addAttribute("webShares", webShares.getWebShares());

        return "inboxView";
        
    }

}
