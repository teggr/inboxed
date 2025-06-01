package news.inboxed.app.subscriptions;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * SubscriptionManager
 */
@Service
@RequiredArgsConstructor
public class Subscriptions {

    private final SubscriptionRepository subscriptionRepository;

    public void subscribe(String subscriptionId) {

      // get a subscription reference
     // String subscriberId = UUID.randomUUID().toString();

     // Subscriber subscriber = subscriberRepository.findAll().getFirst();

    //  subscriber = subscriber.withSubscription( );

     // subscriber = subscriberRepository.save(subscriber);

     Subscription subscription = subscriptionRepository.save( new Subscription( null, SubscriptionType.FEED, subscriptionId, null ));

	
      // do any providers support this subscription
      // if so, start subscription
      // get a reference to store with the subscription details
      // then let the good times rolls

    }

    public Page<Subscription> getSubscriptions(Pageable pageable) {
      return subscriptionRepository.findAll(pageable);
    }

}
