package io.pitlens.racedataservice.openf1.mapper;

import io.pitlens.racedataservice.openf1.dto.OpenF1LapDto;
import io.pitlens.racedataservice.race.model.LapCompletedEvent;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
public class OpenF1LapMapper {

    public LapCompletedEvent map(OpenF1LapDto openF1LapDto) {
        Duration lapTime = Duration.ofMillis(
                Math.round(openF1LapDto.lapDuration() * 1000));

        Instant startTime = Instant.parse(openF1LapDto.dateStart());

        return LapCompletedEvent.builder()
                .sessionId(openF1LapDto.sessionKey())
                .driverNumber(openF1LapDto.driverNumber())
                .lapNumber(openF1LapDto.lapNumber())
                .lapTime(lapTime)
                .occurredAt(startTime.plus(lapTime))
                .build();
    }

}
