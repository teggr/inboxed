package dev.feedhub.app.web.feeds.components;

import org.springframework.data.domain.Page;

import dev.feedhub.app.feeds.Feed;
import dev.feedhub.app.feeds.FeedConfiguration;
import dev.feedhub.app.feeds.FeedId;
import dev.feedhub.app.scheduler.ScheduledJob;
import j2html.tags.DomContent;
import j2html.tags.specialized.TrTag;

import static j2html.TagCreator.*;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.*;
import static news.inboxed.app.web.utils.TimeUtils.formatInstant;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FeedsList {

  public static DomContent feeds(Page<FeedConfiguration> feedConfigurations, List<ScheduledJob> scheduledFetchFeedJobs,
      List<Feed> feeds) {

    Map<FeedId, ScheduledJob> scheduledFetchFeedJobsByFeedId = scheduledFetchFeedJobs.stream()
        .collect(Collectors.toMap(ScheduledJob::feedId, job -> job));
    Map<FeedId, Feed> feedsByFeedId = feeds.stream().collect(Collectors.toMap(Feed::feedId, feed -> feed));

    return div().withId("feeds").withClasses("mx-2").with(

        h3().withText("All feeds"),

        div().with(

            table().withClasses(table, table_striped).with(

                thead().with(

                    tr().with(

                        th("Feed URL"), 

                        th("Title"), 
                        
                        th("Created Date"), 
                        
                        th("Scheduler"), 
                        
                        th("Next Scheduled Date"),

                        th("Last Scheduled Date"), 
                        
                        th("Last Scheduled Result"), 
                        
                        th("View")

                    )

                ),

                tbody().with(

                    each(feedConfigurations.getContent(),
                        feedConfiguration -> feedRow(
                          feedConfiguration, 
                          feedsByFeedId.get(feedConfiguration.feedId()), 
                          scheduledFetchFeedJobsByFeedId.get(feedConfiguration.feedId())))

                )

            )

        )

    );
  }

  private static TrTag feedRow(FeedConfiguration feedConfiguration, Feed feed, ScheduledJob scheduledFetchFeedJob) {
    return tr().with(

        td().with(text(feedConfiguration.url().toString())), 
        td().with(text(feed != null ? feed.title() : "")),
        td().with(text(formatInstant(feedConfiguration.createdDate()))),
        td().with(text(feedConfiguration.schedule().toString())),
        td().with(text(scheduledFetchFeedJob != null ? formatInstant(scheduledFetchFeedJob.nextScheduledRun()) : "")),
        // div().withClasses(col).with(strong().withText(feed.title()),
        // span(feed.summary()).withClasses()),
        td().with(text(scheduledFetchFeedJob != null && scheduledFetchFeedJob.lastScheduledRun() != null
            ? formatInstant(scheduledFetchFeedJob.lastScheduledRun())
            : "")),
        td().with(text(scheduledFetchFeedJob != null && scheduledFetchFeedJob.lastScheduledRunResult() != null
            ? scheduledFetchFeedJob.lastScheduledRunResult().toString()
            : "")),
        td().with(span().withClasses("bi", "bi-box-arrow-up-right"))

    );
  }

}
