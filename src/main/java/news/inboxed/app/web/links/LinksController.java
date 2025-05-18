package news.inboxed.app.web.links;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import news.inboxed.app.domain.Links;
import news.inboxed.app.web.share.WebShareController;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.*;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class LinksController {

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

        return "linksView";
        
    }

}
