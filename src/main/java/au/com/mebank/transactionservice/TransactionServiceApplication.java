package au.com.mebank.transactionservice;

import au.com.mebank.transactionservice.service.RequestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main class for the transaction service application.
 */
public class TransactionServiceApplication {

    private static final String DEFAULT_CSV_LOCATION = "src/main/resources/transactions.csv";
    private static Logger log = LoggerFactory.getLogger(TransactionServiceApplication.class);

    /**
     * Main method which takes the csv file location as input.
     *
     * @param args
     */
    public static void main(String[] args) {

        try {
            log.info(args[0]);
            RequestController requestController = new RequestController(DEFAULT_CSV_LOCATION);
            requestController.processAccountStatusRequest(args[0]);
        } catch (Exception ex) {
            log.error("Exception handling the request", ex);
        }
    }
}
