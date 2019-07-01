package au.com.mebank.transactionservice.service;

import au.com.mebank.transactionservice.domain.AccountStatusRequest;
import au.com.mebank.transactionservice.domain.AccountStatusResponse;

/**
 *
 */
public interface AccountService {

    public AccountStatusResponse getAccountStatus(AccountStatusRequest request);

}
