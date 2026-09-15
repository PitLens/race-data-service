package io.pitlens.racedataservice.openf1.client;

import io.pitlens.racedataservice.openf1.dto.OpenF1LapDto;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@AllArgsConstructor
@Component
public class OpenF1Client {

    private final RestClient restClient;

    public List<OpenF1LapDto> getLaps(long sessionKey) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/laps")
                        .queryParam("session_key", sessionKey)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
