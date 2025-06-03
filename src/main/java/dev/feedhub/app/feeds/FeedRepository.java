package dev.feedhub.app.feeds;

import java.time.Instant;
import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface FeedRepository extends ListCrudRepository<Feed, Long>, ListPagingAndSortingRepository<Feed, Long> {

    List<Feed> findAllByScheduleNextUpdateLessThan(Instant scheduledWindow);

}
