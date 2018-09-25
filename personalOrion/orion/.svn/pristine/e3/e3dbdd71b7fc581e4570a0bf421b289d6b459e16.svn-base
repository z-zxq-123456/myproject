package com.dcits.orion.stria.test.service;

import com.alibaba.fastjson.JSON;
import com.dcits.orion.stria.api.rpc.IFlowService;
import com.dcits.orion.stria.rpc.impl.FlowService;
import com.dcits.galaxy.core.client.ServiceProxy;
import com.dcits.galaxy.core.client.builder.Attributes;
import com.dcits.galaxy.core.client.builder.AttributesBuilderSupport;
import com.dcits.galaxy.core.client.builder.ServiceAttributesBuilder;
import com.dcits.orion.stria.test.TestStriaBase;

/**
 * Created by Tim on 2015/5/28.
 */
public class TestFlow extends TestStriaBase {
    private FlowService flowService;

    private IFlowService iflowService;

    @Override
    protected void setUp() {
        flowService = super.getContext().getBean(FlowService.class);

        // 获取内部服务的引用，是否需要参数
        Attributes attributes = new ServiceAttributesBuilder()
                .setAsync(true)
                .setLoadbalance(
                        AttributesBuilderSupport.LOADBALANCE_RANDOM)
                .setCheck(false).build();
        iflowService = ServiceProxy.getInstance().getService(IFlowService.class, attributes);
    }

    public void testFlow() {
        /**
         Flow flow = new Flow();
         flow.setFlowid("test");
         flow.setContent("{\"flowid\":\"Non-Financial-1288-0103-flow\",\"initNum\":29,\"intNum\":0,\"lines\":{\"stria_line_11\":{\"expr\":\"\",\"from\":\"stria_node_10\",\"name\":\"\",\"to\":\"stria_node_13\",\"type\":\"sl\"},\"stria_line_14\":{\"expr\":\"#_inMsg.getBody().getRestraintArray().get(0).getOption().equals('01')\",\"from\":\"stria_node_13\",\"name\":\"新增\",\"to\":\"stria_node_23\",\"type\":\"sl\"},\"stria_line_15\":{\"expr\":\"#_inMsg.getBody().getRestraintArray().get(0).getOption().equals('02')\",\"from\":\"stria_node_13\",\"name\":\"修改\",\"to\":\"stria_node_25\",\"type\":\"sl\"},\"stria_line_16\":{\"expr\":\"#_inMsg.getBody().getRestraintArray().get(0).getOption().equals('03')\",\"from\":\"stria_node_13\",\"name\":\"解限\",\"to\":\"stria_node_27\",\"type\":\"sl\"},\"stria_line_18\":{\"expr\":\"\",\"from\":\"stria_node_17\",\"name\":\"\",\"to\":\"stria_node_12\",\"type\":\"sl\"},\"stria_line_2\":{\"expr\":\"\",\"from\":\"stria_node_1\",\"name\":\"\",\"to\":\"stria_node_3\",\"type\":\"sl\"},\"stria_line_20\":{\"expr\":\"\",\"from\":\"stria_node_19\",\"name\":\"\",\"to\":\"stria_node_12\",\"type\":\"sl\"},\"stria_line_22\":{\"expr\":\"\",\"from\":\"stria_node_21\",\"name\":\"\",\"to\":\"stria_node_12\",\"type\":\"sl\"},\"stria_line_24\":{\"expr\":\"\",\"from\":\"stria_node_23\",\"name\":\"\",\"to\":\"stria_node_21\",\"type\":\"sl\"},\"stria_line_26\":{\"expr\":\"\",\"from\":\"stria_node_25\",\"name\":\"\",\"to\":\"stria_node_19\",\"type\":\"sl\"},\"stria_line_28\":{\"expr\":\"\",\"from\":\"stria_node_27\",\"name\":\"\",\"to\":\"stria_node_17\",\"type\":\"sl\"},\"stria_line_4\":{\"expr\":\"\",\"from\":\"stria_node_3\",\"name\":\"\",\"to\":\"stria_node_6\",\"type\":\"sl\"},\"stria_line_5\":{\"expr\":\"\",\"from\":\"stria_node_3\",\"name\":\"\",\"to\":\"stria_node_8\",\"type\":\"sl\"},\"stria_line_7\":{\"expr\":\"\",\"from\":\"stria_node_6\",\"name\":\"\",\"to\":\"stria_node_10\",\"type\":\"sl\"},\"stria_line_9\":{\"expr\":\"\",\"from\":\"stria_node_8\",\"name\":\"\",\"to\":\"stria_node_10\",\"type\":\"sl\"}},\"nodes\":{\"stria_node_1\":{\"alt\":true,\"height\":-1,\"left\":23,\"name\":\"开始\",\"top\":242,\"type\":\"start round\",\"width\":-1},\"stria_node_10\":{\"alt\":true,\"height\":-1,\"left\":300,\"name\":\"合并\",\"top\":242,\"type\":\"join round\",\"width\":-1},\"stria_node_12\":{\"alt\":true,\"height\":-1,\"left\":817,\"name\":\"结束\",\"top\":242,\"type\":\"end round\",\"width\":-1},\"stria_node_13\":{\"alt\":true,\"height\":-1,\"left\":372,\"name\":\"决策\",\"top\":242,\"type\":\"chat round\",\"width\":-1},\"stria_node_17\":{\"alt\":true,\"args\":\"*\",\"argsClazz\":\"com.dcits.ensemble.rb.model.nonfinancial.Non12000103In\",\"clazz\":\"com.dcits.ensemble.rb.online.service.nonfinancial.Non12000103\",\"height\":-1,\"left\":648,\"methodName\":\"delRestraint\",\"name\":\"账户限制解限\",\"serviceType\":\"SPRING BEAN\",\"top\":336,\"type\":\"atom\",\"var\":\"\",\"width\":-1},\"stria_node_19\":{\"alt\":true,\"args\":\"*\",\"argsClazz\":\"com.dcits.ensemble.rb.model.nonfinancial.Non12000103In\",\"clazz\":\"com.dcits.ensemble.rb.online.service.nonfinancial.Non12000103\",\"height\":-1,\"left\":651,\"methodName\":\"updRestraint\",\"name\":\"账户限制修改\",\"serviceType\":\"SPRING BEAN\",\"top\":240,\"type\":\"atom\",\"var\":\"\",\"width\":-1},\"stria_node_21\":{\"alt\":true,\"args\":\"*\",\"argsClazz\":\"com.dcits.ensemble.rb.model.nonfinancial.Non12000103In\",\"clazz\":\"com.dcits.ensemble.rb.online.service.nonfinancial.Non12000103\",\"height\":-1,\"left\":651,\"methodName\":\"addRestraint\",\"name\":\"账户限制新增\",\"serviceType\":\"SPRING BEAN\",\"top\":144,\"type\":\"atom\",\"var\":\"\",\"width\":-1},\"stria_node_23\":{\"alt\":true,\"args\":\"*\",\"argsClazz\":\"com.dcits.ensemble.rb.model.nonfinancial.Non12000103In\",\"clazz\":\"com.dcits.ensemble.rb.api.nonfinancial.INon12000103\",\"height\":-1,\"left\":480,\"methodName\":\"checkResAdd\",\"name\":\"新增检查\",\"serviceType\":\"RPC SERVICE API\",\"sourceType\":\"all\",\"top\":144,\"type\":\"check\",\"var\":\"\",\"width\":-1},\"stria_node_25\":{\"alt\":true,\"args\":\"*\",\"argsClazz\":\"com.dcits.ensemble.rb.model.nonfinancial.Non12000103In\",\"clazz\":\"com.dcits.ensemble.rb.api.nonfinancial.INon12000103\",\"height\":-1,\"left\":480,\"methodName\":\"checkResUpd\",\"name\":\"修改检查\",\"serviceType\":\"RPC SERVICE API\",\"sourceType\":\"all\",\"top\":240,\"type\":\"check\",\"var\":\"\",\"width\":-1},\"stria_node_27\":{\"alt\":true,\"args\":\"*\",\"argsClazz\":\"com.dcits.ensemble.rb.model.nonfinancial.Non12000103In\",\"clazz\":\"com.dcits.ensemble.rb.api.nonfinancial.INon12000103\",\"height\":-1,\"left\":480,\"methodName\":\"checkResDel\",\"name\":\"解限检查\",\"serviceType\":\"RPC SERVICE API\",\"sourceType\":\"all\",\"top\":336,\"type\":\"check\",\"var\":\"\",\"width\":-1},\"stria_node_3\":{\"alt\":true,\"height\":-1,\"left\":84,\"name\":\"分派\",\"top\":242,\"type\":\"fork round\",\"width\":-1},\"stria_node_6\":{\"alt\":true,\"args\":\"*\",\"argsClazz\":\"com.dcits.ensemble.rb.model.nonfinancial.Non12000103In\",\"clazz\":\"com.dcits.ensemble.rb.api.nonfinancial.INon12000103\",\"height\":-1,\"left\":156,\"methodName\":\"checkResType\",\"name\":\"限制类型为空检查\",\"serviceType\":\"RPC SERVICE API\",\"sourceType\":\"all\",\"top\":291,\"type\":\"check\",\"var\":\"\",\"width\":-1},\"stria_node_8\":{\"alt\":true,\"args\":\"body.baseAcctNo|body.acctType|body.ccy|sysHead.branchId\",\"argsClazz\":\"String|String|String|String\",\"clazz\":\"com.dcits.ensemble.rb.api.inner.IRbCheck\",\"height\":-1,\"left\":156,\"methodName\":\"checkWithdrawalType\",\"name\":\"跨行支取检查\",\"serviceType\":\"RPC SERVICE API\",\"sourceType\":\"all\",\"top\":189,\"type\":\"check\",\"var\":\"\",\"width\":-1}},\"title\":\"账户限制\"}".getBytes());
         Node node = new Node();
         node.setFlowid("test");
         node.setId("1");
         node.setName("aa");
         node.setType("aa");
         List<Node> nodes = new ArrayList<>();
         nodes.add(node);

         Line line = new Line();
         line.setFlowid("test");
         line.setId("1");
         line.setName("sl");
         line.setType("sladfadfasdfsladfadfasdf");
         List<Line> lines = new ArrayList<>();
         lines.add(line);

         flowService.saveFlow(flow, nodes, lines);
         */
        //iflowService.deploy(JSON.parseObject("{\"flowid\":\"Non-Financial-1288-0103-flow\",\"initNum\":29,\"intNum\":0,\"lines\":{\"stria_line_11\":{\"expr\":\"\",\"from\":\"stria_node_10\",\"name\":\"\",\"to\":\"stria_node_13\",\"type\":\"sl\"},\"stria_line_14\":{\"expr\":\"#_inMsg.getBody().getRestraintArray().get(0).getOption().equals('01')\",\"from\":\"stria_node_13\",\"name\":\"新增\",\"to\":\"stria_node_23\",\"type\":\"sl\"},\"stria_line_15\":{\"expr\":\"#_inMsg.getBody().getRestraintArray().get(0).getOption().equals('02')\",\"from\":\"stria_node_13\",\"name\":\"修改\",\"to\":\"stria_node_25\",\"type\":\"sl\"},\"stria_line_16\":{\"expr\":\"#_inMsg.getBody().getRestraintArray().get(0).getOption().equals('03')\",\"from\":\"stria_node_13\",\"name\":\"解限\",\"to\":\"stria_node_27\",\"type\":\"sl\"},\"stria_line_18\":{\"expr\":\"\",\"from\":\"stria_node_17\",\"name\":\"\",\"to\":\"stria_node_12\",\"type\":\"sl\"},\"stria_line_2\":{\"expr\":\"\",\"from\":\"stria_node_1\",\"name\":\"\",\"to\":\"stria_node_3\",\"type\":\"sl\"},\"stria_line_20\":{\"expr\":\"\",\"from\":\"stria_node_19\",\"name\":\"\",\"to\":\"stria_node_12\",\"type\":\"sl\"},\"stria_line_22\":{\"expr\":\"\",\"from\":\"stria_node_21\",\"name\":\"\",\"to\":\"stria_node_12\",\"type\":\"sl\"},\"stria_line_24\":{\"expr\":\"\",\"from\":\"stria_node_23\",\"name\":\"\",\"to\":\"stria_node_21\",\"type\":\"sl\"},\"stria_line_26\":{\"expr\":\"\",\"from\":\"stria_node_25\",\"name\":\"\",\"to\":\"stria_node_19\",\"type\":\"sl\"},\"stria_line_28\":{\"expr\":\"\",\"from\":\"stria_node_27\",\"name\":\"\",\"to\":\"stria_node_17\",\"type\":\"sl\"},\"stria_line_4\":{\"expr\":\"\",\"from\":\"stria_node_3\",\"name\":\"\",\"to\":\"stria_node_6\",\"type\":\"sl\"},\"stria_line_5\":{\"expr\":\"\",\"from\":\"stria_node_3\",\"name\":\"\",\"to\":\"stria_node_8\",\"type\":\"sl\"},\"stria_line_7\":{\"expr\":\"\",\"from\":\"stria_node_6\",\"name\":\"\",\"to\":\"stria_node_10\",\"type\":\"sl\"},\"stria_line_9\":{\"expr\":\"\",\"from\":\"stria_node_8\",\"name\":\"\",\"to\":\"stria_node_10\",\"type\":\"sl\"}},\"nodes\":{\"stria_node_1\":{\"alt\":true,\"height\":-1,\"left\":23,\"name\":\"开始\",\"top\":242,\"type\":\"start round\",\"width\":-1},\"stria_node_10\":{\"alt\":true,\"height\":-1,\"left\":300,\"name\":\"合并\",\"top\":242,\"type\":\"join round\",\"width\":-1},\"stria_node_12\":{\"alt\":true,\"height\":-1,\"left\":817,\"name\":\"结束\",\"top\":242,\"type\":\"end round\",\"width\":-1},\"stria_node_13\":{\"alt\":true,\"height\":-1,\"left\":372,\"name\":\"决策\",\"top\":242,\"type\":\"chat round\",\"width\":-1},\"stria_node_17\":{\"alt\":true,\"args\":\"*\",\"argsClazz\":\"com.dcits.ensemble.rb.model.nonfinancial.Non12000103In\",\"clazz\":\"com.dcits.ensemble.rb.online.service.nonfinancial.Non12000103\",\"height\":-1,\"left\":648,\"methodName\":\"delRestraint\",\"name\":\"账户限制解限\",\"serviceType\":\"SPRING BEAN\",\"top\":336,\"type\":\"atom\",\"var\":\"\",\"width\":-1},\"stria_node_19\":{\"alt\":true,\"args\":\"*\",\"argsClazz\":\"com.dcits.ensemble.rb.model.nonfinancial.Non12000103In\",\"clazz\":\"com.dcits.ensemble.rb.online.service.nonfinancial.Non12000103\",\"height\":-1,\"left\":651,\"methodName\":\"updRestraint\",\"name\":\"账户限制修改\",\"serviceType\":\"SPRING BEAN\",\"top\":240,\"type\":\"atom\",\"var\":\"\",\"width\":-1},\"stria_node_21\":{\"alt\":true,\"args\":\"*\",\"argsClazz\":\"com.dcits.ensemble.rb.model.nonfinancial.Non12000103In\",\"clazz\":\"com.dcits.ensemble.rb.online.service.nonfinancial.Non12000103\",\"height\":-1,\"left\":651,\"methodName\":\"addRestraint\",\"name\":\"账户限制新增\",\"serviceType\":\"SPRING BEAN\",\"top\":144,\"type\":\"atom\",\"var\":\"\",\"width\":-1},\"stria_node_23\":{\"alt\":true,\"args\":\"*\",\"argsClazz\":\"com.dcits.ensemble.rb.model.nonfinancial.Non12000103In\",\"clazz\":\"com.dcits.ensemble.rb.api.nonfinancial.INon12000103\",\"height\":-1,\"left\":480,\"methodName\":\"checkResAdd\",\"name\":\"新增检查\",\"serviceType\":\"RPC SERVICE API\",\"sourceType\":\"all\",\"top\":144,\"type\":\"check\",\"var\":\"\",\"width\":-1},\"stria_node_25\":{\"alt\":true,\"args\":\"*\",\"argsClazz\":\"com.dcits.ensemble.rb.model.nonfinancial.Non12000103In\",\"clazz\":\"com.dcits.ensemble.rb.api.nonfinancial.INon12000103\",\"height\":-1,\"left\":480,\"methodName\":\"checkResUpd\",\"name\":\"修改检查\",\"serviceType\":\"RPC SERVICE API\",\"sourceType\":\"all\",\"top\":240,\"type\":\"check\",\"var\":\"\",\"width\":-1},\"stria_node_27\":{\"alt\":true,\"args\":\"*\",\"argsClazz\":\"com.dcits.ensemble.rb.model.nonfinancial.Non12000103In\",\"clazz\":\"com.dcits.ensemble.rb.api.nonfinancial.INon12000103\",\"height\":-1,\"left\":480,\"methodName\":\"checkResDel\",\"name\":\"解限检查\",\"serviceType\":\"RPC SERVICE API\",\"sourceType\":\"all\",\"top\":336,\"type\":\"check\",\"var\":\"\",\"width\":-1},\"stria_node_3\":{\"alt\":true,\"height\":-1,\"left\":84,\"name\":\"分派\",\"top\":242,\"type\":\"fork round\",\"width\":-1},\"stria_node_6\":{\"alt\":true,\"args\":\"*\",\"argsClazz\":\"com.dcits.ensemble.rb.model.nonfinancial.Non12000103In\",\"clazz\":\"com.dcits.ensemble.rb.api.nonfinancial.INon12000103\",\"height\":-1,\"left\":156,\"methodName\":\"checkResType\",\"name\":\"限制类型为空检查\",\"serviceType\":\"RPC SERVICE API\",\"sourceType\":\"all\",\"top\":291,\"type\":\"check\",\"var\":\"\",\"width\":-1},\"stria_node_8\":{\"alt\":true,\"args\":\"body.baseAcctNo|body.acctType|body.ccy|sysHead.branchId\",\"argsClazz\":\"String|String|String|String\",\"clazz\":\"com.dcits.ensemble.rb.api.inner.IRbCheck\",\"height\":-1,\"left\":156,\"methodName\":\"checkWithdrawalType\",\"name\":\"跨行支取检查\",\"serviceType\":\"RPC SERVICE API\",\"sourceType\":\"all\",\"top\":189,\"type\":\"check\",\"var\":\"\",\"width\":-1}},\"title\":\"账户限制\"}"), "test");
        try {

            iflowService.deploy(JSON.parseObject("{\n" +
                    "  \"flowid\": \"Non-Financial-1200-0103-flow\",\n" +
                    "  \"initNum\": 29,\n" +
                    "  \"intNum\": 0,\n" +
                    "  \"lines\": {\n" +
                    "    \"stria_line_11\": {\n" +
                    "      \"expr\": \"\"\n" +
                    "    },\n" +
                    "    \"stria_line_14\": {\n" +
                    "      \"expr\": \"#_inMsg.getBody().getRestraintArray().get(0).getOption().equals('01')\",\n" +
                    "      \"from\": \"stria_node_13\",\n" +
                    "      \"name\": \"新增\",\n" +
                    "      \"to\": \"stria_node_23\",\n" +
                    "      \"type\": \"sl\"\n" +
                    "    },\n" +
                    "    \"stria_line_15\": {\n" +
                    "      \"expr\": \"#_inMsg.getBody().getRestraintArray().get(0).getOption().equals('02')\",\n" +
                    "      \"from\": \"stria_node_13\",\n" +
                    "      \"name\": \"修改\",\n" +
                    "      \"to\": \"stria_node_25\",\n" +
                    "      \"type\": \"sl\"\n" +
                    "    },\n" +
                    "    \"stria_line_16\": {\n" +
                    "      \"expr\": \"#_inMsg.getBody().getRestraintArray().get(0).getOption().equals('03')\",\n" +
                    "      \"from\": \"stria_node_13\",\n" +
                    "      \"name\": \"解限\",\n" +
                    "      \"to\": \"stria_node_27\",\n" +
                    "      \"type\": \"sl\"\n" +
                    "    },\n" +
                    "    \"stria_line_18\": {\n" +
                    "      \"expr\": \"\",\n" +
                    "      \"from\": \"stria_node_17\",\n" +
                    "      \"name\": \"\",\n" +
                    "      \"to\": \"stria_node_12\",\n" +
                    "      \"type\": \"sl\"\n" +
                    "    },\n" +
                    "    \"stria_line_2\": {\n" +
                    "      \"expr\": \"\",\n" +
                    "      \"from\": \"stria_node_1\",\n" +
                    "      \"name\": \"\",\n" +
                    "      \"to\": \"stria_node_3\",\n" +
                    "      \"type\": \"sl\"\n" +
                    "    },\n" +
                    "    \"stria_line_20\": {\n" +
                    "      \"expr\": \"\",\n" +
                    "      \"from\": \"stria_node_19\",\n" +
                    "      \"name\": \"\",\n" +
                    "      \"to\": \"stria_node_12\",\n" +
                    "      \"type\": \"sl\"\n" +
                    "    },\n" +
                    "    \"stria_line_22\": {\n" +
                    "      \"expr\": \"\",\n" +
                    "      \"from\": \"stria_node_21\",\n" +
                    "      \"name\": \"\",\n" +
                    "      \"to\": \"stria_node_12\",\n" +
                    "      \"type\": \"sl\"\n" +
                    "    },\n" +
                    "    \"stria_line_24\": {\n" +
                    "      \"expr\": \"\",\n" +
                    "      \"from\": \"stria_node_23\",\n" +
                    "      \"name\": \"\",\n" +
                    "      \"to\": \"stria_node_21\",\n" +
                    "      \"type\": \"sl\"\n" +
                    "    },\n" +
                    "    \"stria_line_26\": {\n" +
                    "      \"expr\": \"\",\n" +
                    "      \"from\": \"stria_node_25\",\n" +
                    "      \"name\": \"\",\n" +
                    "      \"to\": \"stria_node_19\",\n" +
                    "      \"type\": \"sl\"\n" +
                    "    },\n" +
                    "    \"stria_line_28\": {\n" +
                    "      \"expr\": \"\",\n" +
                    "      \"from\": \"stria_node_27\",\n" +
                    "      \"name\": \"\",\n" +
                    "      \"to\": \"stria_node_17\",\n" +
                    "      \"type\": \"sl\"\n" +
                    "    },\n" +
                    "    \"stria_line_4\": {\n" +
                    "      \"expr\": \"\",\n" +
                    "      \"from\": \"stria_node_3\",\n" +
                    "      \"name\": \"\",\n" +
                    "      \"to\": \"stria_node_6\",\n" +
                    "      \"type\": \"sl\"\n" +
                    "    },\n" +
                    "    \"stria_line_5\": {\n" +
                    "      \"expr\": \"\",\n" +
                    "      \"from\": \"stria_node_3\",\n" +
                    "      \"name\": \"\",\n" +
                    "      \"to\": \"stria_node_8\",\n" +
                    "      \"type\": \"sl\"\n" +
                    "    },\n" +
                    "    \"stria_line_7\": {\n" +
                    "      \"expr\": \"\",\n" +
                    "      \"from\": \"stria_node_6\",\n" +
                    "      \"name\": \"\",\n" +
                    "      \"to\": \"stria_node_10\",\n" +
                    "      \"type\": \"sl\"\n" +
                    "    },\n" +
                    "    \"stria_line_9\": {\n" +
                    "      \"expr\": \"\",\n" +
                    "      \"from\": \"stria_node_8\",\n" +
                    "      \"name\": \"\",\n" +
                    "      \"to\": \"stria_node_10\",\n" +
                    "      \"type\": \"sl\"\n" +
                    "    }\n" +
                    "  },\n" +
                    "  \"nodes\": {\n" +
                    "    \"stria_node_1\": {\n" +
                    "      \"alt\": true,\n" +
                    "      \"height\": -1,\n" +
                    "      \"left\": 23,\n" +
                    "      \"name\": \"开始\",\n" +
                    "      \"top\": 242,\n" +
                    "      \"type\": \"start round\",\n" +
                    "      \"width\": -1\n" +
                    "    },\n" +
                    "    \"stria_node_10\": {\n" +
                    "      \"alt\": true,\n" +
                    "      \"height\": -1,\n" +
                    "      \"left\": 300,\n" +
                    "      \"name\": \"合并\",\n" +
                    "      \"top\": 242,\n" +
                    "      \"type\": \"join round\",\n" +
                    "      \"width\": -1\n" +
                    "    },\n" +
                    "    \"stria_node_12\": {\n" +
                    "      \"alt\": true,\n" +
                    "      \"height\": -1,\n" +
                    "      \"left\": 817,\n" +
                    "      \"name\": \"结束\",\n" +
                    "      \"top\": 242,\n" +
                    "      \"type\": \"end round\",\n" +
                    "      \"width\": -1\n" +
                    "    },\n" +
                    "    \"stria_node_13\": {\n" +
                    "      \"alt\": true,\n" +
                    "      \"height\": -1,\n" +
                    "      \"left\": 372,\n" +
                    "      \"name\": \"决策\",\n" +
                    "      \"top\": 242,\n" +
                    "      \"type\": \"chat round\",\n" +
                    "      \"width\": -1\n" +
                    "    },\n" +
                    "    \"stria_node_17\": {\n" +
                    "      \"alt\": true,\n" +
                    "      \"args\": \"*\",\n" +
                    "      \"argsClazz\": \"com.dcits.ensemble.rb.model.nonfinancial.Non12000103In\",\n" +
                    "      \"clazz\": \"com.dcits.ensemble.rb.online.service.nonfinancial.Non12000103\",\n" +
                    "      \"height\": -1,\n" +
                    "      \"left\": 648,\n" +
                    "      \"methodName\": \"delRestraint\",\n" +
                    "      \"name\": \"账户限制解限\",\n" +
                    "      \"serviceType\": \"SPRING BEAN\",\n" +
                    "      \"top\": 336,\n" +
                    "      \"type\": \"atom\",\n" +
                    "      \"var\": \"\",\n" +
                    "      \"width\": -1\n" +
                    "    },\n" +
                    "    \"stria_node_19\": {\n" +
                    "      \"alt\": true,\n" +
                    "      \"args\": \"*\",\n" +
                    "      \"argsClazz\": \"com.dcits.ensemble.rb.model.nonfinancial.Non12000103In\",\n" +
                    "      \"clazz\": \"com.dcits.ensemble.rb.online.service.nonfinancial.Non12000103\",\n" +
                    "      \"height\": -1,\n" +
                    "      \"left\": 651,\n" +
                    "      \"methodName\": \"updRestraint\",\n" +
                    "      \"name\": \"账户限制修改\",\n" +
                    "      \"serviceType\": \"SPRING BEAN\",\n" +
                    "      \"top\": 240,\n" +
                    "      \"type\": \"atom\",\n" +
                    "      \"var\": \"\",\n" +
                    "      \"width\": -1\n" +
                    "    },\n" +
                    "    \"stria_node_21\": {\n" +
                    "      \"alt\": true,\n" +
                    "      \"args\": \"*\",\n" +
                    "      \"argsClazz\": \"com.dcits.ensemble.rb.model.nonfinancial.Non12000103In\",\n" +
                    "      \"clazz\": \"com.dcits.ensemble.rb.online.service.nonfinancial.Non12000103\",\n" +
                    "      \"height\": -1,\n" +
                    "      \"left\": 651,\n" +
                    "      \"methodName\": \"addRestraint\",\n" +
                    "      \"name\": \"账户限制新增\",\n" +
                    "      \"serviceType\": \"SPRING BEAN\",\n" +
                    "      \"top\": 144,\n" +
                    "      \"type\": \"atom\",\n" +
                    "      \"var\": \"\",\n" +
                    "      \"width\": -1\n" +
                    "    },\n" +
                    "    \"stria_node_23\": {\n" +
                    "      \"alt\": true,\n" +
                    "      \"args\": \"*\",\n" +
                    "      \"argsClazz\": \"com.dcits.ensemble.rb.model.nonfinancial.Non12000103In\",\n" +
                    "      \"clazz\": \"com.dcits.ensemble.rb.api.nonfinancial.INon12000103\",\n" +
                    "      \"height\": -1,\n" +
                    "      \"left\": 480,\n" +
                    "      \"methodName\": \"checkResAdd\",\n" +
                    "      \"name\": \"新增检查\",\n" +
                    "      \"serviceType\": \"RPC SERVICE API\",\n" +
                    "      \"sourceType\": \"all\",\n" +
                    "      \"top\": 144,\n" +
                    "      \"type\": \"check\",\n" +
                    "      \"var\": \"\",\n" +
                    "      \"width\": -1\n" +
                    "    },\n" +
                    "    \"stria_node_25\": {\n" +
                    "      \"alt\": true,\n" +
                    "      \"args\": \"*\",\n" +
                    "      \"argsClazz\": \"com.dcits.ensemble.rb.model.nonfinancial.Non12000103In\",\n" +
                    "      \"clazz\": \"com.dcits.ensemble.rb.api.nonfinancial.INon12000103\",\n" +
                    "      \"height\": -1,\n" +
                    "      \"left\": 480,\n" +
                    "      \"methodName\": \"checkResUpd\",\n" +
                    "      \"name\": \"修改检查\",\n" +
                    "      \"serviceType\": \"RPC SERVICE API\",\n" +
                    "      \"sourceType\": \"all\",\n" +
                    "      \"top\": 240,\n" +
                    "      \"type\": \"check\",\n" +
                    "      \"var\": \"\",\n" +
                    "      \"width\": -1\n" +
                    "    },\n" +
                    "    \"stria_node_27\": {\n" +
                    "      \"alt\": true,\n" +
                    "      \"args\": \"*\",\n" +
                    "      \"argsClazz\": \"com.dcits.ensemble.rb.model.nonfinancial.Non12000103In\",\n" +
                    "      \"clazz\": \"com.dcits.ensemble.rb.api.nonfinancial.INon12000103\",\n" +
                    "      \"height\": -1,\n" +
                    "      \"left\": 480,\n" +
                    "      \"methodName\": \"checkResDel\",\n" +
                    "      \"name\": \"解限检查\",\n" +
                    "      \"serviceType\": \"RPC SERVICE API\",\n" +
                    "      \"sourceType\": \"all\",\n" +
                    "      \"top\": 336,\n" +
                    "      \"type\": \"check\",\n" +
                    "      \"var\": \"\",\n" +
                    "      \"width\": -1\n" +
                    "    },\n" +
                    "    \"stria_node_3\": {\n" +
                    "      \"alt\": true,\n" +
                    "      \"height\": -1,\n" +
                    "      \"left\": 84,\n" +
                    "      \"name\": \"分派\",\n" +
                    "      \"top\": 242,\n" +
                    "      \"type\": \"fork round\",\n" +
                    "      \"width\": -1\n" +
                    "    },\n" +
                    "    \"stria_node_6\": {\n" +
                    "      \"alt\": true,\n" +
                    "      \"args\": \"*\",\n" +
                    "      \"argsClazz\": \"com.dcits.ensemble.rb.model.nonfinancial.Non12000103In\",\n" +
                    "      \"clazz\": \"com.dcits.ensemble.rb.api.nonfinancial.INon12000103\",\n" +
                    "      \"height\": -1,\n" +
                    "      \"left\": 156,\n" +
                    "      \"methodName\": \"checkResType\",\n" +
                    "      \"name\": \"限制类型为空检查\",\n" +
                    "      \"serviceType\": \"RPC SERVICE API\",\n" +
                    "      \"sourceType\": \"all\",\n" +
                    "      \"top\": 291,\n" +
                    "      \"type\": \"check\",\n" +
                    "      \"var\": \"\",\n" +
                    "      \"width\": -1\n" +
                    "    },\n" +
                    "    \"stria_node_8\": {\n" +
                    "      \"alt\": true,\n" +
                    "      \"args\": \"body.baseAcctNo|body.acctType|body.ccy|sysHead.branchId\",\n" +
                    "      \"argsClazz\": \"String|String|String|String\",\n" +
                    "      \"clazz\": \"com.dcits.ensemble.rb.api.inner.IRbCheck\",\n" +
                    "      \"height\": -1,\n" +
                    "      \"left\": 156,\n" +
                    "      \"methodName\": \"checkWithdrawalType\",\n" +
                    "      \"name\": \"跨行支取检查\",\n" +
                    "      \"serviceType\": \"RPC SERVICE API\",\n" +
                    "      \"sourceType\": \"all\",\n" +
                    "      \"top\": 189,\n" +
                    "      \"type\": \"check\",\n" +
                    "      \"var\": \"\",\n" +
                    "      \"width\": -1\n" +
                    "    }\n" +
                    "  },\n" +
                    "  \"title\": \"账户限制\"\n" +
                    "}"), "test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
