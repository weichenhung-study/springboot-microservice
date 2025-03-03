package com.ntou.creditcard.billing.feepayment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeePaymentController {

    @PostMapping("/FeePayment")
    public ResponseEntity<FeePaymentRes> doController(@RequestBody FeePaymentReq req) throws Exception {
        return new FeePayment().doAPI(req);
    }
}