package dev.feedhub.app.subscriptions;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface FeedSubscriberRepository extends ListCrudRepository<FeedSubscriber, Long>, ListPagingAndSortingRepository<FeedSubscriber, Long> {

    // Additional query methods can be defined here if needed
    // For example, to find subscribers by subscriberId:
    List<FeedSubscriber> findBySubscriberId(String subscriberId);

}
