package news.inboxed.app.webshares;

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