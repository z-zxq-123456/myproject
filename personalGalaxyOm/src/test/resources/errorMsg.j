<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>${errorMsg}</title>
</head>
<body>
    <div class="padding-10">
        <div class="mr-20 ml-20 mt-10">
            <table id="errorLists" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                     <tr class="text-c">
                        <#list insertCol as insert>
                            <th style="width:${insert[1]}%">${insert[0]}</th>
                        </#list>
                    </tr>
                </thead>
                <tbody>
                    <#list dataSet as dataCol>
                    <tr>
                        <#list insertCol as insert>
                            <td>${dataCol[insert_index]}</td>
                        </#list>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
<script type="text/javascript">
$(document).ready(function() {
	// 获取默认opt配置
	var opt = getDataTableOpt($("#errorLists"));
	opt.stateSave=true;
	opt.processing=true;
	opt.scrollX=true;
	opt.autoWidth=false;
    opt.columns=[
<#list insertCol as insert>
    <#if insert_index==0>
        { title: "${insert[0]}"}
    <#else>
        ,{ title: "${insert[0]}"}
    </#if>
</#list>
        ];
	//渲染tables
	drawDataTable($("#errorLists"),opt);
    $("#errorLists").beautyUi({
        tableId:"errorLists",
        needBtn: false
        });
});
</script>
