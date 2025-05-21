package news.inboxed.app.inbox;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface InboxItemRepository extends ListCrudRepository<InboxItem, Long>, ListPagingAndSortingRepository<InboxItem, Long> {

    int countByReadFalse();

}
