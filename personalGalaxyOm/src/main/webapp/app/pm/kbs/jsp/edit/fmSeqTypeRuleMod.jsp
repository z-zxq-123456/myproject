<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表FM_SEQ_TYPE_RULE修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/fmSeqTypeRuleMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="fmSeqTypeRuleMod">
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>规则类型：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="ruleType" id="ruleType" name="ruleType" datatype="*1-100" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>终止号：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="endNo" id="endNo" name="endNo" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>开始号：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="startNo" id="startNo" name="startNo" datatype="*1-30" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>序号类型：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="seqType" id="seqType" name="seqType" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!">
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
