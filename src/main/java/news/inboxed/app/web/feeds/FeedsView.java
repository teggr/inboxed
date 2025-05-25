package news.inboxed.app.web.feeds;

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
import news.inboxed.app.feeds.Feed;
import news.inboxed.app.web.feeds.components.FeedsActionBar;
import news.inboxed.app.web.feeds.components.FeedsList;
import news.inboxed.app.web.site.SiteLayout;
import news.inboxed.app.web.utils.TimeUtils;

import static j2html.TagCreator.*;
import static j2html.TagCreator.h1;
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
    String addFeedUrl = (String) model.get("addFeedUrl");
    String homeUrl = (String) model.get("homeUrl");
    String searchUrl = (String) model.get("searchUrl");
    String username = (String) model.get("username");
    String logoutUrl = (String) model.get("logoutUrl");
    String adminFeedsUrl = (String) model.get("adminFeedsUrl");
    String refreshUrl = (String) model.get("refreshUrl");
    String updateFeedsUrl = (String) model.get("updateFeedsUrl");

    // build the ui
    DomContent html = SiteLayout.add("Inboxed | Admin Feeds", model,

      each(

          inboxedNavigation(homeUrl, searchUrl, adminFeedsUrl, username, logoutUrl),

          div().withClasses(container_fluid).with(

              FeedsActionBar.feedsActionBar(refreshUrl, addFeedUrl, updateFeedsUrl),

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
