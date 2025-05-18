package news.inboxed.app.web.inbox;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
import news.inboxed.app.webshares.WebShare;

import static dev.rebelcraft.j2html.bootstrap.Bootstrap.*;
import static j2html.TagCreator.*;

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
                DomContent html = SiteTemplate.add("Inbox", model, each(h1("Add a new link"),
                                div(form().withMethod("GET").withAction(createWebShareUrl).with(
                                                input().withType("url").withName("url").withPlaceholder("Enter a URL"),
                                                input().withType("submit"))),
                                div(h1().with(join(text("Inbox"),
                                                a().withHref(starredFeedUrl).with(span().withClasses("bi", "bi-rss-fill")))),
                                                each(webShares.toList(), this::webShareCard)))

                );
                // output the html
                setResponseContentType(request, response);
                html.render(IndentedHtml.into(response.getWriter()));

        }

        private DomContent webShareCard(WebShare webShare) {

                String title = webShare.url().toString();
                if (webShare.webShareData() != null) {
                        title = webShare.webShareData().title();
                }
                if (webShare.webShareMetaData() != null) {
                        title = webShare.webShareMetaData().title();
                }

                boolean wasFetched = webShare.hasWebShareData();
                boolean wasMetaDataGenerated = webShare.hasMetaData();

                return div().withClasses(card, mb_3).with(div().withClasses(row, g_0).with(
                                // div().withClasses(col_md_4).with(
                                // img().withSrc("...").withClasses(img_fluid, rounded_start).withAlt("...")
                                // ),
                                div().withClasses(col_md_8).with(div().withClasses(card_body).with(
                                                h5(title).withClasses(card_title),
                                                p(a(join(webShare.url().toString(),
                                                                span().withClasses("bi", "bi-arrow-up-right-square")))
                                                                                .withHref(webShare.url().toString())
                                                                                .withTarget("_blank")),
                                                p(webShare.notes()).withClasses(card_text), div(each(webShare.tags(), tag -> {
                                                        return span(tag).withClasses(badge, text_bg_secondary);
                                                })),
                                                div(iffElse(wasFetched,
                                                                span("Fetched").withClasses(badge, text_bg_success),
                                                                span("Fetching").withClasses(badge, text_bg_warning)),
                                                                iffElse(wasMetaDataGenerated,
                                                                                span("Meta data generated").withClasses(
                                                                                                badge, text_bg_success),
                                                                                span("Generating meta data")
                                                                                                .withClasses(badge,
                                                                                                                text_bg_warning)

                                                                )),
                                                p(small(join("Created at ", webShare.createdDate()
                                                                .atZone(ZoneId.systemDefault())
                                                                .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)))
                                                                                .withClasses(text_muted)).withClasses(
                                                                                                card_text)))));
        }

}
