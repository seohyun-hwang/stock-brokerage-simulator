package com.stockbrokeragesim.services;

import com.stockbrokeragesim.models.Model_StockPrices;
import com.stockbrokeragesim.repositories.Repository_StockPrices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockPricesService {

    @Autowired
    private Repository_StockPrices stockPricesRepo;


    public List<Model_StockPrices> read_allStockPriceClassData() {
        return stockPricesRepo.findAll();
    }

    public Model_StockPrices read_stockPriceDataById(int id) {
        return stockPricesRepo.findById(id).orElse(null);
    }
}
