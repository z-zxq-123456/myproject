<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>交易CMC_PRODUCT_CAHNNEL 添加</title>
    <script type="text/javascript" src="${ContextPath}/app/cmc/js/add/cmcProductChannelAdd.js"></script>
</head>
<body>
<div class="pd-20">
    <form action="" method="post" class="form form-horizontal" id="cmcProductChannelAdd">
        <div class="row cl">
            <label class="form-label span4"><span class="c-red">*</span>卡产品编号：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" value="" placeholder="cardProductCode" id="cardProductCode" name="CMC_CARD_PRODUCT_CHANNEL"  nullmsg=" 请输入！"  tipmsg="格式错误!">
            </div>
            <div class="span2"> </div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red"></span>限制渠道：</label>
            <div class="formControls span6">
                <select id="limitChannel" name="CMC_CARD_PRODUCT_CHANNEL" data-placeholder="限制渠道" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
                </select>
            </div>
            <div class="span2"> </div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red"></span>限制交易类型：</label>
            <div class="formControls span6">
                <select id="authTranType" name="CMC_CARD_PRODUCT_CHANNEL" data-placeholder="限制交易类型" class="select2"  tabindex="4" datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
                </select>
            </div>
            <div class="span2"> </div>
        </div>
        <div class="row">
            <div class="span-offset-1 span10 span-offset-1 mt-10">
                <input type="submit" class="button-select L smartButton" title="添加"  value="添加">
            </div>
        </div>
    </form>
</div>
</body>
</html>
