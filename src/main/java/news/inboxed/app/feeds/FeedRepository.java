package news.inboxed.app.feeds;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface FeedRepository extends ListCrudRepository<Feed, Long>, ListPagingAndSortingRepository<Feed, Long> {

}
