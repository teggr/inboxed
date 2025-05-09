package dev.rebelcraft.linksapp.web.links;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import dev.rebelcraft.linksapp.domain.Link;
import dev.rebelcraft.linksapp.web.templates.SiteTemplate;
import j2html.TagCreator;
import j2html.rendering.IndentedHtml;
import j2html.tags.DomContent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

        // build the ui
        DomContent html = SiteTemplate.add(
                model,
                each(
                        form()
                                .withMethod("POST")
                                .withAction(createLinkUrl)
                                .with(
                                        input()
                                                .withType("url")
                                                .withName("url")
                                                .withPlaceholder("Enter a URL"),
                                        input()
                                                .withType("submit")
                                                .withName("createUrl")),
                        each(links.toList(), link -> {
                            return text(link.url().toString());
                        })));

        // output the html
        setResponseContentType(request, response);
        html.render(IndentedHtml.into(response.getWriter()));

    }

}
