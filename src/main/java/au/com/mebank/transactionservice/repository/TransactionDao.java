package au.com.mebank.transactionservice.repository;

import java.time.LocalDateTime;
import java.util.List;

import au.com.mebank.transactionservice.domain.AccountTransaction;

/**
 *
 */
public interface TransactionDao {
    public List<AccountTransaction> getAccountTransactions(String account,
                                                           LocalDateTime fromDate,
                                                           LocalDateTime toDate);
}
