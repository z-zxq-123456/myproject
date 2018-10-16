<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/form.jsp" %>
<html>
 <style>
     .div-a{ float:left;width:49%;border:1px }
     .div-b{ float:left;width:49%;border:1px }
</style>
<head>
    <title>zookeeper数据查看</title>
     <link href="${ContextPath}/lib/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
     <link rel="stylesheet" href="${ContextPath}/lib/zTree/css/metroStyle/metroStyle.css" type="text/css">
    <link href="${ContextPath}/lib/zTree/css/demo.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${ContextPath}/lib/zTree/js/jquery.ztree.all.min.js"></script>
    <!--拓展的json-->
    <link rel="stylesheet" href="${ContextPath}/lib/zTree/css/metroStyle/metroStyle.css" type="text/css">
    <script type="text/javascript" src="${ContextPath}/lib/zTree/json2.js" charset="utf-8"></script>
    <script type="text/javascript" src="${ContextPath}/lib/zTree/util.js" charset="utf-8"></script>
    <script type="text/javascript" charset="UTF-8"
            src="${ContextPath}/app/oms/js/middleware/zookeeperDataView.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页</a><span>&gt;</span>中间件管理</a><span>&gt;</span>zookeeper</a><span>&gt;</span><span>zookeeper数据查看</span><a
                   href="javascript:location.replace(location.href);" title="刷新"><i class="iconfont">&#xe61e;</i></a></nav>
</div>
<div  id = "test">
<form action="" method="post" class="form form-horizontal" id="selectForm" name="selectForm">
            <div class="row cl">
                <label class="form-label span2">请选择ZK集群：</label>
                <div class="formControls span3">
                       <select name="midwareId" id="queryMidwareId" size="1" class="select2" style="margin-top:5px">
                       </select>
                 </div>
            </div>
            <div class="row cl">
                 <label class="form-label span2">zk集群IP：</label>
                <div class="span8">
                    <input type="text" style="width:100%;" class="input-text inner-right" value="" placeholder="zookeeper://host:port[/namespace]" name="cxnStr" id="cxnStr" datatype="*2-100" nullmsg="URL必须输入">
                </div>
                <div class="span1"></div>
            </div>
              <div class="row cl">
                  <label class="form-label span2">请输入查询服务名：</label>
                  <div class="formControls span3">
                         <input id="add_search"  style="width:250px;" class="input-text inner-right">
                   </div>
              <div class="span2">
               <a href="javascript:void(0)" class="button-sure M ml-20" style="margin-bottom:10px" onclick="searchNode()" id="searchNode" > <i class="iconfont">&#xe624;</i> 查询</a>
                </div>
              </div>
           </div>
</form>
<div id="main" style="width:96%;  height:350px;">
     <div   id="tab-treeManagement">
         <ul id="zkTree" class="ztree" style="width: 98%;"></ul>
     </div>
</div>
</body>
</html>
<script type="text/javascript">

     $("#queryMidwareId").change(function(){
        readZkUrl ();
     });
     $("#queryMidwareId").ready(function(){
        readZkUrl ();
     });
</script>