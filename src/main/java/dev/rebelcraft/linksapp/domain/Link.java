package dev.rebelcraft.linksapp.domain;

import java.net.URL;
import java.time.Instant;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("LINKS")
public record Link(@Id Long id, URL url, String notes, Set<String> tags, @CreatedDate Instant createdDate) {
  public Link {
    if (notes == null) {
      notes = "";
    }
    if (tags == null) {
      tags = Set.of();
    }
  }
}
