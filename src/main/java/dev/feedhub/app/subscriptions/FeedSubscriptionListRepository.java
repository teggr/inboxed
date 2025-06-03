package dev.feedhub.app.subscriptions;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface FeedSubscriptionListRepository extends ListCrudRepository<FeedSubscriptionList, Long>, ListPagingAndSortingRepository<FeedSubscriptionList, Long> {

}
