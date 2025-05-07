package dev.rebelcraft.linksapp.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

@Component
public class Links {

    public Page<Link> getLinks() {
        return new PageImpl<>(List.of(
            new Link("http://localhost:8181")
        ));
    }

}
