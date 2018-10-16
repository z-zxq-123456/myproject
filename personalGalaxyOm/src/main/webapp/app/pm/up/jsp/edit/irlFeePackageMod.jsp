<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表IRL_FEE_PACKAGE修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/edit/irlFeePackageMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="irlFeePackageMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>套餐代码：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="packageId" name="packageId" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>生效日期：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="effectDate" id="effectDate" name="effectDate" datatype="*1-8" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>套餐描述：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="packageDesc" id="packageDesc" name="packageDesc" datatype="*1-150" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>套餐类型：</label>
                    <div class="formControls span6">
                        <select id="packageType" name="packageType" data-placeholder="套餐类型" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="NUM" >NUM-笔数</option>
                                <option value="AMT" >AMT-金额</option>
                                <option value="ALL" >ALL-笔数+金额</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>套餐状态：</label>
                    <div class="formControls span6">
                        <select id="packageStatus" name="packageStatus" data-placeholder="套餐状态" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="A" >A-启用</option>
                                <option value="C" >C-停用</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>套餐币种：</label>
                    <div class="formControls span6">
                                <select id="packageCcy" name="packageCcy" data-placeholder="套餐币种" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>失效日期：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="endDate" id="endDate" name="endDate" datatype="*1-8" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>套餐模式：</label>
                    <div class="formControls span6">
                        <select id="packageMode" name="packageMode" data-placeholder="套餐模式" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="SUB" >SUB-递减</option>
                                <option value="ADD" >ADD-递加</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>抵扣顺序：</label>
                    <div class="formControls span6">
                        <select id="processOrder" name="processOrder"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="NTA" >NTA-先笔数后金额</option>
                                <option value="ATN" >ATN-先金额后笔数</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>结算金额：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="settleAmt" id="settleAmt" name="settleAmt" datatype="n0-17"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>剩余费用处理方式：</label>
                    <div class="formControls span6">
                        <select id="processMode" name="processMode"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="CON" >CON-自动延续</option>
                                <option value="CLE" >CLE-清零</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>套餐频率：</label>
                    <div class="formControls span6">
                                <select id="packagePeriodFreq" name="packagePeriodFreq" class="select2" tipmsg="格式错误!"  >
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>可抵扣笔数：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="packageNum" id="packageNum" name="packageNum" datatype="n0-10"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>套餐费费用类型：</label>
                    <div class="formControls span6">
                                <select id="packageFeeType" name="packageFeeType" class="select2" multiple tipmsg="格式错误!"  >
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>可抵扣金额：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="packageAmt" id="packageAmt" name="packageAmt" datatype="n0-17"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>下一处理日：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="nextDealDate" datatype="*0-8" id="nextDealDate" name="nextDealDate" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>结算币种：</label>
                    <div class="formControls span6">
                                <select id="settleCcy" name="settleCcy" class="select2" tipmsg="格式错误!"  >
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>客户分类：</label>
                    <div class="formControls span6">
                                <select id="clientType" name="clientType" class="select2"  tipmsg="格式错误!"  >
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>处理日：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="dealDay" datatype="*0-2" id="dealDay" name="dealDay" tipmsg="格式错误!" >
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
