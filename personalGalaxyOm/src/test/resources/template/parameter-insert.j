<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>交易${TableName}添加</title>
${includeAddJsPath}
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="${beanName}Add">
	    	<#list insertCol as insert>
				<#if (insert.nullAble)?has_content>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red">*</span>${insert.remark}：</label>
						<div class="formControls span6">
							<#if (insert.valueMethod == 'VL' )>
								<select id="${insert.paramName}" name="${insert.paramName}" data-placeholder="${insert.remark}" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
									<#list insert.valueScore as valueScore>
										<option value="${valueScore.ScoreValue}" >${valueScore.ScoreValue}-${valueScore.ScoreDesc}</option>
									</#list>
								</select>
							<#else>
								<#if  (insert.valueMethod == 'YN' )>
									<select id="${insert.paramName}" name="${insert.paramName}" data-placeholder="${insert.remark}" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
								<#else>
									<#if (insert.valueMethod == 'RF' )>
										<#if (insert.refTable)== (tableNameBack)>
											<input type="text" class="input-text grid" value="" placeholder="${insert.paramName}" id="${insert.paramName}" name="${TableName}"  datatype="*1-${insert.dataLength}" nullmsg=" 请输入！" tipmsg="格式错误!"<#if insert.pkColumn==insert.paramName> ajaxurl="${r"${ContextPath}"}/baseCommon/verifyOneKeyValueRepeat"</#if>>
										<#else>
											<select id="${insert.paramName}" name="${insert.paramName}" data-placeholder="${insert.remark}" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
											</select>
										</#if>
									<#else>
										<#if insert.javaType=='String'>
											<input type="text" class="input-text grid" value="" placeholder="${insert.paramName}" id="${insert.paramName}" name="${TableName}" datatype="*1-${insert.dataLength}" nullmsg=" 请输入！"  tipmsg="格式错误!" <#if insert.pkColumn==insert.paramName> ajaxurl="${r"${ContextPath}"}/baseCommon/verifyOneKeyValueRepeat"</#if>>
										<#else>
											<input type="text" class="input-text grid" value="" placeholder="${insert.paramName}" id="${insert.paramName}" name="${TableName}" datatype="*1-${insert.dataLength}" nullmsg=" 请输入！"  tipmsg="格式错误!"<#if insert.pkColumn==insert.paramName> ajaxurl="${r"${ContextPath}"}/baseCommon/verifyOneKeyValueRepeat"</#if>>
										</#if>
									</#if>
								</#if>
							</#if>
						</div>
						<div class="span2"> </div>
					</div>
				<#else>
					<div class="row cl">
						<label class="form-label span4"><span class="c-red"></span>${insert.remark}：</label>
						<div class="formControls span6">
							<#if (insert.valueMethod == 'VL' )>
								<select id="${insert.paramName}" name="${insert.paramName}" class="select2" tipmsg="格式错误!">
									<option value="" selected="selected">空</option>
									<#list insert.valueScore as valueScore>
										<option value="${valueScore.ScoreValue}" >${valueScore.ScoreValue}-${valueScore.ScoreDesc}</option>
									</#list>
								</select>
							<#else>
								<#if  (insert.valueMethod == 'YN' )>
									<select id="${insert.paramName}" name="${insert.paramName}"  class="select2"  tipmsg="格式错误!">
										<option value="" selected="selected">空</option>
										<option value="Y" >是</option>
										<option value="N" >否</option>
									</select>
								<#else>
									<#if (insert.valueMethod == 'RF' )>
										<#if (insert.refTable)== (tableName)>
											<input type="text" class="input-text grid" value="" placeholder="${insert.paramName}" datatype="*0-${insert.dataLength}" id="${insert.paramName}" name="${insert.paramName}" tipmsg="格式错误!" >
										<#else>
											<select id="${insert.paramName}" name="${insert.paramName}" class="select2" tabindex="4" tipmsg="格式错误!">
											</select>
										</#if>
									<#else>
										<#if insert.javaType=='String'>
											<input type="text" class="input-text grid" value="" placeholder="${insert.paramName}" datatype="*0-${insert.dataLength}" id="${insert.paramName}" name="${insert.paramName}" tipmsg="格式错误!" >
										<#else>
											<input type="text" class="input-text grid" value="" placeholder="${insert.paramName}" id="${insert.paramName}" name="${insert.paramName}" datatype="n0-${insert.dataLength}"  tipmsg="格式错误!">
										</#if>
									</#if>
								</#if>
							</#if>
						</div>
						<div class="span2"> </div>
					</div>
				</#if>
			</#list>
			<div class="row">
				<div class="span-offset-1 span10 span-offset-1 mt-10">
					<input type="submit" class="button-select L smartButton" title="添加"  value="添加">
				</div>
			</div>
    	</form>
	</div>
</body>
</html>
