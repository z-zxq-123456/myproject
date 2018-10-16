<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <link href="${ContextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${ContextPath}/lib/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
    <link href="${ContextPath}/lib/zTree/css/demo.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ContextPath}/lib/icheck/jquery.icheck.min.js"></script>
    <script type="text/javascript" src="${ContextPath}/app/pm/prod/js/MbProdDefine.js"></script>
    <script src="${ContextPath}/lib/jquery-step/js/jquery.step.js"></script>
    <link href="${ContextPath}/lib/jquery-step/css/jquery.step.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ContextPath}/lib/zTree/js/jquery.ztree.all.min.js"></script>
    <link rel="stylesheet" href="${ContextPath}/lib/zTree/css/metroStyle/metroStyle.css" type="text/css">
    <script type="text/javascript" src="${ContextPath}/js/pf/prodFactory.js"></script>
</head>
<body>
<div class="mr-10 ml-10">
    <nav class="breadcrumb" style="height:40px;"><i class="iconfont">&#xe61d;</i>系统首页<span class="c-gray en">&gt;</span>产品工厂<span
            class="c-gray en">&gt;</span>单一产品定义<a class="btn radius r mr-20 size-MINI"
                                                  href="javascript:location.replace(location.href);" title="刷新"><i
            class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="step-body span-offset-1 span10 span-setoff-1 mt-20" id="myStep"
     style="margin-top:10px;margin-right:5.33333%;margin-left:5.33333%;width:89.3725%">
    <div class="step-header">
        <ul>
            <li><p>基础信息</p></li>
            <li><p>业务描述</p></li>
            <li><p>行为描述</p></li>
            <li><p>定价信息</p></li>
            <li><p>核算信息</p></li>
        </ul>
    </div>
    <div class="row" style="margin-top:55px;">

        <label class="form-label mt-5"><span class="c-red">*</span>操作类型：</label>
        <div class="formControls span2">
            <select id="operateType" name="operateType" class="select2">
                <option value="" selected="selected">请选择</option>
                <option value="update">U--修改</option>
                <option value="add">A--新增</option>
                <option value="copy">C--复制</option>
            </select>
        </div>
        <label class="form-label mt-5">产品类别：</label>
        <div class="formControls span2">
            <select id="Bmodule" name="Bmodule" disabled="true" style="height:28px;">
                <option value="" selected="selected">请选择</option>
            </select>
        </div>
        <label class="form-label mt-5"> 产品：</label>
        <input type="text" class="input-text" value="" placeholder="产品类型" id="prodType" name="prodType"
               style="width:11%;">
        <input type="text" class="input-text" value="" placeholder="产品描述" id="prodDesc"
               name="prodDesc" style="width:11%;">
        <a id="save" class="button-edit M ml-5">
            <i class="iconfont icon-writefill">
            </i>
            保存
        </a>
        <a id="attrList" class="button-edit M ml-5">
            <i class="iconfont icon-writefill">
            </i>
            查看数据
        </a>
        <a id="nullifyAppNo" class="button-edit  M ml-5">
            <i class="iconfont icon-writefill">
            </i>
            操作作废
        </a>
        <a id="query" style="margin-bottom:10px;display:none">
        </a>
    </div>
    <div class="step-list" style="margin-top:10px;">
        <div class="text-c">
            <a id="ome" class="button-select">下一步</a>
            <div style="border:solid 3px #e5e5e5;height:300px;margin-top:10px;">
                <div class="row cl mt-20 " id="newProdDiv" style="text-align:right">
                    <label class="form-label mt-5" style="width:10%">新产品类型：</label>
                    <div class="formControls span2">
                        <input type="text" class="input-text" value="" placeholder="新产品类型" id="newProdType"
                               name="newProdType" datatype="/^[A-Z0-9\(_)]+$/" nullmsg="新产品类型不能为空" disabled="true"
                               style="width:100%">
                    </div>
                    <label class="form-label mt-5" style="width:10%">新产品描述：</label>
                    <div class="formControls span2">
                        <input type="text" placeholder="新产品类型描述" value="" id="newProdDesc" name="newProdDesc"
                               class="input-text" datatype="*1-40" nullmsg="新产品类型描述不能为空" disabled="true"
                               style="width:100%">
                    </div>
                    <div class="span3"></div>
                </div>
                <div class="row cl mt-20 ">
                    <label class="form-label mt-5" style="width:10%;text-align:right;">产品分类：</label>
                    <div class="formControls span2">
                        <select id="prodClass" name="prodClass" data-placeholder="产品分类" datatype="*1-40"
                                nullmsg="产品分类不能为空" disabled="true" class="select2" style="width:100%;">
                            <option value="" selected="selected">请选择</option>
                        </select>
                    </div>
                    <label class="form-label mt-5" style="width:10%;text-align:right;">产品属性：</label>
                    <div class="formControls span2">
                        <select id="prodRange" name="prodRange" data-placeholder="产品属性" datatype="*1-40"
                                nullmsg="产品属性不能为空" disabled="true" class="select2" style="width:100%;">
                            <option value="" selected="selected">请选择</option>
                            <option value="B">B-基础产品</option>
                            <option value="S">S-可售产品</option>
                        </select>
                    </div>
                </div>
                <div class="row cl mt-20 ">
                    <label class="form-label mt-5" style="width:10%;text-align:right;">组合产品：</label>
                    <div class="formControls span2">
                        <select id="prodGroup" name="prodGroup" data-placeholder="组合产品" datatype="*1-40"
                                nullmsg="组合产品不能为空" disabled="true" class="select2" style="width:100%;">
                            <option value="Y">Y-是</option>
                            <option value="N" selected="selected">N-否</option>
                        </select>
                    </div>
                    <label class="form-label mt-5" style="width:10%;text-align:right;">基础产品：</label>
                    <div class="formControls span2">
                        <select id="baseProdType" name="baseProdType" data-placeholder="基础产品" datatype="*1-40"
                                nullmsg="基础产品不能为空" disabled="true" class="select2" style="width:100%;">
                            <option value="" selected="selected">请选择</option>
                        </select>
                    </div>
                    <label class="form-label mt-5" style="width:10%;text-align:right;">产品状态：</label>
                    <div class="formControls span2">
                        <select id="status" name="status" data-placeholder="产品状态" datatype="*1-40" nullmsg="产品状态不能为空"
                                disabled="true" class="select2" style="width:100%;">
                            <option value="A">有效</option>
                            <option value="F">无效</option>
                        </select>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="step-list" style="margin-top:10px;">
        <div class="text-c"><!--2-->
            <a id="preOne" class="button-select">上一步</a>
            <a id="two" class="button-select">下一步</a>
            <div class="tabCon mt-20" style="border:solid 3px #e5e5e5 ;margin-top:10px">
                <div style="width:100%;" style="margin-top:15px">
                    <div class="tabbable-custom ">
                        <ul class="nav nav-tabs " id="list">
                        </ul>
                        <div class="tab-content" id="tabDiv">
                        </div>
                    </div>
                </div>
            </div>
        </div><!--2-->
    </div>
    <div class="step-list" style="margin-top:10px;">
        <div class="text-c"><!--3-->
            <a id="preTwo" class="button-select">上一步</a>
            <a id="three" class="button-select">下一步</a>
            <div class="tabCon cl mt-20" style="border:solid 3px #e5e5e5 ;margin-top:10px">
                <div class="span3">
                    <div class="padding-20">
                        <div class="portlet">
                            <ul id="eventTree" class="ztree showIcon">
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="span9">
                    <div class="padding-20" style="width:100%">
                        <div class="portlet" id="sortable_portlets">
                            <div class="portlet-body " style="margin-top:10px">
                                <div class=" row " id="compontentE">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div><!--3-->
    </div>
    <div class="step-list" style="margin-top:10px;">
        <div class="text-c"><!--4-->
            <a id="preThree" class="button-select">上一步</a>
            <a id="four" class="button-select">下一步</a>
            <div class="mt-20" style="margin-top:10px">
                <div class="tabbable-custom " style="border:solid 3px #e5e5e5 ">
                    <ul class="nav nav-tabs ">
                        <li class="active"><a data-toggle="tab" href="#tab_1_1">利率信息</a></li>
                        <li><a data-toggle="tab" href="#tab_1_2">费率信息</a></li>
                    </ul>
                    <div class="tab-content">
                        <div id="tab_1_1" class="tab-pane active">
                            <div class="tabCon">
                                <a id="IrlProdType" class="button-select">产品类型定义</a>
                                <a id="IrlProdInt" class="button-select">产品利率信息定义</a>
                            </div>
                            <iframe id="index" src=""
                                    style="width:100%;height:500px;border:solid 3px #e5e5e5; margin-top:10px"></iframe>
                        </div>
                        <div id="tab_1_2" class="tab-pane">
                            <div class="tabCon">
                                <a id="IRL_FEE_MAPPING" class="button-select">费用启用规则定义</a>
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
        <div class="text-c"><!--5-->
            <a id="preFour" class="button-select">上一步</a>
            <div class="tabbable-custom mt-20" style="border:solid 3px #e5e5e5 ;margin-top:10px">
                <ul class="nav nav-tabs ">
                    <li class="active"><a data-toggle="tab" href="#tab_1_3">核算信息</a></li>
                </ul>
                <div class="tab-content">
                    <div id="tab_1_3" class="tab-pane active">
                        <a id="GlProdAccount" class="button-select">产品科目</a>
                        <a id="GlProdRule" class="button-select">产品分录规则</a>
                        <a id="GlProdMapping" class="button-select">产品映射</a>
                    </div>
                    <div>
                        <iframe id="index2" src=""
                                style="width:100%;height:500px;margin-top:20px;border:solid 3px #e5e5e5"></iframe>
                    </div>
                </div>
            </div>
        </div><!--5-->
    </div>
</div>
</body>
</html>