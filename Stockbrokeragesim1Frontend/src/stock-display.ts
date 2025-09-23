import { stockPrice_presentDay } from './index';

const randomizedPrices_staticArray = new Array<number>(46800); // 6.5 hours per trading day = 46,800 half-seconds

async function sleep(ms: number): Promise<void> {
    return new Promise((resolve) => setTimeout(resolve, ms));
}

// populate the array with pseudo-randomized values about the present-day price of the selected stock
for (let i = 0; i < 46800; i++) {
    randomizedPrices_staticArray[i] = Math.random() * (stockPrice_presentDay * (1.25 - 0.75)) + (stockPrice_presentDay * 0.75);
}

async function timerLoop(): Promise<void> {
    for (let i = 0; i < 46800; i++) {
      // check for user-input
      // use randomizedPrices_staticArray[i]
      await sleep(500); // half-second cooldown
    }
}
timerLoop();