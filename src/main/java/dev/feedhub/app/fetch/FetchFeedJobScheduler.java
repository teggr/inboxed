package dev.feedhub.app.fetch;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import dev.feedhub.app.feeds.FeedConfiguration;
import dev.feedhub.app.feeds.FeedConfigurationAddedEvent;
import dev.feedhub.app.scheduler.ScheduledJob;
import dev.feedhub.app.scheduler.ScheduledJobRepository;
import dev.feedhub.app.scheduler.ScheduledRunResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FetchFeedJobScheduler {

  private final ScheduledJobRepository scheduledJobRepository;
  private final FetchFeedJob fetchFeedJob;

  @TransactionalEventListener(fallbackExecution = true)
  public void handleFeedConfigurationAddedEvent(FeedConfigurationAddedEvent feedConfigurationAddedEvent) {

    FeedConfiguration feedConfiguration = (FeedConfiguration) feedConfigurationAddedEvent.getSource();

    // TODO: could create the job here (quartz/jobrunr), rather than just use spring

    // given the created date, create next schedule for immediately]
    ScheduledJob scheduledJob = new ScheduledJob(null, feedConfiguration.feedId(), feedConfiguration.createdDate(),
        null, null, null, feedConfiguration.schedule());

    scheduledJob = scheduledJobRepository.save(scheduledJob);

    // schedule an immediate fetch
    runScheduledJob(scheduledJob);

  }

  private void runScheduledJob(ScheduledJob scheduledJob) {

    Instant jobStartTime = Instant.now();

    fetchFeedJob.run();

    scheduledJob = new ScheduledJob(scheduledJob.id(), scheduledJob.feedId(), scheduledJob.nextScheduledRun().plus(scheduledJob.schedule()), jobStartTime, ScheduledRunResult.SUCCESS, scheduledJob.createdDate(), scheduledJob.schedule());

    scheduledJob = scheduledJobRepository.save(scheduledJob);

  }

  public List<ScheduledJob> getScheduledFetchFeedJobs() {
    return scheduledJobRepository.findAll();
  }

  public void run() {

    List<ScheduledJob> jobsToBeRun = scheduledJobRepository.findAllByNextScheduledRunLessThan(Instant.now());

    jobsToBeRun.forEach(this::runScheduledJob);
    
  }

}
