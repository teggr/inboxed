package dev.rebelcraft.linksapp.web.home;

import java.lang.ProcessBuilder.Redirect;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String getCreateLink() {
        return "CreateLinkView";
    }

    @PostMapping
    public String postLink( @RequestParam("url") String url, RedirectAttributes redirectAttributes ) {
        Link newLink = links.createNew(url);
        redirectAttributes.addAttribute("url", newLink.url());
        return "redirect:/triage-link";
    }

    @GetMapping("/triage-link")
    public String getTriageLink( @RequestParam("url") String url, Model model ) {
        model.addAttribute( "link", links.getLink(url) );
        return "TriageLinkView";
    }

    @PostMapping("/triage-link")
    public String postTriageLink( @ModelAttribute Link link, BindingResult bindingResult ) {
        Link updatedLink = links.update(link);
        return "redirect:/links";
    }

}
