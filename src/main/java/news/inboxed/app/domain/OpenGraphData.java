package news.inboxed.app.domain;

public record OpenGraphData(
    String siteName,
    String type,
    String title,
    String description,
    String url,
    String image,
    String imageWidth,
    String imageHeight
) {}