package au.com.mebank.transactionservice.service;

import java.util.List;

import au.com.mebank.transactionservice.domain.AccountStatusRequest;
import au.com.mebank.transactionservice.domain.AccountStatusResponse;
import au.com.mebank.transactionservice.domain.AccountTransaction;
import au.com.mebank.transactionservice.repository.TransactionDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */

public class AccountServiceImpl implements AccountService {
    private TransactionDao transactionDao;
    private Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    public AccountServiceImpl(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @Override
    public AccountStatusResponse getAccountStatus(AccountStatusRequest request) {
        log.info("Received a status request for the account: {}", request.getAccountID());
        List<AccountTransaction> accountTransactions = transactionDao.getAccountTransactions(request.getAccountID(),
            request.getStartDateTime(),
            request.getEndDateTime());
        int numberOfTransactions = accountTransactions.size();
        double netAmount = accountTransactions.stream()
            .mapToDouble(AccountTransaction::getTransactionAmount)
            .sum();
        return new AccountStatusResponse(netAmount, numberOfTransactions);
    }
}
