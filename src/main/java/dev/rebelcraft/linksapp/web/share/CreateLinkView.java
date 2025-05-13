package dev.rebelcraft.linksapp.web.share;

import java.net.URL;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import dev.rebelcraft.linksapp.web.templates.SiteTemplate;
import j2html.TagCreator;
import j2html.rendering.IndentedHtml;
import j2html.tags.DomContent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static j2html.TagCreator.*;
import static j2html.TagCreator.h1;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.*;

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
                URL url = (URL) model.get("url");

                // build the ui
                DomContent html = SiteTemplate.add("Create a new link", model,
                                each(
                                                h1("Create a new link"),
                                                form()
                                                                .withMethod("POST")
                                                                .withAction(postUrl)
                                                                .with(
                                                                                div().withClass(mb_3).with(

                                                                                                label().withFor("url")
                                                                                                                .withClass(form_label)
                                                                                                                .withText("URL"),

                                                                                                input()
                                                                                                                .withClass(form_control)
                                                                                                                .withId("url")
                                                                                                                .withType("url")
                                                                                                                .withName("url")
                                                                                                                .withPlaceholder(
                                                                                                                                "Put your link here")
                                                                                                                .withCondValue(url != null,
                                                                                                                                url != null ? url
                                                                                                                                                .toString()
                                                                                                                                                : "")),

                                                                                div().withClass(mb_3).with(

                                                                                                label().withFor("notes")
                                                                                                                .withClass(form_label)
                                                                                                                .withText("Notes"),

                                                                                                textarea()
                                                                                                                .withId("notes")
                                                                                                                .withClass(form_control)
                                                                                                                .withRows("5")
                                                                                                                .withName("notes")

                                                                                ),

                                                                                div().withClass(mb_3).with(

                                                                                                label().withFor("tags").withClass(
                                                                                                                form_label)
                                                                                                                .withText("Tags"),

                                                                                                div().withId("tags").withClass(mb_3)
                                                                                                                .with(
                                                                                                                                span().withText("MyTag")
                                                                                                                                                .withClasses(badge,
                                                                                                                                                                text_bg_secondary)),

                                                                                                input().withClass(
                                                                                                                form_control)
                                                                                                                .withList("datalistOptions")
                                                                                                                .withPlaceholder(
                                                                                                                                "Type to search"),

                                                                                                datalist().withId(
                                                                                                                "datalistOptions")
                                                                                                                .with(
                                                                                                                                option().withValue(
                                                                                                                                                "AnotherTag")),

                                                                                                button().withId("AddToTag").withType(
                                                                                                                "button")
                                                                                                                .withClasses(btn,
                                                                                                                                btn_secondary)
                                                                                                                .withText("Add to Tags"),

                                                                                                //language=javascript
                                                                                                script().withType("text/javascript").with(rawHtml("""
                                                                                                        document.addEventListener("DOMContentLoaded", function() {
                                                                                                                console.log("DOM fully loaded and parsed");
                                                                                                                // Add your custom logic here
                                                                                                                document.getElementById("AddToTag").addEventListener("click", function() {
                                                                                                                        const input = document.querySelector("input[list='datalistOptions']");
                                                                                                                        const tagValues = input.value.split(",").map(tag => tag.trim());

                                                                                                                         tagValues.forEach(tagValue => {
                                                                                                                                if (tagValue) {
                                                                                                                                        const tagContainer = document.querySelector("#tags");
                                                                                                                                        const newTag = document.createElement("span");
                                                                                                                                        newTag.className = "badge text-bg-secondary";
                                                                                                                                        newTag.textContent = tagValue;

                                                                                                                                        tagContainer.appendChild(newTag);
                                                                                                                                }
                                                                                                                        });

                                                                                                                        input.value = ""; // Clear the input field     
                                                                                                                });
                                                                                                        });
                                                                                                        """))

                                                                                ),

                                                                                input()
                                                                                                .withClasses(btn,
                                                                                                                btn_primary)
                                                                                                .withType("submit")
                                                                                                .withName("createUrl"))));

                // output the response
                setResponseContentType(request, response);
                each(document(), html).render(IndentedHtml.into(response.getWriter()));

        }

}
