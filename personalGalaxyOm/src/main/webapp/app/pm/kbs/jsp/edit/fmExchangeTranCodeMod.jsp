<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表FM_EXCHANGE_TRAN_CODE修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/fmExchangeTranCodeMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="fmExchangeTranCodeMod">
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>升降标志：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="incExpInd" id="incExpInd" name="incExpInd" datatype="*1-2" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>结售汇项目编码描述：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="tranCodeDesc" id="tranCodeDesc" name="tranCodeDesc" datatype="*1-100" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>结售汇项目编码：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="tranCode" id="tranCode" name="tranCode" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!">
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
