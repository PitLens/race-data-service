package io.pitlens.racedataservice.race.service;

import io.pitlens.contracts.race.v1.LapCompleted;
import io.pitlens.racedataservice.kafka.mapper.LapCompletedProtoMapper;
import io.pitlens.racedataservice.kafka.producer.LapCompletedProducer;
import io.pitlens.racedataservice.openf1.client.OpenF1Client;
import io.pitlens.racedataservice.openf1.dto.OpenF1LapDto;
import io.pitlens.racedataservice.openf1.mapper.OpenF1LapMapper;
import io.pitlens.racedataservice.race.model.LapCompletedEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LapIngestionServiceTest {

    @Mock
    private OpenF1Client openF1Client;

    @Mock
    private OpenF1LapMapper openF1LapMapper;

    @Mock
    private LapCompletedProtoMapper lapCompletedProtoMapper;

    @Mock
    private LapCompletedProducer lapCompletedProducer;

    @InjectMocks
    private LapIngestionService lapIngestionService;

    @Test
    void shouldFetchMapAndPublishLaps() {
        long sessionKey = 9161L;

        OpenF1LapDto dto = mock(OpenF1LapDto.class);
        LapCompletedEvent event = mock(LapCompletedEvent.class);
        LapCompleted proto = mock(LapCompleted.class);

        when(openF1Client.getLaps(sessionKey))
                .thenReturn(List.of(dto));

        when(openF1LapMapper.map(dto))
                .thenReturn(event);

        when(lapCompletedProtoMapper.map(event))
                .thenReturn(proto);

        lapIngestionService.ingestSession(sessionKey);

        verify(openF1Client).getLaps(sessionKey);
        verify(openF1LapMapper).map(dto);
        verify(lapCompletedProtoMapper).map(event);
        verify(lapCompletedProducer).send(proto);
    }
}
