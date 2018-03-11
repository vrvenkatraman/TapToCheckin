package se.tre.checkin.domain;

public class UserLocationResponse {

    private String empId;
    private String name;
    private String email;
    private String mobileNumber;
    private String role;
    private String team;
    private Object profilePic;
    private String locationId;
    private Object floorPlan;

    public UserLocationResponse(String empId, String name, String email, String mobileNumber, String role, String team, Object profilePic, String locationId, Object floorPlan) {
        this.empId = empId;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.role = role;
        this.team = team;
        this.profilePic = profilePic;
        this.locationId = locationId;
        this.floorPlan = floorPlan;
    }

    public String getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getRole() {
        return role;
    }

    public String getTeam() {
        return team;
    }

    public Object getProfilePic() {
        return profilePic;
    }

    public String getLocationId() {
        return locationId;
    }

    public Object getFloorPlan() {
        return floorPlan;
    }
}
