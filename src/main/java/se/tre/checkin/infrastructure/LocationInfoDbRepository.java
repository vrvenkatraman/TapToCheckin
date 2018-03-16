package se.tre.checkin.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import se.tre.checkin.domain.db.LocationInfo;
import se.tre.checkin.util.DbUtil;

import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    @Override
    public List<String> getAllLocationInfo() {
        List<Map<String, Object>> floorPlanList = null;
        List<String> floorPlans = new ArrayList<>();
        try {
            floorPlanList = jdbcTemplate.queryForList("SELECT * FROM LOCATION_INFO");
           for(Map floorPlanMap : floorPlanList) {
               floorPlans.add((String) floorPlanMap.get("FLOOR_PLAN"));
           }
        }
        catch (IncorrectResultSizeDataAccessException ie) {
            logger.error("Location of given locationId not found");
            Optional.empty();
        }
        catch (Exception e) {
            logger.error("Database Error",e);
        }
        return floorPlans;
    }


    @Override
    public List<String> getAvailableSeats() {
        List<String> locationInfo = new ArrayList<>();
        try {
            locationInfo = jdbcTemplate.queryForList("SELECT  LOC.LOCATION_ID FROM LOCATION_INFO LOC  WHERE LOC.LOCATION_ID NOT IN (SELECT CHK.LOCATION_ID FROM  CHECKIN_INFO CHK)",String.class);
            logger.info("Empty ? "+locationInfo.isEmpty());
        }
        catch (IncorrectResultSizeDataAccessException ie) {
            logger.error("Location of given locationId not found");
        }
        catch (Exception e) {
            logger.error("Database Error",e);
        }
        return locationInfo;
    }

    private LocationInfo setUserDetailsResultSet(final ResultSet rs) throws SQLException {
        return new LocationInfo(rs.getString("LOCATION_ID"), DbUtil.convertClobToString(rs.getClob("FLOOR_PLAN")));
    }
}
