package news.inboxed.app.tags;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface TagNamesRepository extends ListCrudRepository<TagName, Long>, ListPagingAndSortingRepository<TagName, Long> {

    boolean existsByName(String name);
    
}
