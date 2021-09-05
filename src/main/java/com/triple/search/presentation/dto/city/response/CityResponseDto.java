package com.triple.search.presentation.dto.city.response;

import com.triple.search.domain.city.City;
import lombok.Getter;

@Getter
public class CityResponseDto {

    private String countryId;
    private String countryKorName;
    private Long cityId;
    private String cityName;


    public CityResponseDto(City city){
        this.countryId = city.getCountry().getId();
        this.countryKorName = city.getCountry().getKorName();
        this.cityId = city.getId();
        this.cityName = city.getName();
    }

    public static CityResponseDto create(City city){
        return new CityResponseDto(city);
    }


}
