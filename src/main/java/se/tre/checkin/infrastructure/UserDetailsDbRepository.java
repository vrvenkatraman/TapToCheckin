package se.tre.checkin.infrastructure;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import se.tre.checkin.domain.db.UserDetails;
import se.tre.checkin.util.DbUtil;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.StringWriter;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class UserDetailsDbRepository implements UserDetailsRepository {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsDbRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserDetailsDbRepository() {

    }

    @Override
    public Optional<UserDetails> getUserDetailsByEmail(String email) {

        UserDetails userDetails = null;

        try {

            userDetails = jdbcTemplate.queryForObject("SELECT EMP_ID, NAME, EMAIL, MOBILE_NUMBER, ROLE, TEAM, PROFILE_PIC "
                            + " FROM USER_DETAILS WHERE EMAIL = ?",
                    new Object[]{email}, (rs, rowNum) -> setUserDetailsResultSet(rs));
        } catch (IncorrectResultSizeDataAccessException se) {
            return Optional.empty();
        } catch (Exception e) {
            logger.error("Exception while querying database", e);
        }

        return Optional.ofNullable(userDetails);
    }

    @Override
    public Optional<UserDetails> getUserDetailsByName(String name) {

        UserDetails userDetails = null;

        try {

            userDetails = jdbcTemplate.queryForObject("SELECT EMP_ID, NAME, EMAIL, MOBILE_NUMBER, ROLE, TEAM, PROFILE_PIC "
                            + " FROM USER_DETAILS WHERE UPPER(NAME) = ?",
                    new Object[]{name.toUpperCase()}, (rs, rowNum) -> setUserDetailsResultSet(rs));
        } catch (IncorrectResultSizeDataAccessException se) {
            return Optional.empty();
        } catch (Exception e) {
            logger.error("Exception while querying database", e);
        }

        return Optional.ofNullable(userDetails);
    }


    private UserDetails setUserDetailsResultSet(final ResultSet rs) throws SQLException {

        return new UserDetails(rs.getString("EMP_ID"), rs.getString("NAME"), rs.getString("EMAIL"), rs.getString("MOBILE_NUMBER"),
                rs.getString("ROLE"), rs.getString("TEAM"), DbUtil.convertClobToString(rs.getClob("PROFILE_PIC")));

    }


}
