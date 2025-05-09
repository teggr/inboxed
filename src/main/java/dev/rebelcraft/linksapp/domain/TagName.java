package dev.rebelcraft.linksapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("TAG_NAMES")
public record TagName(@Id String name) {
    
}
