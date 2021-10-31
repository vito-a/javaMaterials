package ua.training.model.dao.mapper;

import ua.training.model.entity.Subscription;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class SubscriptionMapper implements ObjectMapper<Subscription> {

    @Override
    public Subscription extractFromResultSet(ResultSet rs) throws SQLException {
        Subscription subscription = new Subscription();
        subscription.setSubId(rs.getLong("sub_id"));
        subscription.setUserId(rs.getLong("user_id"));
        subscription.setPeriodicalId(rs.getLong("periodical_id"));
        subscription.setStartDate(rs.getDate("startdate").toLocalDate());
        subscription.setEndDate(rs.getDate("enddate").toLocalDate());
        return subscription;
    }

    public Subscription makeUnique(Map<Long, Subscription> cache,
                               Subscription subscription) {
        cache.putIfAbsent(subscription.getSubId(), subscription);
        return cache.get(subscription.getSubId());
    }
}
