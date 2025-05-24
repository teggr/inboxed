package news.inboxed.app.feeds;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Feeds {

    private final FeedRepository feedRepository;

    public Page<Feed> getFeeds(Pageable pageable) {
        return feedRepository.findAll(pageable);
    }

}
