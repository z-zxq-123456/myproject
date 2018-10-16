<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ include file="/form.jsp" %>
<%
    String isRedirect = request.getParameter("isRedirect");
    String isErrorMsg = request.getParameter("isErrorMsg");
%>
<html>
<head>
    <title>版本信息管理</title>
</head>
<body>
<div class="pd-20">
    <form  method="post" class="form form-horizontal"  id="Form" name="Form" enctype="multipart/form-data">
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>版本描述：</label>
            <div class="formControls span6">
                <input type="text" class="input-text grid" placeholder="版本描述" name="appVerDesc" id="appVerDesc"
                       datatype="*1-60" nullmsg="请输入">
            </div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>版本类型：</label>
            <div class="formControls span6">
                <select type="text" class="select2" name="appVerType" id="appVerType" datatype="*1-60" nullmsg="请输入"></select>
            </div>
        </div>
        <div class="row">
            <label class="form-label span4"><span class="c-red">*</span>应用安装文件：</label>
            <div class="formControls span6">
                <span class="btn-upload form-group">
                <input class="input-text upload-url" type="text"  datatype="*"
                       readonly      nullmsg="请添加附件！" style="width:180px">
                <a class="button-add M btnNoRadius upload-btn">浏览文件</a>
                <input type="file"   class="input-file"  name="sourceFile" id="sourceFile"></span>
            </div>
        </div>
        <div class="row cl mb-20">
            <div class="formControls span5"></div>
            <div class="formControls span3">
                <a id="upload" class="button-select">提交</a>
            </div>
        </div>
    </form>
</div>
</body>
</html>
<script type="text/javascript">
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    var form;
    var indexShade;
    $(document).ready(function () {
        //判断是否是重定向到此页面的，如果是，则关闭。
        if(<%=isRedirect%>){
            layer.close(indexShade);
            parent.showMsgDuringTime("添加成功");
        }

        if(<%=isErrorMsg%>){
            layer.close(indexShade);
            showMsg("数据库操作异常","errorMsg");
        }

        getPkList({
            url: contextPath + "/findParaCombox?paraParentId=0022&&isDefault=false",
            id: "appVerType",
            async: false
        });

        $("#upload").click(function (event) {
        console.info($("#sourceFile").val().split(".")[1]);
            if($("#appVerDesc").val()=="")
            {
                showMsg('应用版本描述不能为空!','warning');
                return;
            }else if($("#appVerType").val()==""){
                showMsg('应用版本类型不能为空!','warning');
                return;
            }else if($("#sourceFile").val()==""){
                showMsg('应用安装文件不能为空!','warning');
                return;
            }else if($('#sourceFile').val().indexOf(".tar")<=0) {
                 showMsg('当前仅支持tar包!','warning');
                 return;
            }else{
                indexShade = layer.load(4, {
                    shade: [0.4,'#777777'] //0.5透明度的白色背景
                });
                var appId = parent.$("#name").data("id");
                var url = contextPath+"/saveAppVer?appId="+appId;
                $('#Form').attr("action",url);
                $('#Form').submit();
            }
        });

        $(".select2").select2();
    });

    function appVerAddCancel() {
        var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
        parent.layer.close(index);  //关闭窗口
    }

</script>