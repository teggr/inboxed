package news.inboxed.app.subscriptions;

import java.net.URL;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import news.inboxed.app.subscriptions.services.SubscriptionService;

/**
 * SubscriptionManager
 */
@Service
@RequiredArgsConstructor
public class Subscriptions {

  private final SubscriptionRepository subscriptionRepository;
  private final List<SubscriptionService> subscriptionServices;

  public Subscription subscribe(URL subscriptionUrl) {

    Subscription subscription = null;

    for (SubscriptionService service : subscriptionServices) {

      subscription = service.subscribe(subscriptionUrl);

      if (subscription != null) {
        break;
      }

    }

    if(subscription == null) {
      return null;
    }

    subscription = subscriptionRepository.save(subscription);

    return subscription;

  }

  public Page<Subscription> getSubscriptions(Pageable pageable) {
    return subscriptionRepository.findAll(pageable);
  }

}
