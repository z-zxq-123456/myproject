<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>交易LM_TRAN_LIMIT_LINK添加</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/kbs/js/add/lmTranLimitLinkAdd.js"></script>
</head>
<body>
<div class="pd-20">
    <form action="" method="post" class="form form-horizontal" id="lmTranLimitLinkAdd">
        <div class="row cl">
            <label class="form-label span4"><span class="c-red">*</span>交易限额编码：</label>
            <div class="formControls span6">
                <select id="limitRef" name="limitRef" data-placeholder="交易限额编码" class="select2" tabindex="4"
                        datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
                </select>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red">*</span>交易类型：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" value="" placeholder="tranTypeLink" id="tranTypeLink"
                       name="tranTypeLink" datatype="*1-100" nullmsg=" 请输入！" tipmsg="格式错误!">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red">*</span>客户分类：</label>
            <div class="formControls span6">
                <select id="clientType" name="clientType" class="select2" tabindex="4" tipmsg="格式错误!" nullmsg=" 请输入！">
                </select>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red">*</span>产品类型：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" value="" placeholder="prodType" datatype="*1-50"
                       id="prodType" name="prodType" tipmsg="格式错误!" nullmsg=" 请输入！">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red">*</span>渠道类型：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" value="" placeholder="sourceTypeLink" datatype="*1-100"
                       id="sourceTypeLink" name="sourceTypeLink" tipmsg="格式错误!" nullmsg=" 请输入！">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red">*</span>余额类型：</label>
            <div class="formControls span6">
                <select id="balanceType" name="balanceType" class="select2" tipmsg="格式错误!" nullmsg=" 请输入！">
                    <option value="CA">CA-现钞</option>
                    <option value="TT">TT-现汇</option>
                    <option value="ALL">ALL-不限制</option>
                </select>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red">*</span>地区码：</label>
            <div class="formControls span6">
                <select id="areaCodeLink" name="areaCodeLink" class="select2" tipmsg="格式错误!" nullmsg=" 请输入！">
                    <option value="LC">LC–同城</option>
                    <option value="DP">DP–异地</option>
                    <option value="OS">OS–境外</option>
                    <option value="NA">NA–通配</option>
                    <option value="ALL" >ALL</option>
                </select>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red">*</span>客户境内外标志：</label>
            <div class="formControls span6">
                <select id="inlandInd" name="inlandInd" class="select2" tipmsg="格式错误!" nullmsg=" 请输入！">
                    <option value="I">I-境内</option>
                    <option value="O">O-境外</option>
                </select>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red">*</span>是否附属卡：</label>
            <div class="formControls span6">
                <select id="isAppFlag" name="isAppFlag" class="select2" tipmsg="格式错误!" nullmsg=" 请输入！">
                    <option value="Y">Y-是</option>
                    <option value="N">N-否</option>
                </select>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red">*</span>机构：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" value="" placeholder="branchLink" datatype="*1-100"
                       id="branchLink" name="branchLink" tipmsg="格式错误!" nullmsg=" 请输入！">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <div class="span-offset-1 span10 span-offset-1 mt-10">
                <input type="submit" class="button-select L smartButton" title="添加" value="添加">
            </div>
        </div>
    </form>
</div>
</body>
</html>
