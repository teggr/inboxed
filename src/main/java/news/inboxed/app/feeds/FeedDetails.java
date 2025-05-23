package news.inboxed.app.feeds;

import java.util.Date;

public interface FeedDetails {

String getId();

    String getTitle();

    Date getPublishedDate();

    FeedDetailsLinks getLinks();

}
