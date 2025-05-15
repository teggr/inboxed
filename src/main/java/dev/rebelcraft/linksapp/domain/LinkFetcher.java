package dev.rebelcraft.linksapp.domain;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class LinkFetcher {

  @TransactionalEventListener(fallbackExecution = true)
  public void handleLinkCreatedEvent(LinkCreatedEvent event) {

    Link createdLink = (Link) event.getSource();
   
   // TODO: fetch the url and store the data, then move onto the next part

   
  
  }

}
