package news.inboxed.app.web.inbox.components;

import static dev.rebelcraft.j2html.bootstrap.Bootstrap.bg_light;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.border_bottom;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.border_top;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.col;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.col_2;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.col_auto;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.pt_1;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.py_1;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.row;
import static j2html.TagCreator.div;
import static j2html.TagCreator.each;
import static j2html.TagCreator.h3;
import static j2html.TagCreator.span;
import static j2html.TagCreator.strong;
import static j2html.TagCreator.text;

import org.springframework.data.domain.Page;

import j2html.tags.DomContent;
import news.inboxed.app.inbox.InboxItem;

public class InboxItemsList {

  public static DomContent inboxItems(Page<InboxItem> inboxItems) {
    return div().withId("inbox-items").with(

        h3().withText("All items"),
        each(
            div().withClasses(row, bg_light, border_top, py_1).with(
                div().withClasses(col_auto, pt_1).with(span().withClasses("bi", "bi-star")),
                div().withClasses(col_2).with(text("Spring Blog")),
                div().withClasses(col).with(strong().withText("Spring AI 1.0.0-RC now available"),
                    text("Announcing the release")),
                div().withClasses(col_auto).with(text("22:30")),
                div().withClasses(col_auto, pt_1).with(span().withClasses("bi", "bi-box-arrow-up-right"))),

            each(inboxItems.getContent(), item ->

            div().withClasses(row, bg_light, border_top, border_bottom, py_1).with(
                div().withClasses(col_auto, pt_1).with(span().withClasses("bi", "bi-star")),
                div().withClasses(col_2).with(text(item.subscriptionName())),
                div().withClasses(col).with(strong().withText(item.title()), span(item.summary()).withClasses()),
                div().withClasses(col_auto).with(text(news.inboxed.app.web.utils.TimeUtils.formatInstant(item.date()))),
                div().withClasses(col_auto, pt_1).with(span().withClasses("bi", "bi-box-arrow-up-right")))

            )));
  }

}
