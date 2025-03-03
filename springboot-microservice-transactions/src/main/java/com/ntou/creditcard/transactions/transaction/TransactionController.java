package com.ntou.creditcard.transactions.transaction;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @PostMapping("/Transaction")
    public ResponseEntity<TransactionRes> doController(@RequestBody TransactionReq req) throws Exception {
        return new Transaction().doAPI(req);
    }
}