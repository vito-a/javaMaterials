package ua.training.model;

import org.springframework.beans.factory.annotation.Autowired;
import ua.training.model.dao.CustomerPreferenceDao;
import ua.training.repository.MovieCatalog;

public class MovieRecommender {

    @Autowired
    private MovieCatalog movieCatalog;

    private CustomerPreferenceDao customerPreferenceDao;

    @Autowired
    public MovieRecommender(CustomerPreferenceDao customerPreferenceDao) {
        this.customerPreferenceDao = customerPreferenceDao;
    }

    // ...
}