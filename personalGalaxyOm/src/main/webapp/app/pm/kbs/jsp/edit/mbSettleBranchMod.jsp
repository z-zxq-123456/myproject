<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表MB_SETTLE_BRANCH修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/mbSettleBranchMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="mbSettleBranchMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>机构：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="branch" name="branch" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>清算层级：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="settleLevel" id="settleLevel" name="settleLevel" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>清算机构：</label>
                    <div class="formControls span6">
                                <select id="settleBranch" name="settleBranch" class="select2" tipmsg="格式错误!"  >
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>本机构清算账号：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="settleBaseAcct" datatype="*0-50" id="settleBaseAcct" name="settleBaseAcct" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>上级机构清算账号：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="settleBaseAcctUp" datatype="*0-50" id="settleBaseAcctUp" name="settleBaseAcctUp" tipmsg="格式错误!" >
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
