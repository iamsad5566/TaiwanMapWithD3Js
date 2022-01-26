package com.twyk.myWebApplication.repository;

import com.twyk.myWebApplication.database.bean.CoPsychologistEachCounty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CoPsychologistEachCountyRepository extends JpaRepository<CoPsychologistEachCounty, String> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE co_psychologist_each_county SET numbers = :newNumbers WHERE county = :county", nativeQuery = true)
    void updateNumbers(@Param(value="county") String county, @Param(value="newNumbers") int numbers);
}
