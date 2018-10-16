<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易GL_TRAN_MAPPING添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/glr/js/add/glTranMappingAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="glTranMappingAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>序号：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="indexNo" id="indexNo" name="GL_TRAN_MAPPING" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>映射字段：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="keyName" id="keyName" name="GL_TRAN_MAPPING" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>对象名称：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="objectName" id="objectName" name="GL_TRAN_MAPPING" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>是否金额：</label>
						<div class="formControls span6">
								<select id="isAmount" name="isAmount" class="select2" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="Y" >Y-是</option>
										<option value="N" >N-否</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>映射描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="keyDesc" datatype="*0-100" id="keyDesc" name="keyDesc" tipmsg="格式错误!" >
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