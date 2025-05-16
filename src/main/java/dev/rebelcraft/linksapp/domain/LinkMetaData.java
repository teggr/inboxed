package dev.rebelcraft.linksapp.domain;

import java.time.Instant;

/**
 * MetaData
 */
public record LinkMetaData(String title, Instant createdDate) {

  public LinkMetaData {
    if (createdDate == null) {
      createdDate = Instant.now();
    }
  }

}
