package com.ntou.creditcard.billing.generatebill;

import com.ntou.creditcard.BaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerateBillController extends BaseController {

    public ResponseEntity<GenerateBillRes> doController() throws Exception {
        return new GenerateBill().doAPI();
    }
}