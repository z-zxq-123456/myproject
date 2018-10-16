<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/form.jsp" %>
<%
    String appId = request.getParameter("id");
%>
<html>
<head>
    <title>应用实例配置管理</title>
</head>
<body>
<form method="post" class="form form-horizontal" id="selectForm" name="selectForm">
    <div class="row">
        <label class="form-label span2" >应用服务类名：</label>
        <div class="formControls span3" >
            <input type="text" class="input-text grid" name="appSerClassName" id="appSerClassName">
        </div>
        <label class="form-label span2" >应用服务获取：</label>
        <div class="formControls span3" >
            <select type="text" class="select2" name="appSersource" id="appSersource"></select>
        </div>
        <div class="span2">
        <a class="button-select S " onclick="searchLateRule()"><i class="iconfont icon-4" ></i>搜索</a>
        </div>
    </div>
</form>

<div class="padding-10">
    <div class="mr-20 ml-20 mt-10">
        <table id="servRuleList" fit="false" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr>
                <th>服务名称</th>
                <th>服务类名</th>
                <th>服务方法中文名</th>
                <th>服务方法代码名</th>
            </tr>
            </thead>
        </table>
        <div class="row" style="margin-top: 20px">
            <div class="span5"></div>
            <a onclick="addServRuleInfo()" class="button-cancle M" title="确定">确定</a>&nbsp;
            <a onclick="appInfoAddCancel()" class="button-cancle M" title="关闭">关闭</a>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
var servRuleIdList = new Array();
var appId =<%=appId %>;
 $(document).ready(function () {
        getPkList({
            url: contextPath + "/findParaCombox?paraParentId=0013&&isDefault=false",
            id: "appSersource",
            async: false
        });
        // 获取默认opt配置
        var opt = getDataTableOpt($("#servRuleList"));


        opt.ajax = {
            "url": contextPath + "/findLateAppValRule?appId=<%=appId %>",
            "type": "POST"
        };
        opt.columns = [
            {"data": "appSerName"},
            {"data": "appSerClsnm"},
            {"data": "serMtdCnm"},
            {"data": "serMtdEnm"}
        ];
        //渲染tables
        drawDataTable($("#servRuleList"), opt);
        $("#servRuleList").beautyUi({
            tableId: "servRuleList",
            needBtn: false,
        });

        $('#servRuleList tbody').on('click', 'tr', function (e) {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
            } else {
                $(this).addClass('selected');
            }
        });
        $(".select2").select2();
});

function searchLateRule() {
      var appSerClassName = $("#appSerClassName").val();
      var appSersource = $("#appSersource").val();
      var appConfigList = $("#servRuleList").dataTable();
      var api = appConfigList.api();
      if(appSerClassName==""&&appSersource==""){
          api.ajax.url(contextPath + "/findLateAppValRule?appId="+appId ).load();
      }else{
           if(appSerClassName!=""){
               if(appSersource!=""){
                  api.ajax.url(contextPath + "/findLateAppValRule?appId=" + appId+"&appSersource="+appSersource+"&appSerClassName="+appSerClassName).load();
               }else{
                   api.ajax.url(contextPath + "/findLateAppValRule?appId=" + appId+"&appSerClassName="+appSerClassName).load();
               }
           }else{
               api.ajax.url(contextPath + "/findLateAppValRule?appId=" + appId+"&appSersource="+appSersource).load();
           }
      }
}
function appInfoAddCancel() {
        var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
        parent.layer.close(index);  //关闭窗口
}
function addServRuleInfo() {
     var rowSelect = $('#servRuleList').DataTable().rows(".selected").data();
     if(rowSelect.length!=0){
          $.each(rowSelect,function(i){
             servRuleIdList[i]=rowSelect[i].servRuleId;
          });
          var obj = "&appId="+appId+"&servRuleIdList="+JSON.stringify(servRuleIdList);
          $.post(contextPath + '/saveLateAppValRule',obj,function(result){
                   var result = eval('(' + result + ')');
                    if (result.errorMsg){
                     showMsg(result.errorMsg,'error');
                    }else{
                       parent.showMsgDuringTime("添加成功");
                      parent.location.replace(parent.location.href);
                      appInfoAddCancel();
                    }
            });
     }else{
         showMsg('请选择服务路由规则!','warning');
     }
}
</script>
  