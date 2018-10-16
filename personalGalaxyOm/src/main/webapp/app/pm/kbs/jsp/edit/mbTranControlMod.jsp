<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表MB_TRAN_CONTROL修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/mbTranControlMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="mbTranControlMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>卡客户等级：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="cdCustGrade" name="cdCustGrade" >
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
			    <label class="form-label span4"><span class="c-red">*</span>区域代码：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="cdAreaCode" name="cdAreaCode" >
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
                    <label class="form-label span4"><span class="c-red"></span>交易笔数：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="tranCount" id="tranCount" name="tranCount" datatype="n0-5"  tipmsg="格式错误!">
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
                    <label class="form-label span4"><span class="c-red"></span>法人代码：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="company" datatype="*0-20" id="company" name="company" tipmsg="格式错误!" >
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
        <div class="row">
            <div class="span-offset-1 span10 span-offset-1 mt-10">
                <input type="submit" class="button-select L smartButton" title="修改"  value="修改">
            </div>
        </div>
	</form>
</div>
</body>
</html>
