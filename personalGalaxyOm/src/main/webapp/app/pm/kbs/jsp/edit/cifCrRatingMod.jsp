<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表CIF_CR_RATING修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/cifCrRatingMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="cifCrRatingMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>客户等级：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="crRating" name="crRating" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>信用等级描述：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="crRatingDesc" datatype="*0-30" id="crRatingDesc" name="crRatingDesc" tipmsg="格式错误!" >
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
