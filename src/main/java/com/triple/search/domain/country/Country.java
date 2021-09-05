package com.triple.search.domain.country;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "Country")
public class Country {


    @Id
    @Column(length = 2)
    private String id;

    @Column(nullable = false)
    private String engName;

    @Column(nullable = false)
    private String korName;

    @Builder
    public Country(String id){
        this.id = id;
    }

}
