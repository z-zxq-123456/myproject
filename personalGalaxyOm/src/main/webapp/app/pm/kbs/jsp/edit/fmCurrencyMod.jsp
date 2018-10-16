<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表FM_CURRENCY修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/fmCurrencyMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="fmCurrencyMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>币种：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="ccy" name="ccy" datatype="*1-5" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>名称：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="ccyDesc" id="ccyDesc" name="ccyDesc" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>舍入：</label>
                    <div class="formControls span6">
                        <select id="roundTrunc" name="roundTrunc" data-placeholder="舍入" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="R" >R-四舍五入</option>
                                <option value="T" >T-舍位</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>整数描述：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="integerDesc" id="integerDesc" name="integerDesc" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>小数位：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="deciPlaces" id="deciPlaces" name="deciPlaces" datatype="*1-5" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>利率：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="ccyLibol" datatype="*0-3" id="ccyLibol" name="ccyLibol" tipmsg="格式错误!" >
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
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>基准天数：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="dayBasis" datatype="*1-5" id="dayBasis" name="dayBasis" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>小数描述：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="deciDesc" datatype="*0-15" id="deciDesc" name="deciDesc" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>周末1：</label>
                    <div class="formControls span6">
                        <select id="weekend1" name="weekend1"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="FRI" >FRI-周五</option>
                                <option value="MON" >MON-周一.SAT-周六</option>
                                <option value="SUN" >SUN-周日</option>
                                <option value="THU" >THU-周四</option>
                                <option value="TUE" >TUE-周二</option>
                                <option value="WED" >WED-周三</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>固定日期：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="fixingDays" datatype="*0-5" id="fixingDays" name="fixingDays" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>周末2：</label>
                    <div class="formControls span6">
                        <select id="weekend2" name="weekend2"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="FRI" >FRI-周五</option>
                                <option value="MON" >MON-周一.SAT-周六</option>
                                <option value="SUN" >SUN-周日</option>
                                <option value="THU" >THU-周四</option>
                                <option value="TUE" >TUE-周二</option>
                                <option value="WED" >WED-周三</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>付/收款通知日：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="payAdviceDays" datatype="*1-5" id="payAdviceDays" name="payAdviceDays" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>净头寸限额：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="positionLimit" id="positionLimit" name="positionLimit" datatype="n0-19"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>牌价类型：</label>
                    <div class="formControls span6">
                        <select id="quoteType" name="quoteType"  datatype="*" nullmsg=" 请输入！"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="D" >D-直接</option>
                                <option value="I" >I-间接</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>内部码：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="ccyIntCode" datatype="*0-3" id="ccyIntCode" name="ccyIntCode" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>货币组代码：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="ccyGroupCode" datatype="*0-3" id="ccyGroupCode" name="ccyGroupCode" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>是否货币组：</label>
                    <div class="formControls span6">
                            <select id="ccyGroup" name="ccyGroup"  class="select2"  tipmsg="格式错误!" >
                                <option value="" selected="selected">空</option>
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>即期日期：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="spotDate" datatype="*0-8" id="spotDate" name="spotDate" tipmsg="格式错误!" >
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
