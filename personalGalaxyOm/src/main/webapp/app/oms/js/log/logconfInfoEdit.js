    var form;
    var rowData;
    $(document).ready(function () {

       getPkList({
              url: contextPath + "/findMidwareCombox?midwareType=0006002",
              id: "midwareZKId",
              async: false
          });
          getPkList({
                      url: contextPath + "/findParaCombox",
                      id: "logPlatMode",
                      async: false,
                      params:{paraParentId:'0071',
                                            isDefault:false }
                  });
          getPkList({
                      url: contextPath + "/findParaCombox",
                      id: "outPrtnMode",
                      async: false,
                      params:{paraParentId:'0072',
                      					  isDefault:false }
                  });
          getPkList({
                      url: contextPath + "/findParaCombox?midwareType=0009",
                      id: "isScan",
                      async: false,
                      params:{paraParentId:'0009',
                                         isDefault:false }
                  });
        if (parent.$("#table_data").find(".selected").length == 1) {
            rowData = parent.$('#table_data').DataTable().rows(".selected").data()[0];
        }
        if (rowData) {
        if (rowData.logPlatMode == "0071001") {
                       $("#queueBatchSizeDiv").show();
                       $("#expiredTimeDiv").show();
                       $("#kkMidwareIdDiv").hide();
                       $("#queueBatchSize").val(rowData.queueBatchSize);
                       $("#expiredTime").val(rowData.expiredTime);
            }else{
                  $("#kkMidwareIdDiv").show();
                  $("#kkMidwareId").val(rowData.kkMidwareId);
                  getPkList({
                          url: contextPath + "/findKfkCombox",
                          id: "kkMidwareId",
                          async: false,
                          params: {
                              kfkZksId: rowData.midwareId,
                              isDefault: false
                          }
                  });
                  $("#queueBatchSizeDiv").hide();
                  $("#expiredTimeDiv").hide();
            }
            $("#logconfId").val(rowData.logconfId);
            $("#midwareZKId").val(rowData.midwareId);
            $("#logPlatMode").val(rowData.logPlatMode);
            $("#outPrtnMode").val(rowData.outPrtnMode);
            $("#isScan").val(rowData.isScan);
            $("#scanPeriod").val(rowData.scanPeriod);
            form = $("#form-logconfInfo-edit").Validform({
                tiptype: 2,
                callback: function (form) {
                    dataEdit();
                    return false;
                }
            });
        }
        $(".select2").select2();
        $("#logPlatMode").change(function () {
                $("#queueBatchSize").val("");
                $("#expiredTime").val("");
                var logPlatMode = $("#logPlatMode").val();
                if (logPlatMode == "0071001") {
                    $("#queueBatchSizeDiv").show();
                    $("#expiredTimeDiv").show();
                    $("#kkMidwareIdDiv").hide();
                    $("#queueBatchSize").val(rowData.queueBatchSize);
                    $("#expiredTime").val(rowData.expiredTime);
                }else{
                    $("#kkMidwareIdDiv").show();
                    $("#kkMidwareId").val(rowData.kkMidwareId);
                    getPkList({
                          url: contextPath + "/findKfkCombox",
                          id: "kkMidwareId",
                          async: false,
                          params: {
                              kfkZksId: rowData.midwareId,
                              isDefault: false
                          }
                    });
                    $("#queueBatchSizeDiv").hide();
                    $("#expiredTimeDiv").hide();
                }
        });

    });

    function dataEdit() {
        var url = contextPath + "/updateEcmLogconfInfo";
        var kkMidwareId = "";
        var queueBatchSize ="";
        var expiredTime = "";
        if( $("#logPlatMode").val()!="0071001"){
            if($("#kkMidwareId").val()==""){
                 alert("请输入kafka集群");
            return;
            }else{
                 kkMidwareId = $("#kkMidwareId").val();
            }
        }else{
           queueBatchSize =  $("#queueBatchSize").val();
           expiredTime =$("#expiredTime").val();
        }
        sendPostRequest(url, {
            logconfId: $("#logconfId").val(),
            midwareId: $("#midwareZKId").val(),
            logPlatMode: $("#logPlatMode").val(),
            outPrtnMode: $("#outPrtnMode").val(),
            isScan: $("#isScan").val(),
            scanPeriod: $("#scanPeriod").val(),
            kkMidwareId:kkMidwareId,
            queueBatchSize:queueBatchSize,
            expiredTime:expiredTime,
        }, callback_dataEdit, "json");

    }

    function callback_dataEdit(json) {
        if (json.success) {
            parent.showMsgDuringTime("编辑成功");
        } else if (json.errorMsg) {
            showMsg(json.errorMsg, 'errorMsg');
        }
        //dataEditCancel();
    }
    function dataEditCancel() {
        var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
        parent.layer.close(index);  //关闭窗口
    }