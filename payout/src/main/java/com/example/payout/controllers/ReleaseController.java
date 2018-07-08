package com.example.payout.controllers;

import com.example.payout.domain.FundRelease;
import com.example.payout.services.FundReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReleaseController {

    private FundReleaseService releaseService;

    @Autowired
    public ReleaseController(FundReleaseService releaseService) {
        this.releaseService = releaseService;
    }

    @RequestMapping(value = "/releases", method = RequestMethod.POST)
    public ResponseEntity releaseFunds(@RequestBody FundRelease fundRelease) {
        releaseService.releaseFunds(fundRelease);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
