package com.dcits.galaxy.orion.antirepeate;

import com.dcits.galaxy.base.data.BeanResult;
import com.dcits.galaxy.base.data.SysHead;
import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.junit.TestBase;
import com.dcits.galaxy.orion.encrypt.Fin99990101In;
import com.dcits.galaxy.orion.encrypt.Fin99990101Out;
import com.dcits.orion.core.antirepeate.AntiRepeatUtil;
import com.dcits.orion.core.dao.FwDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixbb on 2016/1/11.
 */
public class TestUnit extends TestBase {

    public Fin99990101In init() throws Exception {
        Fin99990101In in = new Fin99990101In();
        in.setBody(new Fin99990101In.Body());
        in.setSysHead((SysHead) in.fieldTypeNewInstance("sysHead"));
        return in;
    }

    public void testInput() throws Exception {
        Fin99990101In in = init();
        in.writeValue("sysHead.serviceCode", "SVR_FINANCIAL");
        in.writeValue("sysHead.messageType", "1200");
        in.writeValue("sysHead.messageCode", "6048");
        in.writeValue("body.acctNo", "1234");
        in.writeValue("sysHead.tranDate", "20160301");
        in.writeValue("inMsg", in.toString());
        AntiRepeatUtil antiRepeatUtil = SpringApplicationContext.getContext().getBean(AntiRepeatUtil.class);
        BeanResult beanResult = antiRepeatUtil.process(in);
        System.out.println(beanResult);
    }

    public void testUpdate() throws Exception {
        Fin99990101In in = init();
        in.writeValue("sysHead.serviceCode", "SVR_FINANCIAL");
        in.writeValue("sysHead.messageType", "1200");
        in.writeValue("sysHead.messageCode", "6048");
        in.writeValue("body.acctNo", "1234");
        in.writeValue("inMsg", in.toString());
        AntiRepeatUtil antiRepeatUtil = SpringApplicationContext.getContext().getBean(AntiRepeatUtil.class);

        Fin99990101Out out = new Fin99990101Out();
        out.setPassword("toEncrypt");
        out.setAcctName("acctName:lixb");
        List<Fin99990101Out.OutItem> items = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Fin99990101Out.OutItem item = new Fin99990101Out.OutItem();
            item.setPassword("response password in items" + i);
            items.add(item);
        }
        out.setItems(items);
        BeanResult beanResult = new BeanResult(out);
        antiRepeatUtil.updateTranInfo(in, beanResult);
        System.out.println(in);
    }

    public void testInsert() {
        Map tranInfo = new HashMap();
        tranInfo.put("SERVICE_ID", "MBSD_IFP_UM-1200-0105");
        tranInfo.put("KEY_VALUE", "1461898331298-MT");
        tranInfo.put("TRAN_DATE", "20130127");
        tranInfo.put("TRAN_TIME", "2016-04-29 10:52:11");
        tranInfo.put("IN_MSG", "{\n" +
                "   \"SYS_HEAD\":    {\n" +
                "      \"BRANCH_ID\": \"01301\",\n" +
                "      \"DEST_BRANCH_NO\": \"990001\",\n" +
                "      \"SEQ_NO\": \"1461898331298\",\n" +
                "      \"SERVICE_CODE\": \"MBSD_IFP_UM\",\n" +
                "      \"MESSAGE_TYPE\": \"1200\",\n" +
                "      \"MESSAGE_CODE\": \"0105\",\n" +
                "      \"USER_LANG\": \"CHINESE\",\n" +
                "      \"SOURCE_BRANCH_NO\": \"0101\",\n" +
                "      \"SOURCE_TYPE\": \"MT\",\n" +
                "      \"TRAN_DATE\": \"20130127\",\n" +
                "      \"TRAN_TIMESTAMP\": \"172023004\",\n" +
                "      \"USER_ID\": \"CP0101\",\n" +
                "      \"AUTH_FLAG\": \"N\",\n" +
                "      \"AUTH_USER_ID\": \"CP0101\",\n" +
                "      \"PROGRAM_ID\": \"EA101\",\n" +
                "      \"TRAN_MODE\": \"ONLINE\",\n" +
                "      \"APPR_USER_ID\": \"12\",\n" +
                "      \"APPR_FLAG\": \"E\",\n" +
                "      \"FILE_PATH\": \"12\",\n" +
                "      \"MAC_VALUE\": \"123\"\n" +
                "   },\n" +
                "   \"BODY\":    {\n" +
                "      \"PHONE\": \"18275397094\",\n" +
                "      \"PASSWORD\": \"111111\",\n" +
                "      \"CLIENT_TIME\": \"1461898331298\",\n" +
                "      \"LOGIN_IP\": \"10.7.20.151\"\n" +
                "   }\n" +
                "}");
        tranInfo.put("SOURCE_TYPE", "MT");
        tranInfo.put("SEQ_NO", "1461898331298");
        tranInfo.put("PROGRAM_ID", "EA101");
        tranInfo.put("STATUS", "N");
        tranInfo.put("REFERENCE", "1604290000000024");
        tranInfo.put("PLATFORM_ID", "07BD225A6C1745AC85CB7D42C7AC3EC1");
        tranInfo.put("REVERSAL_KEY_VALUE", null);
        FwDao fwDao = SpringApplicationContext.getContext().getBean(FwDao.class);
        fwDao.insertTranInfo(tranInfo);
    }
}
