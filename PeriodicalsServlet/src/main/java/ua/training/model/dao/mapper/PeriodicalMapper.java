package ua.training.model.dao.mapper;

import ua.training.model.entity.Category;
import ua.training.model.entity.Periodical;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class PeriodicalMapper implements ObjectMapper<Periodical> {

    @Override
    public Periodical extractFromResultSet(ResultSet rs) throws SQLException {
        Periodical periodical = new Periodical();
        periodical.setPeriodicalId(rs.getLong("periodical_id"));
        periodical.setName(rs.getString("name"));
        return periodical;
    }

    public Periodical makeUnique(Map<Long, Periodical> cache,
                               Periodical periodical) {
        cache.putIfAbsent(periodical.getPeriodicalId(), periodical);
        return cache.get(periodical.getPeriodicalId());
    }
}
