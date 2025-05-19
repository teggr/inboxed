package news.inboxed.app.web.site;

import static dev.rebelcraft.j2html.bootstrap.Bootstrap.active;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.bg_body_tertiary;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.bg_primary;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.bg_primary_subtle;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.btn;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.btn_outline_secondary;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.btn_secondary;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.btn_success;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.collapse;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.container_fluid;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.d_flex;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.d_inline_flex;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.d_lg_flex;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.dropdown;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.dropdown_divider;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.dropdown_item;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.dropdown_menu;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.dropdown_toggle;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.form_control;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.justify_content_between;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.justify_content_center;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.justify_content_lg_between;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.mb_0;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.mb_2;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.mb_lg_0;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.me_2;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.me_auto;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.nav_item;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.nav_link;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.navbar;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.navbar_brand;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.navbar_collapse;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.navbar_expand_lg;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.navbar_nav;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.navbar_toggler;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.navbar_toggler_icon;
import static j2html.TagCreator.a;
import static j2html.TagCreator.button;
import static j2html.TagCreator.div;
import static j2html.TagCreator.form;
import static j2html.TagCreator.hr;
import static j2html.TagCreator.input;
import static j2html.TagCreator.li;
import static j2html.TagCreator.nav;
import static j2html.TagCreator.span;
import static j2html.TagCreator.ul;

import j2html.tags.specialized.NavTag;

public class Navigation {

    public static NavTag inboxedNavigation(String homeUrl, String searchUrl, String username, String logoutUrl) {

        return nav().withClasses(navbar, navbar_expand_lg, bg_primary_subtle).with(

            div().withClasses(container_fluid).with(

                a().withClasses(navbar_brand).withText("Inboxed").withHref(homeUrl),

                button().withClass(navbar_toggler).withType("button")
                .attr("data-bs-toggle", "collapse").attr("data-bs-target","#navbarToggler")
                .attr("aria-controls", "navbarToggler").attr("aria-expanded", "false")
                .attr("aria-label", "Toggle Navigation").with(
                    span().withClass(navbar_toggler_icon)
                ),

                div().withClasses(collapse, navbar_collapse, d_lg_flex, justify_content_lg_between).withId("navbarToggler").with(

                    div(),

                    form().withClasses(d_inline_flex, mb_0).with(

                        input().withClasses(form_control, me_2).withType("search"),
                        button().withType("submit").withClasses(btn, btn_outline_secondary).withText("Search")

                    ),

                    ul().withClass(navbar_nav).with(

                        li().withClasses(nav_item, dropdown).with(
                            a().withClasses(nav_link, dropdown_toggle).withText("my@email.com")
                            .withHref("#")
                                .attr("data-bs-toggle", "dropdown")
                                .attr("role", "button")
                                .attr("aria-expanded", "false"),

                            ul().withClasses(dropdown_menu).with(
                                li().with(
                                    a().withClasses(dropdown_item).withHref(logoutUrl).withText("Logout")
                                )
                            )
                        )
                            
                        

                    )

                )

            )
            
               );
    }

}
