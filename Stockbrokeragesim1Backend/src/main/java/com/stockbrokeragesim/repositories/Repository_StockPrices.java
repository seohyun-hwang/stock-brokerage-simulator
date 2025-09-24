package com.stockbrokeragesim.repositories;

import com.stockbrokeragesim.models.Model_StockPrices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repository_StockPrices extends JpaRepository<Model_StockPrices, Integer> {}
