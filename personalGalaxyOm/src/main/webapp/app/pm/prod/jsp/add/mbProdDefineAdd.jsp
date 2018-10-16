<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易MB_PROD_DEFINE添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/prod/js/add/mbProdDefineAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="mbProdDefineAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>产品类型：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="prodType" id="prodType" name="MB_PROD_DEFINE" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>序号：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="seqNo" id="seqNo" name="MB_PROD_DEFINE" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>组件类型：</label>
						<div class="formControls span6">
								<select id="assembleType" name="assembleType" data-placeholder="组件类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="EVENT" >EVENT-事件</option>
										<option value="PART" >PART-部件</option>
										<option value="ATTR" >ATTR-属性</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>状态：</label>
						<div class="formControls span6">
								<select id="status" name="status" data-placeholder="状态" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="A" >A-有效</option>
										<option value="C" >C-关闭</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>组件ID：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="assembleId" datatype="*0-50" id="assembleId" name="assembleId" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>属性KEY：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="attrKey" datatype="*0-30" id="attrKey" name="attrKey" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>属性值：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="attrValue" datatype="*0-30" id="attrValue" name="attrValue" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>产品基础事件：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="eventDefault" datatype="*0-20" id="eventDefault" name="eventDefault" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
				<div class="row cl">
					<label class="form-label span4"><span class="c-red"></span>法人代码：</label>
					<div class="formControls span6">
											<select id="company" name="company"  class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
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
