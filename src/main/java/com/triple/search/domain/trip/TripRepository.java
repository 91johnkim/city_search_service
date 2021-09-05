package com.triple.search.domain.trip;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface TripRepository extends JpaRepository<Trip,Long> {
    Optional<Trip> findByCityId(Long cityId);
}
