package news.inboxed.app.feeds.publisher;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import lombok.RequiredArgsConstructor;
import news.inboxed.app.feeds.Feed;
import news.inboxed.app.feeds.FeedUpdatedEvent;
import news.inboxed.app.feeds.content.FeedContent;
import news.inboxed.app.feeds.content.FeedContentRepository;

@Component
@RequiredArgsConstructor
public class NewEntryPublisher {

  private final FeedContentRepository feedContentRepository;

  @Async
  @TransactionalEventListener(fallbackExecution = true)
  public void handleFeedUpdatedEvent(FeedUpdatedEvent feedUpdatedEvent) {

    Feed feed = (Feed) feedUpdatedEvent.getSource();

    // TODO: what's the latest items from the feed
    feedContentRepository.findByFeedId(feed.feedId());

  }

}
