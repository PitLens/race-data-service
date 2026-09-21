package io.pitlens.racedataservice.kafka.mapper;

import io.pitlens.contracts.race.v1.LapCompleted;
import io.pitlens.racedataservice.race.model.LapCompletedEvent;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
public class LapCompletedProtoMapper {

    public LapCompleted map(LapCompletedEvent event) {
        return LapCompleted.newBuilder()
                .setSessionId(event.sessionId())
                .setDriverNumber(event.driverNumber())
                .setLapNumber(event.lapNumber())
                .setLapTime(toProtoDuration(event.lapTime()))
                .setOccurredAt(toProtoTimestamp(event.occurredAt()))
                .build();
    }

    private com.google.protobuf.Duration toProtoDuration(Duration duration) {
        return com.google.protobuf.Duration.newBuilder().setSeconds(duration.getSeconds()).setNanos(duration.getNano()).build();
    }

    private com.google.protobuf.Timestamp toProtoTimestamp(Instant instant) {
        return com.google.protobuf.Timestamp.newBuilder().setSeconds(instant.getEpochSecond()).setNanos(instant.getNano()).build();
    }

}
