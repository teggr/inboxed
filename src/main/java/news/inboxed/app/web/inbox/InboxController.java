package news.inboxed.app.web.inbox;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import news.inboxed.app.web.share.LinksFeedController;
import news.inboxed.app.web.webshares.WebShareController;
import news.inboxed.app.webshares.Links;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.*;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class InboxController {

    private final Links links;

    @GetMapping
    public String getLinks(Model model) {
        
        String createLinkUrl = fromMethodName(WebShareController.class, "getCreateLink", null, (Model) null)
                .build()
                .toUriString();
        String feedUrl = fromMethodName(LinksFeedController.class, "getLinksFeed", (Model) null).build().toUriString();
        
        model.addAttribute("createLinkUrl", createLinkUrl);
        model.addAttribute("feedUrl", feedUrl);
        model.addAttribute("links", links.getLinks());

        return "inboxView";
        
    }

}
