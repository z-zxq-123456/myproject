var  isSuccess = "001";//“001”默认初始值“002”：成功部署标志“003”部署出错返回标志
var isFinish=false;
var process_url;
var jsonObj;
$(document).ready(function () {
    // 获取默认lateInstantListOpt配置
           var lateInstantListOpt = getDataTableOpt($("#lateInstantList"));
           lateInstantListOpt.stateSave = true;
           lateInstantListOpt.processing = true;
           lateInstantListOpt.autoWidth = false;
           lateInstantListOpt.ajax = {
               "url": contextPath + "/findAppIntant?appId="+appId,
               "type": "POST"
           };
           lateInstantListOpt.columns = [
               {
                   "data": "serIp",
                   "defaultContent": ""
               },
               {
                   "data": "appIntantName",
                   "defaultContent": ""
               },
               {
                   "data": "appIntantVer",
                   "defaultContent": ""
               },
               {
                   "data": "appIntantStatusName",
                   "defaultContent": ""
               },
               {
                   "data": "currentAppIntantStatus",
                   "defaultContent": ""
               },
               {
                   "data": "healthMessage",
                   "defaultContent": ""
               },
               {
                   "data": "appWork",
                   "defaultContent": ""
               }
           ];
           //渲染tables
           drawDataTable($("#lateInstantList"), lateInstantListOpt);
           //界面美化tables
           $("#lateInstantList").beautyUi({
               tableId: "lateInstantList",
               needBtn: false
           });
 		 getPkList({
            url: contextPath + "/findParaCombox?paraParentId=0023&&isDefault=false",
            id: "lateIsRemainConfig",
            async: false
         });
          $("#appName").text(appName);
           $('#lateInstantList tbody').on('click', 'tr', function (e) {
               if ($(this).hasClass('selected')) {
                   $(this).removeClass('selected');
               } else {
                   $('#lateInstantList').find("tr").removeClass('selected');
                   $(this).addClass('selected');
               }
           });
 });

   //第四步部署失败
     function lateFailBack() {
        isSuccess = "001";
         $.post(contextPath + '/lateFailBack', {appId: appId}, function (result) {
             isFinish = true;
             var result = eval('(' + result + ')');
             if (result.errorMsg) {
                 if(index_progress!=null) {
                     layer.close(index_progress);
                 }
                 showMsg(result.errorMsg, 'error');
                 isSuccess = "003";
             }
             if (result.success) {
                 isSuccess = "002";
             }
         });
         index_progress = layer.open({
             type: 2,
             content: "appLateInstantProgress.jsp",
             area: ['400px', '200px'],
             title: '停止后启动所有实例进度'
         });
     }
  //第四步部署成功
     function editRec() {
         isSuccess = "001";
         var rows = $('#lateInstantList').DataTable().rows().data();
         process_url = contextPath + '/updateLateEcmAppInstant';
         var isRemainConfig = $("#lateIsRemainConfig").val();
         if (isRemainConfig == "") {
             showMsg('请选择是否保留配置文件', 'warning');
         } else {
                jsonObj = "&appId=" + appId+"&isRemainConfig="+isRemainConfig;
                index_progress = layer.open({
                         type: 2,
                         content: "appLateInstantProgress.jsp",
                         area: ['400px', '200px'],
                         title: '后升级应用实例部署进度'
                     });
         }
     }
function getSubUrl() {
    return process_url;
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