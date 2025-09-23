package com.stockbrokeragesim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockInfo {
    byte sizeOf_qualityList = 3; // Qualities in order: 0 stockTicker, 1 actionType (buy, sell, short, buy-to-cover), 2 orderType (market, limit, stopLoss, stopLimit, trailingStopLoss, trailingStopLimit)
    List<String> qualityList = new ArrayList<>(sizeOf_qualityList);
    Map<String, Integer> qualityList_navigationHashMap = new HashMap<>(sizeOf_qualityList);

    byte sizeOf_quantityList = 9; // Quantities in order: 0 orderCount_ofThisInstance, 1 stockPrice_ofThisInstance, 2 durationInDays (1 for Day, negative value for GTC), 3 tradingDay_ofThisInstance (trading days passed since 06/05/1972), 4 halfSecond_ofThisInstance, 5 limitPrice, 6 stopPrice, 7 trailTriggerDelta, 8 trailTriggerPercentage
    List<Double> quantityList = new ArrayList<>(sizeOf_quantityList);
    Map<String, Integer> quantityList_navigationHashMap = new HashMap<>(sizeOf_quantityList);


    public StockInfo(
            String stockTicker, String actionType, String orderType,
            double orderCount_ofThisInstance, double stockPrice_ofThisInstance, double durationInDays, double tradingDay_ofThisInstance, double halfSecond_ofThisInstance, double limitPrice, double stopPrice, double trailTriggerDelta, double trailTriggerPercentage)
    {

        // quality-list setup
        qualityList.set(0, stockTicker);
        qualityList.set(1, actionType);
        qualityList.set(2, orderType);
        // quantity-list setup
        quantityList.set(0, orderCount_ofThisInstance);
        quantityList.set(1, stockPrice_ofThisInstance);
        quantityList.set(2, durationInDays);
        quantityList.set(3, tradingDay_ofThisInstance);
        quantityList.set(4, halfSecond_ofThisInstance);
        quantityList.set(5, limitPrice);
        quantityList.set(6, stopPrice);
        quantityList.set(7, trailTriggerDelta);
        quantityList.set(8, trailTriggerPercentage);



        // quality-list navigation-HashMap setup
        qualityList_navigationHashMap.put("stockTicker", 0);
        qualityList_navigationHashMap.put("actionType", 1);
        qualityList_navigationHashMap.put("orderType", 2);

        // quantity-list navigation-HashMap setup
        quantityList_navigationHashMap.put("orderCount_ofThisInstance", 0);
        quantityList_navigationHashMap.put("stockPrice_ofThisInstance", 1);
        quantityList_navigationHashMap.put("durationInDays", 2);
        quantityList_navigationHashMap.put("tradingDay_ofThisInstance", 3);
        quantityList_navigationHashMap.put("halfSecond_ofThisInstance", 4);
        quantityList_navigationHashMap.put("limitPrice", 5);
        quantityList_navigationHashMap.put("stopPrice", 6);
        quantityList_navigationHashMap.put("trailTriggerDelta", 7);
        quantityList_navigationHashMap.put("trailTriggerPercentage", 8);
    }


    // getters
    public String get_listElement_quality(String hashKey) {
        return qualityList.get(qualityList_navigationHashMap.get(hashKey));
    }
    public double get_listElement_quantity(String hashKey) {
        return quantityList.get(quantityList_navigationHashMap.get(hashKey));
    }
}
