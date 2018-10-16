var tableNames="MB_EVENT_TYPE,MB_EVENT_ATTR,MB_EVENT_PART";
var transActionId="EVENT_PARA_COMBINE";
var clickFlag=1;
$(function() {
                        $("#newEventDiv").hide();
                          $("#attrList").hide();
                        var step= $("#myStep").step({
                            animate:true,
                            initStep:1,
                            maxStepSize: 3,
                            speed:1000
                        });
                        $.ajax({
                                                  url: contextPath + "/ProdToJson/Start",
                                                  data:{
                                                       reqNum:parent.$(".breadcrumb").data("reqNum"),
                                                       tableNames:tableNames,
                                                       tableName:"PROD_EVENT_PARA_COMBINE",
                                                                                   tableDesc:"事件定义"
                                                  },
                                                success: function(json) {
                                                                                if(json.appNo!==null)
                                                                                {
                                                                                $(".breadcrumb").data("reqNum",json.appNo);
                                                                                if(json.currentStatus==="2"||json.currentStatus==="3"){
                                                                                         $("#save").hide();
                                                                                         $("#nullifyAppNo").hide();
                                                                                }else if(json.currentStatus==="1"){
                                                                                         $("#nullifyAppNo").hide();
                                                                                }
                                                                                }
                                                      },
                                                  dataType: "json",
                                                  type : "POST"
                          });

                        //可以根据需求来写上一步，下一步,跳到特定步骤的操作函数
                        $("#preOne").click(function() {
                            step.preStep();
                        });
                        $("#preTwo").click(function() {
                            step.preStep();
                        });
                        $("#ome").click(function() {
                            step.nextStep();
                        });
                        $("#two").click(function() {
                            step.nextStep();
                        });
                            $('.skin-minimal input').iCheck({
                                checkboxClass: 'icheckbox-blue',
                                radioClass: 'iradio-blue',
                                increaseArea: '20%'
                            });
                            $.galaxytab("#tab-system .tabBar span", "#tab-system .tabCon", "current", "click", "0");
                        });
                        $(document).ready(function() {
                           getPkList({  url: contextPath + "/event/getForPkList",
                               id: "eventClass",
                               async: false
                              });
                        });
                        $(document).ready(function() {
                           attrClassInFor();

                        });
                        function attrClassInFor(){
                                var url = contextPath + "/attr/getAll";
                                sendPostRequest(url,null,
                                function(json) {
                                      for (var i = 0; i < json.data.length; i++) {
                                      var li;
                                      var div;
                                      if(i===0){
                                          li  = '<li class="active"><a href="#'+json.data[i].attrClass +'" data-toggle="tab">'+json.data[i].attrClassDesc+'</a></li>';
                                          div  = '<div class="tab-pane active" id="'+json.data[i].attrClass+'"><div class="span3"><ul id="'+json.data[i].attrClass+'1" class="ztree showIcon"></ul></div><div class="span9"><div class="padding-20" style="width:100%"><div class="portlet" id="sortable_portlets"><div class="row" id="'+json.data[i].attrClass+'D"></div></div></div></div></div>';
                                      }else{
                                          li  = '<li><a href="#'+json.data[i].attrClass +'" data-toggle="tab">'+json.data[i].attrClassDesc+'</a></li>';
                                          div  = '<div class="tab-pane" id="'+json.data[i].attrClass+'"><div class="span3"><ul id="'+json.data[i].attrClass+'1" class="ztree showIcon"></ul></div><div class="span9"><div class="padding-20" style="width:100%"><div class="portlet" id="sortable_portlets"><div class="row" id="'+json.data[i].attrClass+'D"></div></div></div></div></div>';
                                      }
                                         $("#list").append(li);
                                         $("#tabDiv").append(div);
                                         var str11 = "#"+json.data[i].attrClass +"1";
                                         var str12 = "#"+json.data[i].attrClass +"D";
                                         $.ajax({
                                             url: contextPath + "/partCon/getzTreeList?attrClass="+json.data[i].attrClass+"&busiCategory="+$("#Bmodule").val(),
                                             async:false,
                                             success: function(zNodes) {
                                             var treeObj = $.fn.zTree.init($(str11), getSetting(str12, "attrTree", "attr"), zNodes);
                                             treeObj.expandAll(true);
                                             },
                                             dataType: "json"
                                         });
                                      }
                                },"json");
                        }

                $(document).ready(function() {
                    var url = contextPath + "/partCon/getPartzTreeList";
                    $.get(url, null,
                    function(zNodes) {
                        $.fn.zTree.init($("#partTree"), getSetting1("#compontentP", "partTree", "part"), zNodes);
                    },
                    "json");
                    $("#nullifyAppNo").click(function() {
                                               nullifyAppNo();
                    });
              getPkList({  url: contextPath + "/pklist/getBmodule",
                 id: "Bmodule",
                  async: false
                 });
                $('#operateType').change(function(){
                        $("#eventType").val("");
                        $("#eventDesc").val("");
                        $("#prodType").val("");
                        $("#prodDesc").val("");
                        $("#newEventType").val("");
                        $("#newEventDesc").val("");
                        $("#eventClass").select2().val("").trigger("change");
                        $("#processMethod").select2().val("").trigger("change");
                        $("#status").select2().val("A").trigger("change");
                        $("#Bmodule").val("");
                          $("#tabDiv").html("");
                          $("#list").html("");
                          $("#compontentP").html("");
                          attrClassInFor();
                       if($("#operateType").val() === "add"){
                          $("#newEventDiv").show();
                         $("#prodType").attr("disabled",true);
                         $("#prodDesc").attr("disabled",true);
                         $("#eventDesc").attr("disabled",true);
                         $("#eventType").attr("disabled",true);
                         $("#Bmodule").attr("disabled",true);
                         $("#eventClass").attr("disabled",false);
                         $("#processMethod").attr("disabled",false);
                         $("#status").attr("disabled",false);
                         $("#newEventType").attr("disabled",false);
                         $("#newEventDesc").attr("disabled",false);
                       }else if($("#operateType").val() === "copy"){
                         $("#newEventDiv").show();
                         $("#prodType").attr("disabled",false);
                         $("#prodDesc").attr("disabled",false);
                         $("#eventDesc").attr("disabled",false);
                         $("#eventType").attr("disabled",false);
                         $("#Bmodule").attr("disabled",false);
                         $("#newEventType").attr("disabled",false);
                         $("#newEventDesc").attr("disabled",false);
                       }else{
                          $("#newEventDiv").hide();
                          $("#prodType").attr("disabled",false);
                          $("#prodDesc").attr("disabled",false);
                          $("#eventDesc").attr("disabled",false);
                          $("#eventType").attr("disabled",false);
                         $("#Bmodule").attr("disabled",false);
                       }
                    });
                  	$('#Bmodule').change(function(){
                        $("#processMethod").val("");
                         $("#status").val("A");
                          $("#prodType").val("");
                          $("#prodDesc").val("");
                          $("#eventType").val("");
                          $("#eventDesc").val("");
                          $("#newEventType").val("");
                          $("#newEventDesc").val("");
                          $("#tabDiv").html("");
                          $("#list").html("");
                          $("#compontentP").html("");
                          attrClassInFor();
                           $("#newEventType").attr("disabled",false);
                           $("#newEventDesc").attr("disabled",false);
                           $("#eventClass").attr("disabled",false);
                           $("#processMethod").attr("disabled",false);
                           $("#status").attr("disabled",false);
                        });
                          getPkList({  url: contextPath + "/pklist/geteventClass?Bmodule="+$("#Bmodule").val(),
                                           id: "eventClass",
                                           async: false
                                     });

                           $.get(contextPath + "/partCon/getPartzTreeList?Bmodule="+$("#Bmodule").val(),
                           function(zNodes) {
                                $.fn.zTree.init($("#partTree"), getSetting1("#compontentP", "partTree", "part"), zNodes);
                            },
                              "json");

                         $("#attrList").click(function(){
                            $("#attrList").data("contrastFlag",true);
                            var indexForEdit = layer.open({
                                 type: 2,
                                 content: contextPath + "/app/pm/prod/jsp/EventAttrList.jsp",
                                 end: function(){
                                       $("#attrList").data("contrastFlag",false);
                                  }
                            });
                            layer.full(indexForEdit);
                        });
                        $("#query").click(function() {
                            if ($("#eventType").val() === "") {
                                alert("请输入事件类型");
                                return;
                            }
                            clearAll();
                            getDesc();
                            showAttr();
                            showEventType();
                        });
	                 });
                        $(document).ready(function() {
                        	var elReg = /^[A-Z0-9\(_)]+$/;
                        	    $("#newEventType").blur(function(){
                        	    if($("#newEventType").val() === ""){
                                     alert("请输入新事件类型！！！");
                                     return;
                                   }else if(elReg.test($("#newEventType").val()) !==  true){
                                             alert("事件类型应为大写英文/数字/( _ )");
                                            $("#newEventType").val("");
                                            return;
                                   }else{
                                             var url = contextPath + "/eventType/getEventTypeKey";
                                             $.ajax({
                                                 url: url,
                                                 data: "eventType=" + $("#newEventType").val(),
                                                 success: function(json) {
                                                     if (json.retStatus === 'F') {
                                                           showMsg(json.retMsg, 'info');
                                                            $("#newEventType").val("");
                                                     } else if (json.retStatus === 'S') {
                                                          return;
                                                     }
                                                 },
                                                 dataType: "json"
                                             });
                                           }
                                });
                        });

                        function getDesc() {
                            var url = contextPath + "/eventType/getOne";
                            sendPostRequest(url, {
                                eventType: $("#eventType").val()
                            },
                            function(json) {
                                if (json.retStatus === 'F') {
                                      showMsg(json.retMsg, 'info');
                                } else if (json.retStatus === 'S') {
                                      $("#eventDesc").val(json.data.eventDesc);
                                }
                            },
                            "json");
                        }
                        function showAttr() {
                            var url = contextPath + "/eventAttr/getAttr";
                            sendPostRequest(url, {
                                eventType: $("#eventType").val()
                            },
                            function(partRet) {
                                json = eval(partRet);
                                var parts = "";
                                var div = "";
                                for (var i = 0; i < partRet.length; i++) {
                                    if (partRet[i].assembleType === "ATTR") {
                                        div = showAttrDiv(partRet[i].valueMethod, partRet[i].setValueFlag, partRet[i].attrDesc, partRet[i].seqNo, partRet[i].assembleId,"O");
                                        $("#" + partRet[i].attrClass + "D").append(div);
                                        if(partRet[i].setValueFlag === "M"){
                                         if(partRet[i].attrValue === ""||partRet[i].attrValue == null){
                                            $("#s_" + partRet[i].assembleId).val(partRet[i].attrValue);
                                            $("#s_" + partRet[i].assembleId).select2();
                                         }else{
                                            $("#s_" + partRet[i].assembleId).val(partRet[i].attrValue.split(","));
                                             $("#s_" + partRet[i].assembleId).select2();
                                         }
                                        }else{
                                        $("#s_" + partRet[i].assembleId).val(partRet[i].attrValue);
                                        }
                                    } else if (json[i].assembleType === "PART") {
                                        div = showPartDiv1(partRet[i].assembleId,  partRet[i].partDesc,partRet[i].seqNo,partRet[i].data,"O");
                                        $("#compontentP").append(div);
                                       for (var j = 0; j < partRet[i].data.length; j++) {
                                         if(partRet[i].data[j].setValueFlag === "M"){
                                              if(partRet[i].data[j].attrValue === ""|| partRet[i].data[j].attrValue == null){
                                                 $('#s_' + partRet[i].data[j].attrKey).val(partRet[i].data[j].attrValue);
                                                 $("#s_" + partRet[i].data[j].attrKey).select2();
                                              }else{
                                              $('#s_' + partRet[i].data[j].attrKey).val(partRet[i].data[j].attrValue.split(","));
                                              $("#s_" + partRet[i].data[j].attrKey).select2();
                                              }
                                            }else{
                                              $('#s_' + partRet[i].data[j].attrKey).val(partRet[i].data[j].attrValue);
                                            }
                                        }

                                    }
                                }

                                for (var i = 0; i < partRet.length; i++) {
                                    $('#s_' + partRet[i].assembleId).val(partRet[i].attrValue);
                                }
                            },
                            "json");
                        }
                       function showEventType() {
                           var url = contextPath + "/eventType/selectByPrimaryKey";
                           $.ajax({
                               url: url,
                               data: "eventType=" + $("#eventType").val(),
                               success: function(partRet) {
                                  var $eventClass =  $("#eventClass").select2();
                                  $eventClass.val(partRet.data.eventClass).trigger("change");
                                  var $processMethod = $("#processMethod").select2();
                                  $processMethod.val(partRet.data.processMethod).trigger("change");
                                  var $status = $("#status").select2();
                                  $status.val(partRet.data.status).trigger("change");
                               },
                               dataType: "json"
                           });
                       }
function nullifyAppNo() {
    layer.confirm('确认要作废吗？', function () {
        var url = contextPath + "/baseCommon/nullifyParaData";
        sendPostRequest(url, {
            transactionId: transActionId,
            reqNum:$(".breadcrumb").data("reqNum")
        }, callback_fmCompanyNullify, "json");
    });
}

function  callback_fmCompanyNullify(json) {
    if (json.success) {
     showMsg("作废成功");
         $("#nullifyAppNo").hide();
            $("#save").hide();
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}
              $(document).ready(function() {
                        $("#save").click(function() {
                                                     if($("#eventClass").val() === "" || $("#processMethod").val() === ""){
                                                                                      alert("请输入事件基础信息！");
                                                                                      return;
                                                                               }else if($("#operateType").val() === "add"){
                                                                                    if($("#newEventType").val() === "" || $("#newEventDesc").val() === ""){
                                                                                        alert("请输入新事件类型/描述！");
                                                                                        return;
                                                                                    }else{
                                                                                       var url = contextPath + "/eventType/insert";
                                                                                        $.ajax({
                                                                                            url: url,
                                                                                            async:false,
                                                                                            data:{
                                                                                               eventType: $("#newEventType").val(),
                                                                                               eventDesc: $("#newEventDesc").val(),
                                                                                               eventClass: $("#eventClass").val(),
                                                                                               processMethod: $("#processMethod").val(),
                                                                                               status: $("#status").val(),
                                                                                               reqNum:$(".breadcrumb").data("reqNum"),
                                                                                               isStandard:"Y"
                                                                                            },
                                                                                            success: function(json) {
                                                                                                 if (json.retStatus === 'F') {
                                                                                                  showMsg(json.retMsg, 'info');
                                                                                                  } else if (json.retStatus === 'S') {
                                                                                                      var url = contextPath + "/eventAttr/save";
                                                                                                      var data = [];
                                                                                                      var index = 0;
                                                                                                   $("#tabDiv").find(".attr").each(function(i) {
                                                                                                       var abc = {};
                                                                                                       abc["optionFlag"] = $(this).attr("optionFlag");
                                                                                                       abc["eventType"] = $("#newEventType").val();
                                                                                                       abc["assembleType"] = "ATTR";
                                                                                                       abc["assembleId"] = $(this).attr("id");
                                                                                                       abc["seqNo"] = $(this).attr("seqNo");
                                                                                                       abc["reqNum"]=$(".breadcrumb").data("reqNum");
                                                                                                       if($(this).attr("setValueFlag") ==="M"){
                                                                                                           if($('#s_' + abc["assembleId"]).val() == null || $('#s_' + abc["assembleId"]).val()===""){
                                                                                                                 abc["attrValue"]=" ";
                                                                                                               }else{
                                                                                                                 abc["attrValue"] = $('#s_' + abc["assembleId"]).val().join(",");
                                                                                                               }
                                                                                                       }else{
                                                                                                       abc["attrValue"] = $('#s_' + abc["assembleId"]).val();
                                                                                                       }
                                                                                                       data[index] = abc;
                                                                                                       index++;
                                                                                                   });
                                                                                                  $("#compontentP").children().each(function() {
                                                                                                      var abc = {};
                                                                                                      var formData=[];
                                                                                                      var index1 = 0;
                                                                                                      abc["optionFlag"] = $(this).attr("optionFlag");
                                                                                                      abc["eventType"] = $("#newEventType").val();
                                                                                                      abc["assembleType"] = "PART";
                                                                                                      abc["seqNo"] = $(this).attr("seqNo");
                                                                                                      abc["assembleId"] = $(this).attr("id");
                                                                                                       abc["reqNum"]=$(".breadcrumb").data("reqNum");
                                                                                                      $(this).find(".attrs").each(function() {
                                                                                                       var partAttr = {};
                                                                                                         partAttr["attrKey"] = $(this).attr("name");
                                                                                                             if($(this).attr("setValueFlag") ==="M"){
                                                                                                                 if($('#s_' + partAttr["attrKey"]).val() == null || $('#s_' + partAttr["attrKey"]).val() === ""){
                                                                                                                       partAttr["attrValue"]=" ";
                                                                                                                     }else{
                                                                                                                       partAttr["attrValue"] = $('#s_' + partAttr["attrKey"]).val().join(",");
                                                                                                                     }
                                                                                                             }else{
                                                                                                                  partAttr["attrValue"] = $('#s_' + partAttr["attrKey"]).val();
                                                                                                             }

                                                                                                         formData[index1] = partAttr;
                                                                                                           index1++;
                                                                                                       });
                                                                                                      abc["formData"] = formData;
                                                                                                      data[index] = abc;
                                                                                                      index++;
                                                                                                  });
                                                                                                      console.log("abc" + JSON.stringify(data));
                                                                                                      $.ajax({
                                                                                                          url: url,
                                                                                                          async:false,
                                                                                                          data: JSON.stringify(data),
                                                                                                          success: function(json) {
                                                                                                              if (json.retStatus === 'F') {
                                                                                                                  showMsg(json.retMsg, 'info');
                                                                                                              } else if (json.retStatus === 'S') {
                                                                                                                 $.ajax({
                                                                                                                        url: contextPath + "/ProdToJson/End",
                                                                                                                        data:{
                                                                                                                               tableNames:tableNames,
                                                                                                                               reqNum:$(".breadcrumb").data("reqNum"),
                                                                                                                               transActionId:transActionId
                                                                                                                        },
                                                                                                                        dataType: "json",
                                                                                                                        type : "POST",
                                                                                                                        success: function(json) {
                                                                                                                                if (json.retStatus === 'F') {
                                                                                                                                     showMsg(json.retMsg, 'info');
                                                                                                                                } else if (json.retStatus === 'S') {
                                                                                                                                        showMsg(json.retMsg, 'info');
                                                                                                                                         $("#save").hide();
                                                                                                                                            $("#nullifyAppNo").hide();
                                                                                                                                    }
                                                                                                                                }
                                                                                                                 });
                                                                                                              }
                                                                                                          },
                                                                                                          dataType: "json",
                                                                                                          contentType: "application/json",
                                                                                                          type: "POST"
                                                                                                      });
                                                                                                  }
                                                                                            },
                                                                                            dataType: "json",
                                                                                            type : "POST"
                                                                                          });
                                                                                    }
                                                                              }else if($("#operateType").val() === "update"){
                                                                              if($("#eventType").val()==="")
                                                                              {
                                                                                                 alert("请选择要修改的事件");
                                                                                        return;
                                                                              }
                                                                                 var url = contextPath + "/eventType/update";
                                                                                 $.ajax({
                                                                                     url: url,
                                                                                     async:false,
                                                                                     data:{
                                                                                        eventType: $("#eventType").val(),
                                                                                        eventDesc: $("#eventDesc").val(),
                                                                                        eventClass: $("#eventClass").val(),
                                                                                        processMethod: $("#processMethod").val(),
                                                                                        status: $("#status").val(),
                                                                                        reqNum:$(".breadcrumb").data("reqNum")
                                                                                     },
                                                                                     success: function(json) {
                                                                                          if (json.retStatus === 'F') {
                                                                                           showMsg(json.retMsg, 'info');
                                                                                           } else if (json.retStatus === 'S') {
                                                                                          var url = contextPath + "/eventAttr/save";
                                                                                          var data = [];
                                                                                          var index = 0;
                                                                                          $("#tabDiv").find(".attr").each(function() {
                                                                                              var abc = {};
                                                                                              abc["optionFlag"] = $(this).attr("optionFlag");
                                                                                              abc["eventType"] = $("#eventType").val();
                                                                                              abc["seqNo"] = $(this).attr("seqNo");
                                                                                              abc["assembleType"] = "ATTR";
                                                                                              abc["assembleId"] = $(this).attr("id");
                                                                                              abc["reqNum"]=$(".breadcrumb").data("reqNum");
                                                                                              if($(this).attr("setValueFlag") ==="M"){
                                                                                                  if($('#s_' + abc["assembleId"]).val() == null || $('#s_' + abc["assembleId"]).val()===""){
                                                                                                        abc["attrValue"]=" ";
                                                                                                      }else{
                                                                                                        abc["attrValue"] = $('#s_' + abc["assembleId"]).val().join(",");
                                                                                                      }
                                                                                              }else{
                                                                                              abc["attrValue"] = $('#s_' + abc["assembleId"]).val();
                                                                                              }
                                                                                              data[index] = abc;
                                                                                              index++;
                                                                                          });
                                                                                          $("#compontentP").children().each(function() {
                                                                                              var abc = {};
                                                                                              var formData=[];
                                                                                              var index1 = 0;
                                                                                              abc["optionFlag"] = $(this).attr("optionFlag");
                                                                                              abc["eventType"] = $("#eventType").val();
                                                                                              abc["seqNo"] = $(this).attr("seqNo");
                                                                                              abc["assembleType"] = "PART";
                                                                                              abc["assembleId"] = $(this).attr("id");
                                                                                               abc["reqNum"]=$(".breadcrumb").data("reqNum");
                                                                                              $(this).find(".attrs").each(function() {
                                                                                               var partAttr = {};
                                                                                                 partAttr["attrKey"] = $(this).attr("name");
                                                                                                     if($(this).attr("setValueFlag") ==="M"){
                                                                                                         if($('#s_' + partAttr["attrKey"]).val() == null || $('#s_' + partAttr["attrKey"]).val() === ""){
                                                                                                               partAttr["attrValue"]=" ";
                                                                                                             }else{
                                                                                                               partAttr["attrValue"] = $('#s_' + partAttr["attrKey"]).val().join(",");
                                                                                                             }
                                                                                                     }else{
                                                                                                          partAttr["attrValue"] = $('#s_' + partAttr["attrKey"]).val();
                                                                                                     }

                                                                                                 formData[index1] = partAttr;
                                                                                                   index1++;
                                                                                               });
                                                                                              abc["formData"] = formData;
                                                                                              data[index] = abc;
                                                                                              index++;
                                                                                          });
                                                                                          console.log("abc  = " + JSON.stringify(data));
                                                                                          $.ajax({
                                                                                              url: url,
                                                                                              data: JSON.stringify(data),
                                                                                              success: function(json) {
                                                                                                  if (json.retStatus === 'F') {
                                                                                                      showMsg(json.retMsg, 'info');
                                                                                                  } else if (json.retStatus === 'S') {
                                                                                                     $.ajax({
                                                                                                         url: contextPath + "/ProdToJson/End",
                                                                                                         data:{
                                                                                                              tableNames:tableNames,
                                                                                                              reqNum:$(".breadcrumb").data("reqNum"),
                                                                                                              transActionId:transActionId
                                                                                                         },
                                                                                                         dataType: "json",
                                                                                                         type : "POST",
                                                                                                         success: function(json) {
                                                                                                               if (json.retStatus === 'F') {
                                                                                                                        showMsg(json.retMsg, 'info');
                                                                                                               } else if (json.retStatus === 'S') {
                                                                                                                      showMsg(json.retMsg, 'info');
                                                                                                                       $("#save").hide();
                                                                                                                         $("#nullifyAppNo").hide();
                                                                                                               }
                                                                                                         }

                                                                                                     });
                                                                                                  }
                                                                                              },
                                                                                              dataType: "json",
                                                                                              contentType: "application/json",
                                                                                              type: "POST"
                                                                                          });
                                                                                       }
                                                                                    },
                                                                                    dataType: "json",
                                                                                    type : "POST"
                                                                                  });
                                                                              }else if($("#operateType").val() === "copy"){
                                                                                         if($("#newEventType").val() === "" || $("#newEventDesc").val() === ""){
                                                                                               alert("请输入新事件类型/描述！");
                                                                                               return;
                                                                                         }else{
                                                                                             var url = contextPath + "/eventType/insert";
                                                                                             var isStandard;
                                                                                             if(clickFlag===1){
                                                                                             isStandard="Y";
                                                                                             }else{
                                                                                             isStandard="N";
                                                                                             }
                                                                                              $.ajax({
                                                                                                  url: url,
                                                                                                  async:false,
                                                                                                  data:{
                                                                                                     eventType: $("#newEventType").val(),
                                                                                                     eventDesc: $("#newEventDesc").val(),
                                                                                                     eventClass: $("#eventClass").val(),
                                                                                                     processMethod: $("#processMethod").val(),
                                                                                                     status: $("#status").val(),
                                                                                                     reqNum:$(".breadcrumb").data("reqNum"),
                                                                                                     isStandard:isStandard
                                                                                                  },
                                                                                                  success: function(json) {
                                                                                                       if (json.retStatus === 'F') {
                                                                                                        showMsg(json.retMsg, 'info');
                                                                                                        } else if (json.retStatus === 'S') {
                                                                                                          var url = contextPath + "/eventAttr/copy";
                                                                                                          var data = [];
                                                                                                          var index = 0;
                                                                                                             $("#tabDiv").find(".attr").each(function() {
                                                                                                               var abc = {};
                                                                                                               abc["optionFlag"] = $(this).attr("optionFlag");
                                                                                                               abc["eventType"] = $("#newEventType").val();
                                                                                                               abc["oldEventType"] = $("#eventType").val();
                                                                                                               abc["seqNo"] = $(this).attr("seqNo");
                                                                                                               abc["assembleType"] = "ATTR";
                                                                                                               abc["assembleId"] = $(this).attr("id");
                                                                                                               abc["reqNum"]=$(".breadcrumb").data("reqNum");
                                                                                                               if($(this).attr("setValueFlag") ==="M"){
                                                                                                                   if($('#s_' + abc["assembleId"]).val() == null || $('#s_' + abc["assembleId"]).val()===""){
                                                                                                                         abc["attrValue"]=" ";
                                                                                                                       }else{
                                                                                                                         abc["attrValue"] = $('#s_' + abc["assembleId"]).val().join(",");
                                                                                                                       }
                                                                                                               }else{
                                                                                                               abc["attrValue"] = $('#s_' + abc["assembleId"]).val();
                                                                                                               }
                                                                                                               data[index] = abc;
                                                                                                               index++;
                                                                                                           });
                                                                                                               $("#compontentP").children().each(function() {
                                                                                                                   var abc = {};
                                                                                                                   var formData=[];
                                                                                                                   var index1 = 0;
                                                                                                                   abc["optionFlag"] = $(this).attr("optionFlag");
                                                                                                                   abc["eventType"] = $("#newEventType").val();
                                                                                                                   abc["seqNo"] = $(this).attr("seqNo");
                                                                                                                   abc["assembleType"] = "PART";
                                                                                                                   abc["assembleId"] = $(this).attr("id");
                                                                                                                   abc["reqNum"]=$(".breadcrumb").data("reqNum");
                                                                                                                   $(this).find(".attrs").each(function() {
                                                                                                                    var partAttr = {};
                                                                                                                      partAttr["attrKey"] = $(this).attr("name");
                                                                                                                          if($(this).attr("setValueFlag") ==="M"){
                                                                                                                              if($('#s_' + partAttr["attrKey"]).val() == null || $('#s_' + partAttr["attrKey"]).val() === ""){
                                                                                                                                    partAttr["attrValue"]=" ";
                                                                                                                                  }else{
                                                                                                                                    partAttr["attrValue"] = $('#s_' + partAttr["attrKey"]).val().join(",");
                                                                                                                                  }
                                                                                                                          }else{
                                                                                                                               partAttr["attrValue"] = $('#s_' + partAttr["attrKey"]).val();
                                                                                                                          }
                                                                                                                      formData[index1] = partAttr;
                                                                                                                        index1++;
                                                                                                                    });
                                                                                                                   abc["formData"] = formData;
                                                                                                                   data[index] = abc;
                                                                                                                   index++;
                                                                                                               });
                                                                                                           console.log("abc" + JSON.stringify(data));
                                                                                                           $.ajax({
                                                                                                               url: url,
                                                                                                               async:false,
                                                                                                               data: JSON.stringify(data),
                                                                                                               success: function(json) {
                                                                                                                   if (json.retStatus === 'F') {
                                                                                                                       showMsg(json.retMsg, 'info');
                                                                                                                   } else if (json.retStatus === 'S') {
                                                                                                                         $.ajax({
                                                                                                                             url: contextPath + "/ProdToJson/End",
                                                                                                                             data:{
                                                                                                                                     tableNames:tableNames,
                                                                                                                                     reqNum:$(".breadcrumb").data("reqNum"),
                                                                                                                                     transActionId:transActionId
                                                                                                                             },
                                                                                                                             dataType: "json",
                                                                                                                             type : "POST",
                                                                                                                             success: function(json) {
                                                                                                                                    if (json.retStatus === 'F') {
                                                                                                                                          showMsg(json.retMsg, 'info');
                                                                                                                                    } else if (json.retStatus === 'S') {
                                                                                                                                            showMsg(json.retMsg, 'info');
                                                                                                                                            $("#save").hide();
                                                                                                                                              $("#nullifyAppNo").hide();
                                                                                                                                      }
                                                                                                                                    }

                                                                                                                         });
                                                                                                                   }
                                                                                                               },
                                                                                                               dataType: "json",
                                                                                                               contentType: "application/json",
                                                                                                               type: "POST"
                                                                                                           });
                                                                                                       }
                                                                                                 },
                                                                                                 dataType: "json",
                                                                                                 type : "POST"
                                                                                               });
                                                                                         }
                                                                              }

                        });
          handlePortletTools();
        //事件类型事件 eventType
        $("#eventType").focus(function(){
           if($("#prodType").val() ===""){
            layer.open({
                type: 2,
                title:"请选择事件",
                content: contextPath+"/app/pf/jsp/eventTreeTable.jsp",
                area: [500+'px', 400+'px'],
                end: function(){
                    $("#query").click();
               }
            });
           }else{
            layer.open({
                type: 2,
                title:"请选择事件",
                content: contextPath+"/app/pf/jsp/eventTreeTable.jsp",
                area: [500+'px', 400+'px'],
                end: function(){
                    $("#query").click();
               }
            });
            }
        });
        //产品类型事件 prodType
        $("#prodType").focus(function(){
           if($("#Bmodule").val() ===""){
             alert("请选择产品类别！");
             return;
           }else{
            layer.open({
                type: 2,
                title:"请选择产品",
                content: contextPath+"/app/pf/jsp/prodTreeTable.jsp",
                area: [500+'px', 400+'px'],
                end: function(){
                                 }
                               });
                               }
                           });
                           $(".select2").select2();
                         });
                       function showMsgDuringTime(msg)
                       {
                           layer.msg(msg);
                           setTimeout(function(){
                               layer.closeAll('dialog');
                           }, 1000);
                           if(msg==="添加成功") {
                               layer.close(layer_add_index);
                           }
                           if(msg==="复制成功") {
                               layer.close(layer_add_index);
                           }
                       }