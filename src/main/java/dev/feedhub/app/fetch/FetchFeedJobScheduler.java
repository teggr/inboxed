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
        null, null, null);

    scheduledJob = scheduledJobRepository.save(scheduledJob);

    Instant immediateRun = Instant.now();

    // schedule an immediate fetch
    fetchFeedJob.run();

    scheduledJob = new ScheduledJob(scheduledJob.id(), scheduledJob.feedId(), scheduledJob.nextScheduledRun().plus(feedConfiguration.schedule()), immediateRun, ScheduledRunResult.SUCCESS, scheduledJob.createdDate());

    scheduledJob = scheduledJobRepository.save(scheduledJob);

  }

  public List<ScheduledJob> getScheduledFetchFeedJobs() {
    return scheduledJobRepository.findAll();
  }

}
