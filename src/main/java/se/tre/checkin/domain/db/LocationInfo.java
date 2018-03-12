package se.tre.checkin.domain.db;

import java.sql.Clob;

public class LocationInfo {
    private String locationId;
    private String floorPlan;

    public LocationInfo(String locationId, String floorPlan) {
        this.locationId = locationId;
        this.floorPlan = floorPlan;
    }

    public String getLocationId() {
        return locationId;
    }

    public String getFloorPlan() {
        return floorPlan;
    }
}
