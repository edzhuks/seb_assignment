package com.edgars.currency_calculator;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import com.edgars.currency_calculator.util.ExchangeRates;
import com.edgars.currency_calculator.util.ExchangeRate;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Configuration
class LoadDatabase {

  @Bean
  CommandLineRunner initDatabase(RateRepository rateRepository) {

    return args -> {
      LocalDate end = LocalDate.of(2024, 8, 1);
      for (LocalDate i = LocalDate.of(2024, 1, 1); i.isBefore(end); i = i.plusDays(1)) {
        RestClient restClient = RestClient.create();
        String result = restClient.get()
            .uri("https://www.lb.lt/webservices/FxRates/FxRates.asmx/getFxRates?tp=EU&dt=" + i.toString())
            .retrieve()
            .body(String.class);
        // System.out.println(result);
        XmlMapper mapper = new XmlMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        ExchangeRates exchangeRates = mapper.readValue(result, ExchangeRates.class);

        for (ExchangeRate exchangeRate : exchangeRates.getExchangeRates()) {
          Rate rate = new Rate();
          rate.setDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(exchangeRate.getDate()).getTime()));
          rate.setName(exchangeRate.getCurrencyAmounts().get(1).getName());
          rate.setRate(exchangeRate.getCurrencyAmounts().get(1).getRate());
          rateRepository.save(rate);
        }
        List<Rate> rates = rateRepository.findAll();
        // rates.forEach(System.out::println);
        System.out.println(i);
      }
    };
  }
}