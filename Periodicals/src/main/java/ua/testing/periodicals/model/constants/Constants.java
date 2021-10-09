package ua.testing.periodicals.model.constants;

public interface Constants {
    // Users
    String USER_ID       = "user_id";
    String USER_LOGIN    = "login";
    String USER_ROLE_ID  = "role_id";
    String USER_PASSWORD = "password";
    String USER_STATUS   = "status";
    String USER_ACCOUNT  = "account";

    // Categories
    String CATEGORY_ID   = "cat_id";
    String CATEGORY_NAME = "name";

    // Roles
    long ROLE_ID_ADMIN    = 1;
    long ROLE_ID_USER     = 2;

    // Status
    int STATUS_ACTIVE    = 1;
    int STATUS_BLOCKED   = 0;

    //Subscription
    String SUBSCRIPTION_ID            = "sub_id";
    String SUBSCRIPTION_USER_ID       = "user_id";
    String SUBSCRIPTION_PERIODICAL_ID = "periodical_id";
    String SUBSCRIPTION_START_DATE    = "startdate";
    String SUBSCRIPTION_END_DATE      = "enddate";
}