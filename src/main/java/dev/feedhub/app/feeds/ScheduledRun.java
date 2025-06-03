package dev.feedhub.app.feeds;

import java.time.Instant;

/**
 * ScheduledRun
 */
public record ScheduledRun(Instant scheduledUpdate, ScheduledRunResult result) {

}
