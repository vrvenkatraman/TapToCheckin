package se.tre.checkin.infrastructure;

import se.tre.checkin.domain.db.LocationInfo;

import java.util.Optional;

public interface LocationInfoRepository {

    Optional<LocationInfo> getLocationInfo(String locationId);
}
