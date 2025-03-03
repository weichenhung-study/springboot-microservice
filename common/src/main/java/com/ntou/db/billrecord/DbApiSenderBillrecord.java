package com.ntou.db.billrecord;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntou.connections.OkHttpServiceClient;
import com.ntou.tool.Common;
import com.ntou.tool.JsonTool;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class DbApiSenderBillrecord {
    private static final String DB_SERVICE_URL = "http://springboot-microservice-jpa-billrecord-api:8080/springboot-microservice-jpa/billrecord/";

    public static List<BillrecordVO> findCusBillAll(OkHttpServiceClient svc, BillrecordVO vo, String startDate, String endDate) throws JsonProcessingException {
        String str = svc.getService(DB_SERVICE_URL
                + "FindCusBillAll?cid=" + vo.getCid() + "&cardType=" + vo.getCardType() + "&startDate=" + startDate + "&endDate=" + endDate);
        log.info(Common.RESULT + str);
        JsonNode nodeReadTree = JsonTool.readTree(str);
        JsonNode nodeResult = nodeReadTree.get("result");
        if(nodeResult == null)
            return null;
        List<BillrecordVO> output = new ObjectMapper().readValue(nodeResult.toString(), new TypeReference<>() {});
        log.info(Common.NODE_RESULT + output);
        return output;
    }
    public static List<BillrecordVO> FindCusBill(OkHttpServiceClient svc) throws JsonProcessingException {
        String str = svc.getService(DB_SERVICE_URL + "FindCusBill");
        log.info(Common.RESULT + str);
        JsonNode nodeReadTree = JsonTool.readTree(str);
        JsonNode nodeResult = nodeReadTree.get("result");
        if(nodeResult == null)
            return null;
        List<BillrecordVO> output = new ObjectMapper().readValue(nodeResult.toString(), new TypeReference<>() {});
        log.info(Common.NODE_RESULT + output);
        return output;
    }
    public static String insertCusDateBill(OkHttpServiceClient cuscreditSvc, BillrecordVO vo) throws JsonProcessingException {
        String str = cuscreditSvc.postService(DB_SERVICE_URL + "InsertCusDateBill", vo);
        log.info(Common.RESULT + str);
        JsonNode nodeResult = JsonTool.readTree(str);
        String output = nodeResult.get("resCode").asText();
        log.info(Common.NODE_RESULT + output);
        return output;
    }
    public static String updateDisputedFlag(OkHttpServiceClient cuscreditSvc, BillrecordVO vo) throws JsonProcessingException {
        String str = cuscreditSvc.putService(DB_SERVICE_URL + "UpdateDisputedFlag", vo);
        log.info(Common.RESULT + str);
        JsonNode nodeResult = JsonTool.readTree(str);
        String output = nodeResult.get("resCode").asText();
        log.info(Common.NODE_RESULT + output);
        return output;
    }
}
