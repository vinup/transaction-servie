package au.com.mebank.transactionservice.domain;

import java.time.LocalDateTime;

/**
 * Domain class for transaction.
 */
public class Transaction {

    private String transactionID;
    private String fromAccountId;
    private String toAccountId;
    private LocalDateTime transactionDate;
    private Double amount;
    private TransactionType transactionType;
    private boolean isReversed;
    private LocalDateTime reversalDate;
    private String reversalTransactionId;

    public Transaction(String transactionID, String fromAccountId, String toAccountId, LocalDateTime transactionDate, Double amount, TransactionType transactionType) {
        this.transactionID = transactionID;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(String fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(String toAccountId) {
        this.toAccountId = toAccountId;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public boolean isReversed() {
        return isReversed;
    }

    public void setReversed(boolean reversed) {
        isReversed = reversed;
    }

    public LocalDateTime getReversalDate() {
        return reversalDate;
    }

    public void setReversalDate(LocalDateTime reversalDate) {
        this.reversalDate = reversalDate;
    }

    public String getReversalTransactionId() {
        return reversalTransactionId;
    }

    public void setReversalTransactionId(String reversalTransactionId) {
        this.reversalTransactionId = reversalTransactionId;
    }

    @Override
    public String toString() {
        return "Transaction{" +
            "transactionID='" + transactionID + '\'' +
            ", fromAccountId='" + fromAccountId + '\'' +
            ", toAccountId='" + toAccountId + '\'' +
            ", transactionDate=" + transactionDate +
            ", amount=" + amount +
            ", transactionType=" + transactionType +
            ", isReversed=" + isReversed +
            ", reversalDate=" + reversalDate +
            ", reversalTransactionId='" + reversalTransactionId + '\'' +
            '}';
    }
}
