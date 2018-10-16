<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/formServerSide.jsp"%>

<html>
	<head>
		<title>调用链跟踪</title>
		<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/log/tcechainTraceManager.js"></script>
	    <style type="text/css" class="init">
                td.details-control {
                    background: url('${ContextPath}/images/icon-jia.jpg') no-repeat center center;
                    cursor: pointer;
                }
                 td.details-control1 {
                                    background: url('${ContextPath}/images/dataTable/icon-search.png') no-repeat center center;
                                    cursor: pointer;
                                }
                  td.details-control2 {
                                                     background: url('${ContextPath}/images/dataTable/icon-search.png') no-repeat center center;
                                                     cursor: pointer;
                  }
                tr.shown td.details-control {
                    background: url('${ContextPath}/images/icon-jian.jpg') no-repeat center center;
                }

                tableCss { width: 100%; empty-cells: show; background-color: transparent; border-collapse: separate; border-spacing: 0; font-family: Microsoft Yahei; }
                tableCss th { text-align: left; font-weight: 400; }
                tableCss tr {border-left:2px solid #c3ced9;border-right: 2px solid #c3ced9;}
                .tableCss th { padding: 8px 19px 8px 8px; line-height: 20px; font-size: 14px; color: #000000; }
                .tableCss td { font-weight: 400; text-align: left; line-height: 30px; padding-left: 8px; font-size: 13px; color: #4d4d4d; }
                .tableCss tbody tr.success > td { background-color: #dff0d8; }
                .tableCss tbody tr.error > td { background-color: #f2dede; }
                .tableCss tbody tr.warning > td { background-color: #fcf8e3; }
                .tableCss tbody tr.info > td { background-color: #d9edf7; }
                .tableCss tbody + tbody { border-top: 2px solid #ddd; }
                .tableCss .table { background-color: #fff; }
            </style>
	</head>
	<body>
        <div class="mb-10">
             <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<span>&gt;</span><a href="#">日志管理</a><span >&gt;</span><span >调用链跟踪</span></nav>
        </div>
        <form action="" method="post" class="form form-horizontal" id="selectForm" name="selectForm">
        <div class="row">

                            <label class="form-label span2">网点号：</label>
                                <div class="formControls span3">
                                        <input type="text"  id="branchId" name="branchId" class="input-text " style="width:250px;margin-left:0px;">
                                </div>
                            <label class="form-label span2">柜员号：</label>
                                <div class="formControls span3">
                                        <input type="text"  id="tellerId" name="tellerId" class="input-text " style="width:250px;margin-left:0px;">
                                </div>
                              </div>
               <div class="row">
                                <label class="form-label span2">起始日期：</label>
                                <div class="formControls span3">
                                    <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd'})" id="startTime" name="startTime" class="input-text Wdate" style="width:250px;margin-left:0px;">
                                </div>
                                <label class="form-label span2">终止日期：</label>
                                <div class="formControls span3">
                                  <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd'})" id="endTime" name="endTime" class="input-text Wdate" style="width:250px;margin-left:0px;">
                                </div>
                                 <div class="formControls span3"></div>
                                    <a id="selectDate" href="#" class="button-sure M ml-20" style="margin-bottom:10px">
                                    <i class="iconfont">&#xe624;</i> 查询</a>
                                 </div>
        </div>
        </form>
        <div class="mr-20 ml-20 mt-10">
            <table id="table_data" class=" tableCss  table-border table-bordered  table-bg ">
                <thead>
                    <tr>
                        <th></th>
                        <th>名称</th>
                        <th>状态</th>
                        <th>调用时间</th>
                        <th>执行时长</th>
                        <th>调用链详情</th>
                        <th>调用链图示</th>
                        <th>交易日志查看</th>
                    </tr>
                </thead>
            </table>
        </div>
	</body>
</html>
<script type="text/javascript">
$(document).ready(function() {
	$("#startTime").val(today());//获取文本id并且传入当前日期
	$("#endTime").val(today());//获取文本id并且传入当前日期
});
function today(){
             var today=new Date();
             var h=today.getFullYear();
             var m=today.getMonth()+1;
             var d=today.getDate();//得到当天时间
             m= m<10?"0"+m:m;   //  这里判断月份是否<10,如果是在月份前面加'0'
             d= d<10?"0"+d:d;        //  这里判断日期是否<10,如果是在日期前面加'0'
             return h+"-"+m+"-"+d;
 }

</script>