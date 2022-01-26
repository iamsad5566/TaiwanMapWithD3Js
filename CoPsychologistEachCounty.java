package com.twyk.myWebApplication.database.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoPsychologistEachCounty {
    @Id
    @JsonProperty("county")
    private String county;

    @JsonProperty("numbers")
    private int numbers;
}
