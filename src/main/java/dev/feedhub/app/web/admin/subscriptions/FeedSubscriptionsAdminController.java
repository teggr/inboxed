package dev.feedhub.app.web.admin.subscriptions;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.feedhub.app.subscriptions.FeedSubscriptions;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/feedhub/admin/subscriptions")
@RequiredArgsConstructor
public class FeedSubscriptionsAdminController {

    private final FeedSubscriptions feedSubscriptions;

    @GetMapping
    public String getSubscribers(Pageable pageable, Model model) {
        
        model.addAttribute("feedSubscribers", feedSubscriptions.getSubscribers(pageable));

        return "feedSubscriptionsAdminView";

    }

}
