package dev.rebelcraft.linksapp.web.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String getHome() {
        return "newLinkView";
    }

    @PostMapping
    public String postUrl( @RequestParam("url") String url ) {
        // TODO: create the url entry
        return "redirect:/triage";
    }

}
