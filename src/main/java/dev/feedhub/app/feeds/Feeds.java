package dev.feedhub.app.feeds;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Feeds {

  private final FeedRepository feedRepository;
  private final FeedItemRepository feedItemRepository;

  public List<Feed> getFeeds() {
    
    return feedRepository.findAll();

  }

  public void update(Feed feed, List<FeedItem> feedItems) {

    feedRepository.save(feed);

    feedItemRepository.saveAll(feedItems);

  }

  public Page<Feed> getFeeds(Pageable pageable) {
    
    return feedRepository.findAll(pageable);

  }

}
