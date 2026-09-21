package io.pitlens.racedataservice.kafka.producer;

import io.pitlens.contracts.race.v1.LapCompleted;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Component
public class LapCompletedProducer {

    private final KafkaTemplate<String, LapCompleted> kafkaTemplate;

    @Value("${pitlens.kafka.topics.race-laps}")
    private String topic;

    public CompletableFuture<SendResult<String, LapCompleted>> send(LapCompleted event) {
        String key = buildKey(event);

        return kafkaTemplate.send(topic, key, event);
    }

    private String buildKey(LapCompleted event) {
        return event.getSessionId() + ":" + event.getDriverNumber();
    }

}
