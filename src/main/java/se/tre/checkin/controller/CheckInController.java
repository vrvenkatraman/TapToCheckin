package se.tre.checkin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.tre.checkin.application.CheckInService;
import se.tre.checkin.domain.CheckInRequest;


import javax.servlet.http.HttpServletRequest;

@RestController
public class CheckInController {

    @Autowired
    private CheckInService checkInService;

    @RequestMapping(value = "/checkin/mylocation", method = { RequestMethod.POST })
    public HttpStatus checkIn(@RequestBody CheckInRequest checkInRequest, HttpServletRequest httpRequest) {

        return checkInService.checkInLocation(checkInRequest,httpRequest);

    }
    @RequestMapping(value = "/checkin/getUserDetails", method = { RequestMethod.GET })
    public ResponseEntity<?> getUserDetails(@RequestParam(value = "email") String email, HttpServletRequest httpRequest) {

        return checkInService.getUserDetails(email,httpRequest);

    }

    @RequestMapping(value = "/checkin/getUserLocation", method = { RequestMethod.GET })
    public ResponseEntity<?> getUserLocation(@RequestParam String name, HttpServletRequest httpRequest) {

        return checkInService.getUserLocation(name,httpRequest);

    }


}
