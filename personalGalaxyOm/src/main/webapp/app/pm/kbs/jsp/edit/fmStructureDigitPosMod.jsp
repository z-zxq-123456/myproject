<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表FM_STRUCTURE_DIGIT_POS修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/fmStructureDigitPosMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="fmStructureDigitPosMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>数字位置：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="digitPos" name="digitPos" >
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
                    <label class="form-label span4"><span class="c-red">*</span>是否进行数字的权重计算：</label>
                    <div class="formControls span6">
                            <select id="checkDigitInd" name="checkDigitInd" data-placeholder="是否进行数字的权重计算" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>权重：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="weight" datatype="*0-5" id="weight" name="weight" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>法人代码：</label>
                    <div class="formControls span6">
                                <select id="company" name="company"   class="select2" tipmsg="格式错误!"  >
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
