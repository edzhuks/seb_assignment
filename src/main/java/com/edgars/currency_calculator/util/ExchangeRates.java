package com.edgars.currency_calculator.util;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "FxRates", namespace = "http://www.lb.lt/WebServices/FxRates")
public class ExchangeRates {
    ExchangeRates() {}

    private List<ExchangeRate> exchangeRates;

    public List<ExchangeRate> getExchangeRates() {
        return exchangeRates;
    }

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName =  "FxRate")
    public void setExchangeRates(List<ExchangeRate> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }
    
    @Override
    public String  toString() {
        return "Exchangerates" + exchangeRates.toString();
    }
}
