package com.ntou.db.cuscredit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.ntou.connections.OkHttpServiceClient;
import com.ntou.tool.Common;
import com.ntou.tool.JsonTool;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DbApiSenderCuscredit {
    private static final String DB_SERVICE_URL = "http://springboot-microservice-jpa-cuscredit-api:8080/springboot-microservice-jpa/cuscredit/";

    public static CuscreditVO getCardHolder(OkHttpServiceClient cuscreditSvc,String cid, String cardType) throws JsonProcessingException {
        String str = cuscreditSvc.getService(DB_SERVICE_URL + "GetCardHolder?cid=" + cid + "&cardType=" + cardType);
        log.info(Common.RESULT + str);
        JsonNode nodeReadTree = JsonTool.readTree(str);
        JsonNode nodeResult = nodeReadTree.get("result");
        if(nodeResult == null)
            return null;
        CuscreditVO output = JsonTool.readValue(nodeResult.toString(),CuscreditVO.class);
        log.info(Common.NODE_RESULT + output);
        return output;
    }
    public static CuscreditVO getActivatedCardHolder(OkHttpServiceClient cuscreditSvc,String cid, String cardType, String cardNum, String securityCode) throws JsonProcessingException {
        String str = cuscreditSvc.getService(DB_SERVICE_URL + "GetActivatedCardHolder?cid=" + cid + "&cardType=" + cardType + "&cardNum=" + cardNum + "&securityCode=" + securityCode);
        log.info(Common.RESULT + str);
        JsonNode nodeReadTree = JsonTool.readTree(str);
        JsonNode nodeResult = nodeReadTree.get("result");
        if(nodeResult == null)
            return null;
        CuscreditVO output = JsonTool.readValue(nodeResult.toString(),CuscreditVO.class);
        log.info(Common.NODE_RESULT + output);
        return output;
    }
    public static String updateActivationRecord(OkHttpServiceClient cuscreditSvc, CuscreditVO vo) throws JsonProcessingException {
        String str = cuscreditSvc.putService(DB_SERVICE_URL + "UpdateActivationRecord", vo);
        log.info(Common.RESULT + str);
        JsonNode nodeUpdateResult = JsonTool.readTree(str);
        String output = nodeUpdateResult.get("resCode").asText();
        log.info(Common.NODE_RESULT + output);
        return output;
    }
    public static String updateCardApprovalStatus(OkHttpServiceClient cuscreditSvc, CuscreditVO vo) throws JsonProcessingException {
        String str = cuscreditSvc.putService(DB_SERVICE_URL + "UpdateCardApprovalStatus", vo);
        log.info(Common.RESULT + str);
        JsonNode nodeUpdateResult = JsonTool.readTree(str);
        String output = nodeUpdateResult.get("resCode").asText();
        log.info(Common.NODE_RESULT + output);
        return output;
    }
    public static String createCuscredit(OkHttpServiceClient cuscreditSvc, CuscreditVO vo) throws JsonProcessingException {
        String str = cuscreditSvc.postService(DB_SERVICE_URL + "CreateCuscredit", vo);
        log.info(Common.RESULT + str);
        JsonNode nodeUpdateResult = JsonTool.readTree(str);
        String output = nodeUpdateResult.get("resCode").asText();
        log.info(Common.NODE_RESULT + output);
        return output;
    }
}
