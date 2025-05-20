package news.inboxed.app.web.inbox;

import static j2html.TagCreator.*;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.h2;
import static j2html.TagCreator.h5;
import static j2html.TagCreator.nav;
import static news.inboxed.app.web.site.Navigation.inboxedNavigation;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import static dev.rebelcraft.j2html.bootstrap.Bootstrap.*;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.col;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.nav;

import j2html.rendering.IndentedHtml;
import j2html.tags.DomContent;
import j2html.tags.specialized.HeaderTag;
import j2html.tags.specialized.NavTag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import news.inboxed.app.inbox.InboxItem;
import news.inboxed.app.web.site.SiteLayout;

@Component
public class InboxView extends AbstractView {

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
                Page<InboxItem> inboxItems = (Page<InboxItem>) model.get("inboxItems");
                String homeUrl = (String) model.get("homeUrl");
                String searchUrl = (String) model.get("searchUrl");
                String username = (String) model.get("username");
                String logoutUrl = (String) model.get("logoutUrl");
                String subscribeUrl = (String) model.get("logoutUrl");

                // build the ui
                DomContent html = SiteLayout.add("Inboxed | Reader", model, each(
                                inboxedNavigation(homeUrl, searchUrl, username, logoutUrl),
                                div().withClasses(container_fluid).with(actionBar(), hr(),
                                                div().withClass(row).with(
                                                        div().withClass(col_2).with(
                                                                readerNavigation()),
                                                        div().withClass(col).with(
                                                                inboxItems(inboxItems)))),
                                subscriptionModal(subscribeUrl)));

                // output the html
                setResponseContentType(request, response);
                html.render(IndentedHtml.into(response.getWriter()));

        }

        private static DomContent subscriptionModal(String subscribeUrl) {

                return div()
                        .withClasses(modal, fade)
                        .withId("subscription-modal")
                        .withTabindex(-1)
                        .attr("aria-labelledby", "subscription-modal-label")
                        .attr("aria-hidden", "true")
                        .with(
                                form()
                                        .withMethod("POST")
                                        .withAction(subscribeUrl)
                                        .with(
                                                div().withClasses(modal_dialog).with(
                                                        div().withClasses(modal_content).with(
                                                                div().withClasses(modal_header).with(
                                                                        h1()
                                                                                .withClasses(modal_title, fs_5)
                                                                                .withId("subscription-modal-label")
                                                                                .withText("Subscribe"),
                                                                        button()
                                                                                .withType("button")
                                                                                .withClasses(btn_close)
                                                                                .attr("data-bs-dismiss", "modal")
                                                                                .attr("aria-label", "close")
                                                                ),
                                                                div().withClasses(modal_body).with(
                                                                        p()
                                                                                .withText("Enter a search term to find feeds or paste a feed url."),
                                                                        input()
                                                                                .withType("text")
                                                                                .withClasses(form_control),
                                                                        p()
                                                                                .withText("e.g. robintegg.com or Robin Tegg")                
                                                                ),
                                                                div().withClasses(modal_footer).with(
                                                                        button()
                                                                                .withType("button")
                                                                                .withClasses(btn, btn_secondary)
                                                                                .attr("data-bs-dismiss", "modal")
                                                                                .withText("Close"),
                                                                        button()
                                                                                .withType("submit")
                                                                                .withClasses(btn, btn_primary)
                                                                                .withText("Add")
                                                                )
                                                        )
                                                )
                                        )
                        );
 
        }

        private static DomContent inboxItems(Page<InboxItem> inboxItems) {
                return div().withId("inbox-items").with(
                        
                        h3().withText("All items"),
                        each(
                                div().withClasses(row, bg_light, border_top, py_1)
                                        .with(
                                                div().withClasses(col_auto, pt_1).with(
                                                        span().withClasses(  "bi","bi-star")
                                                ),
                                                div().withClasses(col_2).with(
                                                        text("Spring Blog")
                                                ),
                                                div().withClasses(col).with(
                                                        strong().withText("Spring AI 1.0.0-RC now available"),
                                                        text("Announcing the release")
                                                ),
                                                div().withClasses(col_auto).with(
                                                        text("22:30")
                                                ),
                                                div().withClasses(col_auto, pt_1).with(
                                                        span().withClasses( "bi","bi-box-arrow-up-right")
                                                )
                                        ),
                                
                                div().withClasses(row, bg_light, border_top, border_bottom, py_1)
                                        .with(
                                                div().withClasses(col_auto, pt_1).with(
                                                        span().withClasses(  "bi","bi-star")
                                                ),
                                                div().withClasses(col_2).with(
                                                        text("Robin Tegg")
                                                ),
                                                div().withClasses(col).with(
                                                        strong().withText("Maven 4 Release round the corner"),
                                                        text("Start getting ready for the release of")
                                                ),
                                                div().withClasses(col_auto).with(
                                                        text("12:30")
                                                ),
                                                div().withClasses(col_auto, pt_1).with(
                                                        span().withClasses( "bi","bi-box-arrow-up-right")
                                                )
                                        ),

                                each(inboxItems.getContent(), item -> div().withClass(card).with(
                                                div().withClass(card_body).with(h5(item.title()), p(item.summary()))))
                        ));
        }

        private static NavTag readerNavigation() {
                return nav().with(ul().withClasses(nav, flex_column).with(

                                li().withClass(nav_item)
                                                .with(button()
                                                        .withClasses(btn, btn_primary, mx_3, mb_4)
                                                        .attr("data-bs-toggle", "modal")
                                                        .attr("data-bs-target", "#subscription-modal")
                                                        .withType("button")
                                                        .withText("Subscribe")),

                                li().withClass(nav_item).with(a("Home").withClasses(nav_link, active).withHref("#")),

                                li().withClass(nav_item)
                                                .with(a("All Items (23)").withClasses(nav_link, active).withHref("#")),

                                li().withClass(nav_item).with(a("Subscriptions").withClass(nav_link).withHref("#")))

                );
        }

        private static HeaderTag actionBar() {

                return header().withClasses(row).with(

                                div().withClass(col_2).with(h2("Reader").withClasses(px_3)),

                                div().withClass(col).with(

                                                button().withClasses(btn, btn_secondary).withType("button")
                                                                .with(span().withClasses("bi", "bi-arrow-clockwise")),

                                                div()
                                                        .withClass(btn_group)
                                                        .with(
                                                                button()
                                                                        .withClasses(btn, btn_secondary, btn_sm, dropdown_toggle)
                                                                        .attr("data-bs-toggle", "dropdown")
                                                                        .withType("button")
                                                                        .withText("23 new items"),
                                                                ul()
                                                                        .withClasses(dropdown_menu)
                                                                        .with(
                                                                                li().with(a()
                                                                                .withClasses(dropdown_item)
                                                                                .withHref("#").withText("All items")),
                                                                                li().with(a()
                                                                                .withClasses(dropdown_item)
                                                                                .withHref("#").withText("23 new items"))
                                                                        )),

                                                div().withClass(btn_group).with(
                                                        button()
                                                                .withClasses(btn, btn_secondary, btn_sm)
                                                                .withText("Mark all as read"),
                                                        button()
                                                                .withClasses(btn, btn_secondary, btn_sm,
                                                                                dropdown_toggle, dropdown_toggle_split)
                                                                .attr("data-bs-toggle", "dropdown").withType("button")
                                                                .with(
                                                                        span().withClasses(visually_hidden).withText("Toggle Dropdown")
                                                                ),
                                                        ul().withClasses(dropdown_menu).with(
                                                                li().with(a()
                                                                        .withClasses(dropdown_item)
                                                                        .withHref("#").withText("All items")),
                                                                li().with(a()
                                                                        .withClasses(dropdown_item)
                                                                        .withHref("#").withText("Items older than a day")),
                                                                li().with(a()
                                                                        .withClasses(dropdown_item)
                                                                        .withHref("#").withText("Items older than a week")),
                                                                li().with(a()
                                                                        .withClasses(dropdown_item)
                                                                        .withHref("#").withText("Items older than two weeks"))
                                                                )),

                                                div().withClass(btn_group).with(button()
                                                                .withClasses(btn, btn_secondary, btn_sm,
                                                                                dropdown_toggle)
                                                                .attr("data-bs-toggle", "dropdown").withType("button")
                                                                .withText("View settings"),
                                                                ul().withClasses(dropdown_menu).with(
                                                                        li().with(a()
                                                                                .withClasses(dropdown_item)
                                                                                .withHref("#").withText("Sort by newest")),
                                                                        li().with(a()
                                                                                .withClasses(dropdown_item)
                                                                                .withHref("#").withText("Sort by oldest")),
                                                                        li().with(a()
                                                                                .withClasses(dropdown_item)
                                                                                .withHref("#").withText("Sort by magic")),
                                                                        li().with(
                                                                                hr().withClasses(dropdown_divider)
                                                                        ),
                                                                        li().with(a()
                                                                                .withClasses(dropdown_item)
                                                                                .withHref("#").withText("Set as start page")),
                                                                        li().with(
                                                                                hr().withClasses(dropdown_divider)
                                                                        ),
                                                                        li().with(a()
                                                                                .withClasses(dropdown_item)
                                                                                .withHref("#").withText("View details and statistics")),
                                                                        li().with(a()
                                                                                .withClasses(dropdown_item)
                                                                                .withHref("#").withText("Translate into my language")),
                                                                        li().with(
                                                                                hr().withClasses(dropdown_divider)
                                                                        ),
                                                                        li().with(a()
                                                                                .withClasses(dropdown_item)
                                                                                .withHref("#").withText("View in Reader Play"))
                                                                )),

                                                button().withClasses(btn, btn_secondary).withType("button")
                                                                .with(span().withClasses("bi", "bi-card-list")),

                                                button().withClasses(btn, btn_secondary).withType("button")
                                                                .with(span().withClasses("bi", "bi-card-text")),

                                                button().withClasses(btn, btn_secondary).withType("button")
                                                                .with(span().withClasses("bi", "bi-chevron-up")),

                                                button().withClasses(btn, btn_secondary).withType("button")
                                                                .with(span().withClasses("bi", "bi-chevron-down")),

                                                button().withClasses(btn, btn_secondary).withType("button")
                                                                .with(span().withClasses("bi", "bi-gear"))

                                ));

        }

}
