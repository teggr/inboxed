package news.inboxed.app.web.webshares;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.*;

import java.net.URL;
import java.util.List;

import lombok.RequiredArgsConstructor;
import news.inboxed.app.web.inbox.InboxController;
import news.inboxed.app.webshares.Link;
import news.inboxed.app.webshares.Links;
import news.inboxed.app.webshares.TagName;
import news.inboxed.app.webshares.TagNamesRepository;

@Controller
@RequestMapping("/share")
@RequiredArgsConstructor
public class WebShareController {

    private final Links links;
    private final TagNamesRepository tagNamesRepository;

    @GetMapping
    public String getCreateLink(@RequestParam(name = "url", required = false) URL url, Model model) {
        String createUrl = fromMethodName(WebShareController.class, "postCreateLink", null, null, null).build()
                .toUriString();
        String cancelUrl = fromMethodName(InboxController.class, "getLinks", (Model) null).build().toUriString();
        model.addAttribute("createUrl", createUrl);
        model.addAttribute("cancelUrl", cancelUrl);
        model.addAttribute("url", url);
        model.addAttribute("tagNames", tagNamesRepository.findAll());
        String domain = extractDomainName(url);
        model.addAttribute("tags", List.of(domain));
        return "webShareView";
    }

    private String extractDomainName(URL url) {
        if (url == null) {
            return "";
        }
        String host = url.getHost();

        // Split the host into parts
        String[] parts = host.split("\\.");
        if (parts.length >= 2) {
            // Return the second-level domain
            return parts[parts.length - 2];
        }
        return host;
    }

    @PostMapping
    public String postCreateLink(@ModelAttribute Link link, BindingResult bindingResult, Model model) {
        Link newLink = links.createNew(link);
        List<TagName> list = newLink.tags().stream().filter(t -> !tagNamesRepository.existsByName(t))
                .map(t -> new TagName(null, t)).toList();
        tagNamesRepository.saveAll(list);
        return "redirect:/";
    }

}
