package io.pitlens.racedataservice.openf1.mapper;

import io.pitlens.racedataservice.openf1.dto.OpenF1LapDto;
import io.pitlens.racedataservice.race.model.LapCompletedEvent;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OpenF1LapMapperTest {

    private final OpenF1LapMapper openF1LapMapper = new OpenF1LapMapper();

    @Test
    public void toOpenF1LapDto() {
        // Given
        OpenF1LapDto openF1LapDto = new OpenF1LapDto(
                1190L,
                23L,
                23,
                2,
                35.7,
                "2023-09-16T13:59:07.606Z"
        );

        LapCompletedEvent lapCompletedEvent = openF1LapMapper.map(openF1LapDto);

        assertEquals(1190L, lapCompletedEvent.sessionId());
        assertEquals(23, lapCompletedEvent.driverNumber());
        assertEquals(2, lapCompletedEvent.lapNumber());
        assertEquals(Duration.ofMillis(35700), lapCompletedEvent.lapTime());
    }
}
