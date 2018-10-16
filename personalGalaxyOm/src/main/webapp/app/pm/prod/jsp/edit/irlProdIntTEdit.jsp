<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
<script type="text/javascript" src="${ContextPath}/app/pm/prod/js/edit/irlProdIntTEdit.js"></script>
    <title>参数表IRL_PROD_INT修改</title>
</head>
<body>
<div class="pd-20">
    <form action="" method="post" class="form form-horizontal" id="irlProdIntMod">
        <div class="row cl">
            <label class="form-label span4"><span class="c-red">*</span>产品类型：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" value="" id="prodType" name="prodType">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red">*</span>事件类型：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" value="" id="eventType" name="eventType">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red">*</span>计息类型：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" value="" id="intClass" name="intClass">
            </div>
            <div class="span2"></div>
        </div>

        <div class="row cl">
            <label class="form-label span4"><span class="c-red">*</span>利息计算金额ID：</label>
            <div class="formControls span6">
                <select id="intAmtId" name="intAmtId" data-placeholder="利息计算金额ID" class="select2" tabindex="4"
                        datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                </select>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red"></span>利息计算方法：</label>
            <div class="formControls span6">

                <select id="intCalcBal" name="intCalcBal" class="select2" tipmsg="格式错误!">
                    <option value="" selected="selected">空</option>
                    <option value="AB">AB-积数计息</option>
                    <option value="EB">EB-分段计息</option>
                    <option value="BS">BS-差减法计息</option>
                </select>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red"></span>靠档天数计算方式：</label>
            <div class="formControls span6">

                <select id="intDaysType" name="intDaysType" class="select2" tipmsg="格式错误!">
                    <option value="" selected="selected">空</option>
                    <option value="A">A-按存期</option>
                    <option value="B">B-实际天数</option>
                    <option value="C">C-计提天数</option>
                </select>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red"></span>计息起始日期取值方法：</label>
            <div class="formControls span6">

                <select id="intStart" name="intStart" class="select2" tipmsg="格式错误!">
                    <option value="" selected="selected">空</option>
                    <option value="O">O-开户日期</option>
                    <option value="M">M-到期日</option>
                    <option value="I">I-计提日期</option>
                </select>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red"></span>利率类型：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" value="" placeholder="intType" datatype="*0-3" id="intType"
                       name="intType" tipmsg="格式错误!">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red">*</span>利率计算金额ID：</label>
            <div class="formControls span6">
                <select id="rateAmtId" name="rateAmtId" data-placeholder="利率计算金额ID" class="select2" tabindex="4"
                        datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                </select>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red">*</span>重算利息方法：</label>
            <div class="formControls span6">

                <select id="recalMethod" name="recalMethod" data-placeholder="重算利息方法" class="select2" tabindex="4"
                        datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                    <option value="N">N-重新按模型计算</option>
                    <option value="H">H-取历史利率计算</option>
                    <option value="I">I-取历史</option>

                </select>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red"></span>税率类型代码：</label>
            <div class="formControls span6">
                <select id="taxType" name="taxType" class="select2" tipmsg="格式错误!">
                    <option value="" selected="selected">空</option>
                </select>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red"></span>法人代表：</label>
            <div class="formControls span6">
                <select id="company" name="company" data-placeholder="法人代表" class="select2" tabindex="4"
                        nullmsg=" 请输入！" tipmsg="格式错误!">
                </select>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red"></span>利率启用方式：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" value="" placeholder="intApplType" datatype="*0-1"
                       id="intApplType" name="intApplType" tipmsg="格式错误!">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red"></span>利率变更周期：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" value="" placeholder="rollFreq" datatype="*0-3" id="rollFreq"
                       name="rollFreq" tipmsg="格式错误!">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red"></span>变更日期：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" value="" placeholder="rollDay" id="rollDay" name="rollDay"
                       datatype="n0-2" tipmsg="格式错误!">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red"></span>最大利率：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" value="" placeholder="maxRate" id="maxRate" name="maxRate"
                       datatype="n0-21" tipmsg="格式错误!">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red"></span>最小利率：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" value="" placeholder="minRate" id="minRate" name="minRate"
                       datatype="n0-21" tipmsg="格式错误!">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red"></span>利率靠档标志：</label>
            <div class="formControls span6">
                <select id="intRateInd" name="intRateInd" class="select2" tipmsg="格式错误!">
                    <option value="" selected="selected">空</option>
                    <option value="F">F-靠下档</option>
                    <option value="C">C-靠上档</option>
                </select>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red"></span>月基准天数类型：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" value="" placeholder="monthBasis" datatype="*0-3"
                       id="monthBasis" name="monthBasis" tipmsg="格式错误!">
            </div>
            <div class="span2"></div>
        </div>
              <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>分组规则关系：</label>
                    <div class="formControls span6">
                        <input type="text" class="input-text grid" value="" placeholder="groupRuleType" datatype="*0-3"
                               id="groupRuleType" name="groupRuleType" tipmsg="格式错误!">
                    </div>
                    <div class="span2"></div>
                </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red">*</span>首个分段ID：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" value="" placeholder="splitId"
                       id="splitId" name="splitId" nullmsg=" 请输入！"  datatype="*1-10"   tipmsg="格式错误!">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red"></span>分段类型：</label>
            <div class="formControls span6">
                <select id="splitType" name="splitType" class="select2" tipmsg="格式错误!">
                    <option value="" selected="selected">空</option>

                    <option value="PA">PA-先金额后周期</option>

                    <option value="AP">AP-先周期后金额</option>
                </select>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red">*</span>规则ID：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" value="" placeholder="spilitId"
                       id="ruleId" name="ruleId" nullmsg=" 请输入！"  datatype="*1-10"   tipmsg="格式错误!">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <div class="span-offset-1 span10 span-offset-1 mt-10">
                <input type="submit" class="button-select L smartButton" title="修改" value="修改">
            </div>
        </div>
    </form>
</div>
</body>
</html>