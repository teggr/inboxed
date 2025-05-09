package dev.rebelcraft.linksapp.web.home;

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

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import dev.rebelcraft.linksapp.domain.Link;
import dev.rebelcraft.linksapp.domain.Links;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class CreateLinkHomeController {

    private final Links links;

    @GetMapping
    public String getCreateLink(Model model) {
        String createUrl = fromMethodName(CreateLinkHomeController.class, "postLink", null, null)
                .build()
                .toUriString();
        model.addAttribute("createUrl", createUrl);
        return "createLinkView";
    }

    @PostMapping
    public String postLink(@RequestParam(name = "url", required = false) URL url,
            RedirectAttributes redirectAttributes) {
        Link newLink = links.createNew(url);
        redirectAttributes.addAttribute("url", newLink.url());
        return "redirect:/triage-link";
    }

    @GetMapping("/triage-link")
    public String getTriageLink(@RequestParam("url") URL url, Model model) {
        model.addAttribute("link", links.getLink(url));
        String updateUrl = fromMethodName(CreateLinkHomeController.class, "postTriageLink", null, null)
                .build()
                .toUriString();
        model.addAttribute("updateUrl", updateUrl);
        return "triageLinkView";
    }

    @PostMapping("/triage-link")
    public String postTriageLink(@ModelAttribute Link link, BindingResult bindingResult) {
        Link updatedLink = links.update(link);
        return "redirect:/links";
    }

}
