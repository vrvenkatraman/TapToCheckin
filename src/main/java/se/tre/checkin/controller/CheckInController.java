package se.tre.checkin.controller;

import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.tre.checkin.application.CheckInService;
import se.tre.checkin.domain.CheckInRequest;
import se.tre.checkin.domain.FreeSeatResponse;
import se.tre.checkin.domain.UserLocationResponse;
import se.tre.checkin.domain.db.LocationInfo;
import se.tre.checkin.domain.db.UserDetails;
import se.tre.checkin.infrastructure.CheckInInfoDbRepository;


import javax.servlet.http.HttpServletRequest;

@RestController
public class CheckInController {

    private static final Logger logger = LoggerFactory.getLogger(CheckInController.class);

    @Autowired
    private CheckInService checkInService;

    @ApiOperation(value = "CheckIn User", notes = "This endpoint is used to checkin the location of the user",produces = "application/json", response = HttpStatus.class)
    @ApiResponses({
            @ApiResponse(code = 202, message = "Accepted"),
            @ApiResponse(code= 208, message = "Already Reported"),
            @ApiResponse(code= 500, message = "Internal Server Error")
    })
    @RequestMapping(value = "/checkin/mylocation", method = { RequestMethod.POST })
    public ResponseEntity<?> checkIn(@RequestBody CheckInRequest checkInRequest, HttpServletRequest httpRequest) {

        HttpStatus httpStatus = checkInService.checkInLocation(checkInRequest,httpRequest);
        return new ResponseEntity<>(null,httpStatus);

    }

    @ApiOperation(value = "Get User Details", notes = "This endpoint gives the details of the user",produces = "application/json", response = UserDetails.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code= 404, message = "Not found")
    })
    @RequestMapping(value = "/checkin/getUserDetails", method = { RequestMethod.GET })
    public ResponseEntity<?> getUserDetails(@RequestParam String email, HttpServletRequest httpRequest) {

        return checkInService.getUserDetails(email,httpRequest);

    }

    @ApiOperation(value = "Get User Location", notes = "This endpoint gives the location of the user",produces = "application/json", response = UserLocationResponse.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code= 404, message = "Not found")
    })
    @RequestMapping(value = "/checkin/getUserLocation", method = { RequestMethod.GET })
    public ResponseEntity<?> getUserLocation(@RequestParam String name, HttpServletRequest httpRequest) {

        return checkInService.getUserLocation(name,httpRequest);

    }

    @ApiOperation(value = "Get Free Desks", notes = "This endpoint gives the details of the free available desk",produces = "application/json", response = LocationInfo.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code= 404, message = "Not found")
    })
    @RequestMapping(value = "/checkin/getFreeSeats", method = { RequestMethod.GET })
    public ResponseEntity<?> getFreeSeats(HttpServletRequest httpRequest) {

        return checkInService.getFreeSeats(httpRequest);

    }

    @ApiOperation(value = "Get Free Locations", notes = "This endpoint gives the details of the free available locations",produces = "application/json", response = FreeSeatResponse.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code= 404, message = "Not found")
    })
    @RequestMapping(value = "/checkin/getFreeLocations", method = { RequestMethod.GET })
    public ResponseEntity<?> getFreeLocations(HttpServletRequest httpRequest) {

        return checkInService.getFreeLocations(httpRequest);

    }


}
