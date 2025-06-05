package dev.feedhub.app.subscriptions;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.feedhub.app.feeds.FeedId;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedSubscriptions {

  private final FeedSubscriberRepository feedSubscriberRepository;

  public Page<FeedSubscriber> getSubscribers(Pageable pageable) {
    return feedSubscriberRepository.findAll(pageable);
  }

  public void createSubscriber() {

    feedSubscriberRepository.save(new FeedSubscriber(null,FeedSubscriberIdGenerator.generateSubscriberId(), null, null));

  }

  public void subscribeToFeed( String subscriberId, FeedId feedId) {

    FeedSubscriber feedSubscriber = feedSubscriberRepository.findBySubscriberId(subscriberId);

    feedSubscriber.feedSubscriptions().add(new FeedSubscription(feedId));

    feedSubscriberRepository.save(feedSubscriber);

  }

  public Page<FeedSubscription> getFeedSubscriptions(String subscriberId, Pageable pageable) {
    
    return feedSubscriberRepository.findFeedSubscriptionsBySubscriberId(subscriberId, pageable);

  }

}
