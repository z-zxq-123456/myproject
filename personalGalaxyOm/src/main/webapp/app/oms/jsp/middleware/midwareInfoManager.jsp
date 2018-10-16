<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/form.jsp"%>

<html>
	<head>
		<title>中间件信息管理</title>
		<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/middleware/midwareInfoManager.js"></script>
	</head>
	<body>
        <div class="mb-10">
            <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页</a><span>&gt;</span>系统规划</a><span>&gt;</span>中间件规划</a><span>&gt;</span><span>中间件信息管理</span><a
                                          href="javascript:location.replace(location.href);" title="刷新"><i class="iconfont">&#xe61e;</i></a></nav>
        </div>
        <form action="" method="post" class="form form-horizontal" id="selectForm" name="selectForm">
            <div class="row">
                 <label class="form-label span2"><span class="c-red">*</span>中间件类型：</label>
                 <div class="span3">
                      <select name="queryMidwareType" id="queryMidwareType" size="1" class="select2" style="margin-top:5px">
                      </select>
                 </div>
                 <div class="span2"></div>
                 <div class="span4">
                      <a  onclick="searchRec()" class="button-select M ml-20 mr-20"><i class="iconfont">&#xe614;</i>查询</a>
                 </div>
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="midwareInfoList" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                    <tr>
                        <th>中间件名称</th>
                        <th>中间件类型</th>
                        <th>中间件版本号</th>
                        <th>中间件安装路径</th>
                        <th>是否设为默认</th>
                        <th>zk集群</th>
                    </tr>
                </thead>
            </table>
        </div>
	</body>		  
</html>
<script type= "script\text">
  