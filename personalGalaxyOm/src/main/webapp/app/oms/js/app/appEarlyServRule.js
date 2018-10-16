 var  isSuccess = "001";//“001”默认初始值“002”：成功部署标志“003”部署出错返回标志
 var layer_add_index ;
 var jsonObj ;
 var sub_url;
$(document).ready(function () {
    var earlyServRuleListopt = getDataTableOpt($("#earlyServRuleList"));
           earlyServRuleListopt.stateSave = true;
           earlyServRuleListopt.processing = true;
           earlyServRuleListopt.autoWidth = false;
           earlyServRuleListopt.ajax = {
               "url": contextPath + "/findEarlyRule?appId="+appId,
               "type": "POST"
           };
           earlyServRuleListopt.columns = [
               {
                   "data": "appSerName",
                   "defaultContent": ""
               },
               {
                   "data": "appSerClsnm",
                   "defaultContent": ""
               },
               {
                   "data": "serMtdCnm",
                   "defaultContent": ""
               },
               {
                   "data": "serMtdEnm",
                   "defaultContent": ""
               },
               {
                   "data": "routerCondName",
                   "defaultContent": ""
               },
               {
                   "data": "servRuleSelf",
                   "defaultContent": ""
               }

           ];
           //渲染tables
           drawDataTable($("#earlyServRuleList"), earlyServRuleListopt);
           //界面美化tables
           $("#earlyServRuleList").beautyUi({
               tableId: "earlyServRuleList",
               buttonId: ["add1", "delete1"],
               buttonName: ["新增", "删除"]
           });
           $('#earlyServRuleList tbody').on('click', 'tr', function (e) {
               if ($(this).hasClass('selected')) {
                   $(this).removeClass('selected');
               } else {
                   $('#earlyServRuleList').find("tr").removeClass('selected');
                   $(this).addClass('selected');
               }
           });
            $('#earlyServRuleList').on('page.dt',  function (e) {
                       $('#earlyServRuleList').find("tr").removeClass('selected');
                   });
            $(".select2").select2();

        if(flag==0){
           $("#add1").on("click", function () {
               layer_add_index = layer.open({
                   type: 2,
                   content: "appEarlyServRuleAdd.jsp?id="+appId,
                   title: "添加",
                   area: ['100%', '400px'],
                   end: function () {
                               }
               });
           });
           $("#delete1").on("click", function () {
               var rowSelect = $('#earlyServRuleList').DataTable().rows(".selected").data();
               if (rowSelect.length != 1) {
                   showMsg('请选择一行记录！', 'warning');
                   return;
               }
                 var layer_delete_index =layer.confirm('确认要删除吗？', function () {
                   var url = contextPath + "/deleteEcmAppValRule";
                   var rowData = $('#earlyServRuleList').DataTable().rows(".selected").data()[0];  //已经获取数据
                   sendPostRequest(
                           url,
                           {appValruleId:rowData.appValruleId,appId:appId,servRuleId:rowData.servRuleId,appValruleType:rowData.appValruleType},
                           function (json) {
                               if (json.success) {
                                 // $('#appList').dataTable().api().row(".selected").remove().draw(false);
                                //    showMsgDuringTime('删除成功！');
                                  location.replace(location.href);
                                 //   selecttable_data();
                               } else if (json.errorMsg) {
                                   showMsg(json.errorMsg, 'errorMsg');
                               }
                               selecttable_data();
                           },
                           "json"
                   );

               });
           });
        }
 });
 //第三步验证失败回退
    function earlyBackRec() {
    isSuccess = "001";
         sub_url = contextPath + '/earlyBackEcmAppInstant';
         jsonObj = "&appId=" + appId;
        index_progress = layer.open({
            type: 2,
            content: "appEarlyBackRecProgress.jsp",
            area: ['400px', '200px'],
            title: '停止先启动所有实例进度'
        });
    }
    function showMsgDuringTime(msg) {
            layer.msg(msg);
            setTimeout(function () {
                layer.closeAll('dialog');
            }, 1000);
            if (msg == "删除成功") {
                  layer.close(layer_delete_index);
            }
            if (msg == "添加成功") {
                  layer.close(layer_add_index);
                  location.replace(location.href);
             }
            layer.close(index_progress);
        }
    //异步获取规则查询信息
       function selecttable_data() {
           var dataTab = $("#earlyServRuleList").dataTable();
           var api = dataTab.api();
           api.ajax.reload();
       }
 function getSubUrl() {
     return sub_url;
 }
 function getJsonObj() {
     return jsonObj;
 }
 function setError() {
     isSuccess = "003";
 }
 function setSuccess() {
     isSuccess = "002";
 }