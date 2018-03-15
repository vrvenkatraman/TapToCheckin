package se.tre.checkin.domain;

public class FreeSeatResponse {

    private Location location;

    public FreeSeatResponse(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

}
