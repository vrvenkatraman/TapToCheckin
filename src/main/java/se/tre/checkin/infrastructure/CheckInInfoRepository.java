package se.tre.checkin.infrastructure;

import java.util.Optional;

public interface CheckInInfoRepository {

    void checkInUser(String empId,String locId) throws Exception;

    Optional<String> getCheckInInfoByEmpId(String empId);

}
