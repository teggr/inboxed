package news.inboxed.app.web.feeds;

import java.net.URL;
import java.util.List;
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
import news.inboxed.app.tags.TagName;
import news.inboxed.app.web.site.SiteLayout;

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
    Page<Feed> feeds = (Page<Feed>) model.get("feeeds");

    // build the ui
    // @formatter:off
    DomContent html = SiteLayout.add("Create a new link", model,

        each(div().withClasses(container_fluid).with(

            div().withClasses(row).with(

                h1("Feeds"),

                text("something")

            ))

        ));
    // @formatter: on

    // output the response
    setResponseContentType(request, response);
    each(document(), html).render(IndentedHtml.into(response.getWriter()));

  }

}
