package dev.feedhub.app.web.feeds.components;

import org.springframework.data.domain.Page;

import dev.feedhub.app.feeds.Feed;
import dev.feedhub.app.web.feeds.FeedUrlBuilder;
import j2html.tags.DomContent;
import j2html.tags.specialized.TrTag;

import static j2html.TagCreator.*;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.*;

public class FeedsList {

  public static DomContent feeds(Page<Feed> feeds, FeedUrlBuilder feedUrlBuilder) {

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
                          feed, feedUrlBuilder))

                )

            )

        )

    );
  }

  private static TrTag feedRow(Feed feed, FeedUrlBuilder feedUrlBuilder) {
    return tr().with(

        td().with(text(feed.url().toString())), 
        td().with(a().withHref( feedUrlBuilder.build(feed.feedId()) ).withText(feed != null ? feed.title() : ""))

    );
  }

}
