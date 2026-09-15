package io.pitlens.racedataservice;

import io.pitlens.racedataservice.openf1.client.OpenF1Client;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RaceDataServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RaceDataServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner testOpenF1(OpenF1Client  openF1Client) {
        return args -> {
            var laps = openF1Client.getLaps(9161);

            System.out.println("Received laps: " + laps.size());

            laps.stream()
                    .limit(5)
                    .forEach(System.out::println);
        };
    }

}
