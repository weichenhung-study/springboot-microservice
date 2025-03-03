package com.ntou.creditcard.dispute.disputenotation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DisputeNotationController {
    @PutMapping("/DisputeNotation")
    public ResponseEntity<DisputeNotationRes> doController(@RequestBody DisputeNotationReq req) throws Exception {
        return new DisputeNotation().doAPI(req);
    }
}