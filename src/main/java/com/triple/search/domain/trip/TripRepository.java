package com.triple.search.domain.trip;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface TripRepository extends JpaRepository<Trip,Long> {

    @Query("select t from Trip t where t.startedAt <= :currentDate and t.endedAt >= :currentDate order by t.startedAt desc")
    List<Trip> findAllByOnTrip(@Param("currentDate") LocalDate currentDate , Pageable pageable);

    @Query("select t from Trip t where t.city not in(:cityIds) and t.startedAt < :date")
    List<Trip> findAllByNotOnTrip(@Param("cityIds") List<Long> cityIds ,  @Param("date") LocalDate date  , Pageable pageable);

    Optional<Trip> findByCityId(Long cityId);
}
