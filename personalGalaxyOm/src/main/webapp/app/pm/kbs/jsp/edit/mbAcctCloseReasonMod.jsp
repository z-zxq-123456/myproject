<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表MB_ACCT_CLOSE_REASON修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/mbAcctCloseReasonMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="mbAcctCloseReasonMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>原因代码：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="reasonCode" name="reasonCode" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>原因代码描述：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="reasonCodeDesc" id="reasonCodeDesc" name="reasonCodeDesc" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!">
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
        <div class="row">
            <div class="span-offset-1 span10 span-offset-1 mt-10">
                <input type="submit" class="button-select L smartButton" title="修改"  value="修改">
            </div>
        </div>
	</form>
</div>
</body>
</html>