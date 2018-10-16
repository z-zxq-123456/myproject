<!DOCTYPE HTML>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/formServerSide.jsp"%>
<html>
	<head>
        <script type='text/javascript' charset="UTF-8"src="${ContextPath}/app/oms/js/commons/highcharts.js"></script>

	</head>
	<script type="text/javascript">
	var layer_monitor_index;
	$(document).ready(function () {
	getPkList({
                url: contextPath + "/findSerComboxIp",
                id: "serName",
                async: false,
                normalSelect:false,
            });
     monitor();
     $("#serName").change(function(){
           monitor();
        });
	});

    function monitor() {
             if ($("#serName").val()!=""){
                     document.getElementById("serPerDivId").innerHTML = createFrame("serPer.jsp");
             }else {
              showMsg('请选择服务器', 'warning');
             }
    }
    function createFrame (url) {
            var s = '<iframe scrolling="auto"  id="frame_ser"  frameborder="0"  src="' + url+'?'+"serId ="+$("#serName").val()
        	    + '" style="width:100%;height:99%;"></iframe>';
        	return s;
    }
    </script>
	<body>
     <form action="" method="post" class="form form-horizontal" id="selectForm" name="selectForm">
            <div >
                  <label class="form-label span2 " >服务器：</label>
                         <div class="formControls span3" >
                             <select type="text" class="select2" name="serName"
                                     id="serName"></select>
                         </div>
                 </div>
            </form>
     <div class="pd-20">
     <div   id = "serPerDivId"   style="width:100%;height:480px;margin-top:12x"></div>
     </div>
	</body>

</html>