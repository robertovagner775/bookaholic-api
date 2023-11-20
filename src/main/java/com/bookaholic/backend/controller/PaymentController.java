package com.bookaholic.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookaholic.backend.model.CobGerada;
import com.bookaholic.backend.model.ErrorResponse;
import com.bookaholic.backend.model.PayloadLocationQrCode;
import com.bookaholic.backend.payment.ClienteDto;
import com.bookaholic.backend.payment.PixService;

import br.com.efi.efisdk.exceptions.EfiPayException;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    

    @Autowired
    PixService pixService;

    @PostMapping("/client")
    public ResponseEntity<?> createKeyClient(@RequestBody ClienteDto cliente) {
        try {
            CobGerada cob = pixService.pixCreateImmediateCharge(cliente);
            PayloadLocationQrCode qrcode = pixService.PixGenerateQRcode(cob.getLoc().getId());
            return ResponseEntity.ok().body(qrcode);
        } catch (EfiPayException e) {
            // TODO Auto-generated catch block
            return ResponseEntity.badRequest().body(new ErrorResponse(111, e.getError(), e.getMessage()));
        }
    }
}
