<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>应用监控指标新增</title>
     <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/shshpoc/indexAdd.js"></script>
</head>
<body>
<div class="pd-20">
    <form action="" method="post" class="form form-horizontal" id="form-parameter-add" name="fileForm">
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>指标名称：</label>
            <div class="formControls span4">
                <input type="text" class="input-text grid" placeholder="指标名称" name="appIndexName" id="appIndexName"
                       datatype="*1-60" nullmsg="该输入项为必输项">
            </div>
            <div class="span3"></div>
        </div>
        <div class="row">
                  <label class="form-label span4"><span class="c-red">*</span>指标序号：</label>
                  <div class="formControls span4">
                      <input type="text" class="input-text grid" placeholder="指标序号" name="appIndexNo" id="appIndexNo"
                             datatype="*1-60" nullmsg="该输入项为必输项">
                  </div>
                  <div class="span3"></div>
              </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>是否显示：</label>
            <div class="formControls span4">
                <select type="text" class="select2" placeholder="指标序号" name="appIndexIsview" id="appIndexIsview"
                        datatype="*1-60" nullmsg="是否显示"></select>
            </div>
            <div class="span3"></div>
        </div>

        <div class="row">
            <label class="form-label span4">指表字段：</label>
            <div class="formControls span4">
                <input type="text" class="input-text grid" placeholder="0-100个字符" name="appIndexDesc" id="appIndexDesc">
            </div>
        </div>
        <div class="row">
            <label class="form-label span4">指标算法：</label>
            <div class="formControls span4">
                <input type="text" class="input-text grid" placeholder="0-100个字符" name="appIndexFac" id="appIndexFac">
            </div>
        </div>
        <div class="row">
            <div class="span4"></div>
            <input type="submit" class="button-select M" title="提交" value="提交">&nbsp;
            <a onclick="appInfoAddCancel()" class="button-cancle M" title="关闭">关闭</a>
        </div>
    </form>
</div>
</body>
</html>
