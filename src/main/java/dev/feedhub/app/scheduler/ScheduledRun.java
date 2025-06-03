package dev.feedhub.app.scheduler;

import java.time.Instant;

/**
 * ScheduledRun
 */
public record ScheduledRun(Instant scheduledUpdate, ScheduledRunResult result) {

}
