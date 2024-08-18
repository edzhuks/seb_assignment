package com.edgars.currency_calculator;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RateRepository extends JpaRepository<Rate, Long> {

    @Query("SELECT t\r\n" + //
            "FROM Rate t\r\n" + //
            "      Inner join (SELECT name as rname, MAX(date) as maxDate\r\n" + //
            "      FROM Rate \r\n" + //
            "      GROUP BY rname\r\n" + //
            ") r\r\n" + //
            "ON t.name = r.rname AND t.date = r.maxDate")
    List<Rate> findLatestOfEachCurrency();

    List<Rate> findByName(String name);

}
