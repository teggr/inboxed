package dev.rebelcraft.linksapp.domain;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface TagNamesRepository extends ListCrudRepository<TagName, String>, ListPagingAndSortingRepository<TagName, String> {
    
}
