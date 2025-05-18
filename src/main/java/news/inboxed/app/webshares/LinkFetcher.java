package news.inboxed.app.webshares;

import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class LinkFetcher {

  private final Links links;
  private final ApplicationEventPublisher applicationEventPublisher;

  @Async
  @TransactionalEventListener(fallbackExecution = true)
  public void handleLinkCreatedEvent(LinkCreatedEvent event) {

    Link createdLink = (Link) event.getSource();

    FetchedLinkData fetchedLinkData = new FetchedLinkData(null, null, null, "Unknown error");

    try {

      URL url = createdLink.url();
      log.info("Fetching data from URL: {}", url);

      // Use Jsoup to fetch the HTML content
      Document document = Jsoup.connect(url.toString()).get();
      String title = document.title();
      log.info("Fetched title: {}", title);

      fetchedLinkData = new FetchedLinkData(title, document.html());

    } catch (IOException e) {

      log.error("Failed to fetch data from URL: {}", createdLink.url(), e);

      fetchedLinkData = new FetchedLinkData(e.getMessage());

    }

    Link updatedLink = createdLink.withFetchedLinkData(fetchedLinkData);

    updatedLink = links.updateLink(updatedLink);

    if(updatedLink.wasFetched()) {

      applicationEventPublisher.publishEvent(new FetchedLinkDataCreatedEvent(updatedLink));

    }

  }

}
