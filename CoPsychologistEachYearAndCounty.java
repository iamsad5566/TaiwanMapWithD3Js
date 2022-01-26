package com.twyk.myWebApplication.database.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoPsychologistEachYearAndCounty {
    @Id
    @GeneratedValue(strategy=SEQUENCE, generator="CUST_SEQ")
    private long id;

    @JsonProperty("year")
    private short year;

    @JsonProperty("county")
    private String county;

    @JsonProperty("numbers")
    private int numbers;

    public CoPsychologistEachYearAndCounty(short year, String county, int numbers) {
        this.county = county;
        this.year = year;
        this.numbers = numbers;
    }
}
