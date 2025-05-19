package news.inboxed.app.web.inbox;

import static j2html.TagCreator.*;
import static news.inboxed.app.web.site.Navigation.inboxedNavigation;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import dev.rebelcraft.j2html.bootstrap.Bootstrap;
import j2html.rendering.IndentedHtml;
import j2html.tags.DomContent;
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
        @NonNull HttpServletResponse response
    ) throws Exception {

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
                div().withClass(Bootstrap.container)
                // ...existing code (other contents of each, if any)...
            )
        );

        // output the html
        setResponseContentType(request, response);
        html.render(IndentedHtml.into(response.getWriter()));
        
    }

}
