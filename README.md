# Personal ExpenseTracker（Previous Name: Accounting_System)
## Project Description
The accounting system will allow users to manage financial records, generate informal financial reports, and ensure compliance with accounting standards. Each user can create a personal account and open multiple financial accounts. Joint accounts allow multiple users to record and plan cash flows in a synchronized account. Inflow and outflow of cash can be manually recorded in accounts with corresponding annotations. The system can generate both real time informal financial records for user interpretation. Correspondingly, automated financial planning will plan the budget provided by the user periodically, notifications will be sent regarding the status of current budget usage. Import and export features allows users to import their past records, and obtain generated pdf or csv files from their current records.

This project implements Clean Architecture and SOLID design principles to achieve segregation.

## Current Features
Currently, the basic functions including, sign up, login, logout, one time transactions, periodic transactions, text to speech financial report are available for both user and shared accounts.

## Executing the Software
Please mark src/main as the sources root and mark src/test as the test sources root.

To access the user interface, navigate to src/main/app/Main.java and run the main block. Once executed, a user interface will pop up. 

Please use the signup button to create an account and login. Users will then be able to record transactions using the transacion button.

The recorded data for the user account available once logged in, the transaction records can be found in the csv files located in src/main/data.

Once the desired transactions are recorded, the logout button is available in the home page for logging out.

To create a shared account, at least two user account must be present.

The financial report text to speech should be auto-played upon the click of the financial report.
