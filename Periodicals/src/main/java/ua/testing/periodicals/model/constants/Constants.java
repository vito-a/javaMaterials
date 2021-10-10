package ua.testing.periodicals.model.constants;

public interface Constants {
    // Users
    String USER_ID         = "user_id";
    String USER_LOGIN      = "login";
    String USER_ROLE_ID    = "role_id";
    String USER_PASSWORD   = "password";
    String USER_STATUS     = "status";
    String USER_ACCOUNT    = "account";

    // Periodicals
    String PERIODICAL_ID   = "periodical_id";
    String PRICE           = "price";

    // Categories
    String NAME = "name";
    String CATEGORY_ID     = "cat_id";
    String DESCRIPTION     = "description";

    // Roles
    long ROLE_ID_ADMIN     = 1;
    long ROLE_ID_USER      = 2;

    // Status
    int STATUS_ACTIVE      = 1;
    int STATUS_BLOCKED     = 0;

    // Subscription
    String SUBSCRIPTION_ID = "sub_id";
    String START_DATE      = "startdate";
    String END_DATE        = "enddate";
}