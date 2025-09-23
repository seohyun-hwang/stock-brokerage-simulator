package com.stockbrokeragesim.controllers;
import com.stockbrokeragesim.StockBrokerageSimulatorApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StockInfoController {
    @RequestMapping("/api")
    public String returnStockDisplayHTML() {
        return "Hello World (stock-display.html)";
    }

    @Autowired
    StockBrokerageSimulatorApplication mainClass;

    @GetMapping("/stock-display")
    public double read_field_cashVolume() {
        return mainClass.read_field_cashVolume();
    }
    @GetMapping("/stock-display")
    public double[] read_list_stockPrices_wholeTradingDay() {
        return mainClass.read_list_stockPrices_wholeTradingDay();
    }
    @GetMapping("/stock-display")
    public double read_listElement_stockPrice_partialTradingDay(int halfSecondsPassed) {
        return mainClass.read_listElement_stockPrice_partialTradingDay(halfSecondsPassed);
    }

    // ACTIVE-ORDER EFFECTS

    @PutMapping("/stock-display")
    public void update_fieldAndList_cashVolumeAndHeldShares_thruOrderActivation(long activeOrder_id) {
        mainClass.update_fieldAndList_cashVolumeAndHeldShares_thruOrderActivation(activeOrder_id);
    }


    // ORDER ARRANGEMENT

    // potential orders
    @PostMapping("/stock-display")
    public void create_listElement_potentialOrder(
            String stockTicker, String actionType, String orderType,
            double orderCount_ofThisInstance, double stockPrice_ofThisInstance, double durationInDays, double tradingDay_ofThisInstance, double halfSecond_ofThisInstance, double limitPrice, double stopPrice, double trailTriggerDelta, double trailTriggerPercentage)
    {
        mainClass.create_listElement_potentialOrder(
                stockTicker, actionType, orderType,
                orderCount_ofThisInstance, stockPrice_ofThisInstance, durationInDays, tradingDay_ofThisInstance, halfSecond_ofThisInstance, limitPrice, stopPrice, trailTriggerDelta, trailTriggerPercentage
        );
    }
    @PutMapping("/stock-display")
    public void update_potentialOrderIdCounter(int incrementCount) {
        mainClass.update_potentialOrderIdCounter(incrementCount);
    }
    @DeleteMapping("/stock-display")
    public void delete_listElement_potentialOrder(long potentialOrder_id) {
        mainClass.delete_listElement_potentialOrder(potentialOrder_id);
    }
    @DeleteMapping("/stock-display")
    public void delete_allListElements_potentialOrders() {
        mainClass.delete_allListElements_potentialOrders();
    }

    // active orders
    @PostMapping("/stock-display")
    public void create_listElement_activeOrder(
            String stockTicker, String actionType, String orderType,
            double orderCount_ofThisInstance, double stockPrice_ofThisInstance, double durationInDays, double tradingDay_ofThisInstance, double halfSecond_ofThisInstance, double limitPrice, double stopPrice, double trailTriggerDelta, double trailTriggerPercentage)
    {
        mainClass.create_listElement_activeOrder(
                stockTicker, actionType, orderType,
                orderCount_ofThisInstance, stockPrice_ofThisInstance, durationInDays, tradingDay_ofThisInstance, halfSecond_ofThisInstance, limitPrice, stopPrice, trailTriggerDelta, trailTriggerPercentage
        );
    }
    @PutMapping("/stock-display")
    public void update_activeOrderIdCounter(int incrementCount) {
        mainClass.update_activeOrderIdCounter(incrementCount);
    }
    @DeleteMapping("/stock-display")
    public void delete_listElement_activeOrder(long activeOrder_id) {
        mainClass.delete_listElement_activeOrder(activeOrder_id);
    }
    @DeleteMapping("/stock-display")
    public void delete_allListElements_activeOrders() {
        mainClass.delete_allListElements_activeOrders();
    }

    // order history
    @PostMapping("/stock-display")
    public void create_listElement_orderHistory(
            long activeOrder_id,
            String stockTicker, String actionType, String orderType,
            double orderCount_ofThisInstance, double stockPrice_ofThisInstance, double durationInDays, double tradingDay_ofThisInstance, double halfSecond_ofThisInstance, double limitPrice, double stopPrice, double trailTriggerDelta, double trailTriggerPercentage)
    {
        mainClass.create_listElement_orderHistory(
                activeOrder_id,
                stockTicker, actionType, orderType,
                orderCount_ofThisInstance, stockPrice_ofThisInstance, durationInDays, tradingDay_ofThisInstance, halfSecond_ofThisInstance, limitPrice, stopPrice, trailTriggerDelta, trailTriggerPercentage
        );
    }

    // SHAREHOLDING ARRANGEMENT

    @PostMapping("/stock-display")
    public void create_listElement_heldShare(
            long activeOrder_id,
            String stockTicker, String actionType, String orderType,
            double orderCount_ofThisInstance, double stockPrice_ofThisInstance, double durationInDays, double tradingDay_ofThisInstance, double halfSecond_ofThisInstance, double limitPrice, double stopPrice, double trailTriggerDelta, double trailTriggerPercentage)
    {
        mainClass.create_listElement_heldShare(
                activeOrder_id,
                stockTicker, actionType, orderType,
                orderCount_ofThisInstance, stockPrice_ofThisInstance, durationInDays, tradingDay_ofThisInstance, halfSecond_ofThisInstance, limitPrice, stopPrice, trailTriggerDelta, trailTriggerPercentage
        );
    }
    @DeleteMapping("/stock-display")
    public void delete_listElement_heldShare(long activeOrder_id) {
        mainClass.delete_listElement_heldShare(activeOrder_id);
    }
}