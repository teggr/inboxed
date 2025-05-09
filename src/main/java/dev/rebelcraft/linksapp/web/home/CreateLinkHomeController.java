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
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import dev.rebelcraft.linksapp.domain.Link;
import dev.rebelcraft.linksapp.domain.Links;
import dev.rebelcraft.linksapp.domain.TagName;
import dev.rebelcraft.linksapp.domain.TagNamesRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class CreateLinkHomeController {

    private final Links links;
    private final TagNamesRepository tagNamesRepository;

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
        model.addAttribute("tagNames", tagNamesRepository.findAll());
        return "newLinkView";
    }

    @PostMapping(path = "/triage-link", params = "addTag")
    public String postTriageLinkAddTag(
            @RequestParam(name = "existingTag", required = false) String existingTag,
            @RequestParam(name = "newTag", required = false) String newTag,
            @ModelAttribute Link link, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        Set<String> allTags = new HashSet<>();
        if (link.tags() != null) allTags.addAll(link.tags());
        if (existingTag != null && !existingTag.isEmpty()) {
            allTags.add(existingTag);
            if(!tagNamesRepository.existsByName(existingTag)) {
                tagNamesRepository.save(new TagName(null, existingTag));
            }
        }
        if (newTag != null && !newTag.isEmpty()) {
            allTags.add(newTag);
            if(!tagNamesRepository.existsByName(newTag)) {
                tagNamesRepository.save(new TagName(null, newTag));
            }
        }

        link = new Link(link.id(), link.url(), link.notes(), allTags);

        Link updatedLink = links.update(link);

        redirectAttributes.addAttribute("url", updatedLink.url());

        return "redirect:/triage-link";
    }

    @PostMapping("/triage-link")
    public String postTriageLink(@ModelAttribute Link link, BindingResult bindingResult) {
        if (link.tags() == null) {
            link = new Link(link.id(), link.url(), link.notes(), Set.of());
        }
        Link updatedLink = links.update(link);
        return "redirect:/links";
    }

}
