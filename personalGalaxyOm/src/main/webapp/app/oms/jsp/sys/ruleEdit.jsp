<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>用户修改</title>
     <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/sys/ruleEdit.js"></script>
</head>
<body>
<div class="pd-20">
    <form action="" method="post" class="form form-horizontal" id="form-parameter-edit">
        <div class="row"   style="display:none">
            <label class="form-label span4">预警编码：</label>
            <div class="formControls span4">
                <input type="text" class="input-text grid" name="ruleId" id="ruleId" disabled>
            </div>
            <div class="span3"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>预警名称：</label>
            <div class="formControls span4">
                <input type="text" class="input-text grid" placeholder="预警名称" name="ruleName" id="ruleName" style="width:200px"
                       datatype="*1-20" nullmsg="请输入预警名称">
            </div>
            <div class="span3" style="padding-left:15px;"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>预警级别：</label>
            <div class="formControls span4">
                <select type="text" class="select2" placeholder="预警级别" name="ruleRank" id="ruleRank" style="width:200px"
                        datatype="*1-60" nullmsg="应用类型"></select>
            </div>
            <div class="span3" style="padding-left:15px;"></div>
        </div>

        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>预警描述：</label>
            <div class="formControls span6">
                 <textarea cols="" rows=""  id="ruleDesc" name="ruleDesc" style="width:200px"
                    dragonfly="true"  tipmsg="格式错误!" onKeyUp="textarealength(this,100)" ></textarea>
                        <p class="textarea-numberbar"
                            style="position: absolute; right: 85px; bottom: 5px; z-index: 1; margin-bottom: 0; color: #bbb;"><em class="textarea-length">0</em>/100</p>
            </div>
            <div class="span2" style="position: absolute; right: 60px;"> </div>
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
