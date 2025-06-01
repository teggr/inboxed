package news.inboxed.app.web.subscriptions;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import news.inboxed.app.subscriptions.Subscriptions;
import news.inboxed.app.web.feeds.FeedsController;
import news.inboxed.app.web.inbox.InboxController;

import org.springframework.web.bind.annotation.GetMapping;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.*;

@Controller
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionsController {

  private final Subscriptions subscriptions;

  @GetMapping
  public String getSubscriptions(Pageable pageable, Model model) {
    
    String refreshUrl =  fromMethodName(SubscriptionsController.class, "getSubscriptions", pageable, model).build().toUriString();
    model.addAttribute("refreshUrl", refreshUrl);

    String addSubscriptionUrl = fromMethodName(AddSubscriptionController.class, "postAddSubscription", null, null).build().toUriString();
        model.addAttribute("addSubscriptionUrl", addSubscriptionUrl);

    model.addAttribute("subscriptions", subscriptions.getSubscriptions(pageable));
    
    return "subscriptionsView";
  }
  
}
