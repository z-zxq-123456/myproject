<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>

<html>
<head>
<title>规则信息修改</title>
<script type="text/javascript" src="${ContextPath}/app/pm/up/js/edit/irlRuleMessageMod.js"></script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-rules-edit">
        <div class="row">
			<label class="form-label span4"><span class="c-red">*</span>序号：</label>
			<div class="formControls span6">
				 <input type="text" class="input-text grid" id="irlSeqNo" placeholder="序号"  name="irlSeqNo"  nullmsg="规则编号不能为空">
			</div>
			<div class="span2"> </div>
         </div>
        <div class="row">
       			<label class="form-label span4"><span class="c-red">*</span>规则状态：</label>
       			<div class="formControls span6">
                <select name="ruleStatus" id="ruleStatus" class="select" size="1" >
						 <option value="0">不启用</option>
						 <option value="1">启用</option>
                </select>
       			</div>
       			<div class="span2"> </div>
        </div>
         <div class="row">
                	<label class="form-label span4"><span class="c-red">*</span>规则描述：</label>
                	<div class="formControls span6">
                      <input type="text" class="input-text grid" id="ruleDesc" placeholder="规则描述"  name="ruleDesc"  nullmsg="规则描述不能为空">
                	</div>
                	<div class="span2"> </div>
         </div>
          <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>生效日期：</label>
            <div class="formControls span6">
            <input type="text" onfocus="WdatePicker({dateFmt:'yyyyMMddHHmmss'})" id="startDateTime" name="startDateTime" class="input-text Wdate" style="width:262px">
            </div>
            <div class="span2"> </div>
          </div>
         <div class="row">
               			<label class="form-label span4"><span class="c-red">*</span>失效日期：</label>
               			<div class="formControls span6">
                        <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDatetime\')}',dateFmt:'yyyyMMddHHmmss'})" id="endDateTime" name="endDateTime" class="input-text Wdate" style="width:262px">
               			</div>
               			<div class="span2"> </div>
         </div>
         <div class="row" id="float0">
            <label class="form-label span4"><span class="c-red">*</span>浮动类型：</label>
            <div class="formControls span6">
            <select name="intFloatType" id="intFloatType" class="select2" size="1" >
              <option value="">请选择:</option>
              <option value="0">按基准利率</option>
              <option value="1">按行内利率</option>
              <option value="2">按固定值</option>
            </select>
            </div>
            <div class="span2"> </div>
         </div>
         <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>浮动值：</label>
            <div class="formControls span6">
                    <input type="text" class="input-text grid" id="floatValue" placeholder="金额"  name="floatValue"  nullmsg="金额不能为空">
            </div>
            <div class="span2"> </div>
         </div>
		 <div class="row ">
				<div class="span-offset-1 span10 span-offset-1 mt-10">
					<input type="submit" class="button-select L smartButton"   value="修改">
				</div>
         </div>
	</form>
</div>
</body>
</html>