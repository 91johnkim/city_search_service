package com.triple.search.presentation.dto.trip.response;

import com.triple.search.domain.city.City;
import com.triple.search.domain.trip.Trip;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TripResponseDto {

    private City city;
    private LocalDate startedAt;
    private LocalDate endedAt;

    public TripResponseDto(Trip trip){
        this.city = trip.getCity();
        this.startedAt = trip.getStartedAt();
        this.endedAt = trip.getEndedAt();
    }

    public static TripResponseDto create(Trip trip){
        return new TripResponseDto(trip);
    }
}
