PROJECT STILL IN PROGRESS!<br>
<b>Description:</b> a stock market brokerage program for diverse trading-practice (incl. market/limit/stop-loss/stop-limit/trailing-stop day/GTC orders, buy/sell/short/buy-to-cover actions) on historical stock market prices (Google Finance, 1972-2024); implemented in Java, ReactJS (HTML, CSS, TypeScript), and SQL using SpringBoot (project: Maven; database: H2) and JDBC.
<br>
<b>Practice mission:</b> SQL, H2 Database, SpringBoot, REST API, stock market concepts, HashMaps, TypeScript/ReactJS

Implemented in Java version 17.
Backend Java source code found in `src/main/java/com.stockbrokeragesim`.
Main-method (Java) found in `src/main/java/com.stockbrokeragesim` as `StockBrokerageSimulatorApplication`.

Yet to do: 2 more order-types (trailing-stop-loss, trailing-stop-limit), stock-order history tracking, frontend styling, stock-price display, backend-frontend connection


# Types of actions and orders simulated by this program
Stock actions: 
1. Buy
2. Sell
3. Short
4. Buy-To-Cover (BTC)

Stock orders:
1. Market
2. Limit
3. Stop-Loss
4. Stop-Limit
5. Trailing-Stop-Loss
6. Trailing-Stop-Limit

Updates to cash-wallet and stockholding-portfolio in response to different combinations of stock actions/orders
1. Buy (Market): Add share to portfolio --> Deduct from cash-wallet: stock-price of next half-second
2. Buy (Limit): Wait for stock-price to be same as or lower than limit-price --> Add share to portfolio --> Deduct from cash-wallet: stock-price at the instance
3. Buy (Stop-Loss): Wait for stock-price to pass the stop-price --> Add share to portfolio --> Deduct from cash-wallet: stock-price of present half-second
4. Buy (Stop-Limit): Wait for stock-price to pass the stop-price --> Convert active-share to limit-only
5. Buy (Trailing-Stop-Loss): Move stop-price down if stock-price passes a specific trailing condition; Wait for stock-price to pass the stop-price --> Add share to portfolio --> Deduct from cash-wallet: stock-price of next half-second
6. Buy (Trailing-Stop-Limit): Move stop-price down if stock-price passes a specific trailing condition; Wait for stock-price to pass the stop-price --> Convert active-share to limit-only
6. Sell (Market): Remove share from portfolio --> Add to cash-wallet: stock-price of next half-second
6. Sell (Limit): Wait for stock-price to be same as or higher than limit-price --> Remove share from portfolio --> Add to cash-wallet: stock-price at the instance
7. Sell (Stop-Loss): Wait for stock-price to pass the stop-price --> Add to cash-wallet: stock-price of present half-second --> Remove share from portfolio
8. Sell (Stop-Limit): Wait for stock-price to pass the stop-price --> Convert active-share to limit-only
9. Sell (Trailing-Stop-Loss): Move stop-price up if stock-price passes a specific trailing condition; Wait for stock-price to pass the stop-price --> Remove share from portfolio --> Add to cash-wallet: stock-price of next half-second
10. Sell (Trailing-Stop-Limit): Move stop-price up if stock-price passes a specific trailing condition; Wait for stock-price to pass the stop-price --> Convert active-share to limit-only
11. Short (Market): Add share to portfolio
12. Short (Limit): Wait for stock-price to be same as or higher than limit-price --> Add share to portfolio
13. Short (Stop-Loss): Wait for stock-price to pass the stop-price --> Add share to portfolio
14. Short (Stop-Limit): Wait for stock-price to pass the stop-price --> Convert active-share to limit-only
15. Short (Trailing-Stop-Loss): Move stop-price up if stock-price passes a specific trailing condition; Wait for stock-price to pass the stop-price --> Add share to portfolio
16. Short (Trailing-Stop-Limit): Move stop-price up if stock-price passes a specific trailing condition; Wait for stock-price to pass the stop-price --> Convert active-share to limit-only
17. BTC (Market): Add/deduct from cash-wallet: stock-price at short-instance minus stock-price at next half-second --> Remove share from portfolio
18. BTC (Limit): Wait for stock-price to be same as or lower than limit-price --> Add/deduct from cash-wallet: stock-price at short-instance minus stock-price at this instance --> Remove share from portfolio
19. BTC (Stop-Loss): Wait for stock-price to pass the stop-price --> Add/deduct from cash-wallet: stock-price at short-instance minus stock-price at next half-second --> Remove share from portfolio
20. BTC (Stop-Limit): Wait for stock-price to pass the stop-price --> Convert active-share to limit-only
21. BTC (Trailing-Stop-Loss): Move stop-price down if stock-price passes a specific trailing condition; Wait for stock-price to pass the stop-price --> Add/deduct from cash-wallet: stock-price at short-instance minus stock-price at next half-second --> Remove share from portfolio
22. BTC (Trailing-Stop-Limit): Move stop-price down if stock-price passes a specific trailing condition; Wait for stock-price to pass the stop-price --> Convert active-share to limit-only


# Additionally installed dependencies
Backend (SpringBoot):
1. spring-boot-starter-web (3.5.5)
2. spring-boot-starter-jdbc (3.5.5)
3. spring-boot-starter-jpa (3.5.5)
4. spring-boot-devtools (3.5.5)
5. h2 (2.3.232)
6. lombok (1.18.38)

Frontend (ReactJS/Vite):
1. react-router-dom (7.9.2)
2. react-bootstrap (2.10.10)
3. bootstrap (5.3.8)

<br><br>

Thank you for your attention.
~ Seohyun