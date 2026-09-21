package io.pitlens.racedataservice.kafka.mapper;

import io.pitlens.contracts.race.v1.LapCompleted;
import io.pitlens.racedataservice.race.model.LapCompletedEvent;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LapCompletedProtoMapperTest {

    private final LapCompletedProtoMapper mapper = new LapCompletedProtoMapper();

    @Test
    void shouldMapLampCompletedEventToProto() {
        var event= new LapCompletedEvent(
                9161L,
                1,
                42,
                Duration.ofMillis(83_456),
                Instant.parse("2023-03-05T15:42:17.123Z")
        );

        LapCompleted result = mapper.map(event);

        assertEquals(9161L, result.getSessionId());
        assertEquals(1, result.getDriverNumber());
        assertEquals(42, result.getLapNumber());

        assertEquals(83, result.getLapTime().getSeconds());
        assertEquals(456_000_000, result.getLapTime().getNanos());

        assertEquals(
                event.occurredAt().getEpochSecond(),
                result.getOccurredAt().getSeconds()
        );

        assertEquals(
                event.occurredAt().getNano(),
                result.getOccurredAt().getNanos()
        );
    }

}
