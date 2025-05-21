package news.inboxed.app.web.inbox.components;

import static dev.rebelcraft.j2html.bootstrap.Bootstrap.btn;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.btn_close;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.btn_primary;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.btn_secondary;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.fade;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.form_control;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.fs_5;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.modal;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.modal_body;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.modal_content;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.modal_dialog;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.modal_footer;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.modal_header;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.modal_title;
import static j2html.TagCreator.button;
import static j2html.TagCreator.div;
import static j2html.TagCreator.form;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.input;
import static j2html.TagCreator.p;

import j2html.tags.DomContent;

public class AddSubscriptionModal {

    public static DomContent subscriptionModal(String subscribeUrl) {
    
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

}
