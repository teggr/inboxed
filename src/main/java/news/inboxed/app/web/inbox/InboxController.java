package news.inboxed.app.web.inbox;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import news.inboxed.app.inbox.Inbox;
import org.springframework.data.domain.Pageable;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.*;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class InboxController {

    private final Inbox inbox;

    @GetMapping
    public String getInbox(Pageable pageable, Model model) {

        String refreshUrl = fromMethodName(InboxController.class, "getInbox", pageable, model).build().toUriString();
        model.addAttribute("refreshUrl", refreshUrl);

        int newItemCount = inbox.getNewItemCount();
        model.addAttribute("newItemCount", newItemCount);

        model.addAttribute("inboxItems", inbox.getInboxItems(pageable));

        return "inboxView";

    }

}
