package se.tre.checkin.domain.db;

public class CheckInInfo {
    private String empId;
    private String locationId;

    public CheckInInfo(String empId, String locationId) {
        this.empId = empId;
        this.locationId = locationId;
    }

    public String getEmpId() {
        return empId;
    }

    public String getLocationId() {
        return locationId;
    }
}
