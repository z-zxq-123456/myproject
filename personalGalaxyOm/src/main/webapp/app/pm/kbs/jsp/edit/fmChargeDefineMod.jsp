<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表FM_CHARGE_DEFINE修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/fmChargeDefineMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="fmChargeDefineMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>服务费类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="chargeType" name="chargeType" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>转账收费交易类型：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="transEventType" datatype="*0-4" id="transEventType" name="transEventType" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>是否按平均余额计费：</label>
                    <div class="formControls span6">
                        <select id="calcOnAverages" name="calcOnAverages"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="N" >N--当前余额</option>
                                <option value="Y" >Y--平均余额</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>现金收费交易类型：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="cashEventType" datatype="*0-4" id="cashEventType" name="cashEventType" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>币种：</label>
                    <div class="formControls span6">
                                <select id="ccy" name="ccy" datatype="*"  class="select2" tipmsg="格式错误!"  >
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>服务费计费方式：</label>
                    <div class="formControls span6">
                        <select id="chargeMethod" name="chargeMethod"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="F" >F-固定金额</option>
                                <option value="R" >R-按费率计算</option>
                                <option value="A" >A-按数量</option>
                                <option value="M" >M-矩阵</option>
                                <option value="B" >B-固定金额+费率</option>
                                <option value="C" >C-客户自定义计费公式</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>费用收取方式：</label>
                    <div class="formControls span6">
                        <select id="chargeMode" name="chargeMode"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="C" >C-现金</option>
                                <option value="T" >T-转账</option>
                                <option value="A" >A-现金转账皆可</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>金额折算标志：</label>
                    <div class="formControls span6">
                        <select id="convertFlag" name="convertFlag"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="N" >N-不折算</option>
                                <option value="B" >B-比率前折算</option>
                                <option value="A" >A-比率后折算</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>费用描述：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="feeDesc" datatype="*0-50" id="feeDesc" name="feeDesc" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>固定金额/单价：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="fixAmt" id="fixAmt" name="fixAmt" datatype="n0-17"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>固定费率：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="fixRate" id="fixRate" name="fixRate" datatype="n0-13"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>上限：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="highLimit" id="highLimit" name="highLimit" datatype="n0-17"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>下限：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="lowLimit" id="lowLimit" name="lowLimit" datatype="n0-17"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>矩阵计算方式：</label>
                    <div class="formControls span6">
                        <select id="matrixCalc" name="matrixCalc"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="B" >B-差额累进</option>
                                <option value="F" >F-全额累进</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>缺口类型：</label>
                    <div class="formControls span6">
                        <select id="matrixType" name="matrixType"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="A" >A-金额</option>
                                <option value="M" >M-月份</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>说明：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="remarks" datatype="*0-100" id="remarks" name="remarks" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>计算公式名称：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="routineName" datatype="*0-40" id="routineName" name="routineName" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>联机/日终收取：</label>
                    <div class="formControls span6">
                        <select id="boClass" name="boClass"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="O" >O-联机</option>
                                <option value="B" >B-日终</option>
                                <option value="S" >S-累计</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>是否全额计费：</label>
                    <div class="formControls span6">
                        <select id="calcFullUndrawn" name="calcFullUndrawn"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="F" >F-总贷款额</option>
                                <option value="O" >O-未清偿贷款额</option>
                                <option value="U" >U-未发放贷款额</option>
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
