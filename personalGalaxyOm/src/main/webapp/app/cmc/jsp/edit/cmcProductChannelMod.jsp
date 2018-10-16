<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>参数表CMC_CARD_ORDER_NO_INFO修改</title>
    <script type="text/javascript" src="${ContextPath}/app/cmc/js/edit/cmcProductChannelMod.js"></script>
</head>
<body>
<div class="pd-20">
    <form action="" method="post" class="form form-horizontal" id="cardProductChannelMod">
        <div class="row cl">
            <label class="form-label span4"><span class="c-red">*</span>卡产品编号：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" value=""  id="cardProductCode" name="cardProductCode" >
            </div>
            <div class="span2"> </div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red"></span>限制渠道：</label>
            <div class="formControls span6">
                <select id="limitChannel" name="CMC_CARD_PRODUCT_CHANNEL" data-placeholder="限制渠道" class="select2"  tabindex="4" datatype="*" tipmsg="格式错误!">
                </select>
            </div>
            <div class="span2"> </div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red"></span>限制交易类型：</label>
            <div class="formControls span6">
                <select id="authTranType" name="CMC_CARD_PRODUCT_CHANNEL" data-placeholder="限制交易类型" class="select2"  tabindex="4" datatype="*"  tipmsg="格式错误!">
                </select>
            </div>
            <div class="span2"> </div>
        </div>
        <div class="row">
            <div class="span-offset-1 span10 span-offset-1 mt-10">
                <input type="submit" class="button-select L smartButton" title="修改"  value="修改">
            </div>
        </div>
    </form>
</div>
</body>
</html>
