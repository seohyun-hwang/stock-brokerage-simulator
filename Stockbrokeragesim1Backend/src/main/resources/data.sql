CREATE TABLE IF NOT EXISTS cs_project_table_stockBrokerageSim1 {
    id INT PRIMARY KEY,
    dateData DATE,
    gePrices DECIMAL(8,2),
    koPrices DECIMAL(8,2),
    googlPrices DECIMAL(8,2),
    aaplPrices DECIMAL(8,2),
    msftPrices DECIMAL(8,2),
    amznPrices DECIMAL(8,2),
    nvdaPrices DECIMAL(8,2),
    avgoPrices DECIMAL(8,2),
    costPrices DECIMAL(8,2),
    metaPrices DECIMAL(8,2),
    nflxPrices DECIMAL(8,2),
    pepPrices DECIMAL(8,2),
    tslaPrices DECIMAL(8,2),
    pgPrices DECIMAL(8,2),
    clPrices DECIMAL(8,2),
    xomPrices DECIMAL(8,2),
    ibmPrices DECIMAL(8,2)
};

INSERT INTO stockOption (id, dateData, gePrices, koPrices, googlPrices, aaplPrices, msftPrices, amznPrices, nvdaPrices, avgoPrices, costPrices, metaPrices, nflxPrices, pepPrices, tslaPrices, pgPrices, clPrices, xomPrices, ibmPrices)
AS SELECT id, dateData, gePrices, koPrices, googlPrices, aaplPrices, msftPrices, amznPrices, nvdaPrices, avgoPrices, costPrices, metaPrices, nflxPrices, pepPrices, tslaPrices, pgPrices, clPrices, xomPrices, ibmPrices
FROM CSVREAD('classpath:cs_project_table_stockBrokerageSim1.csv');
