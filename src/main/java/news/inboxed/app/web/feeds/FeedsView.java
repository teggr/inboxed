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
import news.inboxed.app.web.site.SiteLayout;
import news.inboxed.app.web.utils.TimeUtils;

import static j2html.TagCreator.*;
import static j2html.TagCreator.h1;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.*;

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

    // build the ui
    // @formatter:off
    DomContent html = SiteLayout.add("Create a new link", model,

        each(

            div().withClasses(container_fluid).with(

                div().withClasses(row).with(div().withClasses(col).with(

                    h1("Feeds")

                ),

                    div().withClasses(col).with(

                        form().withMethod("post").withAction(addFeedUrl).withClasses(row).with(

                            div().withClasses(col).with(input().withType("url").withName("url")),

                            div().withClasses(col)
                                .with(button().withType("submit").withClasses(btn, btn_primary).withText("Add")))))),

            div().withClasses(container_fluid).with(

                each(feeds.getContent(), feed -> {

                  return div().withClasses(row).with(div().withClasses(col).with(text(feed.url().toString())),
                      div().withClasses(col).with(text(TimeUtils.formatInstant(feed.createdDate())))

                );

                })

            )

        ));
    // @formatter: on

    // output the response
    setResponseContentType(request, response);
    each(document(), html).render(IndentedHtml.into(response.getWriter()));

  }

}
