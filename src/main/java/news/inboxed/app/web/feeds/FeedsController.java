package news.inboxed.app.web.feeds;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import news.inboxed.app.feeds.Feeds;

@Controller
@RequestMapping("/feeds")
@RequiredArgsConstructor
public class FeedsController {

    private final Feeds feeds;

    @GetMapping
    public String getFeeds( Pageable pageable, Model model) {
        model.addAttribute("feeds", feeds.getFeeds(pageable) );
        return "feedsView";
    }

}
