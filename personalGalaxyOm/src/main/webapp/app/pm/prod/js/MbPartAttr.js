var tableNames="MB_PART_TYPE,MB_PART_ATTR";
var transActionId="NORM_PARA_COMBINE";
 $(document).ready(function() {
                 $("#newPartDiv").hide();
                $("#attrList").hide();
                   $.ajax({
                                            url: contextPath + "/ProdToJson/Start",
                                            data:{
                                            reqNum:parent.$(".breadcrumb").data("reqNum"),
                                            tableName:"PROD_NORM_PARA_COMBINE",
                                            tableNames:tableNames,
                                            tableDesc:"指标定义"
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
                    var url = contextPath + "/partCon/getzTreeList";
                    $.get(url, null,
                    function(zNodes) {
                        $.fn.zTree.init($("#attrTree"), getSetting1("#compontent", "attrTree", "attr"), zNodes);
                    },
                    "json");
                     getPkList({  url: contextPath + "/pklist/getBmodule1",
                       id: "Bmodule",
                       async: false
                      });
                     getPkList({  url: contextPath + "/pklist/getBmodule",
                         id: "busiCategory",
                         async: false,
                         normalSelect:false
                        });
                $('#operateType').change(function(){
                        $("#partType").val("");
                        $("#partDesc").val("");
                        $("#newPartType").val("");
                        $("#newPartDesc").val("");
                        $("#Bmodule").val("");
                        $("#processMethod").select2().val("").trigger("change");
                        $("#status").select2().val("A").trigger("change");
                        $("#busiCategory").val("");
                        $("#busiCategory").select2();
                        $("#compontent").html("");
                       if($("#operateType").val() === "add"){
                          $("#newPartDiv").show();
                         $("#partType").attr("disabled",true);
                         $("#partDesc").attr("disabled",true);
                         $("#Bmodule").attr("disabled",false);
                       }else if($("#operateType").val() === "copy"){
                         $("#newPartDiv").show();
                         $("#partType").attr("disabled",false);
                         $("#partDesc").attr("disabled",false);
                         $("#Bmodule").attr("disabled",false);
                       }else{
                          $("#newPartDiv").hide();
                          $("#partType").attr("disabled",false);
                          $("#partDesc").attr("disabled",false);
                          $("#Bmodule").attr("disabled",false);
                       }
                    });
              	$('#Bmodule').change(function(){
                       $("#newPartType").attr("disabled",false);
                       $("#newPartDesc").attr("disabled",false);
                       $("#processMethod").attr("disabled",false);
                       $("#status").attr("disabled",false);
                       $("#busiCategory").attr("disabled",false);
                       $("#busiCategory").select2();
                       $.get(contextPath + "/partCon/getzTreeList?attrClass="+$("#Bmodule").val(),
                       function(zNodes) {
                            var tree=$.fn.zTree.init($("#attrTree"), getSetting1("#compontent", "attrTree", "attr"), zNodes);
                            tree.expandAll(true);
                        },
                          "json");
                   	});
                  $(".select2").select2();
                  $("#nullifyAppNo").click(function() {
                                               nullifyAppNo();
                    });
                $("#attrList").click(function(){
                    $("#attrList").data("contrastFlag",true);
                    var indexForEdit = layer.open({
                         type: 2,
                         content: contextPath + "/app/pm/prod/jsp/PartAttrList.jsp",
                         end: function(){
                               $("#attrList").data("contrastFlag",false);
                          }
                    });
                    layer.full(indexForEdit);
                });
                $("#query").click(function() {
                    if ($("#partType").val() === "") {
                        alert("请输入部件类型");
                        return;
                    }
                    clearAll();
                    getDesc();
                    showAttr();
                    showPartType();
                  $(".select2").select2();
                });
                var elReg = /^[A-Z\(_)]+$/;
                    $("#newPartType").blur(function(){
                    if($("#newPartType").val() ===""){
                      alert("请输入新指标类型！！！");
                         return;
                       }else if(elReg.test($("#newPartType").val()) !==  true){
                             alert("指标类型应为大写英文/( _ )");
                            $("#newPartType").val("");
                            return;
                           }else{
                                     var url = contextPath + "/partType/getPartTypeKey";
                                     $.ajax({
                                         url: url,
                                         data: "partType=" + $("#newPartType").val(),
                                         success: function(json) {
                                             if (json.retStatus === 'F') {
                                                   showMsg(json.retMsg, 'info');
                                                    $("#newPartType").val("");
                                             } else if (json.retStatus === 'S') {
                                                  return;
                                             }
                                         },
                                         dataType: "json"
                                     });
                    }
                 });



               $("#save").click(function() {
                                           if($("#processMethod").val() === "" || $("#busiCategory").val() === ""){
                                                        alert("请输入指标基础信息！");
                                                        return;
                                                    }else if($("#operateType").val() === "update"){
                                                            var url = contextPath + "/partType/update";
                                                            $.ajax({
                                                                url: url,
                                                                data:{
                                                                   partType: $("#partType").val(),
                                                                   partDesc: $("#partDesc").val(),
                                                                   partClass: $("#Bmodule").val(),
                                                                   busiCategory:$("#busiCategory").val().join(","),
                                                                   processMethod: $("#processMethod").val(),
                                                                   status: $("#status").val(),
                                                                   reqNum:$(".breadcrumb").data("reqNum")
                                                                },
                                                                success: function(json) {
                                                                     if (json.retStatus === 'F') {
                                                                      showMsg(json.retMsg, 'info');
                                                                      } else if (json.retStatus === 'S') {
                                                                        var url = contextPath + "/partCon/insert";
                                                                        var data = [];
                                                                        $("#compontent").children().each(function(i) {
                                                                            var abc = {};
                                                                            abc["partType"] = $("#partType").val();
                                                                            abc["attrKey"] = $(this).attr("id");
                                                                            abc["flag"] = $(this).attr("optionFlag");
                                                                            abc["reqNum"]=$(".breadcrumb").data("reqNum");
                                                                            data[i] = abc;
                                                                        });
                                                                        console.log("data  = " + JSON.stringify(data));
                                                                        $.ajax({
                                                                            url: url,
                                                                            data:JSON.stringify(data),
                                                                            success: function(json) {
                                                                                if (json.retStatus === 'F') {
                                                                                    showMsg(json.retMsg, 'info');
                                                                                } else if (json.retStatus === 'S') {
                                                                                 $.ajax({
                                                                                         url: contextPath + "/ProdToJson/End",
                                                                                         data:{
                                                                                              reqNum:$(".breadcrumb").data("reqNum"),
                                                                                              tableNames:tableNames,
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
                                                    }else if($("#operateType").val() === "add"){
                                                        if($("#newPartType").val() === "" || $("#newPartDesc").val() === ""){
                                                            alert("请输入新指标类型/描述！");
                                                            return;
                                                        }else{
                                                              var url = contextPath + "/partType/insert";
                                                              $.ajax({
                                                                  url: url,
                                                                  async:false,
                                                                  data:{
                                                                     partType: $("#newPartType").val(),
                                                                     partDesc: $("#newPartDesc").val(),
                                                                     partClass: $("#Bmodule").val(),
                                                                     busiCategory: $("#busiCategory").val().join(","),
                                                                     processMethod: $("#processMethod").val(),
                                                                     status: $("#status").val(),
                                                                     reqNum:$(".breadcrumb").data("reqNum")
                                                                  },
                                                                  success: function(json) {
                                                                       if (json.retStatus === 'F') {
                                                                        showMsg(json.retMsg, 'info');
                                                                        } else if (json.retStatus === 'S') {
                                                                                  var url = contextPath + "/partCon/insert";
                                                                                  var data = [];
                                                                                  var index = 0;
                                                                                  $("#compontent").children().each(function() {
                                                                                     var abc = {};
                                                                                     abc["flag"] = $(this).attr("optionFlag");
                                                                                     abc["partType"] = $("#newPartType").val();
                                                                                     abc["attrKey"] = $(this).attr("id");
                                                                                     abc["reqNum"]=$(".breadcrumb").data("reqNum");
                                                                                     data[index] = abc;
                                                                                     index++;
                                                                                  });
                                                                                     console.log("abc  = " + JSON.stringify(data));
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
                                                    }else if($("#operateType").val() === "copy"){
                                                         if($("#newPartType").val() === "" || $("#newPartDesc").val() === ""){
                                                             alert("请输入新指标类型/描述！");
                                                             return;
                                                         }else{
                                                             var url = contextPath + "/partType/insert";
                                                             $.ajax({
                                                                 url: url,
                                                                 async:false,
                                                                 data:{
                                                                    partType: $("#newPartType").val(),
                                                                    partDesc: $("#newPartDesc").val(),
                                                                    partClass: $("#Bmodule").val(),
                                                                    busiCategory: $("#busiCategory").val().join(","),
                                                                    processMethod: $("#processMethod").val(),
                                                                    status: $("#status").val(),
                                                                    reqNum:$(".breadcrumb").data("reqNum")
                                                                 },
                                                                 success: function(json) {
                                                                      if (json.retStatus === 'F') {
                                                                       showMsg(json.retMsg, 'info');
                                                                       }else if (json.retStatus === 'S') {
                                                                         var url = contextPath + "/partCon/copy";
                                                                         var data = [];
                                                                        $("#compontent").children().each(function(i) {
                                                                             var abc = {};
                                                                             abc["partType"] = $("#newPartType").val();
                                                                             abc["attrKey"] = $(this).attr("id");
                                                                             abc["flag"] = $(this).attr("optionFlag");
                                                                             abc["reqNum"]=$(".breadcrumb").data("reqNum");
                                                                             data[i] = abc;
                                                                         });
                                                                         console.log("data  = " + JSON.stringify(data));
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
       });
                function getDesc() {
                    var url = contextPath + "/partType/getOne";
                    $.ajax({
                        url: url,
                        data: "partType=" + $("#partType").val(),
                        success: function(json) {
                            if (json.retStatus === 'F') {
                                 showMsg(json.retMsg, 'info');
                            } else if (json.retStatus === 'S') {
                                 $("#partDesc").val(json.data.partDesc);
                            }
                        },
                        dataType: "json"
                    });
                }

                function showAttr() {
                    var url = contextPath + "/partCon/getByPartType";
                    $.ajax({
                        url: url,
                         data:{
                            partType: $("#partType").val(),
                            partClass: $("#Bmodule").val()
                         },
                        success: function(partRet) {
                            var parts = "";
                            json = eval(partRet)["data"];
                            for (var i = 0; i < json.length; i++) {
                                parts = showAttrDiv1(json[i].attrKey, json[i].name,"O");
                                $("#compontent").append(parts);
                            }
                            for (var i = 0; i < json.length; i++) {
                                $('#s_' + json[i].attrKey).val(json[i].attrValue);
                            }

                        },
                        dataType: "json"
                    });
                }

                function showPartType() {
                    var url = contextPath + "/partType/selectByPrimaryKey";
                    $.ajax({
                        url: url,
                        data: "partType=" + $("#partType").val(),
                        success: function(partRet) {
                         $("#processMethod").select2().val(partRet.data.processMethod).trigger("change");
                         $("#busiCategory").val(partRet.data.busiCategory.split(","));
                         $("#busiCategory").select2();
                         $("#status").select2().val(partRet.data.status).trigger("change");
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
                    var url = contextPath + "/attrType/getTreeList";
                    $.get(url, null,
                    function(treeList) {
                        var parts = "";
                        treeList = eval(treeList);
                        for (var i = 0; i < treeList.length; i++) {
                            var div = "";
                            if (treeList[i].isClass === 'Y') {

                                div = '<dl id="' + treeList[i].treeId + '"><dt><i class="Hui-iconfont">&#xe62e;</i>' + treeList[i].treeName + '<i class="Hui-iconfont menu_dropdown-arrow" style="float:right">&#xe6d5;</i></dt>';
                            } else if (treeList[i].isClass === 'N') {
                                if (treeList[i].isFist === 'Y') {
                                    div = '<dd><ul>';
                                }
                                div = div + '<li id="' + treeList[i].treeId + '">' + treeList[i].treeName + '</li>';
                                if (treeList[i].isLast === 'Y') {
                                    div = div + '</ul></dd></dl>';
                                }
                            }
                            parts = parts + div;
                        }
                        $("#treeList").append(parts);
                    },
                    "json");

                handlePortletTools();
           //指标
                     $("#partType").focus(function(){
                        if($("#Bmodule").val() ===""){
                          alert("请选择指标分类！");
                          return;
                        }else{
                         layer.open({
                             type: 2,
                             title:"请选择产品",
                             content: contextPath+"/app/pf/jsp/partTreeTable.jsp",
                             area: [500+'px', 400+'px'],
                             end: function(){

                                  $("#query").click();
                            }
                         });
                         }
                     });

         })