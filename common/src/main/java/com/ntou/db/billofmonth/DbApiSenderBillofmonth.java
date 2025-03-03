package com.ntou.db.billofmonth;

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
public class DbApiSenderBillofmonth {
    private static final String DB_SERVICE_URL = "http://springboot-microservice-jpa-billofmonth-api:8080/springboot-microservice-jpa/billofmonth/";

    public static List<BillofmonthVO> findCusBill(OkHttpServiceClient svc, BillofmonthVO vo) throws JsonProcessingException {
        String str = svc.getService(DB_SERVICE_URL
                + "FindCusBill?cid=" + vo.getCid() + "&cardType=" + vo.getCardType() + "payDate" + vo.getPayDate());
        log.info(Common.RESULT + str);
        JsonNode nodeReadTree = JsonTool.readTree(str);
        JsonNode nodeResult = nodeReadTree.get("result");
        if(nodeResult == null)
            return null;
        List<BillofmonthVO> output = new ObjectMapper().readValue(nodeResult.toString(), new TypeReference<>() {});
        log.info(Common.NODE_RESULT + output);
        return output;
    }
    public static String insertBill(OkHttpServiceClient svc, BillofmonthVO vo) throws JsonProcessingException {
        String str = svc.postService(DB_SERVICE_URL + "InsertBill", vo);
        log.info(Common.RESULT + str);
        JsonNode nodeUpdateResult = JsonTool.readTree(str);
        String output = nodeUpdateResult.get("resCode").asText();
        log.info(Common.NODE_RESULT + output);
        return output;
    }
    public static String updatePayDate(OkHttpServiceClient svc, BillofmonthVO vo) throws JsonProcessingException {
        String str = svc.putService(DB_SERVICE_URL + "UpdatePayDate", vo);
        log.info(Common.RESULT + str);
        JsonNode nodeUpdateResult = JsonTool.readTree(str);
        String output = nodeUpdateResult.get("resCode").asText();
        log.info(Common.NODE_RESULT + output);
        return output;
    }
}
