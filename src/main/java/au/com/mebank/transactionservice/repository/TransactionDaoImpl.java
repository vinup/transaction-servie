package au.com.mebank.transactionservice.repository;

import java.io.FileReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import au.com.mebank.transactionservice.domain.AccountTransaction;
import au.com.mebank.transactionservice.domain.Transaction;
import au.com.mebank.transactionservice.domain.TransactionType;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;

/**
 * This is the implementation class for
 */
public class TransactionDaoImpl implements TransactionDao {
    private Map<String, Transaction> allTransactions;

    public TransactionDaoImpl(String inputFileLocation) throws Exception {
        this.allTransactions = readTransactionsFromCSV(inputFileLocation);
    }

    protected static Map<String, Transaction> readTransactionsFromCSV(String inputFileLocation) throws Exception {
        Map<String, Transaction> transactionMap = new HashMap<>();
        Reader in = new FileReader(inputFileLocation);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
            .withIgnoreSurroundingSpaces()
            .parse(in);

        for (CSVRecord record : records) {
            String transactionID = record.get(0);
            String fromAccount = record.get(1);
            String toAccount = record.get(2);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            LocalDateTime transactionDate = LocalDateTime.parse(record.get(3), dateTimeFormatter);

            Double amount = Double.parseDouble(record.get(4));
            TransactionType transactionType = TransactionType.valueOf(record.get(5));
            Transaction transaction = null;

            if (transactionType.equals(TransactionType.REVERSAL) && record.size() > 6) {
                String originalTransactionId = record.get(6);
                transaction = (Transaction) transactionMap.get(originalTransactionId);
                transaction.setReversed(true);
                transaction.setReversalDate(transactionDate);
                transaction.setReversalTransactionId(transactionID);

            } else {
                transaction = new Transaction(transactionID,
                    fromAccount,
                    toAccount,
                    transactionDate,
                    amount,
                    transactionType);
            }
            transactionMap.put(transaction.getTransactionID(), transaction);

        }
        return transactionMap;
    }

    @Override
    public List<AccountTransaction> getAccountTransactions(String accountId,
                                                           LocalDateTime fromDate,
                                                           LocalDateTime toDate) {
        return allTransactions
            .values()
            .stream()
            .filter(transaction -> isAMatchingTransaction(transaction, accountId, fromDate, toDate))
            .map(transaction -> getAccountTransaction(transaction, accountId))
            .collect(Collectors.toList());
    }

    private AccountTransaction getAccountTransaction(Transaction transaction, String accountId) {
        double transactionAmount = 0;
        if (StringUtils.equals(accountId, transaction.getFromAccountId())) {
            transactionAmount = 0 - transaction.getAmount();
        } else {
            transactionAmount = transaction.getAmount();
        }
        return new AccountTransaction(transactionAmount);
    }

    private boolean isDateWithIntheRange(LocalDateTime givenDateTime, LocalDateTime stardDateTime, LocalDateTime endDateTime) {
        return !(givenDateTime.isBefore(stardDateTime) || givenDateTime.isAfter(endDateTime));
    }

    private boolean isTransactionForTheGivenAccount(Transaction transaction, String givenAccount) {
        return StringUtils.equals(givenAccount, transaction.getFromAccountId())
            || StringUtils.equals(givenAccount, transaction.getToAccountId());
    }

    private boolean isNotAReversal(Transaction transaction) {
        return !transaction.isReversed();
    }

    private boolean isAMatchingTransaction(Transaction transaction, String accountId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return isTransactionForTheGivenAccount(transaction, accountId)
            && isDateWithIntheRange(transaction.getTransactionDate(), startDateTime, endDateTime)
            && isNotAReversal(transaction);
    }

}
