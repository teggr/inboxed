package news.inboxed.app.web.subscriptions.components;


import org.springframework.data.domain.Page;

import j2html.tags.DomContent;
import j2html.tags.specialized.TrTag;
import news.inboxed.app.subscriptions.Subscription;

import static j2html.TagCreator.*;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.*;
import static news.inboxed.app.web.utils.TimeUtils.formatInstant;

public class SubscriptionsList {

  public static DomContent subscriptionsList(Page<Subscription> subscriptions) {

    return div().withId("subscriptions").withClasses("mx-2").with(

        h3().withText("All subscriptions"), 
        
        div().with(

          table().withClasses(table, table_striped).with(

            thead().with(

              tr().with(

                th("Type"),
                th("Subscription Url"),
                th("Created Date")

              )   

            ) ,

            tbody().with(

              each(subscriptions.getContent(), subscription -> subscriptionRow(subscription))

            )

          )
        
        )
  
      );
  }

  private static TrTag subscriptionRow(Subscription subscription) {
    return tr().with(

        td().with(text(subscription.type().toString())),
        td().with(text(subscription.subscriptionUrl().toString())),
        td().with(text(formatInstant(subscription.createdDate())))
      
       );
  }

}
