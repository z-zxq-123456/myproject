<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <link href="${ContextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${ContextPath}/lib/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
    <link href="${ContextPath}/lib/zTree/css/demo.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ContextPath}/lib/icheck/jquery.icheck.min.js"></script>
    <script src="${ContextPath}/lib/jquery-step/js/jquery.step.js"></script>
    <link href="${ContextPath}/lib/jquery-step/css/jquery.step.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ContextPath}/lib/zTree/js/jquery.ztree.all.min.js"></script>
    <link rel="stylesheet" href="${ContextPath}/lib/zTree/css/metroStyle/metroStyle.css" type="text/css">
    <script type="text/javascript" src="${ContextPath}/app/cmc/js/CardProdDefine.js"></script>
    <script type="text/javascript" src="${ContextPath}/lib/My97DatePicker/calendar.js"></script>
</head>
<body>
<div class="mr-10 ml-10">
    <nav class="breadcrumb" style="height:40px;"><i class="iconfont">&#xe61d;</i>系统首页<span class="c-gray en">&gt;</span>产品工厂<span
            class="c-gray en">&gt;</span>卡产品定义<a class="btn radius r mr-20 size-MINI" href="javascript:location.replace(location.href);" title="刷新"><i
            class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="step-body span-offset-1 span10 span-setoff-1 mt-20" id="myStep"
     style="margin-top:10px;margin-right:5.33333%;margin-left:5.33333%;width:89.3725%">
    <div class="step-header">
        <ul>
            <li><p>产品信息</p></li>
            <li><p>制卡规则</p></li>
            <li><p>产品限额</p></li>
            <li><p>渠道控制</p></li>
        </ul>
    </div>
    <div class="row" style="margin-top:55px;">

        <label class="form-label mt-5"><span class="c-red">*</span>操作类型：</label>
        <div class="formControls span2">
            <select id="operateType" name="operateType" class="select2">
                <option value="" selected="selected">请选择</option>
                <option value="update">U--修改</option>
                <option value="add">A--新增</option>
            </select>
        </div>
        <label class="form-label mt-5">发行渠道：</label>
        <div class="formControls span2">
            <select id="publishChannel" name="publishChannel" class="select2">
                <option value="" selected="selected">请选择</option>
                <option value="1">本行发卡</option>
                <option value="2">渠道发卡</option>
            </select>
        </div>
        <label class="form-label mt-5"> 产品：</label>
        <input type="text" class="input-text" value="" placeholder="产品编号" id="prodType" name="prodType"
               style="width:11%;">
        <input type="text" class="input-text" value="" placeholder="产品名称" id="prodDesc"
               name="prodDesc" style="width:11%;">
        <a id="save" class="button-edit M">
            保存
        </a>
        <a id="attrList" class="button-edit M">
            查看数据
        </a>
        <a id="nullifyAppNo" class="button-edit  M">
            操作作废
        </a>
        <a id="reset" class="button-edit  M">
            重置
        </a>
    </div>
    <div>
        <a id="query" style="margin-bottom:10px;display:none"></a>
    </div>
    <div class="step-list" style="margin-top:30px;">
        <div class="text-c"><!--4-->
            <a id="one" class="button-select" >下一步</a>
            <div class="tabbable-custom " style="border:solid 3px #e5e5e5">
                <ul class="nav nav-tabs ">
                    <li class="active"><a data-toggle="tab" href="#tab_0_1">基础信息</a></li>
                    <li><a data-toggle="tab" href="#tab_0_2">卡产品信息</a></li>
                </ul>
                <div class="tab-content">
                    <div id="tab_0_1" class="tab-pane active">
                        <div class="row cl mt-20 " id="indexDiv" style="text-align:right">
                            <label class="form-label mt-5" style="width:8%">新产品编号：</label>
                            <div class="formControls span2">
                                <input type="text" class="input-text" value="" placeholder="新产品编号" id="newCardProductCode"
                                       name="newCardProductCode" datatype="/^[A-Z0-9\(_)]+$/" nullmsg="新产品编号不能为空" disabled="true"
                                       style="width:100%">
                            </div>
                            <label class="form-label mt-5" style="width:8%">新产品名称：</label>
                            <div class="formControls span2">
                                <input type="text" placeholder="新产品描述" value="" id="newCardProductName" name="newCardProductName"
                                       class="input-text" datatype="*1-40" maxlength="50" nullmsg="新产品描述不能为空" disabled="true"
                                       style="width:100%">
                            </div>
                        </div>
                        <div class="row cl mt-20">
						        <label class="form-label mt-5" style="width:8%;">产品种类：</label>
                                <div class="formControls span2">
                                    <select id="cardProductType" name="cardProductType" data-placeholder="产品种类" datatype="*1-40"
                                            nullmsg="产品属性不能为空" disabled="true" class="select2" style="width:80%;">
                                        <option value="" selected="selected">请选择</option>
                                        <option value="0">0-虚户类</option>
                                        <option value="1">1-实户类</option>
                                    </select>
                                </div>
                                <label class="form-label mt-5" style="width:8%;">产品状态：</label>
                                <div class="formControls span2">
                                    <select id="status" name="status" data-placeholder="产品状态" datatype="*1-40" nullmsg="产品状态不能为空"
                                            disabled="true" class="select2" style="width:80%;">
                                        <option value="" selected="selected">请选择</option>
                                        <option value="A">有效</option>
                                        <option value="F">无效</option>
                                    </select>
                                </div>
                        </div>
                    </div>
                    <div id="tab_0_2" class="tab-pane">
                        <div class="tabCon">
                            <a id="cardProductInfo" class="button-select">卡产品信息</a>
                        </div>
                        <iframe id="index0" src="" style="width:100%;height:500px;border:solid 3px #e5e5e5;"></iframe>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="step-list" style="margin-top:10px;">
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
                            <div class="tabCon">
                                <a id="cardRoleEx" class="button-select">卡渠道定义</a>
                            </div>
                            <iframe id="index1" src=""
                                    style="width:100%;height:500px;border:solid 3px #e5e5e5;"></iframe>
                        </div>
                    </div>
                </div>
            </div>
        </div><!--4-->
    </div>

    <div class="step-list" style="margin-top:10px;">
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
                            <div class="tabCon">
                                <a id="cardProductLimit" class="button-select">产品限额定义</a>
                            </div>
                            <iframe id="index4" src=""
                                    style="width:100%;height:500px;border:solid 3px #e5e5e5; margin-top:10px"></iframe>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="step-list" style="margin-top:10px;">
        <div class="text-c"><!--5-->
            <a id="preThree" class="button-select">上一步</a>
            <div class="tabbable-custom mt-20" style="border:solid 3px #e5e5e5 ;margin-top:10px">
                <ul class="nav nav-tabs ">
                    <li class="active"><a data-toggle="tab" href="#tab_1_6">渠道限制</a></li>
                </ul>
                <div class="tab-content">
                    <div id="tab_1_6" class="tab-pane active">
                        <a id="cardProdChannel" class="button-select">渠道限制定义</a>
                    </div>
                    <div>
                        <iframe id="index5" src=""
                                style="width:100%;height:500px;margin-top:20px;border:solid 3px #e5e5e5"></iframe>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>