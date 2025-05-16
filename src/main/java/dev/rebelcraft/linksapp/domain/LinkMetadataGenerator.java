package dev.rebelcraft.linksapp.domain;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LinkMetadataGenerator {

  private final Links links;

  @TransactionalEventListener(fallbackExecution = true)
  public void handleFetchLinkDataCreated(FetchedLinkDataCreatedEvent fetchedLinkDataCreatedEvent) {

    Link updatedLink = (Link) fetchedLinkDataCreatedEvent.getSource();

    Document document = Jsoup.parse( updatedLink.fetchedLinkData().html() );

    LinkMetaData linkMetaData = new LinkMetaData(document.title(), null);

    updatedLink = updatedLink.withLinkMetaData(linkMetaData);

    updatedLink = links.updateLink(updatedLink);

  }

}
