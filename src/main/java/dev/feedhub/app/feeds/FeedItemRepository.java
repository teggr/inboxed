package dev.feedhub.app.feeds;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface FeedItemRepository extends ListCrudRepository<FeedItem, Long>, ListPagingAndSortingRepository<FeedItem, Long> {

}
