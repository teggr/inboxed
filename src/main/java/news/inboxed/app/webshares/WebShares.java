package news.inboxed.app.webshares;

import java.net.URL;
import java.time.Instant;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WebShares {

    private final WebShareRepository webShareRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public WebShare addWebShare(WebShare webShare) {
        WebShare savedWebShare = webShareRepository.save(webShare);
        publishWebShareAddedEvent(savedWebShare);
        return savedWebShare;
    }

    private void publishWebShareAddedEvent(WebShare webShare) {
        applicationEventPublisher.publishEvent(new WebShareAddedEvent(webShare));
    }

    public Page<WebShare> getWebShares() {
        return webShareRepository.findAll(Pageable.ofSize(10).withPage(0));
    }

    public WebShare getWebShare(URL url) {
        return webShareRepository.findByUrl(url);
    }

    public Instant getLastUpdated() {
        return webShareRepository.findFirstByOrderByCreatedDateDesc().map(WebShare::createdDate).orElse(Instant.now());
    }

    public WebShare updateWebShare(WebShare updatedWebShare) {
	    WebShare savedWebShare = webShareRepository.save(updatedWebShare);
        return savedWebShare;
    }

}
