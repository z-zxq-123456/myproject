<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>应用操作视图</title>
    <script type="text/javascript" charset="UTF-8"
            src="${ContextPath}/app/oms/js/middleware/zookeeperServerControl.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<span>&gt;</span><a
            href="#">服务管理</a><span>&gt;</span><span>服务控制</span><a href="javascript:location.replace(location.href);"
                                                                    title="刷新"><i class="iconfont">&#xe61e;</i></a>
    </nav>
</div>
<form action="" method="post" class="form form-horizontal" id="selectForm" name="selectForm">
    <div class="row">
        <label class="form-label span2">ZK集群名称：</label>
        <div class="formControls span3">
            <select name="midwareId" id="queryMidwareId" size="1" class="select2" style="margin-top:5px">
            </select>
        </div>
        <label class="form-label span2">服务角色：</label>
        <div class="formControls span3">
            <select name="zkSerType" id="queryZkSerType" size="1" class="select2" style="margin-top:5px">
            </select>
        </div>
    </div>
    <div class="row">
        <label class="form-label span2">IP地址：</label>
       <div class="formControls span3">
                   <select name="zkIp" id="queryZkId" size="1" class="select2" style="margin-top:5px">
                   </select>
               </div>
        <label class="form-label span2">服务名：</label>
        <div class="formControls span3">
                     <input class="input-text grid" name="zkSerName" id="queryZkSerName" type="text" style="margin-top:5px">
                 </div>
                <div class="span1"></div>
            <a id="select" href="#" class="button-sure M ml-20" style="margin-bottom:10px">
               <i class="iconfont">&#xe624;</i> 查询</a>
    </div>
</form>
<div class="mr-20 ml-20 mt-10">
    <table id="serverInfoList" class="table table-border table-bordered table-hover table-bg table-sort">
        <thead>
        <tr>
             <th>
                 <input type="checkbox" id="check_all" name="" value="">
            </th>
            <th>服务名</th>
            <th>服务角色</th>
            <th>IP端口</th>
            <th>版本号</th>
            <th>分组</th>
            <th>状态</th>
        </tr>
        </thead>
    </table>
</div>
<script type="text/javascript">
  $("#queryMidwareId").change(function(){
        var $queryZkSerType =  $("#queryZkSerType").select2();
        $queryZkSerType.val(null);
        $queryZkSerType.select2();
        var $queryZkId =  $("#queryZkId").select2();
        $queryZkId.val(null);
         $queryZkId.select2();
        $("#queryZkSerName").val("");
	    exection();
});
   $("#queryMidwareId").ready(function(){
 	 exection();
 });
</script>
</body>
</html>
  