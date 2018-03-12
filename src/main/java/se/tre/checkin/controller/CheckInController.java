package se.tre.checkin.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.tre.checkin.application.CheckInService;
import se.tre.checkin.domain.CheckInRequest;
import se.tre.checkin.domain.UserLocationResponse;
import se.tre.checkin.domain.db.UserDetails;


import javax.servlet.http.HttpServletRequest;

@RestController
public class CheckInController {

    @Autowired
    private CheckInService checkInService;

    @ApiOperation(value = "CheckIn User", notes = "This endpoint is used to checkin the location of the user",produces = "application/json", response = HttpStatus.class)
    @ResponseStatus( HttpStatus.OK )
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code= 404, message = "Not found")
    })
    @RequestMapping(value = "/checkin/mylocation", method = { RequestMethod.POST })
    public HttpStatus checkIn(@RequestBody CheckInRequest checkInRequest, HttpServletRequest httpRequest) {

        return checkInService.checkInLocation(checkInRequest,httpRequest);

    }

    @ApiOperation(value = "Get User Details", notes = "This endpoint gives the details of the user",produces = "application/json", response = UserDetails.class)
    @ResponseStatus( HttpStatus.OK )
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code= 404, message = "Not found")
    })
    @RequestMapping(value = "/checkin/getUserDetails", method = { RequestMethod.GET })
    public ResponseEntity<?> getUserDetails(@RequestParam String email, HttpServletRequest httpRequest) {

        return checkInService.getUserDetails(email,httpRequest);

    }

    @ApiOperation(value = "Get User Location", notes = "This endpoint gives the location of the user",produces = "application/json", response = UserLocationResponse.class)
    @ResponseStatus( HttpStatus.OK )
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code= 404, message = "Not found")
    })
    @RequestMapping(value = "/checkin/getUserLocation", method = { RequestMethod.GET })
    public ResponseEntity<?> getUserLocation(@RequestParam String name, HttpServletRequest httpRequest) {

        return checkInService.getUserLocation(name,httpRequest);

    }


}
