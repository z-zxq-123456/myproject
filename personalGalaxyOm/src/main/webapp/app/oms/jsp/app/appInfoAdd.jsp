<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<%

    String userName = (String)request.getSession().getAttribute("UserName");
    if (null == userName) {
        response.sendRedirect(request.getSession().getAttribute("ContextPath") + "/login.jsp");
    }
%>
<html>
<head>
    <title>应用信息管理</title>
    <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/app/appInfoAdd.js"></script>
</head>
<body>
<div class="pd-20">
    <form action="" method="post" class="form form-horizontal" id="form-appInfo-add" name="fileForm">
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>应用名称：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" placeholder="应用名称" name="appName" id="appName"
                       datatype="*1-40" nullmsg="请输入应用名称">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>应用英文简称：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" placeholder="应用英文简称" name="appSimpenNm" id="appSimpenNm"
                       datatype="*1-20" nullmsg="请输入应用英文简称">
            </div>
            <div class="span2"></div>
        </div>

        <div class="row">
            <label class="form-label span4">应用分类：</label>
            <div class="formControls span6">
                <select type="text" class="select2" placeholder="应用分类" name="appType" id="appType" datatype="*1-10"
                        nullmsg="应用类型"></select>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>Redis集群：</label>
            <div class="formControls span6">
                <select type="text" class="select2" placeholder="Redis集群" name="redisMidwareName" id="redisMidwareName"
                        datatype="*1-60" nullmsg="应用类型"></select>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>ZK集群：</label>
            <div class="formControls span6">
                <select type="text" class="select2" placeholder="ZK集群" name="zkMidwareName" id="zkMidwareName"
                        datatype="*1-60" nullmsg="ZK集群"></select>
            </div>
            <div class="span2"></div>
        </div>

        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>DB集群：</label>
            <div class="formControls span6">
                <select type="text" class="select2" placeholder="DB集群" name="dbMidwareName" id="dbMidwareName"
                        datatype="*1-60" nullmsg="DB集群"></select>
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>应用安装路径：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" placeholder="应用安装路径" name="appPath" id="appPath"
                       datatype="*1-200" nullmsg="应用安装路径">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <label class="form-label span4">应用描述：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" placeholder="应用描述" name="appDesc" id="appDesc"
                       datatype="*1-60">
            </div>
            <div class="span2"></div>
        </div>
        <div class="row">
            <div class="span4"></div>
            <input type="submit" class="button-select M" title="提交" value="提交">&nbsp;
            <a onclick="appInfoAddCancel()" class="button-cancle M" title="关闭">关闭</a>
        </div>
    </form>
</div>
</body>
</html>
<script>
 var form;
 var appId;
$(document).ready(function () {
    getPkList({
        url: contextPath + "/findParaCombox?paraParentId=0021&&isDefault=false",
        id: "appType",
        async: false
    });
    getPkList({
        url: contextPath + "/findMidwareCombox?midwareType=0006001",
        id: "redisMidwareName",
        async: false
    });
    getPkList({
        url: contextPath + "/findMidwareCombox?midwareType=0006002",
        id: "zkMidwareName",
        async: false
    });
    getPkList({
        url: contextPath + "/findMidwareCombox?midwareType=0006003",
        id: "dbMidwareName",
        async: false
    });
    form = $("#form-appInfo-add").Validform({
        tiptype: 2,
        callback: function (form) {
            appInfoAdd();
            return false;
        }
    });
    $(".select2").select2();
});
function appMenuAdd(json) {
    var url1=contextPath+"/saveAppMenu";
    appId = json.id;
    sendPostRequest(url1, {
        menuName:$("#appName").val(),
        id:appId,
        userId:"<%=userName%>"
    }, callback_appMenuAdd, "json");

}

function callback_appMenuAdd(json) {
    if (json.success) {
        var dataTable=parent.$("#appList").DataTable();
        dataTable.row.add({
             appId: appId,
             appName: $("#appName").val(),
             appSimpenNm: $("#appSimpenNm").val(),
             appTypeName: $("#appType option:selected").val(),
             redisMidwareName: $("#redisMidwareName option:selected").text(),
             zkMidwareName: $("#zkMidwareName option:selected").text(),
             dbMidwareName: $("#dbMidwareName option:selected").text(),
             appPath: $("#appPath").val(),
             appDesc: $("#appDesc").val()
         }).draw(false);
        parent.reference();
        parent.showMsgDuringTime("添加成功");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}
</script>