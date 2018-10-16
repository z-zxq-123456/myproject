<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>用户修改</title>
     <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/sys/paraEdit.js"></script>
</head>
<body>
<div class="pd-20">
    <form action="" method="post" class="form form-horizontal" id="form-parameter-edit">
        <div class="row">
            <label class="form-label span4">参数编码：</label>
            <div class="formControls span4">
                <input type="text" class="input-text grid" name="paraCode" id="paraCode" disabled>
            </div>
            <div class="span3"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>参数名称：</label>
            <div class="formControls span4">
                <input type="text" class="input-text grid" placeholder="参数名称" name="paraName" id="paraName"
                       datatype="*1-60" nullmsg="请输入应用英文简称">
            </div>
            <div class="span3"></div>
        </div>

        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>上级编码：</label>
            <div class="formControls span4">
                <input type="text" class="input-text grid" placeholder="上级编码" name="paraParentId" id="paraParentId"
                       datatype="*1-60" nullmsg="请输入应用名称">
            </div>
            <div class="span3"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>编码状态：</label>
            <div class="formControls span4">
                <select type="text" class="select2" placeholder="编码状态" name="isValidate" id="isValidate"
                        datatype="*1-60" nullmsg="应用类型"></select>
            </div>
            <div class="span3"></div>
        </div>

        <div class="row">
            <label class="form-label span4">备注一：</label>
            <div class="formControls span4">
                <input type="text" class="input-text grid" placeholder="备注一" name="remark1" id="remark1">
            </div>
        </div>
        <div class="row">
            <label class="form-label span4">备注二：</label>
            <div class="formControls span4">
                <input type="text" class="input-text grid" placeholder="备注二" name="remark2" id="remark2">
            </div>
        </div>
        <div class="row">
            <label class="form-label span4">备注三：</label>
            <div class="formControls span4">
                <input type="text" class="input-text grid" placeholder="备注三" name="remark3" id="remark3">
            </div>
        </div>
        <div class="row">
            <div class="span4"></div>
            <input type="submit" class="button-select M" title="提交" value="提交">&nbsp;
            <a onclick="dataEditCancel()" class="button-cancle M" title="关闭">关闭</a>
        </div>
    </form>
</div>
</body>
</html>
