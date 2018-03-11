package se.tre.checkin.domain.db;

import java.sql.Blob;

public class UserDetails {
    private String empId;
    private String name;
    private String email;
    private String mobileNumber;
    private String role;
    private String team;
    private Blob profilePic;

    public UserDetails(String empId, String name, String email, String mobileNumber, String role, String team, Blob profilePic) {
        this.empId = empId;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.role = role;
        this.team = team;
        this.profilePic = profilePic;
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

    public Blob getProfilePic() {
        return profilePic;
    }
}
