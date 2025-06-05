package dev.feedhub.app.subscriptions;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedSubscriptions {

  private final FeedSubscriberRepository feedSubscriberRepository;

  public Page<FeedSubscriber> getSubscribers(Pageable pageable) {
    return feedSubscriberRepository.findAll(pageable);
  }

}
