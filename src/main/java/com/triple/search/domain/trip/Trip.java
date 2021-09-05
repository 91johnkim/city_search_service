package com.triple.search.domain.trip;

import com.triple.search.domain.BaseEntity;
import com.triple.search.domain.city.City;
import com.triple.search.domain.country.Country;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.ValidationException;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "Trip")
public class Trip extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name="city_id", referencedColumnName = "id", nullable = false)
    private City city;

    @Column(nullable = false ,columnDefinition = "date")
    private LocalDate startedAt;

    @Column(nullable = false , columnDefinition = "date")
    private LocalDate endedAt;

    @Builder
    public Trip(City city, LocalDate startedAt , LocalDate endedAt){
        this.city = city;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    public void validation(){
        if(startedAt.compareTo(LocalDate.now()) < 0){
            throw new ValidationException("과거일자는 등록할 수 없습니다. 여행시작일자 : "+startedAt + " 현재일자 :"+LocalDate.now());
        }
    }

    public void setCity(City city){
        this.city = city;
    }

}
