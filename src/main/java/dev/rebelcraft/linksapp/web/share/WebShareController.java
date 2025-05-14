package dev.rebelcraft.linksapp.web.share;

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

import dev.rebelcraft.linksapp.domain.Link;
import dev.rebelcraft.linksapp.domain.Links;
import dev.rebelcraft.linksapp.domain.TagName;
import dev.rebelcraft.linksapp.domain.TagNamesRepository;
import dev.rebelcraft.linksapp.web.links.LinkController;
import lombok.RequiredArgsConstructor;

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
        String cancelUrl = fromMethodName(LinkController.class, "getLinks", (Model)null).build().toUriString();
        model.addAttribute("createUrl", createUrl);
        model.addAttribute("cancelUrl", cancelUrl);
        model.addAttribute("url", url);
        model.addAttribute("tagNames", tagNamesRepository.findAll());
        return "webShareView";
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
