package dev.webshares.app.webshares;


import java.net.URL;
import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface WebShareRepository extends ListCrudRepository<WebShare, Long>, ListPagingAndSortingRepository<WebShare, Long> {

    WebShare findByUrl(URL url);

    Optional<WebShare> findFirstByOrderByCreatedDateDesc();
    
}
