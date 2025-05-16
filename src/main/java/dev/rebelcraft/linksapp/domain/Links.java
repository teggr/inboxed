package dev.rebelcraft.linksapp.domain;

import java.net.URL;
import java.time.Instant;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Links {

    private final LinksRepository linksRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public Link createNew(Link link) {
        Link savedLink = linksRepository.save(link);
        publishEvent(savedLink);
        return savedLink;
    }

    private void publishEvent(Link savedLink) {
        applicationEventPublisher.publishEvent(new LinkCreatedEvent(savedLink));
    }

    public Page<Link> getLinks() {
        return linksRepository.findAll(Pageable.ofSize(10).withPage(0));
    }

    public Link getLink(URL url) {
        return linksRepository.findByUrl(url);
    }

    public Instant getLastUpdated() {
        return linksRepository.findFirstByOrderByCreatedDateDesc().map(Link::createdDate).orElse(Instant.now());
    }

    public Link updateLink(Link updatedLink) {
	    Link savedLink = linksRepository.save(updatedLink);
        return savedLink;
    }

}
