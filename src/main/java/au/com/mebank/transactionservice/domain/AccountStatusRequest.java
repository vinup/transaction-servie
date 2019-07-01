package au.com.mebank.transactionservice.domain;

import java.time.LocalDateTime;

/**
 *
 */
public class AccountStatusRequest {
    private String accountID;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public AccountStatusRequest(String accountID, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.accountID = accountID;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public String getAccountID() {
        return accountID;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }
}
