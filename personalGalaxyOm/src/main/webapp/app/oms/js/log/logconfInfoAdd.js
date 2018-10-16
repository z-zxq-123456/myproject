  var form;
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
      $(".select2").select2();
          $("#queueBatchSizeDiv").hide();
          $("#expiredTimeDiv").hide();
          $("#kfkDiv").hide();
          $("#logPlatMode").change(function () {
                      var logPlatMode = $("#logPlatMode").val();
                      if (logPlatMode == "0071001") {
                            $("#queueBatchSize").parent().parent().show();
                            $("#expiredTime").parent().parent().show();
                            $("#kkMidwareId").parent().parent().hide();
                      }else{
                            $("#queueBatchSize").parent().parent().hide();
                            $("#expiredTime").parent().parent().hide();
                            $("#kkMidwareId").parent().parent().show();
                            getPkList({
                                  url: contextPath + "/findKfkCombox",
                                  id: "kkMidwareId",
                                  async: false,
                                  params: {
                                      kfkZksId: $("#midwareZKId").val(),
                                      isDefault: false
                                  }
                           });
                      }
          });
  });
    function logconfInfoAdd() {
        var url = contextPath + "/saveEcmLogconfInfo";
        var data = formToJson(getFormData("form-logconfInfo-add"));
        var dataJson = eval('('+data+')');
        if(dataJson.midwareId==""||dataJson.logPlatMode==""||dataJson.outPrtnMode==""||dataJson.isScan=="") {
         showMsg('<span class="c-red">*</span>'+"为必选项,请选择", 'errorMsg');
         return;
        }
        if(dataJson.scanPeriod) {
        }
//        if(parseInt(dataJson.queueStartLen)<parseInt(dataJson.queueStopLen)){
//              showMsg('入库停止容量不能大于入库开始容量', 'errorMsg');
//              return;
//        }
//        if(parseInt(dataJson.queueStartLen)>parseInt(dataJson.queueLen)||parseInt(dataJson.queueStopLen)>parseInt(dataJson.queueLen)){
//              showMsg('入库停止容量,入库开始容量都必须小于队列容量', 'errorMsg');
//              return;
//        }
//        if(parseInt(dataJson.queueBatchSize)>parseInt(dataJson.queueLen)){
//              showMsg('入库批量不能大于队列容量', 'errorMsg');
//              return;
//        }
        sendPostRequest(url, getFormData("form-logconfInfo-add"), callback_logconfInfoAdd, "json");
    }

    function formToJson(data) {
    			   data=data.replace(/&/g,"\",\"");
                   data=data.replace(/=/g,"\":\"");
                   data="{\""+data+"\"}";
                   return data;
    		    }

    function callback_logconfInfoAdd(json) {
        if (json.errorMsg) {
            showMsg(json.errorMsg, 'errorMsg');
        } else if (json.success) {
              parent.showMsgDuringTime("添加成功");
        }
    }


    function logconfInfoAddCancel() {
        var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
        parent.layer.close(index);  //关闭窗口
    }