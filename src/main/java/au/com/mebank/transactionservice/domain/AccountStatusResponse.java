package au.com.mebank.transactionservice.domain;

/**
 *
 */
public class AccountStatusResponse {
    private double balanceForTheGivenRange;
    private int numberOfTransactions;

    public AccountStatusResponse(double balanceForTheGivenRange, int numberOfTransactionsInTheGivenRange) {
        this.balanceForTheGivenRange = balanceForTheGivenRange;
        this.numberOfTransactions = numberOfTransactionsInTheGivenRange;
    }

    public double getBalanceForTheGivenRange() {
        return balanceForTheGivenRange;
    }

    public int getNumberOfTransactions() {
        return numberOfTransactions;
    }

    @Override
    public String toString() {
        return "\nRelative balance for the period:" + balanceForTheGivenRange
            + "\nNumber of transactions included:" + numberOfTransactions;
    }
}
