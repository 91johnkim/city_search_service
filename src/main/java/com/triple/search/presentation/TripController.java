package com.triple.search.presentation;

import com.triple.search.application.TripService;
import com.triple.search.presentation.dto.trip.request.TripCreateRequestDto;
import com.triple.search.presentation.dto.trip.response.TripResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trip")
public class TripController {

    private final TripService tripService;

    @GetMapping("/{trip_id}")
    public TripResponseDto search(@PathVariable("trip_id") Long tripId){
        return TripResponseDto.create(tripService.findById(tripId));
    }

    @PostMapping
    public void save(@Valid @RequestBody TripCreateRequestDto tripRequestDto) {
        tripService.save(tripRequestDto.toEntity());
    }
}
