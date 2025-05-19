package news.inboxed.app.web.webshares;

import static dev.rebelcraft.j2html.bootstrap.Bootstrap.*;
import static j2html.TagCreator.*;
import static j2html.TagCreator.h5;
import static j2html.TagCreator.small;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import j2html.tags.DomContent;
import news.inboxed.app.webshares.WebShare;

public class WebShareComponents {

  public static DomContent webShareForm(String createWebShareUrl) {
    return form()
        .withMethod("GET")
        .withAction(createWebShareUrl)
        .with(
          input().withType("url").withName("url")
            .withPlaceholder("Enter a URL"), 
          input().withType("submit")
        );
  }

  public static DomContent webShareCard(WebShare webShare) {

    String title = webShare.url().toString();
    if (webShare.webShareData() != null) {
      title = webShare.webShareData().title();
    }
    if (webShare.webShareMetaData() != null) {
      title = webShare.webShareMetaData().title();
    }

    boolean wasFetched = webShare.hasWebShareData();
    boolean wasMetaDataGenerated = webShare.hasMetaData();

    return div().withClasses(card, mb_3).with(div().withClasses(row, g_0).with(
        // div().withClasses(col_md_4).with(
        // img().withSrc("...").withClasses(img_fluid, rounded_start).withAlt("...")
        // ),
        div().withClasses(col_md_8)
            .with(div().withClasses(card_body).with(h5(title).withClasses(card_title),
                p(a(join(webShare.url().toString(), span().withClasses("bi", "bi-arrow-up-right-square")))
                    .withHref(webShare.url().toString()).withTarget("_blank")),
                p(webShare.notes()).withClasses(card_text), div(each(webShare.tags(), tag -> {
                  return span(tag).withClasses(badge, text_bg_secondary);
                })),
                div(iffElse(wasFetched, span("Fetched").withClasses(badge, text_bg_success),
                    span("Fetching").withClasses(badge, text_bg_warning)),
                    iffElse(wasMetaDataGenerated, span("Meta data generated").withClasses(badge, text_bg_success),
                        span("Generating meta data").withClasses(badge, text_bg_warning)

                    )),
                p(small(join("Created at ",
                    webShare.createdDate().atZone(ZoneId.systemDefault())
                        .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))).withClasses(text_muted))
                            .withClasses(card_text)))));
  }

}
