package dev.webshares.app.web.webshares;

import java.net.URL;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import j2html.rendering.IndentedHtml;
import j2html.tags.DomContent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import news.inboxed.app.tags.TagName;
import news.inboxed.app.web.site.InboxedSiteLayout;

import static j2html.TagCreator.*;
import static j2html.TagCreator.h1;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.*;

@Component
public class WebShareView extends AbstractView {

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
                String postCreateWebShareUrl = (String) model.get("postCreateWebShareUrl");
                String cancelUrl = (String) model.get("cancelUrl");
                URL url = (URL) model.get("url");
                List<TagName> tagNames = (List<TagName>) model.get("tagNames");
                List<String> tags = (List<String>) model.get("tags");

                // build the ui
                DomContent html = InboxedSiteLayout.add("Create a new link", model, each(h1("Create a new link"),
                                form().withMethod("POST").withAction(postCreateWebShareUrl).with(div().withClass(mb_3).with(

                                                label().withFor("url").withClass(form_label).withText("URL"),

                                                input().withClass(form_control).withId("url").withType("url")
                                                                .withName("url").withPlaceholder("Put your link here")
                                                                .withCondValue(url != null,
                                                                                url != null ? url.toString() : "")),

                                                div().withClass(mb_3).with(

                                                                label().withFor("notes").withClass(form_label)
                                                                                .withText("Notes"),

                                                                textarea().withId("notes").withClass(form_control)
                                                                                .withRows("5").withName("notes")

                                                ),

                                                div().withClass(mb_3).with(

                                                                label().withFor("tags").withClass(form_label)
                                                                                .withText("Tags"),

                                                                div().withId("tags").withClass(mb_3)
                                                                                .with(each(tags, tag -> {
                                                                                        return each(input().withType(
                                                                                                        "hidden")
                                                                                                        .withName("tags")
                                                                                                        .withValue(tag),
                                                                                                        span(tag).withClasses(
                                                                                                                        "badge",
                                                                                                                        "text-bg-secondary",
                                                                                                                        "me-1"));
                                                                                })),

                                                                input().withId("dataListInput").withClass(form_control)
                                                                                .withList("datalistOptions")
                                                                                .withPlaceholder("Type to search"),

                                                                datalist().withId("datalistOptions").with(each(tagNames,
                                                                                tagName -> option().withValue(
                                                                                                tagName.name()))),

                                                                button().withId("AddToTag").withType("button")
                                                                                .withClasses(btn, btn_secondary)
                                                                                .withText("Add to Tags"),

                                                                // language=javascript
                                                                script().withType("text/javascript")
                                                                                .with(rawHtml("""
                                                                                                document.addEventListener("DOMContentLoaded", function() {
                                                                                                                console.log("DOM fully loaded and parsed");

                                                                                                                const dataListInput = document.getElementById("dataListInput");
                                                                                                                const addToTagButton = document.getElementById("AddToTag");

                                                                                                                // Intercept Enter key press on dataListInput
                                                                                                                dataListInput.addEventListener("keydown", function(event) {
                                                                                                                                if (event.key === "Enter") {
                                                                                                                                                event.preventDefault(); // Prevent form submission
                                                                                                                                                addToTagButton.click(); // Trigger the AddToTag button click
                                                                                                                                }
                                                                                                                });

                                                                                                                // Add click event for AddToTag button
                                                                                                                addToTagButton.addEventListener("click", function() {
                                                                                                                                const input = document.querySelector("input[list='datalistOptions']");
                                                                                                                                const tagValues = input.value.split(",").map(tag => tag.trim());

                                                                                                                                tagValues.forEach(tagValue => {
                                                                                                                                                if (tagValue) {
                                                                                                                                                                const tagContainer = document.querySelector("#tags");

                                                                                                                                                                const newHidden = document.createElement("input");
                                                                                                                                                                newHidden.type = "hidden";
                                                                                                                                                                newHidden.value = tagValue;
                                                                                                                                                                newHidden.name = "tags";

                                                                                                                                                                const newTag = document.createElement("span");
                                                                                                                                                                newTag.className = "badge text-bg-secondary me-1";
                                                                                                                                                                newTag.textContent = tagValue;

                                                                                                                                                                tagContainer.appendChild(newHidden);
                                                                                                                                                                tagContainer.appendChild(newTag);
                                                                                                                                                }
                                                                                                                                });

                                                                                                                                input.value = ""; // Clear the input field
                                                                                                                });
                                                                                                });
                                                                                                """))

                                                ),

                                                a().withClasses(btn, btn_secondary).withHref(cancelUrl)
                                                                .withText("Cancel"),

                                                input().withClasses(btn, btn_primary).withType("submit")
                                                                .withName("createUrl")

                                )));

                // output the response
                setResponseContentType(request, response);
                each(document(), html).render(IndentedHtml.into(response.getWriter()));

        }

}
