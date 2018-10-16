var isStart=true;//是否轮询
var delayTime = 50000;//轮询间隔周期 单位毫秒
var isFirstAccess = true;//是否首次访问
$(function(){
	startTimeOut();
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
var rowSerNum = new Array();
//根据服务器数量来筹划服务器布局
function planServer(serverNum){
      switch(serverNum){
           case 1 :serverRow=1;rowSerNum[0]=1;break;
           case 2 :serverRow=1;rowSerNum[0]=2;break;
		   case 3 :serverRow=1;rowSerNum[0]=3;break;
		   case 4 :serverRow=2;rowSerNum[0]=2;rowSerNum[1]=2;break;
		   case 5 :serverRow=2;rowSerNum[0]=2;rowSerNum[1]=3;break;
		   case 6 :serverRow=2;rowSerNum[0]=3;rowSerNum[1]=3;break;
		   case 7 :serverRow=2;rowSerNum[0]=3;rowSerNum[1]=4;break;
		   case 8 :serverRow=2;rowSerNum[0]=4;rowSerNum[1]=4;break;
		   case 9 :serverRow=2;rowSerNum[0]=4;rowSerNum[1]=5;break;
		   case 10:serverRow=2;rowSerNum[0]=5;rowSerNum[1]=5;break;
		   case 11:serverRow=3;rowSerNum[0]=3;rowSerNum[1]=4;rowSerNum[2]=4;break;
		   case 12:serverRow=3;rowSerNum[0]=4;rowSerNum[1]=4;rowSerNum[2]=4;break;
		   case 13:serverRow=3;rowSerNum[0]=3;rowSerNum[1]=5;rowSerNum[2]=5;break;
		   case 14:serverRow=3;rowSerNum[0]=4;rowSerNum[1]=5;rowSerNum[2]=5;break;
		   case 15:serverRow=3;rowSerNum[0]=5;rowSerNum[1]=5;rowSerNum[2]=5;break;		  
           default:serverRow=4;computeRowSerNum(serverNum);
	  }
 }
//计算每行布局服务器的个数
function computeRowSerNum(serverNum){
   var nums = serverNum;
   var upNums =Math.floor(nums/2);
   var downNums =nums-upNums;
   var oneNums = Math.floor(upNums/2);
   var threeNums = Math.floor(downNums/2);
   rowSerNum[0]=Math.floor(oneNums);
   rowSerNum[1]=upNums-oneNums; 
   rowSerNum[2]=downNums-threeNums;
   rowSerNum[3]=threeNums;
}

//访问后台获取展现信息
function getViewContent(fn){    	 
     var rowCount_sub_url = contextPath + '/findFullServerView';
     $.post(rowCount_sub_url,{isAutoStartApp: $("#isAutoStartApp").val()},function(result){
    		var result = eval('('+result+')');    		
            if (result.errorMsg){            	   
                   $.messager.show({
                      title: 'Error',
                      msg: result.errorMsg
                   });
                   isStart=false;
            } else {            	   
            	   fn(result);
            }
    });	
}

//画普通服务器行
function drawServerRow(serverObj){
  var unitList = serverObj.unitList;
  if(unitList.length>3){
	  server_height=countServerHeight(unitList.length); 
  }else{
	  server_height=countServerHeight(3); 
  }
  
  var svgRect =OmsServer(mySvg,startX,startY,serverObj);  
  for(var i=0;i<unitList.length;i++){	
    OmsApp(mySvg,startX,startY+space_heiht+orignal_server_height+i*(app_height+space_heiht),unitList[i]);
  }
}

function drawAll(result){
	  var indexServer = 0;
	  for(var rowNum =0;rowNum<serverRow;rowNum++){	    
	    newRowInitArgs(rowNum);
	    for(var serNum =0;serNum<rowSerNum[rowNum];serNum++){
	      if(serNum!=0){
		     startX = startX+server_width+svr_space_width; 
		   }
		   server_height=orignal_server_height;
		   drawServerRow(result[indexServer]);
		   indexServer++;
		 }
	 } 
 }


function openFullScreen() {
var url = "fullServerView.jsp";
   if(url != null) {
        try {
		  var feature = 'fullScreen=yes,top=0, left=0,toolbar=no,menubar=no, scrollbars=yes, resizable=yes,location=no,status=no,channelmode = yes ';
		  var newwindows = window.open(url, "", feature);
		  newwindows.moveTo(0, -30); //将新页面打开位置定位在屏幕左上角
		  newwindows.resizeTo(screen.width, screen.height+30); //设置新页面的大小~实际上也可以在上面的属性中设置~不过~不能最大化~原因未知~坐等牛人解答~
		  if (newwindows != null) {
			  newwindow.focus();//新页面获得焦点
		  }
	  }
	  catch (e) {
	  }
   }
}


 