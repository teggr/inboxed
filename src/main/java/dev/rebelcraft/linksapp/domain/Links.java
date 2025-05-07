package dev.rebelcraft.linksapp.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

@Component
public class Links {

    public Link createNew( String url ) {
        return new Link(url);
    }

    public Page<Link> getLinks() {
        return new PageImpl<>(List.of(
            new Link("http://localhost:8181")
        ));
    }

    public Link getLink(String url) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLink'");
    }

    public Link update(Link link) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
