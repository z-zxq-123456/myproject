<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易MB_ACCT_LINKMAN_TYPE添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/mbAcctLinkmanTypeAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="mbAcctLinkmanTypeAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>联系人类型：</label>
						<div class="formControls span6">
								<select id="linkmanType" name="linkmanType" data-placeholder="联系人类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="HL" >HL-热线验证人</option>
										<option value="CA" >CA-对账有权人</option>
										<option value="FC" >FC-金融交易经办人</option>
								</select>
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
						<label class="form-label span4"><span class="c-red"></span>联系人类型描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="linkmanDesc" datatype="*0-50" id="linkmanDesc" name="linkmanDesc" tipmsg="格式错误!" >
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
