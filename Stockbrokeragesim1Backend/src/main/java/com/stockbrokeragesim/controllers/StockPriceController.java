package com.stockbrokeragesim.controllers;

import com.stockbrokeragesim.models.Model_StockPrices;
import com.stockbrokeragesim.services.StockPricesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StockPriceController {
    @RequestMapping("/api")
    public String returnStockDisplayHTML() {
        return "Hello World (stock-display.html)";
    }

    @Autowired
    private StockPricesService stockPricesService;

    @GetMapping("/stock-prices")
    public ResponseEntity<List<Model_StockPrices>> read_allStockPriceClassData() {
        return new ResponseEntity<>(stockPricesService.read_allStockPriceClassData(), HttpStatus.OK);
    }

    @GetMapping("stock-prices/{id}")
    public ResponseEntity<Model_StockPrices> read_stockPriceDataById(@PathVariable Integer id) {
        Model_StockPrices stockPricesModel = stockPricesService.read_stockPriceDataById(id);

        if (stockPricesModel == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(stockPricesModel);
    }


}
