var form;
$(document).ready(function() {
      $("#conditionValue3").hide();
      var elReg = /^[A-Z]+$/;
	 getPkList({  url: contextPath + "/attrType/getAttrType",
					  id: "condition",
					  async: false
						 });
     $('#condition').change(function(){
 		  var value=$('#condition').val();
 		   var url = contextPath + "/attrType/getValueMethod";
				 $.ajax({
					 url: url,
					 data: "attrKey=" + value,
					 success: function(json) {
						  if(json.data.valueMethod=="FD"){
						      $("#conditionValue2").hide();
						      $("#conditionValue3").hide();
						      $("#conditionValue").val("");
						      $("#conditionValue4").val("");
						      $("#conditionValue1").show();
						  }else if(json.data.valueMethod=="VL" || json.data.valueMethod=="RF"){
                              $("#conditionValue1").hide();
                              $("#conditionValue1").val("");
                              $("#conditionValue4").val("");
                              $("#conditionValue2").show();
                              $("#conditionValue3").hide();
						  }else if(json.data.valueMethod=="YN"){
                              $("#conditionValue1").hide();
                              $("#conditionValue1").val("");
                              $("#conditionValue").val("");
                              $("#conditionValue2").hide();
                              $("#conditionValue3").show();
						  }
					 },
					 dataType: "json",
				 });
			getPkList({  url: contextPath + "/attrValue/getAttrValueByKey?attrKey=" + value,
							   id: "conditionValue",
							   async: false
						 });
 	  });

       $("#conditionId").blur(function(){
		 if(elReg.test($("#conditionId").val()) !=  true){
		   alert("规则代码应为大写英文！！！");
		  $("#conditionId").val("");
		  return;
		 }else{
                     var url = contextPath + "/ruleDefine/getOne";
                     $.ajax({
                         url: url,
                         data: "conditionId=" + $("#conditionId").val(),
                         success: function(json) {
                             if (json.retStatus === 'F') {
                                   showMsg(json.retMsg, 'info');
                                    $("#conditionId").val("");
                             } else if (json.retStatus === 'S') {
                                  return;
                             }
                         },
                         dataType: "json",
                     });
           }
        });
	$(".select2").select2();

$("#submit").click(function() {
   if($("#conditionId").val()=="" || $("#conditionDesc").val()==""){
	  alert("请输入规则代码/描述 ！！！");
   }else{
			var url = contextPath+"/ruleDefine/insert";
			$.ajax({
				url: url,
				async:false,
				data:{
					 conditionId:$("#conditionId").val(),
					 conditionDesc:$("#conditionDesc").val(),
					 conditionRule:$("#conditionRule").val(),
					 status:$("#status").val()
				},
				success: function(json) {
					 if (json.retStatus == 'F') {
					  showMsg(json.retMsg, 'info');
					  } else if (json.retStatus == 'S') {
						 showMsg(json.retMsg, 'info');
					  }
			   },
			   dataType: "json",
			   type : "POST"
			 });
		ruleAddCancel();
   }

	});

 $("#sure").click(function() {
   if($("#condition").val()==""||$("#relation").val()==""){
      alert("请输入全条件信息！！！");
      return;
   }else{
    var str="";
      str =  $("#conditionRule").val() + $("#condition").val() + $("#relation").val() + $("#conditionValue").val() + $("#conditionValue1").val() + $("#conditionValue4").val();
     $("#conditionRule").val(str);
     }
 });
  $("#or").click(function() {
     var str="";
       str =  $("#conditionRule").val() +" "+ $("#or").val()+" ";
      $("#conditionRule").val(str);
  });
  $("#and").click(function() {
   var str="";
	 str =  $("#conditionRule").val() +" "+ $("#and").val()+" ";
	$("#conditionRule").val(str);
   });
});
function ruleAddCancel(){
	var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}