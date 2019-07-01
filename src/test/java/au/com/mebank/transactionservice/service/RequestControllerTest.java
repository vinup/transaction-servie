package au.com.mebank.transactionservice.service;

import au.com.mebank.transactionservice.domain.AccountStatusResponse;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 */
public class RequestControllerTest {

    RequestController requestController;
    private static final String DEFAULT_CSV_LOCATION = "src/test/resources/transactions.csv";

    @Before
    public void before() throws Exception{
        requestController = new RequestController(DEFAULT_CSV_LOCATION);
    }

    @Test
    public void processAccountStatusRequest() {
        AccountStatusResponse response = requestController
            .processAccountStatusRequest("ACC334455,20/10/2018 12:00:00,20/10/2018 19:00:00");
        assertThat(response.getNumberOfTransactions(), is(1));
        assertThat(response.getBalanceForTheGivenRange(), is(-25.00));
    }
}