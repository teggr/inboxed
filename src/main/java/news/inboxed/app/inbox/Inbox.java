package news.inboxed.app.inbox;

import static dev.rebelcraft.j2html.bootstrap.Bootstrap.initialism;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class Inbox {

  private final InboxItemRepository inboxItemRepository;

  public Page<InboxItem> getInboxItems( Pageable pageable ) {

    return inboxItemRepository.findAll( pageable );

  }

  public void add(InboxItem inboxItem) {

    log.info("InboxItem added: {}", inboxItem);
    
    inboxItemRepository.save(inboxItem);
  
  }

  public int getNewItemCount() {

    return inboxItemRepository.countByReadFalse();

  }

}
