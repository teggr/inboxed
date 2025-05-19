package news.inboxed.app.web.inbox;

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
import news.inboxed.app.web.templates.SiteTemplate;
import news.inboxed.app.web.webshares.WebShareComponents;
import news.inboxed.app.webshares.WebShare;

import static j2html.TagCreator.*;
import static news.inboxed.app.web.webshares.WebShareComponents.*;

@Component
public class InboxView extends AbstractView {

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
                Page<WebShare> webShares = (Page<WebShare>) model.get("webShares");
                String createWebShareUrl = (String) model.get("createWebShareUrl");
                String starredFeedUrl = (String) model.get("starredFeedUrl");

                // build the ui
                DomContent html = SiteTemplate.add("Inbox", model,
                                each(div(each(h1("Add a new link"), webShareForm(createWebShareUrl))), div(
                                                h1().with(join(text("Inbox"),
                                                                a().withHref(starredFeedUrl).with(span()
                                                                                .withClasses("bi", "bi-rss-fill")))),
                                                each(webShares.toList(), WebShareComponents::webShareCard)))

                );
                // output the html
                setResponseContentType(request, response);
                html.render(IndentedHtml.into(response.getWriter()));

        }

}
