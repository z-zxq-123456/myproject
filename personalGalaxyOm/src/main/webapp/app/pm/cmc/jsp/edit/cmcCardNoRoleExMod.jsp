<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表CMC_CARD_NO_ROLE_EX修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/cmc/js/edit/cmcCardNoRoleExMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="cmcCardNoRoleExMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>参数名：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="paramName" name="paramName" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>参数值：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="paramValue" id="paramValue" name="paramValue" datatype="*1-100" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>扩展字段1：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="expand1" datatype="*0-50" id="expand1" name="expand1" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>扩展字段2：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="expand2" datatype="*0-50" id="expand2" name="expand2" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>扩展字段3：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="expand3" datatype="*0-50" id="expand3" name="expand3" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>扩展字段4：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="expand4" datatype="*0-50" id="expand4" name="expand4" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>扩展字段5：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="expand5" datatype="*0-50" id="expand5" name="expand5" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>扩展字段6：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="expand6" datatype="*0-50" id="expand6" name="expand6" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>扩展字段7：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="expand7" datatype="*0-50" id="expand7" name="expand7" tipmsg="格式错误!" >
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
