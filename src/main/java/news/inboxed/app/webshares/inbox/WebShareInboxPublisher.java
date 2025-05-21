package news.inboxed.app.webshares.inbox;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import lombok.RequiredArgsConstructor;
import news.inboxed.app.inbox.Inbox;
import news.inboxed.app.inbox.InboxItem;
import news.inboxed.app.webshares.WebShare;
import news.inboxed.app.webshares.WebShareMetaDataUpdatedEvent;

@Component
@RequiredArgsConstructor
public class WebShareInboxPublisher {

  private final Inbox inbox;

  // listen for internal events, publish to the inbox when meta data is ready
  @TransactionalEventListener(fallbackExecution = true)
  public void handleWebShareMetaDataUpdatedEvent(WebShareMetaDataUpdatedEvent webShareMetaDataUpdatedEvent) {

    WebShare webShare = (WebShare) webShareMetaDataUpdatedEvent.getSource();

    // do we need an intermediary route?

    inbox.add( new InboxItem(
      null,
      "webshare",
      "Web Shares",
      webShare.url().toString(),
      webShare.webShareMetaData().title(),
      List.of(),
      webShare.webShareMetaData().title(),
      Instant.now(),
      false,
      false,
      webShare.webShareMetaData().twitterMetaData().image(),
      webShare.tags().stream().toList()
    ) );

  }

}
