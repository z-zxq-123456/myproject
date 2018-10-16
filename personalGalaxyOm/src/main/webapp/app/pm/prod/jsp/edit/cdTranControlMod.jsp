<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表CD_TRAN_CONTROL修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/prod/js/edit/cdTranControlMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="cdTranControlMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>区域代码：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="cdAreaCode" name="cdAreaCode" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>产品类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="prodType" name="prodType" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>渠道：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="prodChannel" name="prodChannel" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>卡客户等级：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="cdCustGrade" name="cdCustGrade" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>商户：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="merchantCode" datatype="*0-20" id="merchantCode" name="merchantCode" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>密码控制：</label>
                    <div class="formControls span6">
                            <select id="passwordCtr" name="passwordCtr"  class="select2"  tipmsg="格式错误!" >
                                <option value="" selected="selected">空</option>
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>日累计限额：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="dayTranLim" id="dayTranLim" name="dayTranLim" datatype="n0-17"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>单次交易限额：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="singleTranLim" id="singleTranLim" name="singleTranLim" datatype="n0-17"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>终端号：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="terminalId" datatype="*0-200" id="terminalId" name="terminalId" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>交易笔数：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="tranCount" id="tranCount" name="tranCount" datatype="n0-5"  tipmsg="格式错误!">
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
