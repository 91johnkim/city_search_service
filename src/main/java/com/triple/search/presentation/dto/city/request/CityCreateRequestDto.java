package com.triple.search.presentation.dto.city.request;

import com.triple.search.domain.city.City;
import com.triple.search.domain.country.Country;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class CityCreateRequestDto {

    @NotBlank(message = "국가코드는 필수입니다")
    @Size(max=2, min = 2,message = "2자리여야 합니다")
    private String countryId;

    @NotBlank(message = "도시명은 필수입니다")
    private String cityName;

    public City toEntity(){
        return City.builder()
                   .country(Country.builder().id(countryId).build())
                   .name(cityName)
                   .build();
    }

}
