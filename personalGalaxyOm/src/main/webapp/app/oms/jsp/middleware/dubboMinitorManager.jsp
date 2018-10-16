<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/form.jsp"%>

<html>
	<head>
		<title>服务检查</title>
		<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/middleware/dubboMinitorManager.js"></script>
	    <style type="text/css" class="init">
                td.details-control {
                    background: url('${ContextPath}/images/icon-jia.jpg') no-repeat center center;
                    cursor: pointer;
                }
                tr.shown td.details-control {
                    background: url('${ContextPath}/images/icon-jian.jpg') no-repeat center center;
                }
            </style>
	</head>
	<body>
        <div class="mb-10">
             <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<span>&gt;</span><a href="#">服务管理</a><span >&gt;</span><span >服务查看</span><a  href="javascript:location.replace(location.href);" title="刷新" ><i class="iconfont">&#xe61e;</i></a></nav>
        </div>
        <form action="" method="post" class="form form-horizontal" id="selectForm" name="selectForm">
            <div class="row">
                 <label class="form-label span2"><span class="c-red">*</span>ZK集群名称：</label>
                 <div class="span3">
                      <select name="queryMidwareId" id="queryMidwareId" size="1" class="select2" style="margin-top:5px">
                      </select>
                 </div>
            </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="table_data" class="table table-border table-bordered table-hover table-bg table-sort">
                <thead>
                    <tr>
                        <th></th>
                        <th>服务名</th>
                        <th>提供者数</th>
                        <th>消费者数</th>
                        <th>路由规则数</th>
                    </tr>
                </thead>
            </table>
        </div>
	</body>
</html>
