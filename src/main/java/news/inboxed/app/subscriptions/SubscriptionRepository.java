package news.inboxed.app.subscriptions;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

/**
 * SubscriptionRepository
 */
public interface SubscriptionRepository
    extends ListCrudRepository<Subscription, Long>, ListPagingAndSortingRepository<Subscription, Long> {

}
