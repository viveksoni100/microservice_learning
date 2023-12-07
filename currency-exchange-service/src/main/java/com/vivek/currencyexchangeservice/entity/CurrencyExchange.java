package com.vivek.currencyexchangeservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyExchange {
    @Id
    private Long id;
    @Column(name = "cur_from")
    private String from;
    @Column(name = "cur_to")
    private String to;
    private BigDecimal conversionMultiple;
    private String environment;
}
