package com.twyk.myWebApplication.repository;

import com.twyk.myWebApplication.database.bean.CoPsychologistEachYearAndCounty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CoPsychologistEachYearAndCountyRepository extends JpaRepository<CoPsychologistEachYearAndCounty, Long> {
    @Query(value = "SELECT numbers FROM co_psychologist_each_year_and_county WHERE year = :year AND county = :county", nativeQuery = true)
    int selectByYearAndCounty(@Param(value = "year") short year, @Param(value = "county") String county);
}
