package io.pitlens.racedataservice.openf1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OpenF1LapDto(

        @JsonProperty("session_key")
        Long sessionKey,

        @JsonProperty("meeting_key")
        Long meetingKey,

        @JsonProperty("driver_number")
        Integer driverNumber,

        @JsonProperty("lap_number")
        Integer lapNumber,

        @JsonProperty("lap_duration")
        Double lapDuration,

        @JsonProperty("date_start")
        String dateStart

) {
}
