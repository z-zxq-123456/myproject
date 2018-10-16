<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表IRL_ELEMENT_GROUP修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/edit/irlElementGroupMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="irlElementGroupMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>分组类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="groupType" name="groupType" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>因子名称：</label>
			    <div class="formControls span6">
					<input type="text" class="input-text grid" id="elementId" name="elementId"  nullmsg=" 请输入！"  tipmsg="格式错误!"  >
					</select>
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
				<label class="form-label span4"><span class="c-red">*</span>因子描述：</label>
				<div class="formControls span6">
									<input type="text" class="input-text grid" value="" placeholder="0" id="elementDesc" name="IRL_ELEMENT_GROUP" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!" >
				</div>
				<div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>法人代码：</label>
                    <div class="formControls span6">
                                <select id="company" name="company" class="select2" tipmsg="格式错误!"  >
                                </select>
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
