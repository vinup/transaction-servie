package au.com.mebank.transactionservice.domain;

/**
 *
 */
public class AccountTransaction {
    private double transactionAmount;

    public AccountTransaction(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    @Override
    public String toString() {
        return "AccountTransaction{" +
            "transactionAmount=" + transactionAmount +
            '}';
    }
}
