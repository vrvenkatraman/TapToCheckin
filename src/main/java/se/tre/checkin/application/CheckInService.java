package se.tre.checkin.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import se.tre.checkin.domain.*;
import se.tre.checkin.domain.db.CheckInInfo;
import se.tre.checkin.domain.db.LocationInfo;
import se.tre.checkin.domain.db.UserDetails;
import se.tre.checkin.infrastructure.CheckInInfoRepository;
import se.tre.checkin.infrastructure.LocationInfoRepository;
import se.tre.checkin.infrastructure.UserDetailsRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class CheckInService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private CheckInInfoRepository checkInInfoRepository;

    @Autowired
    private LocationInfoRepository locationInfoRepository;

    public HttpStatus checkInLocation(CheckInRequest checkInRequest, HttpServletRequest httpServletRequest) {

        if(null == checkInRequest.getEmpId() || null == checkInRequest.getLocationId())
            return HttpStatus.BAD_REQUEST;

        try {
            boolean isCheckedIn = checkInInfoRepository.getCheckInInfoByEmpId(checkInRequest.getEmpId()).isPresent();
            if(!isCheckedIn) {
                checkInInfoRepository.checkInUser(checkInRequest.getEmpId(), checkInRequest.getLocationId());
                return HttpStatus.ACCEPTED;
            }
            else {
                return HttpStatus.ALREADY_REPORTED;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }


    }

    public ResponseEntity<?> getUserDetails(String email, HttpServletRequest httpServletRequest) {
        Optional<UserDetails> userDetailsOptional;
        userDetailsOptional = userDetailsRepository.getUserDetailsByEmail(email);

        return userDetailsOptional.<ResponseEntity<?>>map(userDetails -> new ResponseEntity<>(userDetails, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

    }

    public ResponseEntity<?> getUserLocation(String name, HttpServletRequest httpRequest) {

        UserLocationResponse userLocationResponse;
        Optional<UserDetails> userDetailsOptional;
        Optional<LocationInfo> locationInfoOptional;
        LocationInfo locationInfo = null;
        UserDetails userDetails = null;
        String locationId = null;
        userDetailsOptional = userDetailsRepository.getUserDetailsByName(name);

        if(userDetailsOptional.isPresent()) {
            userDetails = userDetailsOptional.get();
            Optional<String> locOptional = checkInInfoRepository.getCheckInInfoByEmpId(userDetails.getEmpId());
            if(locOptional.isPresent()) {
                locationInfoOptional = locationInfoRepository.getLocationInfo(locOptional.get());
                if(locationInfoOptional.isPresent()) {
                    locationInfo = locationInfoOptional.get();

                  userLocationResponse =  new UserLocationResponse(userDetails.getEmpId(),userDetails.getName(),userDetails.getEmail(),userDetails.getMobileNumber(),
                          userDetails.getRole(),userDetails.getTeam(), userDetails.getProfilePic(),locationInfo.getLocationId(),locationInfo.getFloorPlan());
                  return new ResponseEntity<UserLocationResponse>(userLocationResponse,HttpStatus.OK);
                }
            }

        }


        return new ResponseEntity(null,HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getFreeSeats(HttpServletRequest httpRequest) {

        List<String> freeSeatList =locationInfoRepository.getAvailableSeats();

        return new ResponseEntity<>(freeSeatList,HttpStatus.OK);
    }

    public ResponseEntity<?> getFreeLocations(HttpServletRequest httpRequest) {

        List<String> freeSeatList =locationInfoRepository.getAvailableSeats();
        Set<String> floors = new HashSet<>();
        Set<String> areas= new HashSet<>();
        Set<String> desks= new HashSet<>();
        List<String> floorPlans = new ArrayList<>();

        FreeSeatResponse freeSeatResponse;
        Location location;

        for (String freeSeat : freeSeatList) {
            floors.add(freeSeat.substring(1,3));
            areas.add(freeSeat.substring(4,6));
            desks.add(freeSeat.substring(7,10));

        }

        location = new Location();
        location.setFloors(floors);
        location.setAreas(areas);
        location.setDesks(desks);

        floorPlans =  locationInfoRepository.getAllLocationInfo();
        location.setFloorPlans(floorPlans);

        freeSeatResponse = new FreeSeatResponse(location);

        return new ResponseEntity<>(freeSeatResponse,HttpStatus.OK);
    }
}
