package news.inboxed.app.subscriptions.services;

import java.net.URL;

import news.inboxed.app.subscriptions.Subscription;

public interface SubscriptionService {

  Subscription subscribe( URL subscriptionUrl );

}
