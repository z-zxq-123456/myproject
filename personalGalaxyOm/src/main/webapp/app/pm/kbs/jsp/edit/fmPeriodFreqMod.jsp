<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表FM_PERIOD_FREQ修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/edit/fmPeriodFreqMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="fmPeriodFreqMod">
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>期限类型：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="periodFreq" name="periodFreq" >
			    </div>
			    <div class="span2"> </div>
			</div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>每期数量：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="addNo" id="addNo" name="addNo" datatype="*1-5" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>期初/期末：</label>
                    <div class="formControls span6">
                        <select id="firstLast" name="firstLast" data-placeholder="期初/期末" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="F" >F-期初</option>
                                <option value="L" >L-期末</option>
                                <option value="A" >A-实际天数</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>描述：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="periodFreqDesc" id="periodFreqDesc" name="periodFreqDesc" datatype="*1-20" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>半月标识：</label>
                    <div class="formControls span6">
                            <select id="halfMonth" name="halfMonth" data-placeholder="半月标识" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>每期单位：</label>
                    <div class="formControls span6">
                        <select id="dayMth" name="dayMth" data-placeholder="每期单位" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="D" >D-Day</option>
                                <option value="M" >M-Month</option>
                        </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>每期天数：</label>
                    <div class="formControls span6">
                                    <input type="text" class="input-text grid" value="" placeholder="dayNum" id="dayNum" name="dayNum" datatype="*1-5" nullmsg=" 请输入！"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>节假日是否顺延：</label>
                    <div class="formControls span6">
                            <select id="forceWorkDay" name="forceWorkDay" data-placeholder="节假日是否顺延" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>客户浮动：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="clientSpread" id="clientSpread" name="clientSpread" datatype="n1-7"  tipmsg="格式错误!">
			    </div>
			    <div class="span2"> </div>
			    </div>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>期限单位值：</label>
                    <div class="formControls span6">
                                <input type="text" class="input-text grid" value="" placeholder="priorDays" datatype="*1-5" id="priorDays" name="priorDays" tipmsg="格式错误!" >
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
