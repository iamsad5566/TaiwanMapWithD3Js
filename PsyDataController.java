package com.twyk.myWebApplication.controller;

import com.twyk.myWebApplication.controller.bean.PsyDataDTO;
import com.twyk.myWebApplication.database.bean.CoPsychologistEachCounty;
import com.twyk.myWebApplication.database.bean.CoPsychologistEachYearAndCounty;
import com.twyk.myWebApplication.repository.CoPsychologistEachCountyRepository;
import com.twyk.myWebApplication.repository.CoPsychologistEachYearAndCountyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@CrossOrigin(origins = {"https://www.tw-yk.website","https://tw-yk.website"})
@RequestMapping("")
public class PsyDataController {
    private CoPsychologistEachCountyRepository coPsychologistEachCountyRepository;
    private CoPsychologistEachYearAndCountyRepository coPsychologistEachYearAndCountyRepository;

    public PsyDataController(CoPsychologistEachCountyRepository coPsychologistEachCountyRepository, CoPsychologistEachYearAndCountyRepository coPsychologistEachYearAndCountyRepository) {
        this.coPsychologistEachCountyRepository = coPsychologistEachCountyRepository;
        this.coPsychologistEachYearAndCountyRepository = coPsychologistEachYearAndCountyRepository;
    }

    @GetMapping("/getTotalNumbers")
    public int getTotalNumbers() {
        int totalNumbers = 0;
        for(CoPsychologistEachCounty county:coPsychologistEachCountyRepository.findAll())
            totalNumbers += county.getNumbers();

        return totalNumbers;
    }

    @GetMapping("/getTotalNumbersEachCounty")
    public int getTotalNumberEachCounty(@RequestParam("county") String county) {
        if(!coPsychologistEachCountyRepository.existsById(county))
            return 0;

        return coPsychologistEachCountyRepository.getById(county).getNumbers();
    }

    @GetMapping("/getNumbersByEachYearAndCounty")
    public int getNumbersByEachYearAndCounty(@RequestParam("year") short year, @RequestParam("county") String county) {
        if(!coPsychologistEachCountyRepository.existsById(county))
            return 0;

        return coPsychologistEachYearAndCountyRepository.selectByYearAndCounty(year, county);
    }

    @GetMapping("/getNumbersByAllCounty")
    public List<CoPsychologistEachCounty> getNumbersByAllCounty() {
        return coPsychologistEachCountyRepository.findAll();
    }

    @PostMapping("/saveData")
    public ResponseEntity saveData(@RequestBody PsyDataDTO psyDataDTO) {
        CoPsychologistEachYearAndCounty newData = new CoPsychologistEachYearAndCounty(Short.valueOf(psyDataDTO.getYear()), psyDataDTO.getCounty(), Integer.valueOf(psyDataDTO.getNumbers()));
        coPsychologistEachYearAndCountyRepository.save(newData);
        if(coPsychologistEachCountyRepository.existsById(psyDataDTO.getCounty())) {
            int newNumber = Integer.valueOf(psyDataDTO.getNumbers());
            newNumber += coPsychologistEachCountyRepository.getById(psyDataDTO.getCounty()).getNumbers();
            coPsychologistEachCountyRepository.updateNumbers(psyDataDTO.getCounty(), newNumber);
        } else {
            coPsychologistEachCountyRepository.save(new CoPsychologistEachCounty(psyDataDTO.getCounty(), Integer.valueOf(psyDataDTO.getNumbers())));
        }

        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/resetData")
    public ResponseEntity resetData() {
        coPsychologistEachCountyRepository.deleteAll();
        coPsychologistEachYearAndCountyRepository.deleteAll();

        return ResponseEntity.accepted().build();
    }
}
