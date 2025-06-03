package dev.feedhub.app.feeds;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Feeds {

  private final FeedRepository feedRepository;

  public List<Feed> getFeeds() {
    return feedRepository.findAll();
  }

}
