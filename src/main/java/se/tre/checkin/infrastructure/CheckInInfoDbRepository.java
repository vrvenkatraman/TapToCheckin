package se.tre.checkin.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CheckInInfoDbRepository implements CheckInInfoRepository {

    private static final Logger logger = LoggerFactory.getLogger(CheckInInfoDbRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public CheckInInfoDbRepository() {

    }

    @Override
    public void checkInUser(String empId,String locId) throws Exception {

        jdbcTemplate.update("INSERT INTO CHECKIN_INFO (EMP_ID, LOCATION_ID) "
                            + "VALUES (? , ?) ", empId, locId);

    }

    @Override
    public Optional<String> getCheckInInfoByEmpId(String empId) {

        String locId = null;
        try {
            locId = jdbcTemplate.queryForObject("SELECT LOCATION_ID FROM CHECKIN_INFO WHERE EMP_ID = ?", new Object[]{empId}, String.class);
        }
        catch (IncorrectResultSizeDataAccessException ie) {
            logger.info("Location of given EmpId not found");
            Optional.empty();
        }
        catch (Exception e) {
            logger.error("Database Error",e);
        }
        return Optional.ofNullable(locId);
    }

}
