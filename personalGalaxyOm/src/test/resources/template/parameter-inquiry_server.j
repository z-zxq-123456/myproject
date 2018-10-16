<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/formServerSide.jsp"%>
<html>
<head>
    <title>${TableName}交易</title>
    ${includeJsPath}
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<a><span>&gt;</span>参数管理</a><span >&gt;</span><span >参数录入查看</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
</div>
    <div class="padding-10">
        <form action="" method="post" class="form form-horizontal" id="queryPrimaryKey">
            <div class="row cl">
                <#list insertCol as insert>
                    <#if (insert.YnSelect == "Y")>
                        <label class="form-label">${insert.remark}：</label>
                        <div class="formControls  span2">
                            <#if (insert.valueMethod == 'VL' )>
                                <select id="${insert.colName}" name="${insert.paramName}" class="select2" size="1"  style="width:100%" >
                                    <option value="" selected="selected">空</option>
                                    <#list insert.valueScore as valueScore>
                                        <option value="${valueScore.ScoreValue}" >${valueScore.ScoreValue}-${valueScore.ScoreDesc}</option>
                                    </#list>
                                </select>
                            <#else>
                                <#if  (insert.valueMethod == 'YN' )>
                                    <select id="${insert.colName}"  class="select2" name="${insert.paramName}" size="1"  style="width:100%">
                                        <option value="" selected="selected">空</option>
                                        <option value="Y" >是</option>
                                        <option value="N" >否</option>
                                    </select>
                                <#else>
                                    <#if (insert.valueMethod == 'RF' )>
                                        <select id="${insert.colName}" class="select2" name="${insert.paramName}" tabindex="4" size="1"  style="width:100%">
                                        </select>
                                    <#else>
                                        <input type="text"  value="" class="input-text grid" placeholder="${insert.paramName}" id="${insert.colName}" name="${insert.paramName}"  >
                                    </#if>
                                </#if>
                            </#if>
                        </div>
                    </#if>
                </#list>
                <#if selectIs =="Y">
                    <a id="selectByPrimaryKey" href="#" class="button-sure M" style="margin-bottom:10px">
                    <i class="iconfont">&#xe624;</i>  查询</a>
                </#if>
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="${beanName}" class="table table-border table-bordered table-hover table-bg">
                <thead>
                     <tr class="text-c">
                        <th>操作类型</th>
                    <#list insertCol as insert>
                        <th>${insert.remark}</th>
                    </#list>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>
