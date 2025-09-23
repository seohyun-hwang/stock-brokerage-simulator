let fetchedPrice = 0.00;

let tableRowIndex = 0;
const tableRowIndex_max = 13263; // ending on row 13,264 of the table as id-index 13,263
// Database table: from 6/5/1972 to 12/27/2024, excl. weekends

// fetching a stock price from the database



export const stockPrice_presentDay = fetchedPrice;

const submitButton_indexHTML = document.getElementById('stock-option-submission');

// Add an event listener to the button
if (submitButton_indexHTML) {
  submitButton_indexHTML.addEventListener('DOMContentLoaded', () => {
      const dropdown_stockOptions = document.getElementById('stock-options') as HTMLSelectElement;
      const dropdown_yearOptions = document.getElementById('year-options') as HTMLSelectElement;
      const dropdown_monthOptions = document.getElementById('month-options') as HTMLSelectElement;
      const dropdown_dayOptions = document.getElementById('day-options') as HTMLSelectElement;

      if (dropdown_stockOptions.value !== '' && dropdown_yearOptions.value !== '' && dropdown_monthOptions.value !== '' && dropdown_dayOptions.value !== '') {



          window.location.href = 'stock-display.ts';
    }
    else {
        window.alert('You need to specify your stock, year, month, and date!');
    }
  });
}