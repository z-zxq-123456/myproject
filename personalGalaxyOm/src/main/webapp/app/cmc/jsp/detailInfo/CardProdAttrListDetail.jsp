<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<style type="text/css">
    td.details-control {
        background: url("${ContextPath}/lib/datatables/1.10.7/images/details_open.png") no-repeat center center;
        cursor: pointer;
    }
    tr.shown td.details-control {
        background: url("${ContextPath}/lib/datatables/1.10.7/images/details_close.png") no-repeat center center;
    }
</style>
<head>
    <script type="text/javascript" src="${ContextPath}/app/cmc/js/detailInfo/CardProdAttrListDetail.js"></script>
    <link href="${ContextPath}/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet"
          type="text/css" />
    <link href="${ContextPath}/lib/bootstrap/css/font-awesome.min.css" rel="stylesheet"
          type="text/css" />
    <link href="${ContextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${ContextPath}/lib/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
    <link href="${ContextPath}/lib/zTree/css/demo.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ContextPath}/lib/icheck/jquery.icheck.min.js"></script>
    <script src="${ContextPath}/lib/jquery-step/js/jquery.step.js"></script>
    <link href="${ContextPath}/lib/jquery-step/css/jquery.step.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ContextPath}/lib/zTree/js/jquery.ztree.all.min.js"></script>
    <link rel="stylesheet" href="${ContextPath}/lib/zTree/css/metroStyle/metroStyle.css" type="text/css">
    <style type="text/css">
        .flowStep{
            list-style: none;
        }
    </style>
</head>
<body>
<nav class="flowStep" style="height:5px;"></nav>
<div class="pos-r">
<table>
    </br>
    <tr><td>
        <label class="forSelect2 span4" style="text-align:right"><span class="c-red">*</span>产品编号：</label>
        <div class="formControls span4">
            <input type="text" class="input-text size-MINI" value="" placeholder="CARD_PRODUCT_CODE" id="newCardProductCode" name="newCardProductCode" disabled="true">
        </div></td>
        <td>
            <label class="forSelect2 span4" style="text-align:right"><span class="c-red">*</span>产品描述：</label>
            <div class="formControls span4">
                <input type="text" placeholder="CARD_PRODUCT_NAME"  value="" id="cardProductName" name="cardProductName" class="input-text size-MINI" disabled="true">
            </div></td>
    </tr>
    <tr>
        <td>
        <label class="forSelect2 span4" style="text-align:right"><span class="c-red">*</span>产品类型：</label>
        <div class="formControls span4">
            <input type="text" placeholder="CARD_PRODUCT_TYPE"  value="" id="cardProductType" name="cardProductType" class="input-text size-MINI" disabled="true">
        </div></td>
        <td><label class="forSelect2 span4" style="text-align:right"><span class="c-red">*</span>发卡渠道：</label>
            <div class="formControls span4">
                <input type="text" placeholder="PUBLISH_CHANNEL"  value="" id="publishChannel" name="publishChannel" class="input-text size-MINI" disabled="true">
            </div>
        </td>
    </tr>
</table>
</div>
<br/>
<div class="step-body span-offset-1 span10 span-setoff-1 mt-20" id="myStep" style="margin-top:10px;margin-right:5.33333%;margin-left:5.33333%;width:89.3725%">
    <div class="step-header">
        <ul>
            <li><p>产品信息</p></li>
            <li><p>制卡规则</p></li>
            <li><p>产品限额</p></li>
            <li><p>渠道控制</p></li>
        </ul>
    </div>
    <div class="step-list">
        <div class="text-c"><!--4-->
            <a id="one" class="button-select" >下一步</a>
            <div class="tabbable-custom " style="border:solid 3px #e5e5e5 ">
                <ul class="nav nav-tabs ">
                    <li class="active"><a data-toggle="tab" href="#tab_0_2">卡产品信息</a></li>
                </ul>
                <div class="tab-content">
                    <div id="tab_0_2" class="tab-pane active">
                        <iframe id="index0" src="ProdInfoDetail.jsp" style="width:100%;height:500px;border:solid 3px #e5e5e5;"></iframe>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="step-list">
        <div class="text-c"><!--4-->
            <a id="preOne" class="button-select">上一步</a>
            <a id="two" class="button-select">下一步</a>
            <div class="mt-20" style="margin-top:10px">
                <div class="tabbable-custom " style="border:solid 3px #e5e5e5 ">
                    <ul class="nav nav-tabs ">
                        <li class="active"><a data-toggle="tab" href="#tab_1_2">卡渠道定义</a></li>
                    </ul>
                    <div class="tab-content">
                        <div id="tab_1_2" class="tab-pane active">
                            <iframe id="index1" src="RoleExDetail.jsp"
                                    style="width:100%;height:500px;border:solid 3px #e5e5e5;"></iframe>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="step-list">
        <div class="text-c"><!--4-->
            <a id="preTwo" class="button-select">上一步</a>
            <a id="three" class="button-select">下一步</a>
            <div class="mt-20" style="margin-top:10px">
                <div class="tabbable-custom " style="border:solid 3px #e5e5e5 ">
                    <ul class="nav nav-tabs ">
                        <li class="active"><a data-toggle="tab" href="#tab_1_5">产品限额</a></li>
                    </ul>
                    <div class="tab-content">
                        <div id="tab_1_5" class="tab-pane active">
                            <iframe id="index4" src="ProductLimitDetail.jsp"
                                    style="width:100%;height:500px;border:solid 3px #e5e5e5; margin-top:10px"></iframe>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="step-list">
        <div class="text-c"><!--5-->
            <a id="preThree" class="button-select">上一步</a>
            <div class="tabbable-custom mt-20" style="border:solid 3px #e5e5e5 ;margin-top:10px">
                <ul class="nav nav-tabs ">
                    <li class="active"><a data-toggle="tab" href="#tab_1_6">渠道限制</a></li>
                </ul>
                <div class="tab-content">
                    <div>
                        <iframe id="index5" src="ProductChannelDetail.jsp"
                                style="width:100%;height:500px;margin-top:20px;border:solid 3px #e5e5e5"></iframe>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>