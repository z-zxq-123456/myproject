<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易MB_SETTLE_BRANCH添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/mbSettleBranchAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="mbSettleBranchAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>机构：</label>
						<div class="formControls span6">
											<select id="branch" name="branch" data-placeholder="机构" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>清算层级：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="settleLevel" id="settleLevel" name="MB_SETTLE_BRANCH" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>清算机构：</label>
						<div class="formControls span6">
											<select id="settleBranch" name="settleBranch" class="select2" tabindex="4" tipmsg="格式错误!">
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
					<input type="submit" class="button-select L smartButton" title="添加"  value="添加">
				</div>
			</div>
    	</form>
	</div>
</body>
</html>
