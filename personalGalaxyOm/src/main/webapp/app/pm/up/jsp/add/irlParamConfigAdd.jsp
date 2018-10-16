<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易IRL_PARAM_CONFIG添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/add/irlParamConfigAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="irlParamConfigAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>参数名称：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="paramName" id="paramName" name="IRL_PARAM_CONFIG" datatype="*1-32" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
						<div class="row cl">
                    						<label class="form-label span4"><span class="c-red">*</span>参数值：</label>
                    						<div class="formControls span6">
                    											<input type="text" class="input-text grid" value="" placeholder="paramValue" id="paramValue" name="IRL_PARAM_CONFIG" datatype="*1-64" nullmsg=" 请输入！"  tipmsg="格式错误!" >
                    						</div>
                    						<div class="span2"> </div>
                    					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>参数描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="paramDesc" id="paramDesc" name="IRL_PARAM_CONFIG" datatype="*1-64" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>

					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>备注：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="remark" datatype="*0-100" id="remark" name="remark" tipmsg="格式错误!" >
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


