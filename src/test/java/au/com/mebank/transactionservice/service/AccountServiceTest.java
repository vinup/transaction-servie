package au.com.mebank.transactionservice.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import au.com.mebank.transactionservice.domain.AccountStatusRequest;
import au.com.mebank.transactionservice.domain.AccountStatusResponse;
import au.com.mebank.transactionservice.repository.TransactionDao;
import au.com.mebank.transactionservice.repository.TransactionDaoImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 */
public class AccountServiceTest {

    AccountService accountService;
    TransactionDao transactionDao;
    private static final String DEFAULT_CSV_LOCATION = "src/test/resources/transactions.csv";

    @Before
    public void setUp() throws Exception {
        transactionDao = new TransactionDaoImpl(DEFAULT_CSV_LOCATION);
        accountService = new AccountServiceImpl(transactionDao);
    }
    @Ignore
    @Test
    public void testGetAccountStatus() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime startDateTime = LocalDateTime.parse("20/10/2018 12:00:00", formatter);
        LocalDateTime endDateTime = LocalDateTime.parse("20/10/2018 19:00:00", formatter);
        AccountStatusResponse response = accountService.getAccountStatus(new AccountStatusRequest("ACC334455", startDateTime, endDateTime));
        assertThat(response.getNumberOfTransactions(), is(1));
        assertThat(response.getBalanceForTheGivenRange(), is(-25.00));
    }
}