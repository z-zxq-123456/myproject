<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易IRL_ELEMENT添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/add/irlElementAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="irlElementAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>因子名称：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="elementId" id="elementId" name="IRL_ELEMENT" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
										<div class="row cl">
                    						<label class="form-label span4"><span class="c-red">*</span>因子类型：</label>
                    						<div class="formControls span6">
                    								<select id="elementType" name="elementType" data-placeholder="因子类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
                    										<option value="F" >F-费用因子</option>
                    										<option value="L" >L-利率因子</option>
                    										<option value="E" >E-汇率因子</option>
                    								</select>
                    						</div>
                    						<div class="span2"> </div>
                    					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>因子数据类型：</label>
						<div class="formControls span6">
								<select id="elementAttr" name="elementAttr" data-placeholder="因子数据类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="DATE" >DATE-日期型</option>
										<option value="NUMBER" >NUMBER-数字型</option>
										<option value="STRING" >STRING-字符型</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
										<div class="row cl">
                    						<label class="form-label span4"><span class="c-red">*</span>因子长度：</label>
                    						<div class="formControls span6">
                    											<input type="text" class="input-text grid" value="" placeholder="elementLength" id="elementLength" name="IRL_ELEMENT" datatype="*1-2" nullmsg=" 请输入！"  tipmsg="格式错误!" >
                    						</div>
                    						<div class="span2"> </div>
                    					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>因子描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="elementDesc" id="elementDesc" name="IRL_ELEMENT" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>



					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>因子取值对应的表名：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="tableName" id="tableName" name="IRL_ELEMENT" datatype="*0-20"   tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>因子取值对应的字段：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="fieldValue" id="fieldValue" name="IRL_ELEMENT" datatype="*0-50"  tipmsg="格式错误!" >
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

