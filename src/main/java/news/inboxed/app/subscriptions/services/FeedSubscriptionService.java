package news.inboxed.app.subscriptions.services;

import java.net.URL;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import news.inboxed.app.subscriptions.Subscription;

@Component
@RequiredArgsConstructor
public class FeedSubscriptionService implements SubscriptionService {

  @Override
  public Subscription subscribe(URL subscriptionUrl) {

    // need to go to the feeds subscription service and start a subscription
    // use the return feed
     
      return new Subscription(null, "FEED", subscriptionUrl, null);

  }

}
