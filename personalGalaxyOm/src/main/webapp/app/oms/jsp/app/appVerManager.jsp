<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<%
    String appId = request.getParameter("id");
    String appName = request.getParameter("appName");
    String type = request.getParameter("type");

    try {
        appName = java.net.URLDecoder.decode(appName, "utf-8");
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
<script type="text/javascript" >
 var appName="<%=appName%>";
</script>
<html>
<head>
    <title>应用信息管理</title>
    <script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/app/appVerManager.js"></script>
</head>
<body>
<hidden id="appId" value=""></hidden>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页</a><span>&gt;</span>应用管理</a>
        <span>&gt;</span></span><span>版本管理</span><a
                href="javascript:location.replace(location.href);" title="刷新"><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<div class="padding-10">
    <form action="" method="post" class="form form-horizontal" id="form-appInfo-add" name="fileForm">
        <div class="row">
            <label class="form-label span2">应用名称：</label>
            <label class="form-label span1" id="name"></label>
        </div>
    </form>
    <div class="mr-20 ml-20 mt-10">
        <table id="appVerList" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr>
                <th>
                    版本ID
                </th>
                <th>
                    应用名称
                </th>
                <th>
                    应用简称
                </th>
                <th>
                    版本号
                </th>
                <th>
                    创建日期
                </th>
                <th>
                    创建人
                </th>
                <th>
                    保存路径
                </th>
                <th>
                    版本描述
                </th>
                <th>
                    版本类型
                </th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>
<script>
   $(document).ready(function () {
        // 获取默认opt配置
        var opt = getDataTableOpt($("#appVerList"));
        opt.stateSave = true;
        opt.processing = true;
        opt.autoWidth = false;
        opt.ajax = {
            "url": contextPath + "/findAppVer?appId=<%=appId %>",
            "type": "POST"
        };
        opt.columns = [
            {
                "data": "appVerId",
                "defaultContent": ""
            },
            {
                "data": "appName",
                "defaultContent": ""
            },
            {
                "data": "appSimpenNm",
                "defaultContent": ""
            },
            {
                "data": "appVerNum",
                "defaultContent": ""
            },
            {
                "data": "appVerDate",
                "defaultContent": ""
            },
            {
                "data": "userName",
                "defaultContent": ""
            },
            {
                "data": "appVerPath",
                "defaultContent": ""
            },
            {
                "data": "appVerDesc",
                "defaultContent": ""
            },
            {
                "data": "appVerTypeName",
                "defaultContent": ""
            }
        ];
        //渲染tables
        drawDataTable($("#appVerList"), opt);
        //界面美化tables
        $("#appVerList").beautyUi({
            tableId: "appVerList",
            buttonName: ["添加", "修改", "删除", "下载版本"],
            buttonId: ["add", "edit", "delete", "download"]
        });
        $('#appVerList tbody').on('click', 'tr', function (e) {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
            } else {
                $('#appVerList').find("tr").removeClass('selected');
                $(this).addClass('selected');
            }
        });
        $('#appVerList').on('page.dt', function (e) {
            $('#appVerList').find("tr").removeClass('selected');
        });
        $("#add").on("click", function () {
            data_Add('应用版本信息增加', 'appVerAdd.jsp', '600', '400');
        });
        $("#edit").on("click", function () {
            data_Edit('应用版本信息修改', 'appVerEdit.jsp', '600', '400');
        });
        $("#delete").on("click", function () {
            data_del();
        });
        $("#download").on("click", function () {
            download();
        });
        $("#name").text("<%=appName%>");
        $("#name").data("name", "<%=appName%>");
        $("#name").data("id", "<%=appId %>");
        /*页面按钮根据权限实现隐藏*/

    });
</script>