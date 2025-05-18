package news.inboxed.app.domain;

public record TwitterMetaData(
    String title,
    String description,
    String url,
    String site,
    String creator,
    String card,
    String image
) {}