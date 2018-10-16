<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>参数表IRL_INT_TYPE修改</title>
    <script type="text/javascript" src="${ContextPath}/app/pm/up/js/edit/irlIntTypeMod.js"></script>
    <link type="text/css" rel="stylesheet"
          href="${ContextPath}/lib/jquery/editable-select/jquery.editable-select.min.css"/>
    <script type="text/javascript"
            src="${ContextPath}/lib/jquery/editable-select/jquery.editable-select.min.js"></script>
</head>
<body>
<div class="pd-20">
    <form action="" method="post" class="form form-horizontal" id="irlIntTypeMod">
        <div class="row cl">
            <label class="form-label span4"><span class="c-red">*</span>利率税率类型：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" value="" id="intTaxType" name="intTaxType">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red">*</span>利率税率类型描述：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" value="" placeholder="intTaxTypeDesc" id="intTaxTypeDesc"
                       name="intTaxTypeDesc" datatype="*1-30" nullmsg=" 请输入！" tipmsg="格式错误!">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red">*</span>利率税率标志：</label>
            <div class="formControls span6">
                <select id="intTaxFlag" name="intTaxFlag" data-placeholder="利率税率标志" class="select2" tabindex="4"
                        datatype="*" nullmsg=" 请输入！" tipmsg="格式错误!">
                    <option value="INT">INT-利率</option>
                    <option value="TAX">TAX 税率</option>
                </select>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red">*</span>产品组：</label>
            <div class="formControls span6">
                <select id="prodGrp" name="prodGrp" data-placeholder="产品组" class="select2" tabindex="4" datatype="*"
                        nullmsg=" 请输入！" tipmsg="格式错误!">
                </select>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red">*</span>利息计算模型：</label>
            <div class="formControls span6">
                <select id="rateLadder" name="rateLadder" data-placeholder="利息计算模型" class="select2"  tabindex="4" datatype="*" nullmsg="请输入！" tipmsg="格式错误!">
                </select>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"></span>税率计算模型：</label>
            <div class="formControls span6">
                <select id="taxLadder" name="taxLadder" data-placeholder="税率计算模型" class="select2" tabindex="4"
                         tipmsg="格式错误!">
                    <option value="">空</option>
                    <option value="A">A-增值税</option>
                    <option value="B">B-个人所得税</option>
                </select>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red"></span>法人代码：</label>
            <div class="formControls span6">
                <select id="company" name="company" datatype="*0-20" class="select2" tabindex="4" tipmsg="格式错误!">
                </select>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <div class="span-offset-1 span10 span-offset-1 mt-10">
                <input type="submit" class="button-select L smartButton" title="修改" value="修改">
            </div>
        </div>
    </form>
</div>
</body>
</html>
