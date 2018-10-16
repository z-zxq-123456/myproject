
//生成联动的下拉列表
window.onload=function(){
var height=document.body.clientHeight;
var a=$(window).height();	    
$("#DCITS").css('height',a-30);
	    
	//规则配置第一步所需要的下拉列表
	$("#prodGroup").combobox({
		onChange:function(){

			var value=$('#prodGroup').combobox('getValue');	
			if(value=="CL"){
				var url = contextPath + "/getCLProdList";
				$('#prodName').combobox('clear');
				$('#prodName').combobox('enable');
				$('#prodName').combobox('reload', url);
			}
			if(value=="RB"){
				var url = contextPath + "/getRBProdList";
				$('#prodName').combobox('clear');
				$('#prodName').combobox('enable');
				$('#prodName').combobox('reload', url);
			}
			if (value=="GLOBAL") {
				$('#prodName').combobox('clear');
				$('#prodName').combobox('disable');
			}
		}	

	}); 

	//规则配置第三步所需要的联动的下拉列表
	$("#facInfo").combobox({
		onChange:function(){

			var value=$('#facInfo').combobox('getValue');	
			if(value=="amtType"||value=="prodType"||value=="eventType"||value=="ccy"||value=="prodGrp"||value=="clientType"||value=="categoryType"||value=="crRating"||value=="branch"||value=="country"||value=="state"||value=="sourceType"||value=="drCrFlag"){
				var url = contextPath + '/get'+value;	
				$("#DivinValue1").show();
				$("#DivinValue2").hide();
				$('#inValue').combobox('reload', url);
			}
			if(value=="indInfoList"||value=="amt"||value=="days"){
				$("#DivinValue2").show();
				$("#DivinValue1").hide();

			}
		}	

	}); 
	
	
	$("#floatType").combobox({
		onChange:function(){

			var floatType=$('#floatType').combobox('getValue');	
			if(floatType=="1"){$('#type').html("倍");};
			if(floatType=="0"){$('#type').html("倍");};
			if(floatType=="2"){$('#type').html("%");};
		}	

	}); 
	
	//根据所选产品类型的内容生成币种和借贷标识的联动选择
	$("#prodType").combobox({
		onChange:function(){

			var prodType=$('#prodType').combobox('getValue');
			if(prodType.substring(0,1)=="C"){
				$("#drcrFlag").combobox('setValue',"D");
			}else if(prodType.substring(0,1)=="R"){
				$("#drcrFlag").combobox('setValue',"C");
			}
			$("#ccy").combobox('setValue',"CNY");
		}	

	}); 
		
	
	//根据所选周期类型的内容生成相应的周期值单位显示
	$("#periodType").combobox({
		onChange:function(){
		var periodType=$("#periodType").combobox('getValue');

		
		if(periodType=="D"){$('#ptype').html("D(天)");};
		if(periodType=="W"){$('#ptype').html("W(周)");};
		if(periodType=="M"){$('#ptype').html("M(月)");};
		if(periodType=="Y"){$('#ptype').html("Y(年)");};
		}
	});

};



//日期格式处理
function myformatter(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}
function myparser(s){
	if (!s) return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0],10);
	var m = parseInt(ss[1],10);
	var d = parseInt(ss[2],10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
		return new Date(y,m-1,d);
	} else {
		return new Date();
	}
}

var indexMask;
//网页遮罩方法
function loading(){
	indexMask = layer.load(4, {
		shade: [0.4,'#777777'] //0.5透明度的白色背景
	});
}


function loaded(){
	layer.close(indexMask);
}


