package news.inboxed.app.web.feeds.components;

import org.springframework.data.domain.Page;

import j2html.tags.DomContent;
import j2html.tags.specialized.DivTag;
import j2html.tags.specialized.TrTag;
import news.inboxed.app.feeds.Feed;

import static j2html.TagCreator.*;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.*;
import static news.inboxed.app.web.utils.TimeUtils.formatInstant;

public class FeedsList {

  public static DomContent feeds(Page<Feed> feeds) {

    return div().withId("feeds").withClasses("mx-2").with(

        h3().withText("All feeds"), 
        
        div().with(

          table().withClasses(table, table_striped).with(

            thead().with(

              tr().with(

                th("Feed URL"),
                th("Created Date"),
                th("Next Scheduled Date"),
                th("Last Scheduled Date"),
                th("Last Scheduled Result"),
                th("View")

              )   

            ) ,

            tbody().with(

              each(feeds.getContent(), feed -> feedRow(feed))

            )

          )
        
        )
  
      );
  }

  private static TrTag feedRow(Feed feed) {
    return tr().with(

        td().with(text(feed.url().toString())),
        td().with(text(formatInstant(feed.createdDate()))),
        td().with(text( feed.schedule() != null ? formatInstant(feed.schedule().nextUpdate()) : "")),
        // div().withClasses(col).with(strong().withText(feed.title()),
        // span(feed.summary()).withClasses()),
        td().with(text( feed.lastScheduledRun() != null ? formatInstant(feed.lastScheduledRun().scheduledUpdate()) : "" )),
        td().with(text( feed.lastScheduledRun() != null ? feed.lastScheduledRun().result().toString() : "" )),
        td().with(span().withClasses("bi", "bi-box-arrow-up-right"))
      
       );
  }

}
