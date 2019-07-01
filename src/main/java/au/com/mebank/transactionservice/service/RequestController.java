package au.com.mebank.transactionservice.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

import au.com.mebank.transactionservice.domain.AccountStatusRequest;
import au.com.mebank.transactionservice.domain.AccountStatusResponse;
import au.com.mebank.transactionservice.repository.TransactionDao;
import au.com.mebank.transactionservice.repository.TransactionDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *Request handling class.
 */
public class RequestController {
    private AccountService accountService;
    private TransactionDao transactionDao;
    private Logger log = LoggerFactory.getLogger(RequestController.class);


    public RequestController(String inputFileLocation) throws Exception {
        transactionDao = new TransactionDaoImpl(inputFileLocation);
        accountService = new AccountServiceImpl(transactionDao);
    }

    public AccountStatusResponse processAccountStatusRequest(String request) {
        AccountStatusResponse response = accountService.getAccountStatus(
            buildAccountStatusRequest(request));
        log.info("Account Status: {} ", response);
        return  response;

    }

    private AccountStatusRequest buildAccountStatusRequest(String request) {
        StringTokenizer tokenizer = new StringTokenizer(request, ",");
        String accountId = tokenizer.nextToken();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime startDateTime = LocalDateTime.parse(tokenizer.nextToken(), formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(tokenizer.nextToken(), formatter);
        return new AccountStatusRequest(accountId, startDateTime, endDateTime);
    }
}
