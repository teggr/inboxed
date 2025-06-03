package dev.feedhub.app.scheduler;

import java.time.Instant;
import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface ScheduledJobRepository extends ListCrudRepository<ScheduledJob, Long>, ListPagingAndSortingRepository<ScheduledJob, Long> {

    List<ScheduledJob> findAllByNextScheduledRunLessThan(Instant scheduledWindow);

}
