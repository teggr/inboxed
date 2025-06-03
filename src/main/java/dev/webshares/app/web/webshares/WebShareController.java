package dev.webshares.app.web.webshares;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.webshares.app.webshares.WebShare;
import dev.webshares.app.webshares.WebShares;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.*;

import java.net.URL;
import java.util.List;

import lombok.RequiredArgsConstructor;
import news.inboxed.app.tags.TagName;
import news.inboxed.app.tags.TagNamesRepository;
import news.inboxed.app.web.inbox.InboxController;

@Controller
@RequestMapping("/webshares/share")
@RequiredArgsConstructor
public class WebShareController {

    private final WebShares webShares;
    private final TagNamesRepository tagNamesRepository;

    @GetMapping
    public String getWebShare(@RequestParam(name = "url", required = false) URL url, Model model) {
        String postCreateWebShareUrl = fromMethodName(WebShareController.class, "postCreateWebShare", null, null, null).build()
                .toUriString();
        String cancelUrl = fromMethodName(InboxController.class, "getInbox", null, (Model) null).build().toUriString();
        model.addAttribute("postCreateWebShareUrl", postCreateWebShareUrl);
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
    public String postCreateWebShare(@ModelAttribute WebShare webShare, BindingResult bindingResult, Model model) {
        WebShare newWebShare = webShares.addWebShare(webShare);
        List<TagName> list = newWebShare.tags().stream().filter(t -> !tagNamesRepository.existsByName(t))
                .map(t -> new TagName(null, t)).toList();
        tagNamesRepository.saveAll(list);
        return "redirect:/webshares/";
    }

}
