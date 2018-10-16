<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>应用信息管理</title>
</head>
<body>
<div class="pd-20">
    <form action="" method="post" class="form form-horizontal" id="form" name="fileForm">
    </form>
</div>

</body>
</html>
<script type="text/javascript">
    //[   {"dataName": "最新版本号", "dataValue": "1"},
    //    {"dataName": "最新操作", "dataValue": "已部署"},
    //    {"dataName": "工作目录", "dataValue": "d_1"},
    //    {"dataName": "安装路径", "dataValue": "/home/oms/mengllb/app"},
    //    {"dataName": "健康信息", "dataValue": "应用连通性检查:不连通|HTTP连通性检查:不连通|"}]
    //    }
    var html="";
    var str = parent.$obj.attr("info");
    var json = eval(str);
    for (var i = 0; i < json.length; i++) {
        html=html+"\<div class='row'><label class='form-label span4'>";
        html=html+json[i].dataName + "\:</label><div class='formControls span4'><input type='text' style='width: 180px;border: none' class='input-text grid'disabled value="+json[i].dataValue+"\></div></div>";
    }
    $("#form").html(html);
</script>
