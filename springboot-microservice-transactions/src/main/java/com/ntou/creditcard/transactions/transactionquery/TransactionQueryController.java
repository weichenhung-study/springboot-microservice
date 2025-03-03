package com.ntou.creditcard.transactions.transactionquery;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionQueryController {

    @GetMapping("/TransactionQuery")
    public ResponseEntity<TransactionQueryRes> doController(
            @RequestParam("cid") String cid,
            @RequestParam("cardType") String cardType,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) throws Exception {

        TransactionQueryReq req = new TransactionQueryReq();
        req.setCid(cid);
        req.setCardType(cardType);
        req.setStartDate(startDate);
        req.setEndDate(endDate);
        return new TransactionQuery().doAPI(req);
    }
}