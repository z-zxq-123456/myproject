<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>数据维护</title>
    <link type="text/css" rel="stylesheet"
          href="${ContextPath}/lib/jquery/editable-select/jquery.editable-select.min.css"/>
    <script type="text/javascript"
            src="${ContextPath}/lib/jquery/editable-select/jquery.editable-select.min.js"></script>
    <script type="text/javascript" src="${ContextPath}/app/pm/js/paraIn/differentShow.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<span>&gt;</span><a
            href="#">参数管理</a><span>&gt;</span><span>数据维护</span><a href="javascript:location.replace(location.href);"
                                                                  title="刷新"><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<div id="promptWord" style="display: none">
    <span class="c-red">您正在发布特殊卡号，请检查卡管系统是否处于AB表切换中</span>
</div>
<div class="padding-10">
    <div class="mt-20 mr-20 ml-20 cl" id="publishAreaShow" style="display:none"><!--发布展示区-->
        <div class="row cl mb-20">
            <label class="form-label span2 forSelect2"><span class="c-red">*</span>选择开发环境：</label>
            <div class=" span4">
            <span class="formControls  col-5">
                <select class="select2" size="1" style="width:100%" id="envId" name="envId" datatype="*"
                        nullmsg="请选择开发环境"></select></span>
            </div>
            <div class="span6">
                <input type="text" class="input-text size-MINI" style="width:100%" value=""
                       placeholder="选择测试环境或者输入URL地址" id="envUrl" name="envUrl" datatype="*2-100" nullmsg="必须输入">
            </div>
            <div class="span2">
            </div>
            <div class="span2">
                <input type="text" class="input-text size-MINI" style="width:100%; margin-top: 10px;" value=""
                       placeholder="报文类型" id="messageType" name="messageType" datatype="*2-100" nullmsg="必须输入">
            </div>
            <div class="span2">
                <input type="text" class="input-text size-MINI" style="width:100%; margin-top: 10px;" value=""
                       placeholder="服务代码" id="serviceCode" name="serviceCode" datatype="*2-100" nullmsg="L必须输入">
            </div>
            <div class="span2">
                <input type="text" class="input-text size-MINI" style="width:100%; margin-top: 10px;" value=""
                       placeholder="报文代码" id="messageCode" name="messageCode" datatype="*2-100" nullmsg="必须输入">
            </div>
            <div class="span2">
                <input type="text" class="input-text size-MINI" style="width:100%; margin-top: 10px;" value=""
                       placeholder="模块" id="module" name="module" datatype="*2-100" nullmsg="必须输入">
            </div>
            <div class="span2">
                <input type="text" class="input-text size-MINI" style="width:100%; margin-top: 10px;" value=""
                       placeholder="法人" id="legalentity" name="legalentity" datatype="*2-100" nullmsg="必须输入" disabled>
            </div>
        </div>
        <div class="row cl mb-20">
            <label class="forInput span2"><span class="c-red">*</span>操作的交易：</label>
            <div class="span6">
                <input type="text" class="input-text grid" id="publishTableName" placeholder="请输入3-20位字符"
                       name="publishTableName" datatype="*3-20" nullmsg="请输入！" tipmsg="格式错误!">
            </div>
        </div>
        <div class="row cl mb-20">
            <label class="forInput span2"><span class="c-red">*</span>发布意见：</label>
            <div class="formControls span4" style="padding:0px;margin:0px;">
                <textarea name="" cols="" rows="" id="publishText" name="publishText" style="width:323px"
                          placeholder="请输入2-50位字符" dragonfly="true" datatype="*2-500" nullmsg="请输入发布意见！" tipmsg="格式错误!"
                          onKeyUp="textarealength(this,500)"></textarea>
                <p class="textarea-numberbar"><em class="textarea-length">0</em>/500</p>
            </div>
        </div>
        <div class="row cl mb-20">
            <label class="forInput span2">是否通过：</label>
            <div class=" span4">
                <select class="input-text " id="backOrPublish" style="width:100%">
                    <option value="">请选择</option>
                    <option value="P">通过发布</option>
                    <option value="B">驳回</option>
                </select>
            </div>
            <div class="span-setoff-2 span4 ">
                <a id="publishDiffShow" class="button-select">查看数据</a>
                <a id="publishComplete" class="button-select ">通过发布</a>
                <a id="publishBack" class="button-select " style="display:none">驳回</a>
            </div>
        </div>
    </div>
    <div class="mt-20 mr-20 ml-20 cl" id="checkAreaShow" style="display:none"><!--复核展示区-->
        <div class="row cl mb-20">
            <label class="forInput span2"><span class="c-red">*</span>操作的交易：</label>
            <div class=" span6">
                <input type="text" class="input-text grid" id="checkTableName" placeholder="请输入3-20位字符"
                       name="checkTableName" datatype="*3-20" nullmsg="请输入！" tipmsg="格式错误!">
            </div>
        </div>
        <div class="row cl mb-20">
            <label class="forInput span2"><span class="c-red">*</span>复核意见：</label>
            <div class="formControls span4" style="padding:0px;margin:0px;">
                <textarea name="" cols="" rows="" id="inspectText" name="inspectText" style="width:328px"
                          placeholder="请输入2-50位字符" dragonfly="true" datatype="*2-500" nullmsg="请输入复核意见！" tipmsg="格式错误!"
                          onKeyUp="textarealength(this,500)"></textarea>
                <p class="textarea-numberbar"><em class="textarea-length">0</em>/500</p>
            </div>
        </div>
        <div class="row cl mb-20">
            <label class="forInput span2">是否通过：</label>
            <div class=" span4">
                <select class="input-text grid" id="backOrCheck" style="width:100%">
                    <option value="">请选择</option>
                    <option value="C">通过复核</option>
                    <option value="B">驳回</option>
                </select>
            </div>
            <div class="span-setoff-2 span4 ">
                <a id="checkDiffShow" class="button-select">查看数据</a>
                <a id="checkComplete" class="button-select ">通过复核</a>
                <a id="checkBack" class="button-select " style="display:none">驳回</a>
            </div>
        </div>
    </div>

    <div class="mr-20 ml-20 mt-10">
        <table id="tableList" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th>操作类型</th>
                <th>审核发布意见</th>
                <th>操作人ID</th>
                <th>操作时间</th>
                <th>操作人Ip</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
<script type="text/javascript">


</script>
</html>
