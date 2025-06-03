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

  public static DomContent feeds(Page<Feed> feeds) {

    return div().withId("feeds").withClasses("mx-2").with(

        h3().withText("All feeds"),

        div().with(

            table().withClasses(table, table_striped).with(

                thead().with(

                    tr().with(

                        th("Feed URL"), 

                        th("Title")

                    )

                ),

                tbody().with(

                    each(feeds.getContent(),
                        feed -> feedRow(
                          feed))

                )

            )

        )

    );
  }

  private static TrTag feedRow(Feed feed) {
    return tr().with(

        td().with(text(feed.url().toString())), 
        td().with(text(feed != null ? feed.title() : ""))

    );
  }

}
