package com.stockbrokeragesim.controllers;

import com.stockbrokeragesim.models.Model_StockPrices;
import com.stockbrokeragesim.services.StockPricesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
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

    @GetMapping("stock-prices/databaseInfo?id={id}")
    public ResponseEntity<Model_StockPrices> read_stockPriceDataById(@PathVariable Integer id) {
        Model_StockPrices stockPricesModel = stockPricesService.read_stockPriceDataById(id);

        if (stockPricesModel == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(stockPricesModel);
    }


}
