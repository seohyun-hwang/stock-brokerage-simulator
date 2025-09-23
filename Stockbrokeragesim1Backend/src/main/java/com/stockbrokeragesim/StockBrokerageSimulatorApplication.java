package com.stockbrokeragesim;
import java.util.HashMap;
import java.util.Map;

// SpringBoot imports
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;


@SpringBootApplication
@Component
public class StockBrokerageSimulatorApplication {

	double dollarCash_inYoWallet = 100000.00; // starting off with $100K IN CASH IN YOUR WALLET :DD (perfectly realistic)

	// Hashmaps <ID, qualities/quantities>
	Map<Long, StockInfo> potentialOrders = new HashMap<>();
	Map<Long, StockInfo> activeOrders = new HashMap<>();
	Map<Long, StockInfo> orderHistory = new HashMap<>();
	Map<Long, StockInfo> heldShares = new HashMap<>();
	long potentialOrder_id = 0;
	long activeOrder_id = 0;

	public double[] stockPrices_acrossHalfSeconds_presentTradingDay = new double[46800]; // 6.5 hours per trading day = 46,800 half-seconds


    public static void main(String[] args) {
		// connection to SpringBoot
		SpringApplication.run(StockBrokerageSimulatorApplication.class, args);
		System.out.println("Welcome to my stock market brokerage simulator. Disclaimer: this program ignores dividends.");

	}


	public double read_field_cashVolume() {
		return dollarCash_inYoWallet;
	}
	public void update_field_cashVolume(double increment) {
		dollarCash_inYoWallet += increment;
	}

	public double[] read_list_stockPrices_wholeTradingDay() {
		return stockPrices_acrossHalfSeconds_presentTradingDay;
	}
	public double read_listElement_stockPrice_partialTradingDay(int halfSecondsPassed) {
		return stockPrices_acrossHalfSeconds_presentTradingDay[halfSecondsPassed];
	}

	// ACTIVE-ORDER EFFECTS

	public void update_fieldAndList_cashVolumeAndHeldShares_thruOrderActivation(long activeOrder_id) {
		if (activeOrders.get(activeOrder_id).get_listElement_quality("orderType").equalsIgnoreCase("market")) {
			if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("buy")) {
				create_listElement_heldShare(activeOrder_id, );
				update_field_cashVolume(-);

				create_listElement_orderHistory(activeOrder_id, );
				delete_listElement_activeOrder(activeOrder_id);
			}
			else if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("sell")) {
				delete_listElement_heldShare(activeOrder_id);
				update_field_cashVolume();

				create_listElement_orderHistory(activeOrder_id, );
				delete_listElement_activeOrder(activeOrder_id);
			}
			else if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("short")) {
				create_listElement_heldShare(activeOrder_id, );

				create_listElement_orderHistory(activeOrder_id, );
				delete_listElement_activeOrder(activeOrder_id);
			}
			else if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("btc")) {
				update_field_cashVolume();
				delete_listElement_heldShare(activeOrder_id);

				create_listElement_orderHistory(activeOrder_id, );
				delete_listElement_activeOrder(activeOrder_id);
			}
		}
		else if (activeOrders.get(activeOrder_id).get_listElement_quality("orderType").equalsIgnoreCase("limit")) {
			if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("buy")) {
				if (activeOrders.get(activeOrder_id).get_listElement_quantity("stockPrice_ofThisInstance")
						<= activeOrders.get(activeOrder_id).get_listElement_quantity("limitPrice") )
				{
					create_listElement_heldShare(activeOrder_id, );
					update_field_cashVolume(-);
				}
			}
			else if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("sell")) {
				if (activeOrders.get(activeOrder_id).get_listElement_quantity("stockPrice_ofThisInstance")
						>= activeOrders.get(activeOrder_id).get_listElement_quantity("limitPrice") )
				{
					delete_listElement_heldShare(activeOrder_id);
					update_field_cashVolume();
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
					update_field_cashVolume();
					delete_listElement_heldShare(activeOrder_id);
				}
			}
		}
		else if (activeOrders.get(activeOrder_id).get_listElement_quality("orderType").equalsIgnoreCase("stop-loss")) {
			if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("buy")) {

			}
			else if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("sell")) {

			}
			else if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("short")) {

			}
			else if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("btc")) {

			}
		}
		else if (activeOrders.get(activeOrder_id).get_listElement_quality("orderType").equalsIgnoreCase("stop-limit")) {
			if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("buy")) {

			}
			else if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("sell")) {

			}
			else if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("short")) {

			}
			else if (activeOrders.get(activeOrder_id).get_listElement_quality("actionType").equalsIgnoreCase("btc")) {

			}
		}
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