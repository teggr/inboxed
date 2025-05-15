package dev.rebelcraft.linksapp.web.links;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import dev.rebelcraft.linksapp.domain.Link;
import dev.rebelcraft.linksapp.web.templates.SiteTemplate;
import j2html.rendering.IndentedHtml;
import j2html.tags.DomContent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static dev.rebelcraft.j2html.bootstrap.Bootstrap.badge;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.text_bg_secondary;
import static j2html.TagCreator.*;

@Component
public class LinksView extends AbstractView {

        @Override
        @Nullable
        public String getContentType() {
                return MediaType.TEXT_HTML_VALUE;
        }

        @Override
        protected void renderMergedOutputModel(@Nullable Map<String, Object> model, @NonNull HttpServletRequest request,
                        @NonNull HttpServletResponse response) throws Exception {

                // get from the model
                Page<Link> links = (Page<Link>) model.get("links");
                String createLinkUrl = (String) model.get("createLinkUrl");
                String feedUrl = (String) model.get("feedUrl");

                // build the ui
                DomContent html = SiteTemplate.add("Links", model, each(h1("Create a new link"),
                                div(form().withMethod("GET").withAction(createLinkUrl).with(
                                                input().withType("url").withName("url").withPlaceholder("Enter a URL"),
                                                input().withType("submit").withName("createUrl"))),
                                div(h1().with(join(text("Recent links"),
                                                a().withHref(feedUrl).with(span().withClasses("bi", "bi-rss-fill")))),
                                                each(links.toList(), link -> {
                                                        return div(a(link.url().toString()).withHref(
                                                                        link.url().toString()).withTarget("_blank"),
                                                                        p(link.notes()),
                                                                        p("Created at " + link.createdDate()
                                                                                        .atZone(ZoneId.systemDefault())
                                                                                        .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)),
                                                                        each(link.tags(), tag -> {
                                                                                return span(tag).withClasses(badge,
                                                                                                text_bg_secondary);
                                                                        }));
                                                }))));

                // output the html
                setResponseContentType(request, response);
                html.render(IndentedHtml.into(response.getWriter()));

        }

}
