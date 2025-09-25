package com.stockbrokeragesim.controllers;
import com.stockbrokeragesim.StockBrokerageSimulatorApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class StockInfoController {
    @RequestMapping("/api")
    public String returnStockDisplayHTML() {
        return "Hello World (stock-display.html)";
    }

    @Autowired
    StockBrokerageSimulatorApplication mainClass;

    @GetMapping("/stock-order")
    public double read_field_cashVolume() {
        return mainClass.read_field_cashVolume();
    }

    @PostMapping("/stock-order/stockInfo?sT={stockTicker}&sP_pD={stockPrice_presentDay}")
    public void create_listElement_stockPrices_ofSameTradingDay(@PathVariable String stockTicker, @PathVariable Integer stockPrice_presentDay) {
        mainClass.create_listElement_stockPrices_ofSameTradingDay(stockTicker, stockPrice_presentDay);
    }
    @DeleteMapping("/stock-order")
    public void delete_allListElements_stockPrices_ofSameTradingDay() {
        mainClass.delete_allListElements_stockPrices_ofSameTradingDay();
    }

    // ACTIVE-ORDER EFFECTS

    @PutMapping("/stock-order/stockInfo?aO_id={activeOrder_id}&sT={stockTicker}&aT={actionType}&oT={orderType}&oC_oTI={orderCount_ofThisInstance}&sP_oTI={stockPrice_ofThisInstance}&dID={durationInDays}&tD_oTI={tradingDay_ofThisInstance}&hS_oTI{halfSecond_ofThisInstance}&lP={limitPrice}&sP={stopPrice}&tTD={trailTriggerDelta}&tTP={trailTriggerPercentage}")
    public void update_fieldAndList_cashVolumeAndHeldShares_thruOrderExecution(
            @PathVariable Long activeOrder_id,
            @PathVariable String stockTicker, @PathVariable String actionType, @PathVariable String orderType,
            @PathVariable Double orderCount_ofThisInstance, @PathVariable Double stockPrice_ofThisInstance, @PathVariable Double durationInDays, @PathVariable Double tradingDay_ofThisInstance, @PathVariable Double halfSecond_ofThisInstance, @PathVariable Double limitPrice, @PathVariable Double stopPrice, @PathVariable Double trailTriggerDelta, @PathVariable Double trailTriggerPercentage)
    {
        mainClass.update_fieldAndList_cashVolumeAndHeldShares_thruOrderExecution(
                activeOrder_id,
                stockTicker, actionType, orderType,
                orderCount_ofThisInstance, stockPrice_ofThisInstance, durationInDays, tradingDay_ofThisInstance, halfSecond_ofThisInstance, limitPrice, stopPrice, trailTriggerDelta, trailTriggerPercentage
        );
    }


    // ORDER ARRANGEMENT

    // potential orders
    @PostMapping("/stock-order/stockInfo?sT={stockTicker}&aT={actionType}&oT={orderType}&oC_oTI={orderCount_ofThisInstance}&sP_oTI={stockPrice_ofThisInstance}&dID={durationInDays}&tD_oTI={tradingDay_ofThisInstance}&hS_oTI{halfSecond_ofThisInstance}&lP={limitPrice}&sP={stopPrice}&tTD={trailTriggerDelta}&tTP={trailTriggerPercentage}")
    public void create_listElement_potentialOrder(
            @PathVariable String stockTicker, @PathVariable String actionType, @PathVariable String orderType,
            @PathVariable Double orderCount_ofThisInstance, @PathVariable Double stockPrice_ofThisInstance, @PathVariable Double durationInDays, @PathVariable Double tradingDay_ofThisInstance, @PathVariable Double halfSecond_ofThisInstance, @PathVariable Double limitPrice, @PathVariable Double stopPrice, @PathVariable Double trailTriggerDelta, @PathVariable Double trailTriggerPercentage)
    {
        mainClass.create_listElement_potentialOrder(
                stockTicker, actionType, orderType,
                orderCount_ofThisInstance, stockPrice_ofThisInstance, durationInDays, tradingDay_ofThisInstance, halfSecond_ofThisInstance, limitPrice, stopPrice, trailTriggerDelta, trailTriggerPercentage
        );
    }
    @PutMapping("/stock-order/stockInfo?iC={incrementCount}")
    public void update_potentialOrderIdCounter(@PathVariable Integer incrementCount) {
        mainClass.update_potentialOrderIdCounter(incrementCount);
    }
    @DeleteMapping("/stock-order/stockInfo?pO_id={potentialOrder_id}")
    public void delete_listElement_potentialOrder(@PathVariable Long potentialOrder_id) {
        mainClass.delete_listElement_potentialOrder(potentialOrder_id);
    }
    @DeleteMapping("/stock-order")
    public void delete_allListElements_potentialOrders() {
        mainClass.delete_allListElements_potentialOrders();
    }

    // active orders
    @PostMapping("/stock-order/stockInfo?sT={stockTicker}&aT={actionType}&oT={orderType}&oC_oTI={orderCount_ofThisInstance}&sP_oTI={stockPrice_ofThisInstance}&dID={durationInDays}&tD_oTI={tradingDay_ofThisInstance}&hS_oTI{halfSecond_ofThisInstance}&lP={limitPrice}&sP={stopPrice}&tTD={trailTriggerDelta}&tTP={trailTriggerPercentage}")
    public void create_listElement_activeOrder(
            @PathVariable String stockTicker, @PathVariable String actionType, @PathVariable String orderType,
            @PathVariable Double orderCount_ofThisInstance, @PathVariable Double stockPrice_ofThisInstance, @PathVariable Double durationInDays, @PathVariable Double tradingDay_ofThisInstance, @PathVariable Double halfSecond_ofThisInstance, @PathVariable Double limitPrice, @PathVariable Double stopPrice, @PathVariable Double trailTriggerDelta, @PathVariable Double trailTriggerPercentage)
    {
        mainClass.create_listElement_activeOrder(
                stockTicker, actionType, orderType,
                orderCount_ofThisInstance, stockPrice_ofThisInstance, durationInDays, tradingDay_ofThisInstance, halfSecond_ofThisInstance, limitPrice, stopPrice, trailTriggerDelta, trailTriggerPercentage
        );
    }
    @PutMapping("/stock-order/stockInfo?iC={incrementCount}")
    public void update_activeOrderIdCounter(@PathVariable Integer incrementCount) {
        mainClass.update_activeOrderIdCounter(incrementCount);
    }
    @DeleteMapping("/stock-order/stockInfo?aO_id={activeOrder_id}")
    public void delete_listElement_activeOrder(@PathVariable Long activeOrder_id) {
        mainClass.delete_listElement_activeOrder(activeOrder_id);
    }
    @DeleteMapping("/stock-order")
    public void delete_allListElements_activeOrders() {
        mainClass.delete_allListElements_activeOrders();
    }

    // order history
    @PostMapping("/stock-order/stockInfo?aO_id={activeOrder_id}&sT={stockTicker}&aT={actionType}&oT={orderType}&oC_oTI={orderCount_ofThisInstance}&sP_oTI={stockPrice_ofThisInstance}&dID={durationInDays}&tD_oTI={tradingDay_ofThisInstance}&hS_oTI{halfSecond_ofThisInstance}&lP={limitPrice}&sP={stopPrice}&tTD={trailTriggerDelta}&tTP={trailTriggerPercentage}")
    public void create_listElement_orderHistory(
            @PathVariable Long activeOrder_id,
            @PathVariable String stockTicker, @PathVariable String actionType, @PathVariable String orderType,
            @PathVariable Double orderCount_ofThisInstance, @PathVariable Double stockPrice_ofThisInstance, @PathVariable Double durationInDays, @PathVariable Double tradingDay_ofThisInstance, @PathVariable Double halfSecond_ofThisInstance, @PathVariable Double limitPrice, @PathVariable Double stopPrice, @PathVariable Double trailTriggerDelta, @PathVariable Double trailTriggerPercentage)
    {
        mainClass.create_listElement_orderHistory(
                activeOrder_id,
                stockTicker, actionType, orderType,
                orderCount_ofThisInstance, stockPrice_ofThisInstance, durationInDays, tradingDay_ofThisInstance, halfSecond_ofThisInstance, limitPrice, stopPrice, trailTriggerDelta, trailTriggerPercentage
        );
    }

    // SHAREHOLDING ARRANGEMENT

    @PostMapping("/stock-order/stockInfo?aO_id={activeOrder_id}&sT={stockTicker}&aT={actionType}&oT={orderType}&oC_oTI={orderCount_ofThisInstance}&sP_oTI={stockPrice_ofThisInstance}&dID={durationInDays}&tD_oTI={tradingDay_ofThisInstance}&hS_oTI{halfSecond_ofThisInstance}&lP={limitPrice}&sP={stopPrice}&tTD={trailTriggerDelta}&tTP={trailTriggerPercentage}")
    public void create_listElement_heldShare(
            @PathVariable Long activeOrder_id,
            @PathVariable String stockTicker, @PathVariable String actionType, @PathVariable String orderType,
            @PathVariable Double orderCount_ofThisInstance, @PathVariable Double stockPrice_ofThisInstance, @PathVariable Double durationInDays, @PathVariable Double tradingDay_ofThisInstance, @PathVariable Double halfSecond_ofThisInstance, @PathVariable Double limitPrice, @PathVariable Double stopPrice, @PathVariable Double trailTriggerDelta, @PathVariable Double trailTriggerPercentage)
    {
        mainClass.create_listElement_heldShare(
                activeOrder_id,
                stockTicker, actionType, orderType,
                orderCount_ofThisInstance, stockPrice_ofThisInstance, durationInDays, tradingDay_ofThisInstance, halfSecond_ofThisInstance, limitPrice, stopPrice, trailTriggerDelta, trailTriggerPercentage
        );
    }
    @DeleteMapping("/stock-order/stockInfo?aO_id={activeOrder_id}")
    public void delete_listElement_heldShare(@PathVariable Long activeOrder_id) {
        mainClass.delete_listElement_heldShare(activeOrder_id);
    }
}