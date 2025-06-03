package news.inboxed.app.web.site;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import dev.feedhub.app.web.feeds.FeedsController;
import dev.webshares.app.web.webshares.WebSharesController;
import news.inboxed.app.web.inbox.InboxController;
import news.inboxed.app.web.subscriptions.SubscriptionsController;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.*;

@ControllerAdvice
public class InboxedNavigationControllerAdvice {

  @ModelAttribute
  public void populateModel(Model model) {

    String homeUrl = fromMethodName(InboxController.class, "getInbox", null, null).build().toUriString();
    model.addAttribute("homeUrl", homeUrl);

    
    String subscriptionsUrl =  fromMethodName(SubscriptionsController.class, "getSubscriptions", null, null).build().toUriString();
    model.addAttribute("subscriptionsUrl", subscriptionsUrl);

    String feedHubUrl = fromMethodName(FeedsController.class, "getFeeds", null, null).build().toUriString();
    model.addAttribute("feedHubUrl", feedHubUrl);

    String websharesUrl = fromMethodName(WebSharesController.class, "getHome", null, null).build().toUriString();
    model.addAttribute("webSharesUrl", websharesUrl);

  }

}
