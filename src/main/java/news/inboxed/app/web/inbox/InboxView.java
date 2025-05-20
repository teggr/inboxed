package news.inboxed.app.web.inbox;

import static j2html.TagCreator.*;
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
        protected void renderMergedOutputModel(
                        @Nullable Map<String, Object> model,
                        @NonNull HttpServletRequest request,
                        @NonNull HttpServletResponse response) throws Exception {

                // get from the model
                Page<InboxItem> inboxItems = (Page<InboxItem>) model.get("inboxItems");
                String homeUrl = (String) model.get("homeUrl");
                String searchUrl = (String) model.get("searchUrl");
                String username = (String) model.get("username");
                String logoutUrl = (String) model.get("logoutUrl");

                // build the ui
                DomContent html = SiteLayout.add(
                                "Inboxed | Reader",
                                model,
                                each(
                                                inboxedNavigation(homeUrl, searchUrl, username, logoutUrl),
                                                div().withClasses(container_fluid).with(
                                                                actionBar(),
                                                                hr(),
                                                                div().withClass(row).with(
                                                                                div().withClass(col_2).with(
                                                                                                readerNavigation()),
                                                                                div().withClass(col).with(
                                                                                                inboxItems(inboxItems))))));

                // output the html
                setResponseContentType(request, response);
                html.render(IndentedHtml.into(response.getWriter()));

        }

        private static DomContent inboxItems(Page<InboxItem> inboxItems) {
                return div().withId("inbox-items").with(
                        h3().withText("All items"),
                        each(
                                inboxItems.getContent(),
                                item -> div().withClass(
                                                card)
                                                .with(
                                                                div().withClass(card_body)
                                                                                .with(
                                                                                                h5(item.title()),
                                                                                                p(item.summary())))));
        }

        private static NavTag readerNavigation() {
                return nav()
                                .with(
                                                ul().withClasses(
                                                                nav,
                                                                flex_column)
                                                                .with(

                                                                                li().withClass(nav_item)
                                                                                                .with(button()
                                                                                                                .withClasses(btn,
                                                                                                                                btn_primary,
                                                                                                                                mx_3,
                                                                                                                                mb_4)
                                                                                                                .withType("button")
                                                                                                                .withText("Subscribe")),

                                                                                li().withClass(nav_item)
                                                                                                .with(a("Home").withClasses(
                                                                                                                nav_link,
                                                                                                                active)
                                                                                                                .withHref("#")),

                                                                                li().withClass(nav_item)
                                                                                                .with(a("All Items (23)")
                                                                                                                .withClasses(nav_link,
                                                                                                                                active)
                                                                                                                .withHref("#")),

                                                                                li().withClass(nav_item)
                                                                                                .with(a("Subscriptions")
                                                                                                                .withClass(nav_link)
                                                                                                                .withHref("#")))

                                );
        }

        private static HeaderTag actionBar() {

                return header().withClasses(row).with(

                                div().withClass(col_2).with(
                                                h2("Reader").withClasses(px_3)),

                                div().withClass(col).with(

                                                button().withClasses(btn, btn_secondary)
                                                                .withType("button")
                                                                .with(span().withClasses("bi", "bi-arrow-clockwise")),

                                                div().withClass(btn_group).with(
                                                                button().withClasses(btn, btn_secondary, btn_sm,
                                                                                dropdown_toggle)
                                                                                .attr("data-bs-toggle", "dropdown")
                                                                                .withType("button")
                                                                                .withText("23 new items"),
                                                                ul().withClasses(dropdown_menu).with(
                                                                                li().with(
                                                                                                a().withClasses(dropdown_item)
                                                                                                                .withHref("#")
                                                                                                                .withText("All items")))),

                                                div().withClass(btn_group).with(
                                                                button().withClasses(btn, btn_secondary, btn_sm,
                                                                                dropdown_toggle)
                                                                                .attr("data-bs-toggle", "dropdown")
                                                                                .withType("button")
                                                                                .withText("Refresh"),
                                                                ul().withClasses(dropdown_menu).with(
                                                                                li().with(
                                                                                                a().withClasses(dropdown_item)
                                                                                                                .withHref("#")
                                                                                                                .withText("All items")))),

                                                div().withClass(btn_group).with(
                                                                button().withClasses(btn, btn_secondary, btn_sm,
                                                                                dropdown_toggle)
                                                                                .attr("data-bs-toggle", "dropdown")
                                                                                .withType("button")
                                                                                .withText("Mark all as read"),
                                                                ul().withClasses(dropdown_menu).with(
                                                                                li().with(
                                                                                                a().withClasses(dropdown_item)
                                                                                                                .withHref("#")
                                                                                                                .withText("All items")))),

                                                div().withClass(btn_group).with(
                                                                button().withClasses(btn, btn_secondary, btn_sm,
                                                                                dropdown_toggle)
                                                                                .attr("data-bs-toggle", "dropdown")
                                                                                .withType("button")
                                                                                .withText("View settings"),
                                                                ul().withClasses(dropdown_menu).with(
                                                                                li().with(
                                                                                                a().withClasses(dropdown_item)
                                                                                                                .withHref("#")
                                                                                                                .withText("All items")))),

                                                button().withClasses(btn, btn_secondary)
                                                                .withType("button")
                                                                .with(span().withClasses("bi", "bi-card-list")),

                                                button().withClasses(btn, btn_secondary)
                                                                .withType("button")
                                                                .with(span().withClasses("bi", "bi-card-text")),

                                                button().withClasses(btn, btn_secondary)
                                                                .withType("button")
                                                                .with(span().withClasses("bi", "bi-chevron-up")),

                                                button().withClasses(btn, btn_secondary)
                                                                .withType("button")
                                                                .with(span().withClasses("bi", "bi-chevron-down")),

                                                button().withClasses(btn, btn_secondary)
                                                                .withType("button")
                                                                .with(span().withClasses("bi", "bi-gear"))

                                ));

        }

}
