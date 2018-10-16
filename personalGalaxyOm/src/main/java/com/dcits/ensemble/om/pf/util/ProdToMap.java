package com.dcits.ensemble.om.pf.util;


import com.dcits.ensemble.om.pf.module.dataFactory.*;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by ligan on 2016/8/2.
 */
public class ProdToMap {
   public static boolean partType(MbPartType mbPartType,String operType,String req){
       Map dataMap =new HashMap();
       String[] keyList=new String[]{"PART_TYPE"};
       String[] genList=new String[]{"PART_DESC","PART_CLASS","DEFAULT_PART","IS_STANDARD","BUSI_CATEGORY","PROCESS_METHOD","STATUS"};
       dataMap.put("genList",genList);
       dataMap.put("keyList",keyList);
       dataMap.put("tableName", "MB_PART_TYPE");
       dataMap.put("PART_TYPE",mbPartType.getPartType());
       dataMap.put("PART_CLASS",mbPartType.getPartClass());
       dataMap.put("DEFAULT_PART",mbPartType.getDefaultPart());
       dataMap.put("IS_STANDARD",mbPartType.getIsStandard());
       dataMap.put("PART_DESC",mbPartType.getPartDesc());
       dataMap.put("STATUS",mbPartType.getStatus());
       dataMap.put("PROCESS_METHOD",mbPartType.getProcessMethod());
       dataMap.put("BUSI_CATEGORY",mbPartType.getBusiCategory());
       dataMap.put("operType", operType);
       ProdJsonBundlingAndSend.ProdJsonBundling(req, ProdMapToJson.MapToJson(dataMap));
       return true;
   }
    public static boolean partCon(MbPartAttr mbPartAttr,String openType,String req){
        String[] keyList=new String[]{"PART_TYPE","ATTR_KEY"};
        String[] genList=new String[]{"ATTR_VALUE"};
        Map dataMap =new HashMap();
        dataMap.put("PART_TYPE",mbPartAttr.getPartType());
        dataMap.put("ATTR_KEY",mbPartAttr.getAttrKey());
        dataMap.put("ATTR_VALUE",mbPartAttr.getAttrValue());
        dataMap.put("keyList",keyList);
        dataMap.put("genList",genList);
        dataMap.put("tableName","MB_PART_ATTR");
        dataMap.put("operType", openType);
        ProdJsonBundlingAndSend.ProdJsonBundling(req, ProdMapToJson.MapToJson(dataMap));
        return true;
    }
    public static boolean eventType(MbEventType mbEventType,String openType,String req){
        String[] keyList=new String[]{"EVENT_TYPE"};
        String[] genList=new String[]{"EVENT_DESC","EVENT_CLASS","PROCESS_METHOD","IS_STANDARD","STATUS"};
        Map dataMap =new HashMap();
        dataMap.put("EVENT_TYPE",mbEventType.getEventType());
        dataMap.put("EVENT_DESC",mbEventType.getEventDesc());
        dataMap.put("EVENT_CLASS",mbEventType.getEventClass());
        dataMap.put("PROCESS_METHOD",mbEventType.getProcessMethod());
        dataMap.put("IS_STANDARD",mbEventType.getIsStandard());
        dataMap.put("STATUS",mbEventType.getStatus());
        dataMap.put("keyList",keyList);
        dataMap.put("genList",genList);
        dataMap.put("tableName","MB_EVENT_TYPE");
        dataMap.put("operType", openType);
        ProdJsonBundlingAndSend.ProdJsonBundling(req, ProdMapToJson.MapToJson(dataMap));
        return true;
    }
    public static boolean mbProdType(MbProdType mbProdType,String openType,String req){
        String[] keyList=new String[]{"PROD_TYPE"};
        String[] genList=new String[]{"PROD_DESC","PROD_CLASS","PROD_GROUP","PROD_RANGE","BASE_PROD_TYPE","STATUS"};
        Map dataMap =new HashMap();
        dataMap.put("PROD_TYPE",mbProdType.getProdType());
        dataMap.put("PROD_DESC",mbProdType.getProdDesc());
        dataMap.put("PROD_CLASS",mbProdType.getProdClass());
        dataMap.put("PROD_GROUP",mbProdType.getProdGroup());
        dataMap.put("PROD_RANGE",mbProdType.getProdRange());
        dataMap.put("BASE_PROD_TYPE",mbProdType.getBaseProdType());
        dataMap.put("STATUS",mbProdType.getStatus());
        dataMap.put("keyList",keyList);
        dataMap.put("genList",genList);
        dataMap.put("tableName","MB_PROD_TYPE");
        dataMap.put("operType", openType);
        ProdJsonBundlingAndSend.ProdJsonBundling(req, ProdMapToJson.MapToJson(dataMap));
        return true;
    }
    public static boolean prodGroup(MbProdGroup mbProdGroup,String openType,String req){
        String[] keyList=new String[]{"PROD_TYPE","PROD_SUB_TYPE"};
        String[] genList=new String[]{"SEQ_NO","DEFAULT_PROD"};
        Map dataMap=new HashMap();
        dataMap.put("PROD_TYPE",mbProdGroup.getProdType());
        dataMap.put("PROD_SUB_TYPE",mbProdGroup.getProdSubType());
        dataMap.put("SEQ_NO",mbProdGroup.getSeqNo());
        dataMap.put("DEFAULT_PROD",mbProdGroup.getDefaultProd());
        dataMap.put("keyList",keyList);
        dataMap.put("genList",genList);
        dataMap.put("tableName","MB_PROD_GROUP");
        dataMap.put("operType", openType);
        ProdJsonBundlingAndSend.ProdJsonBundling(req, ProdMapToJson.MapToJson(dataMap));
        return true;
    }
    public static boolean prodRelationDefine(MbProdRelationDefine mbProdRelationDefine,String openType,String req){
      String[] keyList=new String[]{"PROD_TYPE","SUB_PROD_TYPE","EVENT_TYPE","ASSEMBLE_ID"};
        String[] genList=new String[]{"ASSEMBLE_TYPE","STATUS"};
        Map dataMap=new HashMap();
        dataMap.put("PROD_TYPE",mbProdRelationDefine.getProdType());
        dataMap.put("SUB_PROD_TYPE",mbProdRelationDefine.getSubProdType());
        dataMap.put("EVENT_TYPE",mbProdRelationDefine.getEventType());
        dataMap.put("ASSEMBLE_ID",mbProdRelationDefine.getAssembleId());
        dataMap.put("ASSEMBLE_TYPE",mbProdRelationDefine.getAssembleType());
        dataMap.put("STATUS",mbProdRelationDefine.getStatus());
        dataMap.put("keyList",keyList);
        dataMap.put("genList",genList);
        dataMap.put("tableName","MB_PROD_RELATION_DEFINE");
        dataMap.put("operType", openType);
        ProdJsonBundlingAndSend.ProdJsonBundling(req, ProdMapToJson.MapToJson(dataMap));
       return true;
    }
    public static boolean eventLink(MbEventLink mbEventLink,String openType,String req){
        String[] keyList=new String[]{"PROD_TYPE","ORIG_PROD_TYPE","ORIG_EVENT_TYPE","LINK_PROD_TYPE","LINK_EVENT_TYPE"};
        String[] genList=new String[]{"LINK_CONDITION","STATUS"};
        Map dataMap=new HashMap();
        dataMap.put("PROD_TYPE",mbEventLink.getProdType());
        dataMap.put("ORIG_PROD_TYPE",mbEventLink.getOrigProdType());
        dataMap.put("ORIG_EVENT_TYPE",mbEventLink.getOrigEventType());
        dataMap.put("LINK_PROD_TYPE",mbEventLink.getLinkProdType());
        dataMap.put("LINK_EVENT_TYPE",mbEventLink.getLinkEventType());
        dataMap.put("LINK_CONDITION",mbEventLink.getLinkCondition());
        dataMap.put("STATUS",mbEventLink.getStatus());
        dataMap.put("keyList",keyList);
        dataMap.put("genList",genList);
        dataMap.put("tableName","MB_EVENT_LINK");
        dataMap.put("operType", openType);
        ProdJsonBundlingAndSend.ProdJsonBundling(req, ProdMapToJson.MapToJson(dataMap));
       return true;
    }
    public static boolean eventAttr(MbEventAttr mbEventAttr,String openType,String req){
        String[] keyList=new String[]{"EVENT_TYPE","SEQ_NO"};
        String[] genList=new String[]{"ASSEMBLE_TYPE","ASSEMBLE_ID","ATTR_VALUE","ASSEMBLE_RULE"};
        Map dataMap =new HashMap();
        dataMap.put("EVENT_TYPE",mbEventAttr.getEventType());
        dataMap.put("SEQ_NO",mbEventAttr.getSeqNo());
        dataMap.put("ASSEMBLE_TYPE",mbEventAttr.getAssembleType());
        dataMap.put("ASSEMBLE_ID",mbEventAttr.getAssembleId());
        dataMap.put("ATTR_VALUE",mbEventAttr.getAttrValue());
        dataMap.put("ASSEMBLE_RULE",mbEventAttr.getAssembleRule());
        dataMap.put("keyList",keyList);
        dataMap.put("genList",genList);
        dataMap.put("tableName","MB_EVENT_ATTR");
        dataMap.put("operType", openType);
        ProdJsonBundlingAndSend.ProdJsonBundling(req, ProdMapToJson.MapToJson(dataMap));
        return true;
    }
    public static boolean prodDefine(MbProdDefine mbProdDefine,String openType,String req){
       String[] keyList=new String[]{"PROD_TYPE","SEQ_NO"};
        String[] genList=new String[]{"ASSEMBLE_TYPE","ASSEMBLE_ID","EVENT_DEFAULT","ATTR_KEY","ATTR_VALUE","STATUS"};
        Map dataMap=new HashMap();
        dataMap.put("PROD_TYPE", mbProdDefine.getProdType());
        dataMap.put("SEQ_NO", mbProdDefine.getSeqNo());
        dataMap.put("ASSEMBLE_TYPE",mbProdDefine.getAssembleType());
        dataMap.put("ASSEMBLE_ID",mbProdDefine.getAssembleId());
        dataMap.put("EVENT_DEFAULT",mbProdDefine.getEventDefault());
        dataMap.put("ATTR_KEY",mbProdDefine.getAttrKey());
        dataMap.put("ATTR_VALUE",mbProdDefine.getAttrValue());
        dataMap.put("STATUS",mbProdDefine.getStatus());
        dataMap.put("keyList",keyList);
        dataMap.put("genList",genList);
        dataMap.put("tableName", "MB_PROD_DEFINE");
        dataMap.put("operType", openType);
        ProdJsonBundlingAndSend.ProdJsonBundling(req, ProdMapToJson.MapToJson(dataMap));
       return true;
    }

    public static boolean eventPart(MbEventPart mbEventPart,String openType,String req){
        String[] keyList=new String[]{"EVENT_TYPE","ASSEMBLE_ID","ATTR_KEY"};
        String[] genList=new String[]{"ATTR_VALUE"};
        Map dataMap =new HashMap();
        dataMap.put("EVENT_TYPE",mbEventPart.getEventType());
        dataMap.put("ASSEMBLE_ID",mbEventPart.getAssembleId());
        dataMap.put("ATTR_KEY",mbEventPart.getAttrKey());
        dataMap.put("ATTR_VALUE",mbEventPart.getAttrValue());
        dataMap.put("keyList",keyList);
        dataMap.put("genList",genList);
        dataMap.put("tableName","MB_EVENT_PART");
        dataMap.put("operType", openType);
        ProdJsonBundlingAndSend.ProdJsonBundling(req, ProdMapToJson.MapToJson(dataMap));
        return true;
    }
    public static boolean irlFeeMappingPf(IrlFeeMappingPf irlFeeMappingPf,String openType,String req){
        String[] keyList=new String[]{"IRL_SEQ_NO"};
        String[] genList=new String[]{"FEE_TYPE","BRANCH_RULE","EVENT_TYPE_RULE","TRAN_TYPE_RULE","PROD_GROUP_RULE","PROD_TYPE_RULE","URGENT_FLAG_RULE","IS_LOCAL_RULE","AREA_RULE","CCY_RULE","CLIENT_TYPE_RULE","CATEGORY_TYPE_RULE","SOURCE_TYPE_RULE","DOC_TYPE_RULE","OLD_STATUS_RULE","NEW_STATUS_RULE","IS_RULE","COMPANY_RULE","SERVICE_ID_RULE"};
        Map dataMap =new HashMap();
        dataMap.put("IRL_SEQ_NO",irlFeeMappingPf.getIrlSeqNo());
        dataMap.put("FEE_TYPE",irlFeeMappingPf.getFeeType());
        dataMap.put("BRANCH_RULE",irlFeeMappingPf.getBranchRule());
        dataMap.put("EVENT_TYPE_RULE",irlFeeMappingPf.getEventTypeRule());
        dataMap.put("TRAN_TYPE_RULE",irlFeeMappingPf.getTranTypeRule());
        dataMap.put("PROD_GROUP_RULE",irlFeeMappingPf.getProdGroupRule());
        dataMap.put("PROD_TYPE_RULE",irlFeeMappingPf.getProdTypeRule());
        dataMap.put("URGENT_FLAG_RULE",irlFeeMappingPf.getUrgentFlagRule());
        dataMap.put("IS_LOCAL_RULE",irlFeeMappingPf.getIsLocalRule());
        dataMap.put("AREA_RULE",irlFeeMappingPf.getAreaRule());
        dataMap.put("CCY_RULE",irlFeeMappingPf.getCcyRule());
        dataMap.put("CLIENT_TYPE_RULE",irlFeeMappingPf.getClientTypeRule());
        dataMap.put("CATEGORY_TYPE_RULE",irlFeeMappingPf.getCategoryTypeRule());
        dataMap.put("SOURCE_TYPE_RULE",irlFeeMappingPf.getSourceTypeRule());
        dataMap.put("DOC_TYPE_RULE",irlFeeMappingPf.getDocTypeRule());
        dataMap.put("OLD_STATUS_RULE",irlFeeMappingPf.getOldStatusRule());
        dataMap.put("NEW_STATUS_RULE",irlFeeMappingPf.getNewStatusRule());
        dataMap.put("IS_RULE",irlFeeMappingPf.getIsRule());
        dataMap.put("COMPANY_RULE",irlFeeMappingPf.getCompanyRule());
        dataMap.put("SERVICE_ID_RULE",irlFeeMappingPf.getServiceIdRule());
        dataMap.put("keyList",keyList);
        dataMap.put("genList",genList);
        dataMap.put("tableName","IRL_FEE_MAPPING");
        dataMap.put("operType", openType);
        ProdJsonBundlingAndSend.ProdJsonBundling(req, ProdMapToJson.MapToJson(dataMap));
        return true;
    }
    public static Boolean irlProdTypePf(IrlProdTypePf irlProdTypePf ,String openType,String req){
        String[] keyList=new String[]{"PROD_TYPE"};
        String[] genList=new String[]{"PROD_TYPE_DESC","PROD_GRP","INT_DATE_TYPE","COMPANY","FIXED_CALL"};
        Map dataMap =new HashMap();
        dataMap.put("PROD_TYPE",irlProdTypePf.getProdType());
        dataMap.put("PROD_TYPE_DESC",irlProdTypePf.getProdTypeDesc());
        dataMap.put("PROD_GRP",irlProdTypePf.getProdGrp());
        dataMap.put("INT_DATE_TYPE",irlProdTypePf.getIntDateType());
        dataMap.put("COMPANY",irlProdTypePf.getCompany());
        dataMap.put("FIXED_CALL",irlProdTypePf.getFixedCall());
        dataMap.put("keyList",keyList);
        dataMap.put("genList",genList);
        dataMap.put("tableName","IRL_PROD_TYPE");
        dataMap.put("operType", openType);
        ProdJsonBundlingAndSend.ProdJsonBundling(req, ProdMapToJson.MapToJson(dataMap));
      return true;
    }
    public static Boolean irlProdIntPf(IrlProdIntPf irlProdIntPf,String openType,String req){
        String[] keyList=new String[]{"PROD_TYPE","EVENT_TYPE","INT_CLASS","SPLIT_ID","RULEID"};
        String[] genList=new String[]{"INT_TYPE","TAX_TYPE","RATE_AMT_ID","INT_AMT_ID","RECAL_METHOD","COMPANY","INT_START","INT_DAYS_TYPE","INT_CALC_BAL","INT_APPL_TYPE","ROLL_FREQ","ROLL_DAY","MIN_RATE","MAX_RATE","INT_RATE_IND","MONTH_BASIS","GROUP_RULE_TYPE","SPLIT_TYPE"};
        Map dataMap =new HashMap();
        dataMap.put("PROD_TYPE",irlProdIntPf.getProdType());
        dataMap.put("EVENT_TYPE",irlProdIntPf.getEventType());
        dataMap.put("INT_TYPE",irlProdIntPf.getIntType());
        dataMap.put("INT_CLASS",irlProdIntPf.getIntClass());
        dataMap.put("TAX_TYPE",irlProdIntPf.getTaxType());
        dataMap.put("RATE_AMT_ID",irlProdIntPf.getRateAmtId());
        dataMap.put("INT_AMT_ID",irlProdIntPf.getIntAmtId());
        dataMap.put("RECAL_METHOD",irlProdIntPf.getRecalMethod());
        dataMap.put("COMPANY",irlProdIntPf.getCompany());
        dataMap.put("INT_START",irlProdIntPf.getIntStart());
        dataMap.put("INT_DAYS_TYPE",irlProdIntPf.getIntDaysType());
        dataMap.put("INT_CALC_BAL",irlProdIntPf.getIntCalcBal());
        dataMap.put("INT_APPL_TYPE",irlProdIntPf.getIntApplType());
        dataMap.put("ROLL_FREQ",irlProdIntPf.getRollFreq());
        dataMap.put("ROLL_DAY",irlProdIntPf.getRollDay());
        dataMap.put("MIN_RATE",irlProdIntPf.getMinRate());
        dataMap.put("MAX_RATE",irlProdIntPf.getMaxRate());
        dataMap.put("INT_RATE_IND",irlProdIntPf.getIntRateInd());
        dataMap.put("MONTH_BASIS",irlProdIntPf.getMonthBasis());
        dataMap.put("GROUP_RULE_TYPE",irlProdIntPf.getGroupRuleType());
        dataMap.put("SPLIT_ID",irlProdIntPf.getSplitId());
        dataMap.put("SPLIT_TYPE",irlProdIntPf.getSplitType());
        dataMap.put("RULEID",irlProdIntPf.getRuleId());
        dataMap.put("keyList",keyList);
        dataMap.put("genList",genList);
        dataMap.put("tableName","IRL_PROD_INT");
        dataMap.put("operType", openType);
        ProdJsonBundlingAndSend.ProdJsonBundling(req, ProdMapToJson.MapToJson(dataMap));
        return true;
    }
    public static boolean glProdAccountingPf(GlProdAccountingPf glProdAccountingPf,String openType,String req){
        String[] keyList=new String[]{"PROD_TYPE","ACCOUNTING_STATUS"};
        String[] genList=new String[]{"PROFIT_CENTRE","BUSINESS_UNIT","GL_CODE_A","GL_CODE_L","GL_CODE_INT_E","GL_CODE_INT_PAY","GL_CODE_INT_I","GL_CODE_INT_REC","GL_CODE_INT_ACR","GL_CODE_ODP_I","GL_CODE_ODP_REC","GL_CODE_ODP_ACR","GL_CODE_ODI_I","GL_CODE_ODI_REC","GL_CODE_ODI_ACR","GL_CODE_A_LOSS","GL_CODE_ADJUST"};
        Map dataMap =new HashMap();
        dataMap.put("PROD_TYPE",glProdAccountingPf.getProdType());
        dataMap.put("ACCOUNTING_STATUS",glProdAccountingPf.getAccountingStatus());
        dataMap.put("PROFIT_CENTRE",glProdAccountingPf.getProfitCentre());
        dataMap.put("BUSINESS_UNIT",glProdAccountingPf.getBusinessUnit());
        dataMap.put("GL_CODE_A",glProdAccountingPf.getGlCodeA());
        dataMap.put("GL_CODE_L",glProdAccountingPf.getGlCodeL());
        dataMap.put("GL_CODE_INT_E",glProdAccountingPf.getGlCodeIntE());
        dataMap.put("GL_CODE_INT_PAY",glProdAccountingPf.getGlCodeIntPay());
        dataMap.put("GL_CODE_INT_I",glProdAccountingPf.getGlCodeIntI());
        dataMap.put("GL_CODE_INT_REC",glProdAccountingPf.getGlCodeIntRec());
        dataMap.put("GL_CODE_INT_ACR",glProdAccountingPf.getGlCodeIntAcr());
        dataMap.put("GL_CODE_ODP_I",glProdAccountingPf.getGlCodeOdpI());
        dataMap.put("GL_CODE_ODP_REC",glProdAccountingPf.getGlCodeOdpRec());
        dataMap.put("GL_CODE_ODP_ACR",glProdAccountingPf.getGlCodeOdpAcr());
        dataMap.put("GL_CODE_ODI_I",glProdAccountingPf.getGlCodeOdiI());
        dataMap.put("GL_CODE_ODI_REC",glProdAccountingPf.getGlCodeOdiRec());
        dataMap.put("GL_CODE_ODI_ACR",glProdAccountingPf.getGlCodeOdiAcr());
        dataMap.put("GL_CODE_A_LOSS",glProdAccountingPf.getGlCodeALoss());
        dataMap.put("GL_CODE_ADJUST",glProdAccountingPf.getGlCodeAdjust());
        dataMap.put("keyList",keyList);
        dataMap.put("genList",genList);
        dataMap.put("tableName","GL_PROD_ACCOUNTING");
        dataMap.put("operType", openType);
        ProdJsonBundlingAndSend.ProdJsonBundling(req, ProdMapToJson.MapToJson(dataMap));
        return true;
    }
    public static boolean glProdRulePf(GlProdRulePf glProdRulePf,String openType,String req){
        String[] keyList=new String[]{"PROD_TYPE","SYS_NAME","SOURCE_TYPE","CLIENT_TYPE","ACCOUNTING_STATUS","TRAN_EVENT_TYPE","CCY","TRAN_TYPE","SOURCE_MODULE"};
        String[] genList=new String[]{"ACCOUNTING_NO","CUSTOM_RULE","ACCOUNTING_DESC"};
        Map dataMap =new HashMap();
        dataMap.put("PROD_TYPE",glProdRulePf.getProdType());
        dataMap.put("SYS_NAME",glProdRulePf.getSysName());
        dataMap.put("SOURCE_TYPE",glProdRulePf.getSourceType());
        dataMap.put("CLIENT_TYPE",glProdRulePf.getClientType());
        dataMap.put("ACCOUNTING_STATUS",glProdRulePf.getAccountingStatus());
        dataMap.put("TRAN_EVENT_TYPE",glProdRulePf.getTranEventType());
        dataMap.put("CCY",glProdRulePf.getCcy());
        dataMap.put("CUSTOM_RULE",glProdRulePf.getCustomRule());
        dataMap.put("ACCOUNTING_DESC",glProdRulePf.getAccountingDesc());
        dataMap.put("TRAN_TYPE",glProdRulePf.getTranType());
        dataMap.put("ACCOUNTING_NO",glProdRulePf.getAccountingNo());
        dataMap.put("SOURCE_MODULE",glProdRulePf.getSourceModule());
        dataMap.put("keyList",keyList);
        dataMap.put("genList",genList);
        dataMap.put("tableName","GL_PROD_RULE");
        dataMap.put("operType", openType);
        ProdJsonBundlingAndSend.ProdJsonBundling(req, ProdMapToJson.MapToJson(dataMap));
        return true;
    }
    public static boolean glProdMappingPf(GlProdMappingPf glProdMappingPf,String openType,String req){
        String[] keyList=new String[]{"MAPPING_TYPE"};
        String[] genList=new String[]{"PROD_TYPE","MAPPING_DESC","PROD_DESC"};
        Map dataMap =new HashMap();
        dataMap.put("MAPPING_TYPE",glProdMappingPf.getMappingType());
        dataMap.put("PROD_TYPE",glProdMappingPf.getProdType());
        dataMap.put("MAPPING_DESC",glProdMappingPf.getMappingDesc());
        dataMap.put("PROD_DESC",glProdMappingPf.getProdDesc());
        dataMap.put("keyList",keyList);
        dataMap.put("genList",genList);
        dataMap.put("tableName","GL_PROD_MAPPING");
        dataMap.put("operType", openType);
        ProdJsonBundlingAndSend.ProdJsonBundling(req, ProdMapToJson.MapToJson(dataMap));
        return true;
    }
}
