package dev.rebelcraft.linksapp.domain;

import java.net.URL;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("LINKS")
public record Link(@Id Long id, URL url) {

}
