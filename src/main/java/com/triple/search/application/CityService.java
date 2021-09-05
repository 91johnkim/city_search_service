package com.triple.search.application;

import com.triple.common.exception.AlreadyExistsException;
import com.triple.common.exception.NotFoundException;
import com.triple.search.domain.city.City;
import com.triple.search.domain.city.CityRepository;
import com.triple.search.domain.country.Country;
import com.triple.search.domain.country.CountryRepository;
import com.triple.search.domain.trip.TripRepository;
import com.triple.search.presentation.dto.city.request.CityCreateRequestDto;
import com.triple.search.presentation.dto.city.response.CityResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final TripRepository tripRepository;


    public City findByName(String name){
        City city = cityRepository.findByName(name).orElseThrow(()->new NotFoundException("도시가 존재하지 않습니다 : "+name));
        city.setSelectedAt();
        cityRepository.save(city);
        return city;
    }



    public List<City> findAll(int size){

        List<City> list = cityRepository.findAllByOnTrip(LocalDate.now() , Pageable.ofSize(size));

        if(list.size() < size){
            List<Long> ids = list.stream().map(obj->obj.getId()).collect(Collectors.toList());
            list.addAll(cityRepository.findAllByNotOnTrip(ids,LocalDate.now(), Pageable.ofSize(size- list.size())));
        }

        if(list.size() < size){
            List<Long> ids = list.stream().map(obj->obj.getId()).collect(Collectors.toList());
            list.addAll(cityRepository.findAllByCityFromCreatedAt(ids, LocalDateTime.now().minusDays(1) , Pageable.ofSize(size-list.size())));
        }

        if(list.size() < size){
            List<Long> ids = list.stream().map(obj->obj.getId()).collect(Collectors.toList());
            list.addAll(cityRepository.findAllByCityFromSelectedAt(ids, LocalDate.now().minusWeeks(1), Pageable.ofSize(size- list.size())));
        }

        if(list.size() < size){
            List<Long> ids = list.stream().map(obj->obj.getId()).collect(Collectors.toList());
            list.addAll(cityRepository.findAllByIdIsNotIn(ids, Pageable.ofSize(size-list.size())));
        }

        return list;
    }

    @Transactional
    public synchronized void save(City city){
        countryRepository.findById(city.getCountry().getId()).orElseThrow(()-> new NotFoundException("국가코드가 존재하지 않습니다 : "+city.getCountry().getId()));
        if(!cityRepository.findByName(city.getName()).isEmpty()){
            throw new AlreadyExistsException("이미 존재하는 도시입니다 : " + city.getName());
        }
        cityRepository.save(city);
    }
}
