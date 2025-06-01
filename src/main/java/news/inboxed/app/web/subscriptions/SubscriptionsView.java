package news.inboxed.app.web.subscriptions;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import j2html.rendering.IndentedHtml;
import j2html.tags.DomContent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import news.inboxed.app.subscriptions.Subscription;
import news.inboxed.app.web.site.SiteLayout;
import news.inboxed.app.web.subscriptions.components.SubscriptionsActionBar;
import news.inboxed.app.web.subscriptions.components.SubscriptionsList;

import static j2html.TagCreator.*;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.*;
import static news.inboxed.app.web.site.InboxedNavigation.inboxedNavigation;

@Component
public class SubscriptionsView extends AbstractView {

  @Override
  @Nullable
  public String getContentType() {
    return MediaType.TEXT_HTML_VALUE;
  }

  @SuppressWarnings({ "unchecked", "null" })
  @Override
  protected void renderMergedOutputModel(@Nullable Map<String, Object> model, @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response) throws Exception {

    // get from the model
    Page<Subscription> subscriptions = (Page<Subscription>) model.get("subscriptions");
    String refreshUrl = (String) model.get("subscriptionsUrl");

    String addSubscriptionUrl = (String) model.get("addSubscriptionUrl");

    // build the ui
    DomContent html = SiteLayout.add("Inboxed | Subscriptions", model, 
    
        each(

          inboxedNavigation(model),

          div().withClasses(container_fluid).with(

              SubscriptionsActionBar.subscriptionsActionBar(refreshUrl, addSubscriptionUrl),

              hr(),

              div().withClasses(row).with(

                div().withClasses(col).with(
                  SubscriptionsList.subscriptionsList(subscriptions)
                )

              )

          )

      )
    
    );

    // output the html
    setResponseContentType(request, response);
    html.render(IndentedHtml.into(response.getWriter()));

  }

}
