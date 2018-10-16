<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<script type="text/javascript" src="${ContextPath}/app/pf/js/mbRuleDefineAdd.js"></script>
<title>产品规则添加</title>
</head>
<body>
<div class="padding-20" style="padding:0px;font-size:12px">
				  <div class="row " style="font-size:12px;text-align:left;">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
					  规则代码：
					 <input type="text" class="input-query" value="" placeholder="conditionId" style="width:120px;font-size:12px" id="conditionId" name="conditionId" datatype="*1-40">&nbsp&nbsp&nbsp
					  规则描述：
					 <input type="text" class="input-query" value="" placeholder="conditionDesc" style="width:150px;font-size:12px" id="conditionDesc" name="conditionDesc"  datatype="*1-40">&nbsp&nbsp&nbsp
					  状态：
					  <select id="status" name="status" data-placeholder="状态" style="width:100px" datatype="*1-40">
						  <option value="A">有效</option>
						  <option value="F">无效</option>
					  </select>
				   </div>
			</br>&nbsp&nbsp---------规则定义---------------------------------------------------------------------------------------------------------------</br>
				  <div class="row " style="font-size:12px;"></br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
					  条件：
					  <select id="condition" name="condition" data-placeholder="规则条件" class="select2" style="width:150px" datatype="*1-40">
			              <option value="" selected="selected">请选择</option>
					  </select>&nbsp
					  <select id="relation" name="relation" data-placeholder="状态" class="select2" style="width:100px" datatype="*1-40">
						  <option value="" selected="selected">请选择</option>
						  <option value=">">></option>
						  <option value="=">=</option>
						  <option value="<"><</option>
					  </select>&nbsp
					  <span id="conditionValue2"><select id="conditionValue" name="conditionValue" data-placeholder="参数值" class="select2" style="width:120px;" datatype="*1-40"><option value="" selected="selected">请选择</option></select></span>
					  <input id="conditionValue1" name="conditionValue1"  type="text" class="input-text grid" value="" style="width:120px;display:none" datatype="*1-40">
					  <span id="conditionValue3"><select id="conditionValue4" name="conditionValue4" data-placeholder="参数值" class="select2" style="width:120px;" datatype="*1-40"><option value="" selected="selected">请选择</option><option value="Y">Y</option><option value="N">N</option></select></span>
					  <button id="or" href="#" class="button-select M" value= "||" style="margin-bottom:10px; width:30px">||</button>
					  <button id="and" href="#" class="button-select M" value= "&" style="margin-bottom:10px; width:30px">&</button>
					  <button id="sure" href="#" class="button-select M" value= "" style="margin-bottom:10px; width:70px">确定</button>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                      <textarea id="conditionRule" style="width:400px;height:160px;" class="textarea" nullmsg="请输入" datatype="*1-100" placeholder="" rows="" cols="" name="conditionRule"></textarea>
					 </div>
						<div class="span-offset-1 span10 span-offset-1 mt-10">
						    <button id="submit" href="#" class="button-select L smartButton">添加</button>
						</div>
</div>
</body>
</html>