package com.triple.search.domain.city;

import com.triple.search.domain.BaseEntity;
import com.triple.search.domain.country.Country;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@ToString
@Entity
public class City extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(columnDefinition = "datetime")
    private LocalDateTime selectedAt;

    @OneToOne
    @JoinColumn(name="country_id", referencedColumnName = "id", nullable = false)
    private Country country = null;

    @Builder
    public City(String name, Country country){
        this.country = country;
        this.name = name;
    }

    public void setSelectedAt(){
        this.selectedAt = LocalDateTime.now();
    }

}
