package se.tre.checkin.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import se.tre.checkin.domain.db.LocationInfo;
import se.tre.checkin.util.DbUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class LocationInfoDbRepository implements LocationInfoRepository {

    private static final Logger logger = LoggerFactory.getLogger(LocationInfoDbRepository.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Optional<LocationInfo> getLocationInfo(String locationId) {
        LocationInfo locationInfo = null;
        try {
            locationInfo = jdbcTemplate.queryForObject("SELECT LOCATION_ID, FLOOR_PLAN FROM LOCATION_INFO WHERE LOCATION_ID = ?",
                    new Object[]{locationId}, (rs, rowNum) -> setUserDetailsResultSet(rs));
        }
        catch (IncorrectResultSizeDataAccessException ie) {
            logger.error("Location of given locationId not found");
            Optional.empty();
        }
        catch (Exception e) {
            logger.error("Database Error",e);
        }
        return Optional.ofNullable(locationInfo);
    }

    private LocationInfo setUserDetailsResultSet(final ResultSet rs) throws SQLException {
        return new LocationInfo(rs.getString("LOCATION_ID"), DbUtil.convertBlobToString(rs.getClob("FLOOR_PLAN")));
    }
}
