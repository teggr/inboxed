package dev.feedhub.app.web.feeds;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import dev.feedhub.app.feeds.Feed;
import dev.feedhub.app.feeds.FeedConfiguration;
import dev.feedhub.app.scheduler.ScheduledJob;
import dev.feedhub.app.web.admin.feeds.components.FeedsAdminActionBar;
import dev.feedhub.app.web.admin.feeds.components.FeedsAdminList;
import dev.feedhub.app.web.feeds.components.FeedsActionBar;
import dev.feedhub.app.web.feeds.components.FeedsList;
import j2html.rendering.IndentedHtml;
import j2html.tags.DomContent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import news.inboxed.app.web.site.SiteLayout;

import static j2html.TagCreator.*;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.*;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.col;
import static news.inboxed.app.web.site.InboxedNavigation.inboxedNavigation;

@Component
public class FeedsView extends AbstractView {

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
    Page<Feed> feeds = (Page<Feed>) model.get("feeds");
    
    String refreshUrl = (String) model.get("refreshUrl");

    // build the ui
    DomContent html = SiteLayout.add("Inboxed | Feeds", model,

      each(

          inboxedNavigation(model),

          div().withClasses(container_fluid).with(

              FeedsActionBar.feedsActionBar(refreshUrl),

              hr(),

              div().withClasses(row).with(

                div().withClasses(col).with(
                  FeedsList.feeds(feeds)
                )

              )

          )

      ));

    // output the response
    setResponseContentType(request, response);
    each(document(), html).render(IndentedHtml.into(response.getWriter()));

  }

}
