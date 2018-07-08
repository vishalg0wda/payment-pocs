package com.example.payin.controllers;

import com.example.payin.domain.Charge;
import com.example.payin.domain.Refund;
import com.example.payin.services.ChargeService;
import com.example.payin.services.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenericController {

    private ChargeService chargeService;
    private RefundService refundService;

    @Autowired
    public GenericController(ChargeService chargeService, RefundService refundService) {
        this.chargeService = chargeService;
        this.refundService = refundService;
    }

    @RequestMapping(value = "/charge", method = RequestMethod.POST)
    public ResponseEntity makeCharge(@RequestBody Charge charge) {
        chargeService.executeCharge(charge.getId(), charge.getAmount(), charge.getTransactionId());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/refund", method = RequestMethod.POST)
    public ResponseEntity makeCharge(@RequestBody Refund refund) {
        refundService.executeRefund(refund);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
