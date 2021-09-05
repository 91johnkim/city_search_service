package com.triple.search.presentation.dto.trip.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.triple.search.domain.city.City;
import com.triple.search.domain.trip.Trip;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class TripCreateRequestDto {

    @NotBlank(message = "도시명은 필수입니다")
    private String cityName;

    @NotNull(message = "여행시작일자는 필수입니다")
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDate startedAt;

    @NotNull(message = "여행종료일자는 필수입니다")
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDate endedAt;

    public Trip toEntity(){
        return Trip.builder()
                .city(City.builder().name(cityName).build())
                .startedAt(startedAt)
                .endedAt(endedAt)
                .build();
    }

}
