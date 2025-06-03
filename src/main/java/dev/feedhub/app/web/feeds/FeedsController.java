package dev.feedhub.app.web.feeds;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.feedhub.app.feeds.Feeds;
import dev.feedhub.app.web.admin.feeds.FeedsAdminController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.*;


@Controller
@RequestMapping("/feedhub/feeds")
@RequiredArgsConstructor
public class FeedsController {

  private final Feeds feeds;

  @GetMapping
  public String getFeeds(Pageable pageable, Model model) {

    String refreshUrl = fromMethodName(FeedsController.class, "getFeeds", pageable, model).build().toUriString();
    model.addAttribute("refreshUrl", refreshUrl);

      model.addAttribute("feeds", feeds.getFeeds(pageable));

      return "feedsView";

  }
  

}
