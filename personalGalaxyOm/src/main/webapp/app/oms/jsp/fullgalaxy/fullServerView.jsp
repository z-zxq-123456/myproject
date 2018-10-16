<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + request.getContextPath();
    request.getSession().setAttribute("ContextPath", basePath);
    String userIp = request.getRemoteHost();
    request.getSession().setAttribute("UserIp", userIp);
%>
<script type="text/javascript">
    var contextPath = "${ContextPath}";
</script>

<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" type="text/css" href="${ContextPath}/app/oms/css/module/default.css" />

<link rel="stylesheet" type="text/css" href="${ContextPath}/app/oms/css/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="${ContextPath}/app/oms/css/common/App.css" />

<script type="text/javascript" src="${ContextPath}/app/oms/js/commons/ajaxTool.js"></script>
<link href="${ContextPath}/css/app/galaxy.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ContextPath}/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${ContextPath}/lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="${ContextPath}/app/oms/js/commons/select.js"></script>
<script type="text/javascript" src="${ContextPath}/js/application.js"></script>

<!-- 定义JS公共变量-->
<script type="text/javascript">
    var contextPath = "${ContextPath}";
</script>

<html>
	<head>
		<title>全景视图</title>
		<script type="text/javascript" charset="UTF-8"	src="${ContextPath}/app/oms/js/fullgalaxy/fullServerView.js"></script>
		<script type="text/javascript" src="${ContextPath}/app/oms/js/commons/svgcommon.js"></script>
		<script type="text/javascript" src="${ContextPath}/app/oms/js/commons/json2.js"></script>
	</head>
	<body >
	<div region="center" style="padding: 5px;" border="false">
	<form action="" method="post" class="form form-horizontal" id="selectForm" name="selectForm">

       <div >
            <label class="form-label span2">是否自动重启应用：</label>
            <div class="formControls span3">
                <select name="isAutoStartApp" id="isAutoStartApp" size="1" class="select2" style="margin-top:5px">
                </select>
            </div>



       </form>
<svg id="svgdiv" width="100%" height="100%"  version="1.1" >
<defs>
<linearGradient id="orange_red" x1="0%" y1="0%" x2="100%" y2="0%">
<stop offset="0%" style="stop-color:rgb(0,0,255);
stop-opacity:1"/>
<stop offset="100%" style="stop-color:rgb(0,255,255);
stop-opacity:1"/>
</linearGradient>
</defs>

</svg>
	</div>	  
	</body>		  
</html>
<script type="text/javascript">
var isStart=true;//是否轮询
var delayTime = 50000;//轮询间隔周期 单位毫秒
var isFirstAccess = true;//是否首次访问
$(function(){
  $.ajax({
	   url : contextPath + "/getRefreshTime",
	   async : false,
	   type : "POST",
	   success : function (result) {
	   delayTime = parseFloat(result)*1000
	   console.info(delayTime);
	      }
	  });

	 startTimeOut();
	 getPkList({
                        url: contextPath + "/findParaCombox",
                        id: "isAutoStartApp",
                        async: false,
                        params: {
                            paraParentId: '0009',
                            isDefault: false
                        }
                    });


});
var startX = 3;//起始x坐标
var startY = 3;//起始y坐标
var mySvg = document.getElementById("svgdiv");

//新行初始化参数
function newRowInitArgs(rowNum){
      svr_space_width= Math.floor(($(document).width()-rowSerNum[rowNum]*server_width)/(rowSerNum[rowNum]+1));
	  startX = svr_space_width;        
      //startY = Math.floor(($(document).height())/(9*serverRow+1))*(9*rowNum)+10*(rowNum+1);
      startY = Math.floor(($(document).height())/(2*serverRow+1))*(2*rowNum+1)-10*(11-(rowNum+1)*3);
}

function startTimeOut(){
	if(isFirstAccess){
	   loading();
	}
	getViewContent(function(result){
		if(isFirstAccess){
			loaded();
			planServer(result.length);	
			drawAll(result);
			isFirstAccess=false;			
		}else{
			for(var i=0;i<result.length;i++){
	            var serverObj = result[i];                
	        	document.getElementById(createStatusId(serverObj)).href.baseVal=contextPath +serverObj.statusImage;
	        	document.getElementById(createTextId(serverObj)).setAttribute("info",JSON.stringify(serverObj.infoList));
	        	var unitList = serverObj.unitList;
	        	for(var a=0;a<unitList.length;a++){
	            	var appObj = unitList[a];
	        		document.getElementById(createStatusId(appObj)).href.baseVal=contextPath +appObj.statusImage;
	        		document.getElementById(createTextId(appObj)).setAttribute("info",JSON.stringify(appObj.infoList));
	            }
	        }
		}	        
	});
	if(isStart==true){
		 setTimeout('startTimeOut()',delayTime);	
	}
}

 </script>
  