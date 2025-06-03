package dev.webshares.app.webshares;

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
  public void handleWebShareAddedEvent(WebShareAddedEvent event) {

    WebShare newWebShare = (WebShare) event.getSource();

    WebShareData webShareData = new WebShareData(null, null, null, "Unknown error");

    try {

      URL url = newWebShare.url();
      log.info("Fetching data from URL: {}", url);

      // Use Jsoup to fetch the HTML content
      Document document = Jsoup.connect(url.toString()).get();
      String title = document.title();
      log.info("Fetched title: {}", title);

      webShareData = new WebShareData(title, document.html());

    } catch (IOException e) {

      log.error("Failed to fetch data from URL: {}", newWebShare.url(), e);

      webShareData = new WebShareData(e.getMessage());

    }

    WebShare updatedWebShare = newWebShare.withWebShareData(webShareData);

    updatedWebShare = webShares.updateWebShare(updatedWebShare);

    if(updatedWebShare.hasWebShareData()) {

      applicationEventPublisher.publishEvent(new WebShareDataUpdatedEvent(updatedWebShare));

    }

  }

}
