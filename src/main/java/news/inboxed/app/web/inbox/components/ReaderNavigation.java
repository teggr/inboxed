package news.inboxed.app.web.inbox.components;

import static dev.rebelcraft.j2html.bootstrap.Bootstrap.active;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.btn;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.btn_primary;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.flex_column;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.mb_4;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.mx_3;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.nav;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.nav_item;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.nav_link;
import static j2html.TagCreator.a;
import static j2html.TagCreator.button;
import static j2html.TagCreator.li;
import static j2html.TagCreator.nav;
import static j2html.TagCreator.ul;

import j2html.tags.specialized.NavTag;

public class ReaderNavigation {

  public static NavTag readerNavigation() {
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

}
