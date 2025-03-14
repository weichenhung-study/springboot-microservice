package com.ntou.creditcard.dispute.disputenotation;

import com.ntou.connections.OkHttpServiceClient;
import com.ntou.db.billrecord.BillrecordVO;
import com.ntou.db.billrecord.DbApiSenderBillrecord;
import com.ntou.tool.Common;
import com.ntou.tool.ResTool;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/** 爭議款項申請:上註記 */
@Log4j2
public class DisputeNotation {
    private final OkHttpServiceClient okHttpServiceClient = new OkHttpServiceClient();

    public ResponseEntity<DisputeNotationRes> doAPI(DisputeNotationReq req,DbApiSenderBillrecord dbApiSendeBillrecord) throws Exception {
        log.info(Common.API_DIVIDER + Common.START_B + Common.API_DIVIDER);
        log.info(Common.REQ + req);
        DisputeNotationRes res = new DisputeNotationRes();

        if(!req.checkReq())
            ResTool.regularThrow(res, DisputeNotationRC.T162A.getCode(), DisputeNotationRC.T162A.getContent(), req.getErrMsg());

        String updateResult = dbApiSendeBillrecord.updateDisputedFlag(okHttpServiceClient, voBillrecordSelect(req));
        if(!updateResult.equals("UpdateDisputedFlag00"))
            ResTool.commonThrow(res, DisputeNotationRC.T162C.getCode(), DisputeNotationRC.T162C.getContent());

        ResTool.setRes(res, DisputeNotationRC.T1620.getCode(), DisputeNotationRC.T1620.getContent());

        log.info(Common.RES + res);
        log.info(Common.API_DIVIDER + Common.END_B + Common.API_DIVIDER);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    private BillrecordVO voBillrecordSelect(DisputeNotationReq req){
        BillrecordVO vo = new BillrecordVO();
        vo.setUuid		(req.getUuid());
        return vo;
    }
}
