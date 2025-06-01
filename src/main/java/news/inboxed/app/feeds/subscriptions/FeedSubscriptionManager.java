package news.inboxed.app.feeds.subscriptions;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import lombok.RequiredArgsConstructor;
import news.inboxed.app.feeds.FeedsUpdatedEvent;

@Component
@RequiredArgsConstructor
public class FeedSubscriptionManager {

  private final FeedSubscriptionListRepository feedSubscriptionListRepository;

  @TransactionalEventListener(fallbackExecution = true)
  public void handleFeedsUpdatedEvent(FeedsUpdatedEvent event) {

    // now we need to look at the list of subscriptions
    // are any interested in the feeds in the event
    // gather the updated items according to the subscription pointer for the feed

  }

}
