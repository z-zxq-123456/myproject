<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易MB_PROD_CLASS添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/prod/js/add/mbProdClassAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="mbProdClassAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>产品分类：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="prodClass" id="prodClass" name="MB_PROD_CLASS"  datatype="*1-10" nullmsg=" 请输入！" tipmsg="格式错误!" ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>产品分类描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="prodClassDesc" id="prodClassDesc" name="MB_PROD_CLASS" datatype="*1-100" nullmsg=" 请输入！"  tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>产品分类层级：</label>
						<div class="formControls span6">
								<select id="prodClassLevel" name="prodClassLevel" class="select2" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="1" >1</option>
										<option value="2" >2</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>上级产品分类：</label>
						<div class="formControls span6">
							  <select id="parentProdClass" name="parentProdClass" data-placeholder="上级产品分类" class="select2" tabindex="4" tipmsg="格式错误!">
								 <option value="" selected="selected">请选择</option>
							   </select>
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
