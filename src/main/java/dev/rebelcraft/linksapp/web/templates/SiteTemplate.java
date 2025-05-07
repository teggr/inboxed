package dev.rebelcraft.linksapp.web.templates;

import java.util.Map;

import j2html.TagCreator;
import j2html.tags.DomContent;
import j2html.tags.specialized.HtmlTag;

public class SiteTemplate {

    public static DomContent add(Map<String, Object> model, DomContent... content) {

        // build the view
        HtmlTag html = TagCreator.html(
                TagCreator.head(

                ),
                TagCreator.body(
                    TagCreator.each( content )
                )
        );

        return html;

    }

}
