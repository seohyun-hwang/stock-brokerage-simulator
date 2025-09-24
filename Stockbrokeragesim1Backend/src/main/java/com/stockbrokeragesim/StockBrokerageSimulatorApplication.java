package com.stockbrokeragesim;
import java.util.*;

// SpringBoot imports
import com.stockbrokeragesim.services.StockPricesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;


@SpringBootApplication
@Component
public class StockBrokerageSimulatorApplication {

	double dollarCash_inYoWallet = 100000.00; // starting off with $100K IN CASH IN YOUR WALLET :DD (perfectly realistic)

	@Autowired
	private StockPricesService stockPricesService;


	// Hashmaps <ID, qualities/quantities>
	Map<Long, StockInfo> potentialOrders = new HashMap<>();
	Map<Long, StockInfo> activeOrders = new HashMap<>();
	Map<Long, StockInfo> orderHistory = new HashMap<>();
	Map<Long, StockInfo> heldShares = new HashMap<>();
	long potentialOrder_id = 0;
	long activeOrder_id = 0;

	Map<String, List<Double>> stockPrices_byStockTicker_ofSameTradingDay = new HashMap<>();

    public static void main(String[] args) {
		// connection to SpringBoot
		SpringApplication.run(StockBrokerageSimulatorApplication.class, args);
		System.out.println("Welcome to my stock market brokerage simulator. Disclaimer: this program ignores dividends.");
	}

	public void create_listElement_stockPrices_ofSameTradingDay(String stockTicker, int stockPrice_presentDay) {
		List<Double> stockPrices_individualStockTicker = new ArrayList<>();

		for (int i = 0; i < 46800; i++) { // 6.5 hours per trading day --> 23400 seconds --> 46800 half-seconds
			stockPrices_individualStockTicker.set(i, Math.random() * (stockPrice_presentDay * (1.25 - 0.75)) + (stockPrice_presentDay * 0.75));
		}
		stockPrices_byStockTicker_ofSameTradingDay.put(stockTicker, stockPrices_individualStockTicker);
	}
	public void delete_allListElements_stockPrices_ofSameTradingDay() {
		stockPrices_byStockTicker_ofSameTradingDay.clear();
	}

	public boolean isStopPricePassed(long activeOrder_id, String stockTicker, double halfSecond_ofThisInstance) {
		if (stockPrices_byStockTicker_ofSameTradingDay.get(stockTicker).get((int) halfSecond_ofThisInstance)
				< activeOrders.get(activeOrder_id).get_listElement_quantity("stopPrice"))
		{
			if (stockPrices_byStockTicker_ofSameTradingDay.get(stockTicker).get((int) halfSecond_ofThisInstance - 1)
					>= activeOrders.get(activeOrder_id).get_listElement_quantity("stopPrice"))
			{
				return true;
			}
		}
		else if (stockPrices_byStockTicker_ofSameTradingDay.get(stockTicker).get((int) halfSecond_ofThisInstance - 1)
				> activeOrders.get(activeOrder_id).get_listElement_quantity("stopPrice"))
		{
			if (stockPrices_byStockTicker_ofSameTradingDay.get(stockTicker).get((int) halfSecond_ofThisInstance)
					<= activeOrders.get(activeOrder_id).get_listElement_quantity("stopPrice"))
			{
				return true;
			}
		}

		return false;
	}



	// Only CRUD methods below this line


	public double read_field_cashVolume() {
		return dollarCash_inYoWallet;
	}
	public void update_field_cashVolume(double increment) {
		dollarCash_inYoWallet += increment;
	}

	// ACTIVE-ORDER EFFECTS

	public void update_fieldAndList_cashVolumeAndHeldShares_thruOrderActivation(
			long activeOrder_id,
			String stockTicker, String actionType, String orderType,
			double orderCount_ofThisInstance, double stockPrice_ofThisInstance, double durationInDays, double tradingDay_ofThisInstance, double halfSecond_ofThisInstance, double limitPrice, double stopPrice, double trailTriggerDelta, double trailTriggerPercentage)
	{
		if (activeOrders.get(activeOrder_id).get_listElement_quality("orderType").equalsIgnoreCase("market")) {
			if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("buy")) {
				create_listElement_heldShare(
						activeOrder_id,
						stockTicker, actionType, orderType,
						orderCount_ofThisInstance, stockPrice_ofThisInstance, durationInDays, tradingDay_ofThisInstance, halfSecond_ofThisInstance, limitPrice, stopPrice, trailTriggerDelta, trailTriggerPercentage
				);
				update_field_cashVolume(-1 * stockPrice_ofThisInstance);

				//create_listElement_orderHistory(activeOrder_id, );
				delete_listElement_activeOrder(activeOrder_id);
			}
			else if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("sell")) {
				delete_listElement_heldShare(activeOrder_id);
				update_field_cashVolume(stockPrice_ofThisInstance);

				//create_listElement_orderHistory(activeOrder_id, );
				delete_listElement_activeOrder(activeOrder_id);
			}
			else if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("short")) {
				create_listElement_heldShare(
						activeOrder_id,
						stockTicker, actionType, orderType,
						orderCount_ofThisInstance, stockPrice_ofThisInstance, durationInDays, tradingDay_ofThisInstance, halfSecond_ofThisInstance, limitPrice, stopPrice, trailTriggerDelta, trailTriggerPercentage
				);

				//create_listElement_orderHistory(activeOrder_id, );
				delete_listElement_activeOrder(activeOrder_id);
			}
			else if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("btc")) {
				update_field_cashVolume(stockPrice_ofThisInstance);
				delete_listElement_heldShare(activeOrder_id);

				create_listElement_orderHistory(
						activeOrder_id,
						stockTicker, actionType, orderType,
						orderCount_ofThisInstance, stockPrice_ofThisInstance, durationInDays, tradingDay_ofThisInstance, halfSecond_ofThisInstance, limitPrice, stopPrice, trailTriggerDelta, trailTriggerPercentage
				);
				delete_listElement_activeOrder(activeOrder_id);
			}
		}
		else if (activeOrders.get(activeOrder_id).get_listElement_quality("orderType").equalsIgnoreCase("limit")) {
			if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("buy")) {
				if (activeOrders.get(activeOrder_id).get_listElement_quantity("stockPrice_ofThisInstance")
						<= activeOrders.get(activeOrder_id).get_listElement_quantity("limitPrice") )
				{
					create_listElement_heldShare(
							activeOrder_id,
							stockTicker, actionType, orderType,
							orderCount_ofThisInstance, stockPrice_ofThisInstance, durationInDays, tradingDay_ofThisInstance, halfSecond_ofThisInstance, limitPrice, stopPrice, trailTriggerDelta, trailTriggerPercentage
					);
					update_field_cashVolume(-1 * stockPrice_ofThisInstance);
				}
			}
			else if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("sell")) {
				if (activeOrders.get(activeOrder_id).get_listElement_quantity("stockPrice_ofThisInstance")
						>= activeOrders.get(activeOrder_id).get_listElement_quantity("limitPrice") )
				{
					delete_listElement_heldShare(activeOrder_id);
					update_field_cashVolume(stockPrice_ofThisInstance);
				}
			}
			else if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("short")) {
				if (activeOrders.get(activeOrder_id).get_listElement_quantity("stockPrice_ofThisInstance")
						>= activeOrders.get(activeOrder_id).get_listElement_quantity("limitPrice") )
				{
					delete_listElement_heldShare(activeOrder_id);
				}
			}
			else if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("btc")) {
				if (activeOrders.get(activeOrder_id).get_listElement_quantity("stockPrice_ofThisInstance")
						<= activeOrders.get(activeOrder_id).get_listElement_quantity("limitPrice") )
				{
					update_field_cashVolume(stockPrice_ofThisInstance);
					delete_listElement_heldShare(activeOrder_id);
				}
			}
		}
		else if (activeOrders.get(activeOrder_id).get_listElement_quality("orderType").equalsIgnoreCase("stop-loss")) {
			if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("buy")) {
				if (isStopPricePassed(activeOrder_id, stockTicker, halfSecond_ofThisInstance)) {
					create_listElement_heldShare(
							activeOrder_id,
							stockTicker, actionType, orderType,
							orderCount_ofThisInstance, stockPrice_ofThisInstance, durationInDays, tradingDay_ofThisInstance, halfSecond_ofThisInstance, limitPrice, stopPrice, trailTriggerDelta, trailTriggerPercentage
					);
					update_field_cashVolume(-1 * stockPrice_ofThisInstance);
				}
			}
			else if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("sell")) {
				if (isStopPricePassed(activeOrder_id, stockTicker, halfSecond_ofThisInstance)) {
					update_field_cashVolume(stockPrice_ofThisInstance);
					delete_listElement_heldShare(activeOrder_id);
				}
			}
			else if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("short")) {
				if (isStopPricePassed(activeOrder_id, stockTicker, halfSecond_ofThisInstance)) {
					create_listElement_heldShare(
							activeOrder_id,
							stockTicker, actionType, orderType,
							orderCount_ofThisInstance, stockPrice_ofThisInstance, durationInDays, tradingDay_ofThisInstance, halfSecond_ofThisInstance, limitPrice, stopPrice, trailTriggerDelta, trailTriggerPercentage
					);
				}
			}
			else if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("btc")) {
				if (isStopPricePassed(activeOrder_id, stockTicker, halfSecond_ofThisInstance)) {
					update_field_cashVolume(stockPrice_ofThisInstance);
					delete_listElement_heldShare(activeOrder_id);
				}
			}
		}
		else if (activeOrders.get(activeOrder_id).get_listElement_quality("orderType").equalsIgnoreCase("stop-limit")) {
			if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("buy")) {
				if (isStopPricePassed(activeOrder_id, stockTicker, halfSecond_ofThisInstance)) {
					activeOrders.get(activeOrder_id).set_listElement_quality("orderType", "limit");
				}
			}
			else if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("sell")) {
				if (isStopPricePassed(activeOrder_id, stockTicker, halfSecond_ofThisInstance)) {
					activeOrders.get(activeOrder_id).set_listElement_quality("orderType", "limit");
				}
			}
			else if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("short")) {
				if (isStopPricePassed(activeOrder_id, stockTicker, halfSecond_ofThisInstance)) {
					activeOrders.get(activeOrder_id).set_listElement_quality("orderType", "limit");
				}
			}
			else if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("btc")) {
				if (isStopPricePassed(activeOrder_id, stockTicker, halfSecond_ofThisInstance)) {
					activeOrders.get(activeOrder_id).set_listElement_quality("orderType", "limit");
				}
			}
		}
		/*
		else if (activeOrders.get(activeOrder_id).get_listElement_quality("orderType").equalsIgnoreCase("trailing-stop-loss")) {
			if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("buy")) {

			}
			else if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("sell")) {

			}
			else if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("short")) {

			}
			else if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("btc")) {

			}
		}
		else if (activeOrders.get(activeOrder_id).get_listElement_quality("orderType").equalsIgnoreCase("trailing-stop-limit")) {
			if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("buy")) {

			}
			else if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("sell")) {

			}
			else if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("short")) {

			}
			else if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("btc")) {

			}
		}
		 */
	}


	// ORDER ARRANGEMENT

	// potential orders
	public void create_listElement_potentialOrder(
			String stockTicker, String actionType, String orderType,
			double orderCount_ofThisInstance, double stockPrice_ofThisInstance, double durationInDays, double tradingDay_ofThisInstance, double halfSecond_ofThisInstance, double limitPrice, double stopPrice, double trailTriggerDelta, double trailTriggerPercentage)
	{
		potentialOrders.put(
				potentialOrder_id,
				new StockInfo(
						stockTicker, actionType, orderType,
						orderCount_ofThisInstance, stockPrice_ofThisInstance, durationInDays, tradingDay_ofThisInstance, halfSecond_ofThisInstance, limitPrice, stopPrice, trailTriggerDelta, trailTriggerPercentage)
		);
	}
	public void update_potentialOrderIdCounter(int incrementCount) {
		potentialOrder_id += incrementCount;
	}
	public void delete_listElement_potentialOrder(long potentialOrder_id) {
		potentialOrders.remove(potentialOrder_id);
	}
	public void delete_allListElements_potentialOrders() {
		potentialOrders.clear();
		potentialOrder_id = 0;
	}

	// active orders
	public void create_listElement_activeOrder(
			String stockTicker, String actionType, String orderType,
			double orderCount_ofThisInstance, double stockPrice_ofThisInstance, double durationInDays, double tradingDay_ofThisInstance, double halfSecond_ofThisInstance, double limitPrice, double stopPrice, double trailTriggerDelta, double trailTriggerPercentage)
	{
		activeOrders.put(
				activeOrder_id,
				new StockInfo(
						stockTicker, actionType, orderType, orderCount_ofThisInstance,
						stockPrice_ofThisInstance, durationInDays, tradingDay_ofThisInstance, halfSecond_ofThisInstance, limitPrice, stopPrice, trailTriggerDelta, trailTriggerPercentage)
		);
	}
	public void update_activeOrderIdCounter(int incrementCount) {
		activeOrder_id += incrementCount;
	}
	public void delete_listElement_activeOrder(long activeOrder_id) {
		activeOrders.remove(activeOrder_id);
	}
	public void delete_allListElements_activeOrders() {
		activeOrders.clear();
	}

	// order history
	public void create_listElement_orderHistory(
			long activeOrder_id,
			String stockTicker, String actionType, String orderType,
			double orderCount_ofThisInstance, double stockPrice_ofThisInstance, double durationInDays, double tradingDay_ofThisInstance, double halfSecond_ofThisInstance, double limitPrice, double stopPrice, double trailTriggerDelta, double trailTriggerPercentage)
	{
		orderHistory.put(
				activeOrder_id,
				new StockInfo(
						stockTicker, actionType, orderType, orderCount_ofThisInstance,
						stockPrice_ofThisInstance, durationInDays, tradingDay_ofThisInstance, halfSecond_ofThisInstance, limitPrice, stopPrice, trailTriggerDelta, trailTriggerPercentage)
		);
	}

	// SHAREHOLDING ARRANGEMENT

	public void create_listElement_heldShare(
			long activeOrder_id,
			String stockTicker, String actionType, String orderType,
			double orderCount_ofThisInstance, double stockPrice_ofThisInstance, double durationInDays, double tradingDay_ofThisInstance, double halfSecond_ofThisInstance, double limitPrice, double stopPrice, double trailTriggerDelta, double trailTriggerPercentage)
	{
		heldShares.put(
				activeOrder_id,
				new StockInfo(
						stockTicker, actionType, orderType, orderCount_ofThisInstance,
						stockPrice_ofThisInstance, durationInDays, tradingDay_ofThisInstance, halfSecond_ofThisInstance, limitPrice, stopPrice, trailTriggerDelta, trailTriggerPercentage)
		);
	}
	public void delete_listElement_heldShare(long activeOrder_id) {
		heldShares.remove(activeOrder_id);
	}
}