package news.inboxed.app.web.inbox.components;

import static dev.rebelcraft.j2html.bootstrap.Bootstrap.btn;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.btn_group;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.btn_secondary;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.btn_sm;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.col;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.col_2;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.dropdown_divider;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.dropdown_item;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.dropdown_menu;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.dropdown_toggle;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.dropdown_toggle_split;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.pt_1;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.px_3;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.row;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.visually_hidden;
import static j2html.TagCreator.a;
import static j2html.TagCreator.button;
import static j2html.TagCreator.div;
import static j2html.TagCreator.h2;
import static j2html.TagCreator.header;
import static j2html.TagCreator.hr;
import static j2html.TagCreator.li;
import static j2html.TagCreator.span;
import static j2html.TagCreator.ul;

import j2html.tags.specialized.HeaderTag;

public class ReaderActionBar {

    public static HeaderTag readerActionBar(int newItemCount, String refreshUrl) {
    
            return header().withClasses(row).with(
    
                            div().withClass(col_2).with(h2("Reader").withClasses(px_3)),
    
                            div().withClasses(col, pt_1).with(
    
                                            a()
                                                .withClasses(btn, btn_secondary)
                                                .with(span().withClasses("bi", "bi-arrow-clockwise"))
                                                .withHref(refreshUrl),
    
                                            div()
                                                    .withClass(btn_group)
                                                    .with(
                                                            button()
                                                                    .withClasses(btn, btn_secondary, btn_sm, dropdown_toggle)
                                                                    .attr("data-bs-toggle", "dropdown")
                                                                    .withType("button")
                                                                    .withText(newItemCount + " new items"),
                                                            ul()
                                                                    .withClasses(dropdown_menu)
                                                                    .with(
                                                                            li().with(a()
                                                                            .withClasses(dropdown_item)
                                                                            .withHref("#").withText("All items")),
                                                                            li().with(a()
                                                                            .withClasses(dropdown_item)
                                                                            .withHref("#").withText(newItemCount + " new items"))
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
