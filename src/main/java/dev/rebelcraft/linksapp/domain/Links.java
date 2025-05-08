package dev.rebelcraft.linksapp.domain;

import java.net.URL;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Links {

    private final LinksMapper linksMapper;

    public Link createNew( String url ) {
        Link link = new Link(url);
        linksMapper.insertLink(link);
        return link;
    }

    public Page<Link> getLinks() {
        return new PageImpl<>(linksMapper.getAllLinks());
    }

    public Link getLink(URL url) {
        return linksMapper.getLinkByUrl(url);
    }

    public Link update(Link link) {
        linksMapper.updateLink(link);
        return link;
    }

}
