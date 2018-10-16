 var  isSuccess = "001";//“001”默认初始值“002”：成功部署标志“003”部署出错返回标志
 var appIntantId = $("#appIntantId").val();
 var layer_add_index, layer_edit_index;
 var process_jsonObj ;
 var process_url;
$(document).ready(function () {
   // 获取默认earlyConfigListOpt配置
          var earlyConfigListOpt = getDataTableOpt($("#earlyConfigList"));
          earlyConfigListOpt.stateSave = true;
          earlyConfigListOpt.processing = true;
          earlyConfigListOpt.autoWidth = false;
          earlyConfigListOpt.ajax = {
              "url": contextPath + "/findEcmAppFileConfig",
              "type": "POST"
          };
          earlyConfigListOpt.columns = [
              {
                  "data": "fileName",
                  "defaultContent": ""
              },
              {
                  "data": "configKey",
                  "defaultContent": ""
              },
              {
                  "data": "configValue",
                  "defaultContent": ""
              }
          ];
          //渲染tables
          drawDataTable($("#earlyConfigList"), earlyConfigListOpt);
          //界面美化tables
          $("#earlyConfigList").beautyUi({
              tableId: "earlyConfigList",
              buttonId: ["edit", "checkPort"],
              buttonName: ["修改", "校验端口"]
          });
           getPkList({
                url: contextPath + "/findEarlyComboxAppIntant?appId="+appId,
                id: "appIntantId",
                async: false
            });
          $('#earlyConfigList tbody').on('click', 'tr', function (e) {
              if ($(this).hasClass('selected')) {
                  $(this).removeClass('selected');
              } else {
                  $('#earlyConfigList').find("tr").removeClass('selected');
                  $(this).addClass('selected');
              }
          });

          $('#earlyConfigList').on('page.dt', function (e) {
              $('#earlyConfigList').find("tr").removeClass('selected');
          });
         $(".select2").select2();
         if(flag==0){//如果是当前页面，点击按钮生效
           $("#checkPort").on("click", function () {
               checkOriginalProtearly();
           });
           $("#edit").on("click", function () {
               if ($("#earlyConfigList").find(".selected").length != 1) {
                   showMsg('请选择一行记录！', 'warning');
                   return;
               }
              layer_edit_index = layer.open({
                   type: 2,
                   content: "appEarlyConfigFileEdit.jsp",
                   title: "修改",
                   area: ['600px', '400px'],
                   end: function () {

                   }
               });
              });
          }
});

    function searchEarlyConfig() {
        var appIntantId = $("#appIntantId").val();
        var fileName = $("#fileName").val();
        if (appIntantId == "") {
            showMsg('应用实例名称不能为空！', 'warning');
        } else if (fileName == "") {
            showMsg('配置文件名称不能为空！', 'warning');
        } else {
            var userTable = $("#earlyConfigList").dataTable();
            var userApi = userTable.api();
            userApi.ajax.url(contextPath + "/findEcmAppFileConfig?appIntantId=" + appIntantId + "&fileName=" + fileName).load();
        }
    }
     function startEarlyApp() {
             isSuccess = "001";
             process_jsonObj = "&appId=" + appId;
             process_url = contextPath + '/startEarlyEcmAppInstant';
             index_progress = layer.open({
                        type: 2,
                        content: "appEarlyConfigProgress.jsp",
                        area: ['400px', '200px'],
                        title: '先升级应用实例启动进度'
                    });
//            $.post(sub_url, jsonObj, function (result) {
//                isFinish = true;
//                var result = eval('(' + result + ')');
//                if (result.errorMsg) {
//                    if(index_progress!=null) {
//                        layer.close(index_progress);
//                    }
//                    showMsg(result.errorMsg, 'error');
//                    isSuccess = "003";
//                }
//                if (result.success) {
//                    isSuccess = "002";
//                    var configTab = $("#earlyConfigList").dataTable();
//                    var configApi = configTab.api();
//                    configApi.ajax.url(contextPath + "/findEcmAppFileConfig").load();
//                }
//            });

    }
   function checkOriginalProtearly() {
     if ($("#earlyConfigList").find(".selected").length != 1) {
              showMsg('请选择一行记录！', 'warning');
           return;
       }
          var sub_url = contextPath + '/findCheckPort';
          var row = $('#earlyConfigList').DataTable().rows(".selected").data()[0];
          if (row) {
              if (row.configKey.indexOf('port') > 0) {
                  var checkValue = row.configValue;
                  var jsonObj = "&appIntantId=" + $("#appIntantId").val() + "&port=" + checkValue;
                  $.post(sub_url, jsonObj, function (json) {
                      var json = eval('(' + json + ')');
                      if (json.errorMsg) {
                          showMsg({
                              title: 'Error',
                              msg: json.errorMsg
                          });
                      } else {
                          if (json.isConnection == "true") {
                              showMsg("该端口已被占用!", "info");
                          } else {
                              showMsg("该端口未被占用,可放心使用!", "info");
                          }
                      }
                  });
              } else {
                  showMsg("此条数据非端口配置，请重新选择!");
              }
          } else {
              showMsg("请选择一条端口配置记录！");
          }
      }
 function showMsgDuringTime(msg) {
     layer.msg(msg);
     setTimeout(function () {
         layer.closeAll('dialog');
     }, 1000);
     if (msg == "添加成功") {
         layer.close(layer_add_index);
     }
     if (msg == "编辑成功") {
         layer.close(layer_edit_index);
         searchEarlyConfig();
     }
 }
function getSubUrl() {
    return process_url;
}
function getJsonObj() {
    return process_jsonObj;
}
function setError() {
    isSuccess = "003";
}
function setSuccess() {
    isSuccess = "002";
}