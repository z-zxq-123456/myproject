<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<title>参数表${TableName}修改</title>
${includeModJsPath}
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="${beanName}Mod">
		<#list pks as pk>
			<div class="row cl">
			    <label class="form-label span4"><span class="c-red">*</span>${pk.remark}：</label>
			    <div class="formControls span6">
			        <input type="text" class="input-text grid" value=""  id="${pk.paramName}" name="${pk.paramName}" >
			    </div>
			    <div class="span2"> </div>
			</div>
		</#list>
		<#list updateCol as col>
			<#if (col.nullAble)?has_content>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red">*</span>${col.remark}：</label>
                    <div class="formControls span6">
                    <#if (col.valueMethod == 'VL' )>
                        <select id="${col.paramName}" name="${col.paramName}" data-placeholder="${col.remark}" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                            <#list col.valueScore as valueScore>
                                <option value="${valueScore.ScoreValue}" >${valueScore.ScoreValue}-${valueScore.ScoreDesc}</option>
                            </#list>
                        </select>
                    <#else>
                        <#if  (col.valueMethod == 'YN' )>
                            <select id="${col.paramName}" name="${col.paramName}" data-placeholder="${col.remark}" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
                        <#else>
                            <#if (col.valueMethod == 'RF' )>
                                <select id="${col.paramName}" name="${col.paramName}" data-placeholder="${col.remark}" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                                </select>
                            <#else>
                                <#if col.javaType=='String'>
                                    <input type="text" class="input-text grid" value="" placeholder="${col.paramName}" id="${col.paramName}" name="${col.paramName}" datatype="*1-${col.dataLength}" nullmsg=" 请输入！"  tipmsg="格式错误!">
                                <#else>
                                    <input type="text" class="input-text grid" value="" placeholder="${col.paramName}" id="${col.paramName}" name="${col.paramName}" datatype="*1-${col.dataLength}" nullmsg=" 请输入！"  tipmsg="格式错误!">
                                </#if>
                            </#if>
                        </#if>
                    </#if>
			    </div>
			    <div class="span2"> </div>
			    </div>
			<#else>
                <div class="row cl">
                    <label class="form-label span4"><span class="c-red"></span>${col.remark}：</label>
                    <div class="formControls span6">
                    <#if (col.valueMethod == 'VL' )>
                        <select id="${col.paramName}" name="${col.paramName}"  class="select2" tipmsg="格式错误!" >
                            <option value="" selected="selected">空</option>
                            <#list col.valueScore as valueScore>
                                <option value="${valueScore.ScoreValue}" >${valueScore.ScoreValue}-${valueScore.ScoreDesc}</option>
                            </#list>
                        </select>
                    <#else>
                        <#if  (col.valueMethod == 'YN' )>
                            <select id="${col.paramName}" name="${col.paramName}"  class="select2"  tipmsg="格式错误!" >
                                <option value="" selected="selected">空</option>
                                <option value="Y" >是</option>
                                <option value="N" >否</option>
                            </select>
                        <#else>
                            <#if (col.valueMethod == 'RF' )>
                                <select id="${col.paramName}" name="${col.paramName}" class="select2" tipmsg="格式错误!"  >
                                </select>
                            <#else>
                                <#if col.javaType=='String'>
                                <input type="text" class="input-text grid" value="" placeholder="${col.paramName}" datatype="*0-${col.dataLength}" id="${col.paramName}" name="${col.paramName}" tipmsg="格式错误!" >
                                <#else>
                                <input type="text" class="input-text grid" value="" placeholder="${col.paramName}" id="${col.paramName}" name="${col.paramName}" datatype="n0-${col.dataLength}"  tipmsg="格式错误!">
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
                <input type="submit" class="button-select L smartButton" title="修改"  value="修改">
            </div>
        </div>
	</form>
</div>
</body>
</html>
