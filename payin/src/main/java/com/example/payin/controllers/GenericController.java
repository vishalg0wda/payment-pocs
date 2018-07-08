package com.example.payin.controllers;

import com.example.payin.domain.Charge;
import com.example.payin.domain.Refund;
import com.example.payin.services.ChargeService;
import com.example.payin.services.RefundService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeCreator;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.UUID;

@RestController
public class GenericController {

    private ChargeService chargeService;
    private RefundService refundService;

    @Autowired
    public GenericController(ChargeService chargeService, RefundService refundService) {
        this.chargeService = chargeService;
        this.refundService = refundService;
    }

    @RequestMapping(value = "/charges", method = RequestMethod.POST)
    public ResponseEntity makeCharge(@RequestBody Charge charge) {
        chargeService.executeCharge(charge.getId(), charge.getAmount(), charge.getTransactionId());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/refunds", method = RequestMethod.POST)
    public ResponseEntity makeCharge(@RequestBody Refund refund) {
        refundService.executeRefund(refund);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/refunds", method = RequestMethod.GET)
    public ResponseEntity makeCharge(
            @RequestParam(value = "transactionId", defaultValue = "00000000-0000-0000-0000-000000000000") UUID transactionId,
            @RequestParam(value = "includeBalance", defaultValue = "false") Boolean includeBalance) {

        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("asd", "def");
        return ResponseEntity.ok(node);
    }
}
