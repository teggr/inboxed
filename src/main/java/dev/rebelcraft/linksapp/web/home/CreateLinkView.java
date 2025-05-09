package dev.rebelcraft.linksapp.web.home;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import dev.rebelcraft.linksapp.web.templates.SiteTemplate;
import j2html.rendering.IndentedHtml;
import j2html.tags.DomContent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static j2html.TagCreator.*;

@Component
public class CreateLinkView extends AbstractView {

        @Override
        @Nullable
        public String getContentType() {
                return MediaType.TEXT_HTML_VALUE;
        }

        @Override
        protected void renderMergedOutputModel(@Nullable Map<String, Object> model, @NonNull HttpServletRequest request,
                        @NonNull HttpServletResponse response) throws Exception {

                // get from the model
                String postUrl = (String) model.get("createUrl");

                // build the ui
                DomContent html = SiteTemplate.add(model,
                                each(
                                                h1("Create a new link"),
                                                form()
                                                                .withMethod("POST")
                                                                .withAction(postUrl)
                                                                .with(
                                                                                input()
                                                                                                .withType("url")
                                                                                                .withName("url")
                                                                                                .withPlaceholder(
                                                                                                                "Put your link here"),
                                                                                input()
                                                                                                .withType("submit")
                                                                                                .withName("createUrl"))));

                // output the response
                setResponseContentType(request, response);
                each(document(), html).render(IndentedHtml.into(response.getWriter()));

        }

}
