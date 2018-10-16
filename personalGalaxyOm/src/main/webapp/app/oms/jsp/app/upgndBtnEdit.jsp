<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>用户修改</title>
<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/app/upgndBtnEdit.js"></script>
</head>
<body>
<hidden id="upgndBtnId" value=""></hidden>
<div class="pd-20">
    <form action="" method="post" class="form form-horizontal" id="form-user-edit">
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>流程节点名称：</label>
            <div class="formControls span4">
                <select type="text" class="select2" placeholder="流程节点名" name="upgflowNodeId" id="upgflowNodeId"
                        nullmsg="请输入" datatype=" *1-10"></select>
            </div>
            <div class="span4"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>节点按钮名：</label>
            <div class="formControls span4">
                <input type="text" class="input-text grid" placeholder="节点按钮名" name="upgndBtnName" id="upgndBtnName"
                       nullmsg="请输入" datatype=" *1-20" >
            </div>
            <div class="span4"></div>
        </div>

        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>节点按钮序号：</label>
            <div class="formControls span4">
                <input type="text" class="input-text grid" placeholder="节点按钮序号" name="upgndBtnSeq" id="upgndBtnSeq"
                       nullmsg="请输入" datatype="n1-20" >
            </div>
            <div class="span4"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>节点按钮处理类：</label>
            <div class="formControls span4">
                <input type="text" class="input-text grid" placeholder="节点按钮处理类" name="upgndBtnClass" id="upgndBtnClass"
                       nullmsg="请输入" datatype=" *1-20">
            </div>
            <div class="span4"></div>
        </div>
        <div class="row">
            <label class="form-label span4">转向节点名称：</label>
            <div class="formControls span4">
                <select type="text" class="select2" placeholder="转向节点名称" name="upgndBtnDirnodeid" id="upgndBtnDirnodeid"
                        ></select>
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
                <input type="text" class="input-text grid" placeholder="按钮触发函数" name="upgndBtnFunc" id="upgndBtnFunc"
                       nullmsg="请输入"  datatype="*1-100">
            </div>
            <div class="span4"></div>
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
