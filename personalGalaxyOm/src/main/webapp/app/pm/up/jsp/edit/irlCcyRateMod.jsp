<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表IRL_CCY_RATE修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/edit/irlCcyRateMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="irlCcyRateMod">
			<div class="row cl">
            			    <label class="form-label span4"><span class="c-red">*</span>币种：</label>
            			    <div class="formControls span6">
            			        <input type="text" class="input-text grid" value=""  id="ccy" name="ccy" >
            			    </div>
            			    <div class="span2"> </div>
            			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>机构代码：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="branch" name="branch" >
			    </div>
			    <div class="span2"> </div>
			</div>
<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>汇率类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="rateType" name="rateType" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>生效日期：</label>
			    <div class="formControls span6">
			    <input type="text" onfocus="WdatePicker({dateFmt:'yyyyMMdd'})" id="effectDate" name="IRL_CCY_RATE" class="input-text Wdate" style="width:100%"  tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			</div>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>生效时间：</label>
			    <div class="formControls span6">
			        <input type="text" onfocus="WdatePicker({dateFmt:'HHmmss'})" id="effectTime" name="IRL_CCY_RATE" class="input-text Wdate" style="width:100%"  tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			</div>
          <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>牌价类型：</label>
                    <div class="formControls span6">
                        <select id="quoteType" name="quoteType" data-placeholder="牌价类型" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="D" >D-直接</option>
                                <option value="I" >I-间接</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
			        <div class="row cl">
                                   <label class="form-label span4"><span class="c-red">*</span>中间价：</label>
                                   <div class="formControls span6">
                                                   <input type="text" class="input-text grid" value="" placeholder="middleRate" id="middleRate" name="middleRate" datatype="/^([0-9][0-9]*)+(.[0-9]{1,9})?$/" nullmsg=" 请输入！"  tipmsg="格式错误!">
               			    </div>
               			    <div class="span2"> </div>
               			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>汇买价：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="exchBuyRate" id="exchBuyRate" name="exchBuyRate" datatype="/^([0-9][0-9]*)+(.[0-9]{1,9})?$/" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
			      <div class="row cl">
                                   <label class="form-label span4"><span class="c-red">*</span>汇卖价：</label>
                                   <div class="formControls span6">
                                                   <input type="text" class="input-text grid" value="" placeholder="exchSellRate" id="exchSellRate" name="exchSellRate" datatype="/^([0-9][0-9]*)+(.[0-9]{1,9})?$/" nullmsg=" 请输入！"  tipmsg="格式错误!">
               			    </div>
               			    <div class="span2"> </div>
               			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>参考汇率：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="centralBankRate" id="centralBankRate" name="centralBankRate" datatype="n0-1|/^([0-9][0-9]*)+(.[0-9]{1,4})?$/"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
			      <div class="row cl">
                                   <label class="form-label span4"><span class="c-red"></span>钞买价：</label>
                                   <div class="formControls span6">
                                               <input type="text" class="input-text grid" value="" placeholder="notesBuyRate" id="notesBuyRate" name="notesBuyRate" datatype="n0-1|/^([0-9][0-9]*)+(.[0-9]{1,9})?$/"  tipmsg="格式错误!">
               			    </div>
               			    <div class="span2"> </div>
               			    </div>
                               <div class="row cl">
                                   <label class="form-label span4"><span class="c-red"></span>钞卖价：</label>
                                   <div class="formControls span6">
                                               <input type="text" class="input-text grid" value="" placeholder="notesSellRate" id="notesSellRate" name="notesSellRate" datatype="n0-1|/^([0-9][0-9]*)+(.[0-9]{1,9})?$/"  tipmsg="格式错误!">
               			    </div>
               			    <div class="span2"> </div>
               			    </div>

                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>最大浮动点：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="maxFloatRate" id="maxFloatRate" name="maxFloatRate" datatype="n0-1|/^([0-9][0-9]*)+(.[0-9]{1,4})?$/"  tipmsg="格式错误!">
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