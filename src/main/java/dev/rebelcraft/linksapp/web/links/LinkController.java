package dev.rebelcraft.linksapp.web.links;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.rebelcraft.linksapp.domain.Links;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/links")
@RequiredArgsConstructor
public class LinkController {

    private final Links links;

    @GetMapping
    public String getLinks(Model model) {
        model.addAttribute("links", links.getLinks() );
        return "linksView";
    }

}
