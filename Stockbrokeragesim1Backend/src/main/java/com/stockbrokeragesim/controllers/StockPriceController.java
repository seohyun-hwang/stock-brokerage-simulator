package com.stockbrokeragesim.controllers;

import com.stockbrokeragesim.models.Model_StockPrices;
import com.stockbrokeragesim.services.StockPricesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class StockPriceController {
    @RequestMapping("/api")
    public String returnStockDisplayHTML() {
        return "Hello World (stock-display.html)";
    }

    @Autowired
    private StockPricesService stockPricesService;

    @GetMapping("/{id}")
    public ResponseEntity<Model_StockPrices> readById_model_stockPrices_sameTradingDay(@PathVariable Integer id) {
        Optional<Model_StockPrices> stockPricesModel = stockPricesService.getStockPricesById(id);
        return stockPricesModel.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
