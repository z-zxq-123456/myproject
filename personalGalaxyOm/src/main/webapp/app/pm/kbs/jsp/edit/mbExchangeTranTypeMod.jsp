<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表MB_EXCHANGE_TRAN_TYPE修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/mbExchangeTranTypeMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="mbExchangeTranTypeMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>交易类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="tranType" name="tranType" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>结售汇类型：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="exType" datatype="*0-1" id="exType" name="exType" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>操作类型：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="opType" datatype="*0-2" id="opType" name="opType" tipmsg="格式错误!" >
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
