<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表IRL_BASIS_RATE修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/edit/irlBasisRateMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="irlBasisRateMod">
				<div class="row cl">
            			    <label class="form-label span4"><span class="c-red">*</span>基准利率类型：</label>
            			    <div class="formControls span6">
            			     <select id="intBasis" name="intBasis" data-placeholder="基准利率类型" class="select2"  tabindex="4" datatype="*1-2" nullmsg=" 请输入！" tipmsg="格式错误!">
                                                     											</select>
            			    </div>
            			    <div class="span2"> </div>
            			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>币种：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="ccy" name="ccy" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>生效日期：</label>
			    <div class="formControls span6">
                <input type="text" onfocus="WdatePicker({dateFmt:'yyyyMMdd'})" id="effectDate" name="effectDate" class="input-text Wdate" style="width:265px;">
			    </div>
			    <div class="span2"> </div>
			</div>

                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>利率：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="intBasisRate" id="intBasisRate" name="intBasisRate" datatype="/^([0-9][0-9]*)+(.[0-9]{1,4})?$/" nullmsg=" 请输入！"  tipmsg="格式错误!">
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

