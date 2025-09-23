package com.stockbrokeragesim.services;

import com.stockbrokeragesim.models.Model_StockPrices;
import com.stockbrokeragesim.repositories.Repository_StockPrices;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class StockPricesService {

    @Autowired
    private Repository_StockPrices stockPricesRepo;

    public Optional<Model_StockPrices> getStockPricesById(int id) {
        return stockPricesRepo.findById(id);
    }
}
