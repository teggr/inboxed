package dev.feedhub.app.inbox;

import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import news.inboxed.app.inbox.Inbox;

@Component
@RequiredArgsConstructor
public class FeedItemInboxPublisher {

  private final Inbox inbox;

  // publish to inbox

}
