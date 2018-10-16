<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/form.jsp" %>
<html>
<head>
    <title>业务流水查询</title>
    <script type="text/javascript" charset="UTF-8" src="${ContextPath}/app/oms/js/leaderview/fwTranInfoManager.js"></script>
            <script type="text/javascript" src="${ContextPath}/js/application.js"></script>
</head>
<body>
<div class="mb-10">
    <nav class="breadcrumb"><i class="iconfont">&#xe61d;</i>系统首页<span>&gt;</span><a
            href="#">管理视图</a><span>&gt;</span><span>业务流水查询</span><a href="javascript:location.replace(location.href);"
                                                                    title="刷新"><i class="iconfont">&#xe61e;</i></a>
    </nav>
</div>
<form action="" method="post" class="form form-horizontal" id="selectForm" name="selectForm">
    <div class="row">
        <label class="form-label span2">起始日期：</label>
        <div class="formControls span3 ">
            <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd'})"
                   id="startTime" name="startTime" class="input-text Wdate " style="width:250px;margin-left:0px;">
        </div>
        <label class="form-label span2">终止日期：</label>
        <div class="formControls span3">
            <input type="text"
                   onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd'})"
                   id="endTime" name="endTime" class="input-text Wdate" style="width:250px;margin-left:0px;">
        </div>
    </div>
    <div class="row">
        <label class="form-label span2">刷新时间：</label>
        <div class="formControls span3">
           			<span class="select-box  size-L">
           			  <select class="select" size="1" name="refreshTime"   id="refreshTime">
           				<option value="10" >10秒</option>
           				<option value="15">15秒</option>
           				<option value="20">20秒</option>
           			</select>
           			</span>
          </div>
        <div class="span1"></div>
        <a id="select" href="#" class="button-sure M ml-20" style="margin-bottom:10px">
            <i class="iconfont">&#xe624;</i> 查询</a>
    </div>
</form>
	  </div>	
	 </div>
<div class="mr-20 ml-20 mt-10">
    <table id="table_data" class="table table-border table-bordered table-hover table-bg table-sort"   data-options="
     rowStyler: function(index,row){
    if (row.status=='F'){
        return 'background-color:#f00;';
    }
}
">
        <thead>
        <tr>
            <th>服务ID</th>
            <th>防重KEY</th>
            <th>交易日期</th>
            <th>开始时间</th>
            <th>结束时间</th>
            <th>渠道类型</th>
            <th>渠道流水号</th>
            <th>状态</th>
            <th>业务参考号</th>
			<th>输入报文</th>
			<th>输出报文</th>
        </tr>
        </thead>
    </table>
</div>
	</div>
	<div id="msgDetailDiv" class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px"   closed="true"  >
    	  <div id="msgDetailText"  style="width:560px;word-break:break-all;">
    	  </div>
       </div>
	</body>		  
</html>
<script type="text/javascript">
$function(){
alert();
});
</script>
