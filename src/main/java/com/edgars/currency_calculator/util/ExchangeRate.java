package com.edgars.currency_calculator.util;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

public class ExchangeRate {

  private String date;
  private String tp;
  private List<CurrencyAmount> currencyAmounts;

  ExchangeRate(@JsonProperty("Tp") String tp, @JsonProperty("Dt") String date) {

    this.date = date;
    this.tp = tp;
  }

  public String getTp() {
    return tp;
  }

  @JsonProperty("Tp")
  public void setTp(String tp) {
    this.tp = tp;
  }

  public String getDate() {
    return date;
  }

  @JsonProperty("Dt")
  public void setDate(String date) {
    this.date = date;
  }

  public List<CurrencyAmount> getCurrencyAmounts() {
    return currencyAmounts;
  }

  @JacksonXmlElementWrapper(useWrapping = false)
  @JsonProperty("CcyAmt")
  public void setCurrencyAmounts(List<CurrencyAmount> currencyAmounts) {
    this.currencyAmounts = currencyAmounts;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof ExchangeRate))
      return false;
    ExchangeRate exchangeRate = (ExchangeRate) o;
    return Objects.equals(this.date, exchangeRate.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.date);
  }

  @Override
  public String toString() {
    return "ExchangeRate{" + ", date=" + this.date + " tp= " + this.tp + " ccys= " + this.currencyAmounts + '}';
  }

}