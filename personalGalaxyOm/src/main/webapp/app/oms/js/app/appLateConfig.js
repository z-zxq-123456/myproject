var  isSuccess = "001";//“001”默认初始值“002”：成功部署标志“003”部署出错返回标志
var layer_add_index, layer_edit_index;
 var process_jsonObj ;
 var process_url;
$(document).ready(function () {
 // 获取默认lateConfigListOpt配置
        var lateConfigListOpt = getDataTableOpt($("#lateConfigList"));
        lateConfigListOpt.stateSave = true;
        lateConfigListOpt.processing = true;
        lateConfigListOpt.autoWidth = false;
        lateConfigListOpt.ajax = {
            "url": contextPath + "/findEcmAppFileConfig",
            "type": "POST"
        };
        lateConfigListOpt.columns = [
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
        drawDataTable($("#lateConfigList"), lateConfigListOpt);
        //界面美化tables
        $("#lateConfigList").beautyUi({
            tableId: "lateConfigList",
            buttonId: ["edit1", "checkPort1"],
            buttonName: ["修改", "校验端口"]
        });
        if(flag==0){//如果是当前页面，点击按钮生效
            $("#checkPort1").on("click", function () {
                checkOriginalProtlate();
            });
            $("#edit1").on("click", function () {
                if ($("#lateConfigList").find(".selected").length != 1) {
                    showMsg('请选择一行记录！', 'warning');
                    return;
                }
               layer_edit_index = layer.open({
                    type: 2,
                    content: "appLateConfigFileEdit.jsp",
                    title: "修改",
                    area: ['600px', '400px'],
                    end: function () {

                    }
                });
            });
        }

        $('#lateConfigList tbody').on('click', 'tr', function (e) {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
            } else {
                $('#lateConfigList').find("tr").removeClass('selected');
                $(this).addClass('selected');
            }
        });

        $('#lateConfigList').on('page.dt', function (e) {
            $('#lateConfigList').find("tr").removeClass('selected');
        });
           getPkList({
                url: contextPath + "/findLateComboxAppIntant?appId="+appId,
                id: "queryAppIntantId",
                async: false
           });
          $(".select2").select2();
 });


     function startLateApp() {
         isSuccess = "001";
         process_url = contextPath + '/startLateEcmAppInstant';
         process_jsonObj = "&appId=" + appId;
         index_progress = layer.open({
                     type: 2,
                     content: "appLateConfigProgress.jsp",
                     area: ['400px', '200px'],
                     title: '后升级应用实例部署进度'
                 });
         }
    function searchLateConfig() {
            var appIntantId = $("#queryAppIntantId").val();
            var fileName = $("#queryFileName").val();
            if (appIntantId == "") {
                showMsg('应用实例名称不能为空！', 'warning');
            } else if (fileName == "") {
                showMsg('配置文件名称不能为空！', 'warning');
            } else {
                var userTable = $("#lateConfigList").dataTable();
                var userApi = userTable.api();
                userApi.ajax.url(contextPath + "/findEcmAppFileConfig?appIntantId=" + appIntantId + "&fileName=" + fileName).load();
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
            searchLateConfig();
        }
    }
  function checkOriginalProtlate() {
          var sub_url = contextPath + '/findCheckPort';
          var row = $('#lateConfigList').DataTable().rows(".selected").data()[0];
          if (row) {
              if (row.configKey.indexOf('port') > 0) {
                  var checkValue = row.configValue;
                  var jsonObj = "&appIntantId=" + $("#queryAppIntantId").val() + "&port=" + checkValue;
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