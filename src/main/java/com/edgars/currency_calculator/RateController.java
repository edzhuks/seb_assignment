package com.edgars.currency_calculator;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RateController {

    private final RateRepository rateRepository;

    RateController(RateRepository rateRepository){
        this.rateRepository = rateRepository;
    }
    
    @GetMapping(path = "/rates", produces=MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Rate>> all(){
        return new ResponseEntity<>(rateRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/latest", produces=MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Rate>> latest(){
        return new ResponseEntity<>(rateRepository.findLatestOfEachCurrency(), HttpStatus.OK);
    }    

@GetMapping(path = "/byName/{name}", produces=MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Rate>> latest(@PathVariable String name){
        return new ResponseEntity<>(rateRepository.findByName(name), HttpStatus.OK);
    }    


}
