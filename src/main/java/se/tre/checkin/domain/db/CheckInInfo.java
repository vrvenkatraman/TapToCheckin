package se.tre.checkin.domain.db;

public class CheckInInfo {
    private String empId;
    private String locId;

    public CheckInInfo(String empId, String locId) {
        this.empId = empId;
        this.locId = locId;
    }

    public String getEmpId() {
        return empId;
    }

    public String getLocId() {
        return locId;
    }
}
