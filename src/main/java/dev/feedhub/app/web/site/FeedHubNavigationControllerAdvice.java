package dev.feedhub.app.web.site;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import dev.feedhub.app.web.admin.feeds.FeedsAdminController;
import dev.feedhub.app.web.feeds.FeedsController;
import dev.webshares.app.web.webshares.WebSharesController;
import news.inboxed.app.web.inbox.InboxController;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.*;

@ControllerAdvice
public class FeedHubNavigationControllerAdvice {

  @ModelAttribute
  public void populateModel(Model model) {

    String homeUrl = fromMethodName(FeedsController.class, "getFeeds", null, null).build().toUriString();
    model.addAttribute("homeUrl", homeUrl);

    String feedsAdminUrl =  fromMethodName(FeedsAdminController.class, "getFeeds", null, null).build().toUriString();
    model.addAttribute("feedsAdminUrl", feedsAdminUrl);

    String inboxedUrl = fromMethodName(InboxController.class, "getInbox" , null, null).build().toUriString();
    model.addAttribute("inboxedUrl", inboxedUrl);

    String websharesUrl = fromMethodName(WebSharesController.class, "getHome", null, null).build().toUriString();
    model.addAttribute("webSharesUrl", websharesUrl);

  }

}
