package com.ntou.creditcard.management.activation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivationController {
    @PutMapping("/Activation")
    public ResponseEntity<ActivationRes> doController(@RequestBody ActivationReq req) throws Exception {
        return new Activation().doAPI(req);
    }
}