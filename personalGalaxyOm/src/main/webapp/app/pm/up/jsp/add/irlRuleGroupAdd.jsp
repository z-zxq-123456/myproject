<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易IRL_RULE_GROUP添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/add/irlRuleGroupAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="irlRuleGroupAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>分组类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="groupType" id="groupType" name="IRL_RULE_GROUP" datatype="/^[a-zA-Z]+$/" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"></span>权重：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="weight" id="weight" name="IRL_RULE_GROUP" datatype="*0-5" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>组内规则关系：</label>
						<div class="formControls span6">
								<select id="grpMatchType" name="grpMatchType" data-placeholder="组内规则关系" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="01" >01-折上折</option>
										<option value="02" >02-取最大</option>
										<option value="03" >03-取最小</option>
										<option value="04" >04-取平均值</option>
										<option value="05" >05-取叠加</option>
										<option value="06" >06-取权重</option>
										<option value="07" >07-取固定</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>分组归属分类：</label>
						<div class="formControls span6">
								<select id="groupClass" name="groupClass" data-placeholder="分组归属分类" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="D" >D-折扣</option>
										<option value="L" >L-利率</option>
										<option value="E" >E-汇率</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>分组描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="groupTypeDesc" id="groupTypeDesc" name="IRL_RULE_GROUP" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!">
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
