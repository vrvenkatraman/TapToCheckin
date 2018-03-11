package se.tre.checkin.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CheckInRequest {

    private String empId;
    private String locationId;

    public CheckInRequest() {
    }

    public CheckInRequest(String empId, String locationId) {
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
