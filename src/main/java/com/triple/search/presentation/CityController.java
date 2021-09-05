package com.triple.search.presentation;

import com.triple.search.application.CityService;
import com.triple.search.presentation.dto.city.request.CityCreateRequestDto;
import com.triple.search.presentation.dto.city.response.CityResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/city")
public class CityController {

    private final CityService cityService;

    @GetMapping("/{city_name}")
    public CityResponseDto search(@PathVariable("city_name") String cityName){
        return CityResponseDto.create(cityService.findByName(cityName));
    }

    @GetMapping("/list")
    public List<CityResponseDto> list(){
        return cityService.findAll(10).stream().map(it-> CityResponseDto.create(it)).toList();
    }


    @PostMapping
    public void save(@Valid @RequestBody CityCreateRequestDto cityCreateRequestDto){
        cityService.save(cityCreateRequestDto.toEntity());
    }

}
