package io.pitlens.racedataservice.race.model;

import lombok.Builder;

import java.time.Duration;
import java.time.Instant;

@Builder
public record LapCompletedEvent(
        Long sessionId,
        Integer driverNumber,
        Integer lapNumber,
        Duration lapTime,
        Instant occurredAt
) {
}
