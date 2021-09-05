package com.triple.search.application;

import com.triple.common.exception.AlreadyExistsException;
import com.triple.common.exception.NotFoundException;
import com.triple.search.domain.city.City;
import com.triple.search.domain.city.CityRepository;
import com.triple.search.domain.trip.Trip;
import com.triple.search.domain.trip.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final CityRepository cityRepository;

    public Trip findById(Long id){
        return tripRepository.findById(id).orElseThrow(() -> new NotFoundException("여행ID가 존재하지 않습니다 : "+id));
    }

    @Transactional
    public synchronized void save(Trip trip){

        trip.validation();
        City city = cityRepository.findByName(trip.getCity().getName()).orElseThrow(()-> new NotFoundException("도시가 존재하지 않습니다 : "+trip.getCity().getName()));
        if(!tripRepository.findByCityId(city.getId()).isEmpty()){
            throw new AlreadyExistsException("이미 등록된 일정이 있습니다 : "+city.getName());
        }
        trip.setCity(city);
        tripRepository.save(trip);
    }
}
