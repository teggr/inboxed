package news.inboxed.app.feeds;

import java.net.URL;
import java.time.Duration;
import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Embedded.OnEmpty;

@Table("FEEDS")
public record Feed(
    @Id Long id, 
    @Embedded(prefix = "FEED_", onEmpty = OnEmpty.USE_NULL) FeedId feedId, 
    URL url,
    @CreatedDate Instant createdDate,
    Duration scheduler,
    @Embedded(prefix = "SCHEDULE_", onEmpty = OnEmpty.USE_NULL) Schedule schedule,
    @Embedded(prefix = "LAST_SCHEDULED_RUN_", onEmpty = OnEmpty.USE_NULL) ScheduledRun lastScheduledRun
) {

    public Feed withSchedule(Schedule schedule) {
      return new Feed(
        this.id,
        this.feedId,
        this.url,
        this.createdDate,
        this.scheduler,
        schedule,
        this.lastScheduledRun
      );
    }

    public Feed withLastScheduledRun(ScheduledRun lastScheduledRun) {
      return new Feed(
        this.id,
        this.feedId,
        this.url,
        this.createdDate,
        this.scheduler,
        this.schedule,
        lastScheduledRun
      );
    }
  
}
