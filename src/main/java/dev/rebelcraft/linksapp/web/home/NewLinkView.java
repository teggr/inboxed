package dev.rebelcraft.linksapp.web.home;

import java.util.Map;

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

import static j2html.TagCreator.*;

@Component
public class NewLinkView extends AbstractView {

        @Override
        @Nullable
        public String getContentType() {
                return MediaType.TEXT_HTML_VALUE;
        }

        @Override
        protected void renderMergedOutputModel(@Nullable Map<String, Object> model, @NonNull HttpServletRequest request,
                        @NonNull HttpServletResponse response) throws Exception {

                // get from the model
                String updateUrl = (String) model.get("updateUrl");
                Link link = (Link) model.get("link");

                // build the ui
                DomContent html = SiteTemplate.add(model,
                                each(
                                                h2("New link"),
                                                form()
                                                                .withMethod("POST")
                                                                .withAction(updateUrl)
                                                                .with(
                                                                                input()
                                                                                                .withType("hidden")
                                                                                                .withName("id")
                                                                                                .withValue(link.id()
                                                                                                                .toString()),
                                                                                input()
                                                                                                .withType("url")
                                                                                                .withName("url")
                                                                                                .withValue(link.url()
                                                                                                                .toString()),
                                                                                input()
                                                                                                .withType("text")
                                                                                                .withName("notes")
                                                                                                .withValue(link.notes()),
                                                                                each(link.tags(), tag -> input()
                                                                                                .withType("checkbox")
                                                                                                .withName("tags")
                                                                                                .withValue(tag.name())
                                                                                                .withCondChecked(true)),
                                                                                input()
                                                                                                .withType("submit")
                                                                                                .withName("updateUrl"))));

                // output the response
                setResponseContentType(request, response);
                each(document(), html).render(IndentedHtml.into(response.getWriter()));

        }

}