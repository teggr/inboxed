package dev.rebelcraft.linksapp.web.home;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import dev.rebelcraft.linksapp.web.templates.SiteTemplate;
import j2html.TagCreator;
import j2html.rendering.IndentedHtml;
import j2html.tags.DomContent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class NewLinkView extends AbstractView {

    @Override
    @Nullable
    public String getContentType() {
        return MediaType.TEXT_HTML_VALUE;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        // get from the model

        // build the ui
        DomContent html = SiteTemplate.add(model, TagCreator.text("hello world"));

        // output the html
        html.render(IndentedHtml.into(response.getWriter()));

    }

}
