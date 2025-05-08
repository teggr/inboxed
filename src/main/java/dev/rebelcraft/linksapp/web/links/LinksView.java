package dev.rebelcraft.linksapp.web.links;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;
import dev.rebelcraft.linksapp.domain.Link;
import dev.rebelcraft.linksapp.web.templates.SiteTemplate;
import j2html.TagCreator;
import j2html.rendering.IndentedHtml;
import j2html.tags.DomContent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LinksView implements View {

    @Override
    @Nullable
    public String getContentType() {
        return MediaType.TEXT_HTML_VALUE;
    }

    @Override
    public void render(@Nullable Map<String, ?> model, @NonNull HttpServletRequest request, @NonNull HttpServletResponse response)
            throws Exception {

        // get from the model
        Page<Link> links = (Page<Link>) model.get("links");

        // build the ui
        DomContent html = SiteTemplate.add(
                model,
                TagCreator.each(links.toList(), link -> {
                    return TagCreator.text(link.url());
                }));

        // output the html
        html.render(IndentedHtml.into(response.getWriter()));

    }

}
