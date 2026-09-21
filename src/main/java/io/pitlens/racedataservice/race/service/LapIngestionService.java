package io.pitlens.racedataservice.race.service;

import io.pitlens.racedataservice.kafka.mapper.LapCompletedProtoMapper;
import io.pitlens.racedataservice.kafka.producer.LapCompletedProducer;
import io.pitlens.racedataservice.openf1.client.OpenF1Client;
import io.pitlens.racedataservice.openf1.dto.OpenF1LapDto;
import io.pitlens.racedataservice.openf1.mapper.OpenF1LapMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LapIngestionService {

    private final OpenF1Client openF1Client;
    private final OpenF1LapMapper openF1LapMapper;
    private final LapCompletedProtoMapper protoMapper;
    private final LapCompletedProducer producer;

    public void ingestSession(long sessionKey) {
        List<OpenF1LapDto> laps = openF1Client.getLaps(sessionKey);
        List<OpenF1LapDto> completedLaps = laps.stream()
                .filter(this::isCompletedLap)
                .toList();

        log.info(
                "Fetched {} laps for session {}, {} are complete",
                laps.size(),
                sessionKey,
                completedLaps.size()
        );

        completedLaps.stream()
                .map(openF1LapMapper::map)
                .map(protoMapper::map)
                .forEach(producer::send);
    }

    private boolean isCompletedLap(OpenF1LapDto lap) {
        return lap.lapDuration() != null && lap.dateStart() != null;
    }

}
