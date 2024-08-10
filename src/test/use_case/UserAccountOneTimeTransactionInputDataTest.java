package use_case;

import org.junit.jupiter.api.Test;
import use_case.transaction.one_time.OneTimeTransactionInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserAccountOneTimeTransactionInputDataTest {
    private class ConcreteOneTimeTransactionInputData extends OneTimeTransactionInputData {
        /**
         * Constructs a TransactionInputData object with the specified details.
         *
         * @param id
         * @param transactionAmount      the amount of the transaction
         * @param transactionDescription the description of the transaction
         * @param transactionCategory    the category of the transaction
         * @param transactionDate
         */
        public ConcreteOneTimeTransactionInputData(String id, String transactionAmount, String transactionDescription, String transactionCategory, String transactionDate) {
            super(id, transactionAmount, transactionDescription, transactionCategory, transactionDate);
        }
    }

    @Test
    public void testOneTimeTransactionInputData() {
        String transactionAmount = "100.0";
        String identification = "id999";
        String transactionDate = "2024-07-17";
        String transactionDescription = "Grocery Shopping";
        String transactionCategory = "Food";

        OneTimeTransactionInputData inputData = new ConcreteOneTimeTransactionInputData(
                identification,
                transactionAmount,
                transactionDate,
                transactionDescription,
                transactionCategory
        );

        assertEquals(transactionAmount, inputData.getTransactionAmount());
        assertEquals(identification, inputData.getId());
        assertEquals(transactionDate, inputData.getTransactionDate());
        assertEquals(transactionDescription, inputData.getTransactionDescription());
        assertEquals(transactionCategory, inputData.getTransactionCategory());
    }
}

