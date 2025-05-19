package news.inboxed.app.inbox;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Inbox {

  private final InboxItemRepository inboxItemRepository;

  public Page<InboxItem> getInboxItems( Pageable pageable ) {

    return inboxItemRepository.findAll( pageable );

  }

}
