<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表IRL_PROD_TYPE修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/edit/irlProdTypeMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="irlProdTypeMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>产品类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="prodType" name="prodType" >
			    </div>
			    <div class="span2"> </div>
			</div>
			   <div class="row cl">
                                <label class="form-label span4"><span class="c-red">*</span>产品类型描述：</label>
                                <div class="formControls span6">
                                                <input type="text" class="input-text grid" value="" placeholder="prodTypeDesc" id="prodTypeDesc" name="prodTypeDesc" datatype="*1-60" nullmsg=" 请输入！"  tipmsg="格式错误!">
            			    </div>
            			    <div class="span2"> </div>
            			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>产品组：</label>
                    <div class="formControls span6">
                                <select id="prodGrp" name="prodGrp" data-placeholder="产品组" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>

                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>法人代码：</label>
                    <div class="formControls span6">
                                <select id="company" name="company" datatype="*0-20"  class="select2" tipmsg="格式错误!"  >
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>定期账户细类：</label>
                    <div class="formControls span6">
                            <select id="fixedCall" name="fixedCall"  class="select2"  tipmsg="格式错误!" >
										<option value="A" >A-协议存款</option>
										<option value="B" >B-定期一本通</option>
										<option value="C" >C-通知存款</option>
										<option value="D" >D-定活两便</option>
										<option value="E" >E-教育储蓄</option>
										<option value="F" >F-整存整取</option>
										<option value="L" >L-零存整取</option>
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

