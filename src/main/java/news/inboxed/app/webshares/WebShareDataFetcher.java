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
public class WebShareDataFetcher {

  private final WebShares webShares;
  private final ApplicationEventPublisher applicationEventPublisher;

  @Async
  @TransactionalEventListener(fallbackExecution = true)
  public void handleWebShareCreatedEvent(WebShareCreatedEvent event) {

    WebShare createdWebShare = (WebShare) event.getSource();

    WebShareData fetchedWebShareData = new WebShareData(null, null, null, "Unknown error");

    try {

      URL url = createdWebShare.url();
      log.info("Fetching data from URL: {}", url);

      // Use Jsoup to fetch the HTML content
      Document document = Jsoup.connect(url.toString()).get();
      String title = document.title();
      log.info("Fetched title: {}", title);

      fetchedWebShareData = new WebShareData(title, document.html());

    } catch (IOException e) {

      log.error("Failed to fetch data from URL: {}", createdWebShare.url(), e);

      fetchedWebShareData = new WebShareData(e.getMessage());

    }

    WebShare updatedWebShare = createdWebShare.withWebShareData(fetchedWebShareData);

    updatedWebShare = webShares.updateWebShare(updatedWebShare);

    if(updatedWebShare.hasWebShareData()) {

      applicationEventPublisher.publishEvent(new WebShareDataUpdatedEvent(updatedWebShare));

    }

  }

}
