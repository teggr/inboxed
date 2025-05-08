package dev.rebelcraft.linksapp.web.home;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;
import dev.rebelcraft.linksapp.web.templates.SiteTemplate;
import j2html.rendering.IndentedHtml;
import j2html.tags.DomContent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static j2html.TagCreator.*;

@Component
public class CreateLinkView implements View {

    @Override
    @Nullable
    public String getContentType() {
        return MediaType.TEXT_HTML_VALUE;
    }

    @Override
    public void render(@Nullable Map<String, ?> model, @NonNull HttpServletRequest request, @NonNull HttpServletResponse response)
            throws Exception {

        // get from the model
        String postUrl = (String) model.get("createUrl");

        // build the ui
        DomContent html = SiteTemplate.add(model,
                form()
                        .withMethod("POST")
                        .withAction(postUrl)
                        .with(
                                input()
                                        .withType("url")
                                        .withName("url")
                                        .withPlaceholder("Put your link here"),
                                input()
                                        .withType("submit")
                                        .withName("createUrl")));

        // output the html
        html.render(IndentedHtml.into(response.getWriter()));

    }

}
