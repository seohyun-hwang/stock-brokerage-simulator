package com.stockbrokeragesim.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Entity
@Getter
@Setter
public class Model_StockPrices {

    @Id
    private int id;

    private LocalDate dateData;
    private double gePrice;
    private double koPrice;
    private double googlPrice;
    private double aaplPrice;
    private double msftPrice;
    private double amznPrice;
    private double nvdaPrice;
    private double avgoPrice;
    private double costPrice;
    private double metaPrice;
    private double nflxPrice;
    private double pepPrice;
    private double tslaPrice;
    private double pgPrice;
    private double clPrice;
    private double xomPrice;
    private double ibmPrice;
}