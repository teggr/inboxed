package dev.rebelcraft.linksapp.domain;

public record TwitterMetaData(
    String title,
    String description,
    String url,
    String site,
    String creator,
    String card,
    String image
) {}