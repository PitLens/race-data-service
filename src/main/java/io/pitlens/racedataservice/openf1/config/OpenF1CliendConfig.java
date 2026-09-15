package io.pitlens.racedataservice.openf1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class OpenF1CliendConfig {

    @Bean
    public RestClient openF1RestClient(
            RestClient.Builder builder,
            @Value("${openf1.base-url}") String baseUrl
    ) {
        return builder
                .baseUrl(baseUrl)
                .build();
    }

}
