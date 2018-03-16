package se.tre.checkin.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Clob;

public class DbUtil {

    private static Logger logger = LoggerFactory.getLogger(DbUtil.class);

    public static String convertClobToString(Clob clob) {
        if(null != clob) {
            try {
                return clob.getSubString(1,(int)clob.length());
            } catch (Exception e) {
                logger.error("Could not convert blob to String object", e);
            }
        }
        return null;
    }
}
