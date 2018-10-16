<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表CMC_CARD_NO_PARAM修改</title>
<script type="text/javascript" src="${ContextPath}/app/cmc/js/edit/cmcCardNoParamMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="cmcCardNoParamMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>卡号规则：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="productRuleNo" name="productRuleNo" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>制卡数量：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="cardNum" id="cardNum" name="cardNum" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>启用标识：</label>
                    <div class="formControls span6">
                        <select id="flag" name="flag" data-placeholder="启用标识" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="0" >0-初始状态</option>
                                <option value="1" >1-启用A表</option>
                                <option value="2" >2-启用B表</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>制卡数量警戒值：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="thresholdNum" id="thresholdNum" name="thresholdNum" datatype="*1-10" nullmsg=" 请输入！"  tipmsg="格式错误!">
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
