<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>应用信息管理</title>
<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/app/upgndBtnAdd.js"></script>
</head>
<body>
<div class="pd-20">
    <form action="" method="post" class="form form-horizontal" id="form-data-add" name="fileForm">
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>流程节点名：</label>
            <div class="formControls span4">
                <select type="text" class="select2" name="upgflowNodeId" id="upgflowNodeId"
                        datatype="*1-10" placeholder="请选择！" nullmsg="请输入！" tipmsg="格式错误!"></select>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>节点按钮名：</label>
            <div class="formControls span4">
                <input type="text" class="input-text grid" placeholder="节点按钮名" name="upgndBtnName" id="upgndBtnName"
                       datatype="*1-20" placeholder="请输入1-20位字符！" nullmsg="请输入！" tipmsg="格式错误!">
            </div>
            <div class="span2"></div>
        </div>

        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>节点按钮序号：</label>
            <div class="formControls span4">
                <input type="text" class="input-text grid" name="upgndBtnSeq" id="upgndBtnSeq"
                       datatype="n1-20" placeholder="请输入1-20位数字！" nullmsg="请输入！" tipmsg="格式错误!">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>节点按钮处理类：</label>
            <div class="formControls span4">
                <input type="text" class="input-text grid" name="upgndBtnClass" id="upgndBtnClass"
                       datatype="*1-20" placeholder="请输入1-20位字符！" nullmsg="请输入！" tipmsg="格式错误!">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <label class="form-label span4">转向节点名称：</label>
            <div class="formControls span4">
                <select type="text" class="select2" placeholder="转向节点名称" name="upgndBtnDirnodeid" id="upgndBtnDirnodeid"
                        datatype="*1-60"></select>
            </div>
        </div>

        <div class="row">
            <label class="form-label span4">是否显示按钮：</label>
            <div class="formControls span4">
                <select type="text" class="select2" placeholder="是否显示按钮" name="upgndBtnIsview" id="upgndBtnIsview"
                        datatype="*1-60"></select>
            </div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>按钮触发函数：</label>
            <div class="formControls span4">
                <input type="text" class="input-text grid" name="upgndBtnFunc" id="upgndBtnFunc"
                       datatype="*1-100" placeholder="请输入1-100位字符！" nullmsg="请输入！" tipmsg="格式错误!">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <div class="span4"></div>
            <input type="submit" class="button-select M" title="提交" value="提交">&nbsp;
            <a onclick="dataAddCancel()" class="button-cancle M" title="关闭">关闭</a>
        </div>
    </form>
</div>
</body>
</html>
