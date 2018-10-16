<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易RC_LIST_CATEGORY添加</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/rcListCategoryAdd.js"></script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="rcListCategoryAdd">
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>名单种类：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="listCategory" id="listCategory" name="RC_LIST_CATEGORY" datatype="*1-12" nullmsg=" 请输入！"  tipmsg="格式错误!"  ajaxurl="${ContextPath}/baseCommon/verifyOneKeyValueRepeat">
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>内外部名单标识：</label>
						<div class="formControls span6">
								<select id="appInd" name="appInd" class="select2" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
										<option value="I" >I-内部</option>
										<option value="E" >E-外部</option>
								</select>
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>名单种类描述：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="listCategoryDesc" datatype="*0-100" id="listCategoryDesc" name="listCategoryDesc" tipmsg="格式错误!" >
						</div>
						<div class="span2"> </div>
					</div>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>属性：</label>
						<div class="formControls span6">
											<input type="text" class="input-text grid" value="" placeholder="property" datatype="*0-12" id="property" name="property" tipmsg="格式错误!" >
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
