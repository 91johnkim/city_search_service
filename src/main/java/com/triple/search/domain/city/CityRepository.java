package com.triple.search.domain.city;


import com.triple.search.domain.trip.Trip;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City,Long> {

    Optional<City> findByName(String name);

    List<City> findAllByIdIsNotIn(List<Long> ids, Pageable pageable);

    @Query("select c from Trip t inner join t.city c on t.city.id = c.id where t.startedAt <= :currentDate and t.endedAt >= :currentDate order by t.startedAt desc")
    List<City> findAllByOnTrip(@Param("currentDate") LocalDate currentDate , Pageable pageable);

    @Query("select c from Trip t inner join t.city c on t.city.id = c.id where t.city.id not in(:ids) and t.startedAt < :currentDate")
    List<City> findAllByNotOnTrip(@Param("ids") List<Long> ids ,  @Param("currentDate") LocalDate currentDate  , Pageable pageable);


    @Query("select c from City c where c.id not in(:ids) and c.createdAt >= :date order by c.createdAt desc")
    List<City> findAllByCityFromCreatedAt(@Param("ids") List<Long> ids , @Param("date") LocalDateTime date, Pageable pageable);

    @Query("select c from City c where c.id not in(:ids) and c.selectedAt >= :date order by c.selectedAt desc")
    List<City> findAllByCityFromSelectedAt(@Param("ids") List<Long> ids , @Param("date") LocalDate date , Pageable pageable);
}
