package dev.feedhub.app.web.feeds.components;

import org.springframework.data.domain.Page;

import dev.feedhub.app.feeds.Feed;
import dev.feedhub.app.feeds.FeedConfiguration;
import dev.feedhub.app.feeds.FeedId;
import dev.feedhub.app.feeds.FeedItem;
import dev.feedhub.app.scheduler.ScheduledJob;
import dev.feedhub.app.web.feeds.FeedUrlBuilder;
import j2html.tags.DomContent;
import j2html.tags.specialized.TrTag;

import static j2html.TagCreator.*;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.*;
import static news.inboxed.app.web.utils.TimeUtils.formatInstant;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FeedItemsList {

  public static DomContent feeds(Page<FeedItem> feedsItems) {

    return div().withId("feeds").withClasses("mx-2").with(

        h3().withText("All items"),

        div().with(

            table().withClasses(table, table_striped).with(

                thead().with(

                    tr().with(

                        th("Title")

                    )

                ),

                tbody().with(

                    each(feedsItems.getContent(),
                        feedItem -> feedRow(
                          feedItem))

                )

            )

        )

    );
  }

  private static TrTag feedRow(FeedItem feedItem) {
    return tr().with(

        td().with(text(feedItem.title()))

    );
  }

}
