<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表FM_STRUCTURE_PARAM修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/fmStructureParamMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="fmStructureParamMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>参数类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="paramType" name="paramType" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>起始位置：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="startPos" name="startPos" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>结构类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="structureType" name="structureType" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>长度：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="length" id="length" name="length" datatype="*1-5" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>结束位置：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="endPos" id="endPos" name="endPos" datatype="*1-5" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>填充字符：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="paddingChar" datatype="*0-1" id="paddingChar" name="paddingChar" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>字符值：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="stringValue" datatype="*0-99" id="stringValue" name="stringValue" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>序号类型：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="seqType" datatype="*0-3" id="seqType" name="seqType" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>法人代码：</label>
                    <div class="formControls span6">
                                <select id="company" name="company"  class="select2" tipmsg="格式错误!"  >
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
