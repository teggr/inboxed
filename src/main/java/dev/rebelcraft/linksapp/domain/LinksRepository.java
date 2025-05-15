package dev.rebelcraft.linksapp.domain;


import java.net.URL;
import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface LinksRepository extends ListCrudRepository<Link, Long>, ListPagingAndSortingRepository<Link, Long> {

    Link findByUrl(URL url);

    Optional<Link> findFirstByOrderByCreatedDateDesc();
    
}
