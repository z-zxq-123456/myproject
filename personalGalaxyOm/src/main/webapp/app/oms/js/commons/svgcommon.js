var server_stroke_color="#00a0e9";//服务器边线颜色
//var server_fill_color="#00b7ee";//应用填充颜色
var server_fill_color="url(#orange_red)";//应用填充颜色
var orignal_server_height=32; //原始服务器高度
var server_height=orignal_server_height; //服务器高度
var server_width=160; //服务器宽度
var server_fill_opacity = "0.6";//服务器填充透明度
var server_stroke_opacity="1";//服务器边线透明度
var server_text_color="#ffffff";//服务器文本填充颜色
var server_font_size="14";//应用文本字体大小

var app_fill_color="#0099ff";//应用填充颜色
var app_stroke_color="#383838";//应用边线颜色
var app_height=24; //应用高度
var app_width=160; //应用宽度
var app_fill_opacity = "0.2";//应用填充透明度
var app_stroke_opacity="0.6";//应用边线透明度
var app_text_color="white";//应用文本填充颜色
var app_font_size="10";//应用文本字体大小

var icon_height= 18;//图标高度
var icon_width = 18;//图标宽度

var statusImage_height= 14;//状态图片高度
var statusImage_width = 14;//状态图片宽度

var space_heiht = 2;//应用之间间隔高度
var svr_space_heigth=orignal_svr_space_heigth;//服务器垂直间隔
var svr_space_width=orignal_svr_space_width;//服务器水平间隔
var orignal_svr_space_heigth=10; //原始服务器垂直间隔
var orignal_svr_space_width=10; //原始服务器水平间隔

var serverRow = 0;//服务器行数

//svg 基础对象
function SvgOms(name){
  this.node = document.createElementNS("http://www.w3.org/2000/svg", name);	 
}
SvgOms.prototype.appendTo = function(parent){ 
         if(typeof this.node !== "undefined" && parent.nodeType == 1){ 
           parent.appendChild(this.node); 
         } 
         return this; 
}; 
SvgOms.prototype.mattr = function(bag){ //添加多个属性
         for(var i in bag){ 
           if(bag.hasOwnProperty(i)){ 
             this.node.setAttributeNS(null,i,bag[i]);
           } 
         } 
         return this; 
 }; 
SvgOms.prototype.attr = function(attrName,attrValue){//添加一个属性 
         this.node.setAttributeNS(null,attrName,attrValue);
         return this; 
}; 
SvgOms.prototype.getAttr = function(attrName){
         return this.node.getAttributeNS(null,attrName);
}; 

 //服务器长方形
function omsServerRect(svgObj){	
	return new SvgOms("rect").mattr({"width":server_width,"height":orignal_server_height,"fill":server_fill_color,
	           "stroke":server_stroke_color,"stroke-width":"1","id":createRecId(svgObj),
	           "fill-opacity":server_fill_opacity,"stroke-opacity":server_stroke_opacity});
}

//服务器容纳应用长方形
function omsServerContainerRect(svgObj){	
	return new SvgOms("rect").mattr({"width":server_width,"height":server_height-orignal_server_height,"fill":"#ffffff",
	           "stroke":server_stroke_color,"stroke-width":"1",
	           "fill-opacity":server_fill_opacity,"stroke-opacity":server_stroke_opacity});
}

 //应用长方形
 function omsAppRect(svgObj){		
	return new SvgOms("rect").mattr({"width":app_width,"height":app_height,"fill":svgObj.bgColor,
	           "stroke":app_stroke_color,"stroke-width":"0","id":createRecId(svgObj),
	           "fill-opacity":app_fill_opacity,"stroke-opacity":app_stroke_opacity});
}
//创建图标
function createIcon(svgObj){
   var iconImage= new SvgOms("image").mattr({"width":icon_width,"height":icon_height,"id":createIconId(svgObj)});
   iconImage.node.href.baseVal=contextPath +svgObj.imageUrl;   
   return iconImage;
 }
//创建状态图标
function createStatusImage(svgObj){   
   var statusImage= new SvgOms("image").mattr({"width":statusImage_width,"height":statusImage_height,
	                                           "id":createStatusId(svgObj)});
   statusImage.node.href.baseVal=contextPath +svgObj.statusImage;   
   return statusImage;
 }
//创建服务器文本
function createServerText(svgObj){   
   var info = JSON.stringify(svgObj.infoList);
   var svgText= new SvgOms("text").mattr({"fill":server_text_color,"font-size":server_font_size,
	                                      "id":createTextId(svgObj),"info":info});
   svgText.node.textContent=svgObj.name; 
   svgText.node.addEventListener('click',unitMouseOver);
   svgText.node.addEventListener('mouseout',unitMouseOut);
   return svgText;
 }
 //创建应用文本
function createAppText(svgObj){  
   var info = JSON.stringify(svgObj.infoList);
   var svgText= new SvgOms("text").mattr({"fill":svgObj.bgColor,"font-size":app_font_size,
   "id":createTextId(svgObj),"info":info});
   svgText.node.textContent=svgObj.name;  
   svgText.node.addEventListener('click',unitMouseOver);
   svgText.node.addEventListener('mouseout',unitMouseOut);
   return svgText;
 }
//服务器对象
 function OmsServer(svgDiv,startX,startY,serverObj){
   var serverId =serverObj.serId;
   this.svgRect =omsServerRect(serverObj).mattr({"x":startX,"y":startY}).appendTo(svgDiv);
   this.serverContainer=omsServerContainerRect(serverObj).mattr({"x":startX,"y":startY+orignal_server_height}).appendTo(svgDiv);
   //var newStartY = startY + server_height - app_height;
   var newStartY = startY + space_heiht;
   startX =startX+5;
   this.iconImage=createIcon(serverObj).mattr({"x":startX,"y":newStartY+4}).appendTo(svgDiv);
   this.svgText =createServerText(serverObj).mattr({"x":startX+icon_width+5,"y":newStartY+17}).appendTo(svgDiv); 
   this.statusImage=createStatusImage(serverObj).mattr({"x":startX+server_width-statusImage_width-11,"y":newStartY+5}).appendTo(svgDiv);
   return this;
 } 
 //创建应用
 function OmsApp(svgDiv,startX,startY,appObj){
   //startX =startX+5;
   this.svgRect =omsAppRect(appObj).mattr({"x":startX,"y":startY}).appendTo(svgDiv);  
   this.iconImage=createIcon(appObj).mattr({"x":startX+5,"y":startY}).appendTo(svgDiv);
   this.statusImage=createStatusImage(appObj).mattr({"x":startX+app_width-statusImage_width-6,"y":startY+5}).appendTo(svgDiv);
   this.svgText =createAppText(appObj).mattr({"x":startX+icon_width+5,"y":startY+17}).appendTo(svgDiv); 
   return this;
 }
//鼠标over事件 显示激活对象的详细信息
var $obj;
var index_info;
function unitMouseOver(e) {
    e = e ? e : window.event;
    var obj = e.srcElement ? e.srcElement : e.target;
    $obj = $(obj);
    var svgText = document.getElementById($obj.attr("id"));
    var $svgText = $(svgText);
    index_info = layer.open({
        type: 2,
        content: 'info.jsp',
        title: svgText.textContent + '详细信息',
        area: ['450px', '450px'],
        shadeClose: true,
        shade: 0.2,
        closeBtn: 0,
        bgcolor: '#fff' 
    });
}
 //鼠标out事件 清除激活对象的详细信息
 function unitMouseOut(e){	
    /*$('#svgDetailDiv').dialog('close');
	$('#svgDetailData').datagrid({
	   data: []
    })*/
  }

//创建方形ID
function createRecId(svgObj){
   return svgObj.unitId+"_"+svgObj.unitType+"_"+"rect";
}
//创建图标ID
function createIconId(svgObj){
   return svgObj.unitId+"_"+svgObj.unitType+"_"+"icon";
}
//创建状态图标ID
function createStatusId(svgObj){
   return svgObj.unitId+"_"+svgObj.unitType+"_"+"statu";
}
//创建text ID
function createTextId(svgObj){
   return svgObj.unitId+"_"+svgObj.unitType+"_"+"text";
}
//计算服务器高度
function countServerHeight(appNum){
   return appNum*app_height+(appNum+2)*space_heiht+server_height;
}
