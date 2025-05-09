package dev.rebelcraft.linksapp.domain;

import java.net.URL;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Links {

    private final LinksRepository linksRepository;

    public Link createNew( URL url ) {
        Link link = new Link(null, url, "some notes", Set.of(new Tag(null, "tag1"), new Tag(null, "tag2")));
        return linksRepository.save(link);
    }

    public Page<Link> getLinks() {
        return linksRepository.findAll(Pageable.ofSize(10).withPage(0));
    }

    public Link getLink(URL url) {
        return linksRepository.findByUrl(url);
    }

    public Link update(Link link) {
        return linksRepository.save(link);
    }

}
