package news.inboxed.app.web.inbox;

import static j2html.TagCreator.*;
import static news.inboxed.app.web.site.InboxedNavigation.inboxedNavigation;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import static dev.rebelcraft.j2html.bootstrap.Bootstrap.*;
import static dev.rebelcraft.j2html.bootstrap.Bootstrap.col;

import j2html.rendering.IndentedHtml;
import j2html.tags.DomContent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import news.inboxed.app.inbox.InboxItem;
import news.inboxed.app.web.inbox.components.ReaderActionBar;
import news.inboxed.app.web.inbox.components.AddSubscriptionModal;
import news.inboxed.app.web.inbox.components.InboxItemsList;
import news.inboxed.app.web.inbox.components.ReaderNavigation;
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
                String subscribeUrl = (String) model.get("subscribeUrl");
                String refreshUrl = (String) model.get("refreshUrl");
                int newItemCount = (int)model.get("newItemCount");
                String adminFeedsUrl = (String) model.get("adminFeedsUrl");

                // build the ui
                DomContent html = SiteLayout.add("Inboxed | Reader", model, 
                  
                  each(
                    
                    inboxedNavigation(homeUrl, searchUrl, adminFeedsUrl, username, logoutUrl),
                    
                    div().withClasses(container_fluid).with(
                      
                      ReaderActionBar.readerActionBar(newItemCount, refreshUrl), 
                      
                      hr(),

                      div().withClass(row).with(
                        
                        div().withClass(col_2).with(
                                      ReaderNavigation.readerNavigation(newItemCount)),
                        
                        div().withClasses(col, px_2).with(
                                      InboxItemsList.inboxItems(inboxItems))
                                      
                      )
                                      
                    ),
                      
                    AddSubscriptionModal.subscriptionModal(subscribeUrl)
                    
                  ));

                // output the html
                setResponseContentType(request, response);
                html.render(IndentedHtml.into(response.getWriter()));

        }

}
