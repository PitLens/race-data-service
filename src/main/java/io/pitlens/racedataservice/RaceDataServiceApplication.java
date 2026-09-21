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

}
