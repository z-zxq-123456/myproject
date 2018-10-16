<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表CMC_PRODUCT_LIMIT修改</title>
<script type="text/javascript" src="${ContextPath}/app/cmc/js/edit/cmcProductLimitMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="cmcProductLimitMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>卡产品编号：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="cardProductCode" name="cardProductCode" >
			    </div>
			    <div class="span2"> </div>
			</div>
		<div class="row cl">
			<label class="form-label span4"><span class="c-red">*</span>渠道类型：</label>
			<div class="formControls span6">
				<select id="channelType" name="CMC_PRODUCT_LIMIT" data-placeholder="限制渠道" class="select2"  tabindex="4" datatype="*" tipmsg="格式错误!">
				</select>
			</div>
			<div class="span2"> </div>
		</div>
		<div class="row cl">
			<label class="form-label span4"><span class="c-red">*</span>主帐户的币种：</label>
			<div class="formControls span6">
				<input type="text" class="input-text grid" value="" placeholder="ccy" id="ccy" name="ccy" datatype="*1-3" nullmsg=" 请输入！"  tipmsg="格式错误!">
			</div>
			<div class="span2"> </div>
		</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>限制周期：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="period" name="period" >
			    </div>
			    <div class="span2"> </div>
			</div>

                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>本周期消费限额：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="conLimitAmt" id="conLimitAmt" name="conLimitAmt"
										   datatype="/(^[1-9]([0-9]{1,15})?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
		<div class="row cl">
			<label class="form-label span4"><span class="c-red">*</span>本周期转入限额：</label>
			<div class="formControls span6">
				<input type="text" class="input-text grid" value="" placeholder="tranInLimitAmt" id="tranInLimitAmt" name="tranInLimitAmt"
					   datatype="/(^[1-9]([0-9]{1,15})?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/" nullmsg=" 请输入！"  tipmsg="格式错误!">
			</div>
			<div class="span2"> </div>
		</div>
		<div class="row cl">
			<label class="form-label span4"><span class="c-red">*</span>本周期转出限额：</label>
			<div class="formControls span6">
				<input type="text" class="input-text grid" value="" placeholder="tranOutLimitAmt" id="tranOutLimitAmt" name="tranOutLimitAmt"
					   datatype="/(^[1-9]([0-9]{1,15})?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/" nullmsg=" 请输入！"  tipmsg="格式错误!">
			</div>
			<div class="span2"> </div>
		</div>

                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>本周期消费次数：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="conLimitTime" id="conLimitTime" name="conLimitTime" datatype="/(^[0]{1}$)|(^[1-9]{1}[0-9]{0,4}$)/" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>


                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>本周期转入次数：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="tranInLimitTime" id="tranInLimitTime" name="tranInLimitTime" datatype="/(^[0]{1}$)|(^[1-9]{1}[0-9]{0,4}$)/" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>

                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>本周期转出次数：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="tranOutLimitTime" id="tranOutLimitTime" name="tranOutLimitTime" datatype="/(^[0]{1}$)|(^[1-9]{1}[0-9]{0,4}$)/" nullmsg=" 请输入！"  tipmsg="格式错误!">
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
