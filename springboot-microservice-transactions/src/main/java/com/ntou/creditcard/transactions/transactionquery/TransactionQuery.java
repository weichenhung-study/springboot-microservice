package com.ntou.creditcard.transactions.transactionquery;

import com.ntou.connections.OkHttpServiceClient;
import com.ntou.db.billrecord.BillrecordVO;
import com.ntou.db.billrecord.DbApiSenderBillrecord;
import com.ntou.exceptions.TException;
import com.ntou.tool.Common;
import com.ntou.tool.ResTool;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/** 消費紀錄區間查詢 */
@Log4j2
@NoArgsConstructor
public class TransactionQuery {
    private final OkHttpServiceClient okHttpServiceClient = new OkHttpServiceClient();

    public ResponseEntity<TransactionQueryRes> doAPI(TransactionQueryReq req) throws Exception {
        log.info(Common.API_DIVIDER + Common.START_B + Common.API_DIVIDER);
        log.info(Common.REQ + req);
        TransactionQueryRes res = new TransactionQueryRes();

        if(!req.checkReq())
            ResTool.regularThrow(res, TransactionQueryRC.T161A.getCode(), TransactionQueryRC.T161A.getContent(), req.getErrMsg());

        List<BillrecordVO> billList = DbApiSenderBillrecord.findCusBillAll(okHttpServiceClient, voBillrecordSelect(req), req.getStartDate(), req.getEndDate());
        if(billList == null || billList.isEmpty()) {
            ResTool.setRes(res, TransactionQueryRC.T161C.getCode(), TransactionQueryRC.T161C.getContent());
            throw new TException(res);
        }
        ResTool.setRes(res, TransactionQueryRC.T1610.getCode(), TransactionQueryRC.T1610.getContent());
        res.setResult(billList);

        log.info(Common.RES + res);
        log.info(Common.API_DIVIDER + Common.END_B + Common.API_DIVIDER);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    private BillrecordVO voBillrecordSelect(TransactionQueryReq req){
        BillrecordVO vo = new BillrecordVO();
        vo.setCid		(req.getCid());
        vo.setCardType	(req.getCardType());
        return vo;
    }
}
