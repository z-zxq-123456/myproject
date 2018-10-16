<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表FM_SETTLE_METHOD修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/fmSettleMethodMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="fmSettleMethodMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>结算方法：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="settleMethod" name="settleMethod" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>收付标记：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="payRec" name="payRec" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>目标客户类型：</label>
                    <div class="formControls span6">
                        <select id="destClientType" name="destClientType" data-placeholder="目标客户类型" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="C" >C-交易对手</option>
                                <option value="I" >I-内部</option>
                                <option value="L" >L-贷方</option>
                                <option value="N" >N-不产生消息S结算</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>报表格式：</label>
                    <div class="formControls span6">
                        <select id="media" name="media" data-placeholder="报表格式" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="REPORT" >REPORT-报表</option>
                                <option value="FILE" >FILE-文件</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>结算方法描述：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="settleMethodDesc" id="settleMethodDesc" name="settleMethodDesc" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>结算帐户类型：</label>
                    <div class="formControls span6">
                        <select id="settleAcctType" name="settleAcctType" data-placeholder="结算帐户类型" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="R" >R-Retail零售账户</option>
                                <option value="I" >I-Internal内部账户</option>
                                <option value="N" >N-Nostro 往帐账户</option>
                                <option value="V" >V-Vostro往来帐账户</option>
                                <option value="C" >C-Card Account卡号</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>联系方式类型：</label>
                    <div class="formControls span6">
                        <select id="route" name="route" data-placeholder="联系方式类型" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="SWIFT" >SWIFT-SWIFT电文</option>
                                <option value="POSTAL" >POSTAL-邮寄</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>安全释放：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="releaseSecurity" id="releaseSecurity" name="releaseSecurity" datatype="*1-1" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>安全复合：</label>
                    <div class="formControls span6">
                        <select id="verifySecurity" name="verifySecurity" data-placeholder="安全复合" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="0" >0-Lowest Level</option>
                                <option value="1" >1-Teller</option>
                                <option value="2" >2-Clerk</option>
                                <option value="3" >3-Senior Clerk</option>
                                <option value="4" >4-Assistant Supervisor</option>
                                <option value="5" >5-Supervisor</option>
                                <option value="6" >6-Senior Supervisor</option>
                                <option value="7" >7-Officer</option>
                                <option value="8" >8-Senior Officer</option>
                                <option value="9" >9-Manager</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>电位类型：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="format" id="format" name="format" datatype="*1-8" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>联系类型：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="contactType" datatype="*0-3" id="contactType" name="contactType" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>打印模式：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="printMode" datatype="*0-4" id="printMode" name="printMode" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>法人代码：</label>
                    <div class="formControls span6">
                                <select id="company" name="company"   class="select2" tipmsg="格式错误!"  >
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>是否现金：</label>
                    <div class="formControls span6">
                            <select id="isCash" name="isCash"  class="select2"  tipmsg="格式错误!" >
                                <option value="" selected="selected">空</option>
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>目标ID：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="destId" datatype="*0-15" id="destId" name="destId" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>是否为DP清算：</label>
                    <div class="formControls span6">
                            <select id="dpSettle" name="dpSettle"  class="select2"  tipmsg="格式错误!" >
                                <option value="" selected="selected">空</option>
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>证件类型：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="docType" datatype="*0-10" id="docType" name="docType" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>目标类型：</label>
                    <div class="formControls span6">
                        <select id="destType" name="destType"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="P" >P-打印机</option>
                                <option value="T" >T-磁带</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>发报方联系类型：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="sendersContactType" datatype="*0-3" id="sendersContactType" name="sendersContactType" tipmsg="格式错误!" >
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
