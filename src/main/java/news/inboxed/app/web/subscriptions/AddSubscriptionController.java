package news.inboxed.app.web.subscriptions;

import java.net.URI;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import news.inboxed.app.subscriptions.Subscription;
import news.inboxed.app.subscriptions.Subscriptions;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/add-subscription")
@RequiredArgsConstructor
public class AddSubscriptionController {

  private final Subscriptions subscriptions;

  @PostMapping
  public String postAddSubscription(@RequestParam(value = "subscription", required = false) String rawSubscriptionText,
      RedirectAttributes redirectAttributes) {

    try {

      Subscription subscription = subscriptions.subscribe(URI.create(rawSubscriptionText).toURL());
      redirectAttributes.addAttribute("subscriptionUrl", subscription.subscriptionUrl());
      return "redirect:/subscriptions";

    } catch (Exception e) {

      redirectAttributes.addAttribute("q", rawSubscriptionText);
      redirectAttributes.addAttribute("type", "subscriptions");
      return "redirect:/search";
      
    }

  }

}
