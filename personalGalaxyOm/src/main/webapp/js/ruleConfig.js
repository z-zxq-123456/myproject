	 $(document).ready(function() {
		    $(".select2").select2();
	});

function addClick(){
	 var ruleType = $("#ruleType").val();
	 var str;
	 var value;
  	 var str1;
	 if(ruleType=="LI"){
	   str = $("#facInfo0").val()+$("#condition").val();
	   value = $("#facInfo0").val();
	   str1 = $("#facInfo0 option:selected").text()+$("#condition option:selected").text();

	      if(value=="indInfoList"||value=="amt"||value=="days"){
          			str+='"'+ $("#inValue1").val() + '"';
          			str1+='"'+ $("#inValue1").val() + '"';
          }else{
                   str+='"'+ $("#inValue").val() + '"';
                   str1+='"'+ $("#inValue option:selected").text() + '"';
           }
	 }else{
	 var attr = $("#elementAttr").val();
	 var facInfo1 = $("#factInfo1").val();
	 var str1 =$("#factInfo1 option:selected").text().split(" ")[1]+$("#condition option:selected").text();
	 if(attr=="NUMBER"){
			str = "(BigDecimalUtil.toBigDecimal(factInfo.getInfoMap.get("+'"'+facInfo1+'"'+"))).compareTo(BigDecimalUtil.toBigDecimal("+'"'+$("#inValue1").val()+'"'+"))"+$("#condition").val()+"0";
			str1+='"'+ $("#inValue1").val() + '"';
	 }else{
	 	str = "(StringUtil.ObjectToEmpty(factInfo.getInfoMap.get("+'"'+facInfo1+'"'+"))).compareTo("+'"'+$("#inValue1").val()+'"'+")"+$("#condition").val()+"0";
	 	 str1+='"'+ $("#inValue option:selected").text() + $("#inValue1").val()+ '"';
	 }

	 }
      $("#details").val($("#details").val() + str);
      $("#details1").val($("#details1").val() + str1);
}
function clsClick(){
      $("#details").val("");
      $("#details1").val("");
}
function andClick() {
		$("#details").val($("#details").val() + " && ");
		$("#details1").val($("#details1").val() + "   并且   ");
}
function orClick() {
		$("#details").val($("#details").val() + " || ");
		$("#details1").val($("#details1").val() + "    或者    ");
}
function leftClick() {
		$("#details").val($("#details").val() + "(");
		$("#details1").val($("#details1").val() + "   (   ");
}
function rightClick() {
		$("#details").val($("#details").val() + ")");
		$("#details1").val($("#details1").val() + "   )   ");
}
function finish(){
         layer.confirm('确认完成规则配置？',function(){
                    ruleConfig();
                });
}
function ruleConfig(){
        var value = $("#salience").val();
     	if ($("#userId").val().replace(/(^\s*)|(\s*$)/g, "")=="" || $("#userId").val()== null )
     	{
     		showMsg('创建人不能为空，谢谢！','error');
     		return ;
     	}
		else if ($('#ruleType').val() == ""){
			showMsg('规则类型不能为空，谢谢！','error');
			return ;
		}
     	else if ($('#prodGroup').val() == ""){
     		showMsg('产品组不能为空，谢谢！','error');
     		return ;
     	}
     	else if ($("#salience").val().replace(/(^\s*)|(\s*$)/g, "")=="" || $("#salience").val()== null )
     	{
     		showMsg('优先级不能为空，谢谢！','error');
     		return ;
     	}

     	else if ( value.length > 6 )
     	{
     		showMsg('优先级输入过长！','error');
     		return ;
     	}

     	else if ($('#startDate').val() == ""){
     		showMsg('生效日期不能为空，谢谢！','error');
     		return ;
     	}
     	else if ($('#endDate').val() == ""){
     		showMsg('失效日期不能为空，谢谢！','error');
     		return ;
     	}
     	else if ($('#status').val() == ""){
     		showMsg('规则状态不能为空，谢谢！','error');
     		return ;
     	}
     	else if ($("#startDate").val() >= $("#endDate").val()) {
     		showMsg('生效日期必须小于失效日期，谢谢！','error');
     		return ;
     	}
     	 var reg= /^(-|\+)?\d+(\.\d+)?$/;
     	 if ($("#details").val() == "") {
     			showMsg('规则条件配置不能为空，谢谢！','error');
     			return ;
     		}
     	else{
				var id = "";
				var url1 = contextPath + "/baseCommon/getRuleId";
				sendPostRequest(url1,{}, function (json) {
							var maxId = json;
							id = maxId;

				},"json",false);


				var prodRuleId = "";
				var url2 = contextPath + "/baseCommon/getMaxAutoAddID";

				var params;
				var ruleType = $('#ruleType').val();
				var prodGroup;
				var prodType="";
				var groupC="";

				if(ruleType=="LI"){
						prodGroup = $('#prodGroupLi').val();
				}else if(ruleType=="FEE"){
						prodGroup = $('#prodGroupFee').val();
				}else if(ruleType=="EXCH"){
                 		prodGroup = $('#prodGroupExch').val();
				}else if(ruleType=="DISC"){
                 		prodGroup = $('#prodGroupDisc').val();
                }

                //费用和折扣的产品组为GROUP时 产品为 clglobal/rbglobal
                if(prodGroup=="GROUP"&&(ruleType=="FEE"||ruleType=="DISC")){
                		prodType = $('#prodGroupDisc').val();
                }else {
                		if(prodGroup=="RATETYPE"){
                			groupC=$('#TCcy').val()+$('#SCcy').val();;
                		}
                		prodType = $('#prodType').val();
                }

				if (prodType == ""||prodType == "undefined"||prodType == null){
						prodType="global";
				}

				 params={
							tableName: "IRL_RULE_MESSAGE",
                            fieldName: "PROD_RULE_ID",
                            inqueryCondition:"PROD_GROUP='"+prodGroup+"' "+"AND PROD_ID='"+prodType+"'"};

				sendPostRequest(url2,params, function (json) {
							var maxId = json;
							prodRuleId = maxId;

				},"json",false);




				var date = new Date();
				var month = date.getMonth();
				var createDate;
				var year = date.getFullYear();
				if(month<10){
					createDate = date.getFullYear()+"0"+(date.getMonth()+1)+date.getDate();
				}
				else{
					createDate = date.getFullYear()+(date.getMonth()+1)+date.getDate();
				}

				var paraJson,generalFieldsJson,keyFieldsJson;
				paraJson = {};
				generalFieldsJson={};
				keyFieldsJson={};
						var url = contextPath+"/baseCommon/updateAndInsertForSave";
					  paraJson.tableName="IRL_RULE_MESSAGE";
					  			keyFieldsJson.ID=id;
                               	generalFieldsJson.CHECK_TYPE=$("#addFlag").val();
                               	generalFieldsJson.COMPANY=$("#company").val();
                               	generalFieldsJson.RULE_TYPE=ruleType;
                               	generalFieldsJson.CREATE_DATE=createDate;
                               	generalFieldsJson.END_DATE=$("#endDate").val();
                               	generalFieldsJson.FLOAT_TYPE=$("#floatType").val();
                               	generalFieldsJson.FLOAT_VALUE=$("#floatValue").val();
                               	generalFieldsJson.IMPORT_MESSAGE=$("#importMessage").val();
                               	generalFieldsJson.PROD_GROUP=prodGroup;
                               	generalFieldsJson.PROD_ID=prodType;
                               	generalFieldsJson.PROD_RULE_ID=prodRuleId;
                               	generalFieldsJson.RULE_DESC=$("#details1").val();
                               	generalFieldsJson.RULE_EXPRESS=$("#details").val();
                               	generalFieldsJson.SALIENCE=$("#salience").val();
                               	generalFieldsJson.SPECIAL_RULE_PROCESS=$("#specialRuleProcess").val();
                               	generalFieldsJson.START_DATE=$("#startDate").val();
                               	generalFieldsJson.STATUS=$("#status").val();
                               	generalFieldsJson.USER_ID=$("#userId").val();
                               	generalFieldsJson.GROUP_C=groupC;
          	paraJson.key = keyFieldsJson;
          	paraJson.general=generalFieldsJson;
      		var params = {
                      				paraJson:JSON.stringify(paraJson)
                      			};
	sendPostRequest(url,params, callback_irlRuleMessageAdd,"json");
	}
}
function callback_irlRuleMessageAdd(json){
	if (json.success) {
     parent.showMsgDuringTime("添加成功");
	} else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
	}
}


function irlRuleMessageAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}
