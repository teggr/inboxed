package dev.webshares.app.web.webshares;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/webshares/home")
public class WebSharesController {

    @GetMapping
    public String getHome(Pageable pageable, Model model) {
        return "webshares/home";
    }

}
