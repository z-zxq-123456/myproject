<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表TB_VOUCHER_DEF修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/tbVoucherDefMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="tbVoucherDefMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>凭证类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="docType" name="docType" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>是否支票标记：</label>
                    <div class="formControls span6">
                            <select id="isChequeBook" name="isChequeBook" data-placeholder="是否支票标记" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>前缀标志：</label>
                    <div class="formControls span6">
                            <select id="prefixReq" name="prefixReq" data-placeholder="前缀标志" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>状态：</label>
                    <div class="formControls span6">
                        <select id="status" name="status" data-placeholder="状态" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="A" >A-有效</option>
                                <option value="C" >C-关闭</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>总行入库标志：</label>
                    <div class="formControls span6">
                            <select id="inContral" name="inContral" data-placeholder="总行入库标志" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>是否有号：</label>
                    <div class="formControls span6">
                            <select id="haveNumber" name="haveNumber" data-placeholder="是否有号" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>类型描述：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="docTypeDesc" id="docTypeDesc" name="docTypeDesc" datatype="*1-50" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>凭证种类：</label>
                    <div class="formControls span6">
                        <select id="docClass" name="docClass" data-placeholder="凭证种类" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="PBK" >PBK-存折</option>
                                <option value="CHK" >CHK-支票</option>
                                <option value="DCT" >DCT-存单</option>
                                <option value="CRD" >CRD-卡</option>
                                <option value="CFT" >CFT-存款证明</option>
                                <option value=" BNK" > BNK-银行票据</option>
                                <option value="COL" >COL-托收票据</option>
                                <option value="DFT" >DFT-银行汇票</option>
                                <option value="TCH" >TCH-旅行支票</option>
                                <option value="BAT" >BAT-银行承兑汇票</option>
                                <option value="CAT" >CAT-商业承兑汇票</option>
                                <option value="CHQ" >CHQ-支票</option>
                                <option value="OTH" >OTH-其他</option>
                                <option value="SCV" >SCV-印鉴</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>他行票据标记：</label>
                    <div class="formControls span6">
                            <select id="otherBankFlag" name="otherBankFlag" data-placeholder="他行票据标记" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>允许调拨标志：</label>
                    <div class="formControls span6">
                            <select id="allowDistr" name="allowDistr" data-placeholder="允许调拨标志" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>是否限制机构使用：</label>
                    <div class="formControls span6">
                            <select id="branchRestraint" name="branchRestraint" data-placeholder="是否限制机构使用" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>凭证票据标识：</label>
                    <div class="formControls span6">
                        <select id="voucherBillInd" name="voucherBillInd" data-placeholder="凭证票据标识" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="V" >V-凭证</option>
                                <option value="C" >C-票据</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>是否按顺序使用：</label>
                    <div class="formControls span6">
                            <select id="useByOrder" name="useByOrder" data-placeholder="是否按顺序使用" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>凭证号长度：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="voucherLength" id="voucherLength" name="voucherLength" datatype="n1-20"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>有价单证固定面额组：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="tcDenomGroup" datatype="*0-8" id="tcDenomGroup" name="tcDenomGroup" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>生效日期：</label>
                    <div class="formControls span6">
                                <input type="text" onfocus="WdatePicker({dateFmt:'yyyyMMdd'})" class="input-text Wdate" value="" placeholder="startDate" datatype="*0-8" id="startDate" name="startDate" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>利润中心：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="profitCentre" datatype="*0-12" id="profitCentre" name="profitCentre" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>柜员ID：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="userId" datatype="*0-30" id="userId" name="userId" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>上次修改柜员：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="lastChangeUserId" datatype="*0-30" id="lastChangeUserId" name="lastChangeUserId" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>复核柜员：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="checkUserId" datatype="*0-30" id="checkUserId" name="checkUserId" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>代办人口挂天数：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="commissionVouLostDays" id="commissionVouLostDays" name="commissionVouLostDays" datatype="n0-3"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>法人：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="company" datatype="*0-20" id="company" name="company" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>存款类型：</label>
                    <div class="formControls span6">
                        <select id="depositType" name="depositType"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="S" >S-储蓄</option>
                                <option value="C" >C-往来</option>
                                <option value="T" >T-定期</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>失效日期：</label>
                    <div class="formControls span6">
                                <input type="text" onfocus="WdatePicker({dateFmt:'yyyyMMdd'})" class="input-text Wdate" value="" placeholder="expireDate" datatype="*0-8" id="expireDate" name="expireDate" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>是否现金支票标记：</label>
                    <div class="formControls span6">
                            <select id="isCashCheque" name="isCashCheque"  class="select2"  tipmsg="格式错误!" >
                                <option value="" selected="selected">空</option>
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>有价单证是否固定面额标志：</label>
                    <div class="formControls span6">
                            <select id="allowChequeDenom" name="allowChequeDenom"  class="select2"  tipmsg="格式错误!" >
                                <option value="" selected="selected">空</option>
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>上次修改日期：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="lastChangeDate" datatype="*0-8" id="lastChangeDate" name="lastChangeDate" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>口挂天数：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="vouLostDays" id="vouLostDays" name="vouLostDays" datatype="n0-3"  tipmsg="格式错误!">
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
