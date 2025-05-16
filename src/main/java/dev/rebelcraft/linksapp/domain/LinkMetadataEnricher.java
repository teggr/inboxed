package dev.rebelcraft.linksapp.domain;

import java.time.Instant;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LinkMetadataEnricher {

  private final Links links;

  @TransactionalEventListener(fallbackExecution = true)
  public void handleFetchLinkDataCreated(FetchedLinkDataCreatedEvent fetchedLinkDataCreatedEvent) {

    Link updatedLink = (Link) fetchedLinkDataCreatedEvent.getSource();

    Document document = Jsoup.parse(updatedLink.fetchedLinkData().html());

    Element head = document.head();

    String ogSiteName = head.select("meta[property=og:site_name]").attr("content");
    String ogType = head.select("meta[name=og:type]").attr("content");
    String ogTitle = head.select("meta[name=og:title]").attr("content");
    String ogDescription = head.select("meta[name=og:description]").attr("content");
    String ogUrl = head.select("meta[property=og:url]").attr("content");
    String ogImage = head.select("meta[property=og:image]").attr("content");
    String ogImageWidth = head.select("meta[property=og:image:width]").attr("content");
    String ogImageHeight = head.select("meta[property=og:image:height]").attr("content");

    OpenGraphData openGraphData = new OpenGraphData(ogSiteName, ogType, ogTitle, ogDescription, ogUrl, ogImage,
        ogImageWidth, ogImageHeight);

    System.out.println("OpenGraphData: " + openGraphData);

    String twitterTitle = head.select("meta[name=twitter:title]").attr("content");
    String twitterDescription = head.select("meta[name=twitter:description]").attr("content");
    String twitterUrl = head.select("meta[name=twitter:url]").attr("content");
    String twitterSite = head.select("meta[name=twitter:site]").attr("content");
    String twitterCreator = head.select("meta[name=twitter:creator]").attr("content");
    String twitterCard = head.select("meta[name=twitter:card]").attr("content");
    String twitterImage = head.select("meta[name=twitter:image]").attr("content");

    TwitterMetaData twitterMetaData = new TwitterMetaData(twitterTitle, twitterDescription, twitterUrl, twitterSite,
        twitterCreator, twitterCard, twitterImage);

    System.out.println("TwitterMetaData: " + twitterMetaData);

    String publishedTime = head.select("meta[property=article:published_time]").attr("content");
    String modifiedTime = head.select("meta[property=article:modified_time]").attr("content");
    String expirationTime = head.select("meta[property=article:expiration_time]").attr("content");
    String author = head.select("meta[property=article:author]").attr("content");
    String section = head.select("meta[property=article:section]").attr("content");
    List<String> tags = head.select("meta[property=article:tag]").eachAttr("content");

    ArticleMetaData articleMetaData = new ArticleMetaData(publishedTime.isBlank() ? null : Instant.parse(publishedTime),
        modifiedTime.isBlank() ? null : Instant.parse(modifiedTime),
        expirationTime.isBlank() ? null : Instant.parse(expirationTime), author, section, tags);

    System.out.println("ArticleMetaData: " + articleMetaData);

    LinkMetaData linkMetaData = new LinkMetaData(document.title(), null, openGraphData, twitterMetaData,
        articleMetaData);

    updatedLink = updatedLink.withLinkMetaData(linkMetaData);

    updatedLink = links.updateLink(updatedLink);

  }

}
