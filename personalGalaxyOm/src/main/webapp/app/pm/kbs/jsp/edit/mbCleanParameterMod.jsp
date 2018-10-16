<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表MB_CLEAN_PARAMETER修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/mbCleanParameterMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="mbCleanParameterMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>序号：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="seqNo" name="seqNo" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>账户状态：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="acctStatus" datatype="*0-1" id="acctStatus" name="acctStatus" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>期限：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="termPeriod" datatype="*0-5" id="termPeriod" name="termPeriod" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>状态：</label>
                    <div class="formControls span6">
                        <select id="status" name="status"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                                <option value="A" >A-有效</option>
                                <option value="C" >C-关闭</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>开始时间：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="startTime" datatype="*0-8" id="startTime" name="startTime" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>产品类型：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="prodType" datatype="*0-10" id="prodType" name="prodType" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>期限频率类型：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="periodFreqType" datatype="*0-2" id="periodFreqType" name="periodFreqType" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>期限频率：</label>
                    <div class="formControls span6">
                                <select id="periodFreq" name="periodFreq" class="select2" tipmsg="格式错误!"  >
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>下次清理日期：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="nextCleanDate" datatype="*0-8" id="nextCleanDate" name="nextCleanDate" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>上次清理日期：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="lastCleanDate" datatype="*0-8" id="lastCleanDate" name="lastCleanDate" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>结束时间：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="endTime" datatype="*0-8" id="endTime" name="endTime" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>清理类型：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="cleanType" datatype="*0-4" id="cleanType" name="cleanType" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>币种：</label>
                    <div class="formControls span6">
                                <select id="ccy" name="ccy" class="select2" tipmsg="格式错误!"  >
                                </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>金额：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="balance" id="balance" name="balance" datatype="n0-17"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>协议类型：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="agreementType" datatype="*0-3" id="agreementType" name="agreementType" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>账户类型：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="acctType" datatype="*0-10" id="acctType" name="acctType" tipmsg="格式错误!" >
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>期限类型：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="termType" datatype="*0-1" id="termType" name="termType" tipmsg="格式错误!" >
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
