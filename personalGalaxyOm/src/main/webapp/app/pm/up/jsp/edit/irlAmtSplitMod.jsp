<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表IRL_AMT_SPLIT修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/edit/irlAmtSplitMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="irlAmtSplitMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>序号：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="amtSeqNo" name="amtSeqNo" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>金额分段ID：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="amtSplitId" name="amtSplitId" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>分段模式：</label>
                    <div class="formControls span6">
                        <select id="amtSplitMode" name="amtSplitMode"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="Q" >Q-全额</option>
                                <option value="C" >C-差额</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>利率类型代码：</label>
                    <div class="formControls span6">
                                <select id="intType" name="intType" class="select2" tipmsg="格式错误!"  >
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>周期分段ID：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="periSplitId" datatype="*0-10" id="periSplitId" name="periSplitId" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>规则ID：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="ruleId" datatype="*0-50" id="ruleId" name="ruleId" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>分段金额：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="splitAmt" id="splitAmt" name="splitAmt" datatype="n0-20"  tipmsg="格式错误!">
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
