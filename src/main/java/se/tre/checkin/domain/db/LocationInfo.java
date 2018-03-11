package se.tre.checkin.domain.db;

import java.sql.Blob;

public class LocationInfo {
    private String locationId;
    private Blob floorPlan;

    public LocationInfo(String locationId, Blob floorPlan) {
        this.locationId = locationId;
        this.floorPlan = floorPlan;
    }

    public String getLocationId() {
        return locationId;
    }

    public Blob getFloorPlan() {
        return floorPlan;
    }
}
