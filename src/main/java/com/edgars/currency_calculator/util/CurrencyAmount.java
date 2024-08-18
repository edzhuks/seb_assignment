package com.edgars.currency_calculator.util;

import java.sql.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "CcyAmt")
public class CurrencyAmount {
     private @Id
  @GeneratedValue Long id;
  private String name;
  private float rate;
 
  CurrencyAmount() {}

  CurrencyAmount(String name, float rate) {
    this.name = name;
    this.rate = rate;
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public float getRate() {
    return rate;
  }

  @JsonProperty("Amt")
  public void setRate(float value) {
    this.rate = value;
  }


  public void setId(Long id) {
    this.id = id;
  }

  @JsonProperty("Ccy")
  public void setName(String name) {
    this.name = name;
  }


  

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name, this.rate);
  }

  @Override
  public String toString() {
    return "ExchangeRate{" + "id=" + this.id + ", name='" + this.name + '\'' + ", value='" + this.rate + '\'' + '}';
  }
}
