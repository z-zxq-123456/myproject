<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表LM_TRAN_LIMIT_DEF修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/lmTranLimitDefMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="lmTranLimitDefMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>交易限额编码：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="limitRef" name="limitRef" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>币种：</label>
                    <div class="formControls span6">
                                <select id="ccy" name="ccy" data-placeholder="币种" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>交易处理方式取值：</label>
                    <div class="formControls span6">
                        <select id="dealFlow" name="dealFlow" data-placeholder="交易处理方式取值" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="A" >A-授权处理</option>
                                <option value="B" >B-拒绝处理</option>
                                <option value="D" >D-提醒处理；</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>生效日期：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="effectDate" id="effectDate" name="effectDate" datatype="*1-8" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>交易限额编码说明：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="limitDesc" id="limitDesc" name="limitDesc" datatype="*1-180" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>限额类型：</label>
                    <div class="formControls span6">
                        <select id="limitType" name="limitType" data-placeholder="限额类型" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="PD" >PD-每天累计</option>
                                <option value="PT" >PT-单笔交易</option>
                                <option value="PM" >PM-每月累计</option>
                                <option value="PY" >PY-每年累计</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>最大额：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="maxAmt" id="maxAmt" name="maxAmt" datatype="*1-17" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>最小额：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="minAmt" id="minAmt" name="minAmt" datatype="*1-17" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>状态：</label>
                    <div class="formControls span6">
                        <select id="status" name="status" data-placeholder="状态" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="Y" >Y-启用</option>
                                <option value="N" >N-不启用</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>其他级别：</label>
                    <div class="formControls span6">
                        <select id="otherLevel" name="otherLevel"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="CUST" >CUST-客户级别</option>
                                <option value="ACCT" >ACCT-账户级别</option>
                                <option value="NULL" >NULL-无级别</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>是否允许自定义：</label>
                    <div class="formControls span6">
                        <select id="enableDefine" name="enableDefine" class="select2" tipmsg="格式错误!">
                            <option value="Y" >Y-是</option>
                            <option value="N" >N-否</option>
                        </select>
                    </div>
                    <div class="span2"> </div>
                </div>
        <div class="row">
            <div class="span-offset-1 span10 span-offset-1 mt-10">
                <input type="submit" class="button-select L smartButton" title="修改"  value="修改">
            </div>
        </div>
	</form>
</div>
</body>
</html>
