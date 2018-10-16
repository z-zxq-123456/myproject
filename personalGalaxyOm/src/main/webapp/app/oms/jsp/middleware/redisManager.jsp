<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
    <title>Redis实例部署</title>
    <script type="text/javascript" src="${ContextPath}/app/oms/js/middleware/redisManager.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页</a><span>&gt;</span>中间件管理</a><span>&gt;</span>Redis</a><span>&gt;</span><span>redis实例部署</span><a
            href="javascript:location.replace(location.href);" title="刷新"><i class="iconfont">&#xe61e;</i></a></nav>
</div>
 <form action="" method="post" class="form form-horizontal" id="selectForm" name="selectForm">
                <div class="row">
                         <label class="form-label span2"><span class="c-red">*</span>集群名称：</label>
                         <div class="span3">
                              <select name="queryMidwareId" id="queryMidwareId" size="1" class="select2" style="margin-top:5px">
                              </select>
                         </div>
                         <div class="span2"></div>
                </div>
                </form>
<div class="padding-10">
    <div class="mr-20 ml-20 mt-10">
        <table id="redisList" class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr>
                <th>
                    集群节点
                </th>
                <th>
                    Redis实例名称
                </th>
                <th>
                    Redis实例类型
                </th>
                <th>
                   IP和端口
                </th>
                <th>
                    版本号
                </th>
                <th>
                    最新操作
                </th>
                <th>
                    当前状态
                </th>
                <th>
                    健康信息
                </th>
                <th>
                分配内存
                </th>
                <th>
                Redis实例安装路径
                </th>
            </tr>
            </thead>
        </table>
    </div>
</div>
</body>
</html>