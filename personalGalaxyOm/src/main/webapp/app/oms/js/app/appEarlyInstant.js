   var  isSuccess = "001";//“001”默认初始值“002”：成功部署标志“003”部署出错返回标志
   var isFinish=false;
   var message="";
    var jsonObj ;
   var interval;
   var intantIdString = new Array();
   var tempVerinfo = new Array();
   var sub_url;
   var isGo =true;
 $(document).ready(function () {
     $("#name").text(appName);
     var earlyInstantListOpt = getDataTableOpt($("#earlyInstantList"));
     earlyInstantListOpt.stateSave = true;
     earlyInstantListOpt.processing = true;
     earlyInstantListOpt.autoWidth = false;
     earlyInstantListOpt.ajax = {
 	   "url": contextPath + "/findAppIntant?appId="+appId,
 	   "type": "POST"
    };
      earlyInstantListOpt.columns = [
                 {
                     "data": "Id",
                     "defaultContent": ""
                 },
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
      earlyInstantListOpt.columnDefs=[
                  {
                  "targets": 0,
                  "orderable":false,
                  "render": function(data,type,row)
                          {
                              return "<input type='checkbox' name='checkList' value=''>";
                          }

                  }
                  ];
     //渲染tables
     drawDataTable($("#earlyInstantList"), earlyInstantListOpt);
     //界面美化tables
     $("#earlyInstantList").beautyUi({
         tableId: "earlyInstantList",
         needBtn: false
     });
//    $('#earlyInstantList tbody').on('click', 'tr', function (e) {
//         if ($(this).hasClass('selected')) {
//             $(this).removeClass('selected');
//         } else {
//             $('#earlyInstantList').find("tr").removeClass('selected');
//             $(this).addClass('selected');
//         }
//     });
     $('#earlyInstantList').on('page.dt', function (e) {
         $('#earlyInstantList').find("tr").removeClass('selected');
     });

     //dataTable 实现多选，选中时同时选中复选框
         //$('#serverInfoList tbody tr').on('click', function (e) {
         $("#earlyInstantList").find('tbody').on('click', 'tr', function (e) {

             if ($(this).hasClass('selected')) {
                 $(this).removeClass('selected');
                 $(this).find(":checkbox :first").prop("checked",false);
             } else {
                 $(this).addClass('selected');
                 $(this).find(":checkbox :first").prop("checked",true);
             }

         });
//    getPkList({
// 	   url: contextPath + "/findAppVerCombox?appId="+appId,
// 	   id: "demoAppIntantVer",
// 	   async: false
//    });
    getPkList({
 	   url: contextPath + "/findParaCombox?paraParentId=0023&&isDefault=false",
 	   id: "isRemainConfig",
 	   async: false
    });
     $.post(contextPath + '/findAppVerComboxEarly', {appId: appId}, function (result) {
               var result = eval('(' + result + ')');// reload the database data
               if (result.errorMsg) {
                   showMsg(result.errorMsg, 'error');
               }
               if (result.success) {
                  var data = result.data;
                  tempVerinfo = data;
                  var strOption="";
                  var obj=document.getElementById('demoAppIntantVer');
                  for (var i in data) {
                   //   obj.options[i]=new Option(data[i].appVerNum+"-"+data[i].appVerTypeName,data[i].appVerNum );
                      strOption+="<option value='" + data[i].appVerNum + "' selected>" + data[i].appVerNum+"-"+data[i].appVerTypeName + "</option>";
                 //     $("#demoAppIntantVer").append("<option value='" + data[i].appVerNum + "' selected>" +  data[i].appVerNum+"-"+data[i].appVerTypeName + "</option>");
                  }
                  $("#demoAppIntantVer").append(strOption);
                //   obj.options[0]=new Option("qw","ww");
                  //    obj.options[0].selected =true;
               // $('demoAppIntantVer').prop('selectedIndex', 0);
               }
           });
             $(".select2").select2();
   $("#demoAppIntantVer").change(function () {
        for (var i in tempVerinfo) {
                if($('#demoAppIntantVer').val()==tempVerinfo[i].appVerNum){
                     $('#appVerPath').val(tempVerinfo[i].appVerPath);
                     $('#appVerId').val(tempVerinfo[i].appVerId);
                     $('#appVerType').val(tempVerinfo[i].appVerType);
                }
        }
    });
 });
 //第一步部署失败回退
   function earlyFailBack() {
       isSuccess = "001";
       $.post(contextPath + '/earlyFailBack', {appId: appId}, function (result) {
           isFinish = true;
           var result = eval('(' + result + ')');// reload the database data
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
       deploy_start();
   }
 function deployRec() {
         isSuccess = "001";
         var rowAll = $('#earlyInstantList').DataTable().rows().data();
         var rowSelect = $('#earlyInstantList').DataTable().rows(".selected").data();
         if ($("#demoAppIntantVer").val() == "" || $("#isRemainConfig").val() == "") {
             showMsg('请选择升级版本和是否保留配置文件!', 'warning');
             isSuccess = "003";
             return false;
         } else if (rowAll.length != 1) {
             if (rowSelect.length != 0) {
                 if (rowSelect.length < rowAll.length) {
                     validateIntant(rowAll);
                     if (message != "") {
                         showMsg(message, 'warning');
                         message = "";
                     } else {
                         if ($("#demoAppIntantVer").val() != rowSelect[0].appIntantVer) {
                             $.each(rowSelect, function (i) {
                                 if(rowSelect[i].currentAppIntantStatus =="启动"&&rowSelect[i].appIntantStatusName =="已部署"){
                                       showMsg("该实例端口已被占用，请您到配置调整菜单下修改端口",'warning');
                                        isGo = false;
                                 }else{
                                   intantIdString[i] = rowSelect[i].appIntantId;
                                 }
                             });
                           if(isGo==true){
                                 var tempJsonObj =  getFormData("appVerForm");
                                 jsonObj = tempJsonObj + "&isRemainConfig=" + $('#isRemainConfig').val() + "&newAppIntantVer=" + $('#demoAppIntantVer').val() + "&appId=" + appId + "&intantIdString=" + JSON.stringify(intantIdString);
                                  sub_url = contextPath + '/updateEarlyEcmAppInstant';
                                  layer.open({
                                              type: 2,
                                              content: "appEarlyInstantProgress.jsp",
                                              area: ['400px', '200px'],
                                              title: '部署应用实例进度'
                                          });
                           }

                         } else {
                             showMsg('当前版本与升级版本一样,请重新选择升级版本!', 'warning');
                             isSuccess = "003";
                         }
                     }
                 } else {
                     showMsg('在不间断升级中，不能一次选中所有实例!', 'warning');
                     isSuccess = "003";
                 }
             } else {
                 showMsg('请选择升级实例!', 'warning');
                 isSuccess = "003";
             }
         } else {
             showMsg('升级实例数量出错，请确认!', 'warning');
             isSuccess = "003";
         }
     }
 function validateIntant(temp) {
         isSuccess = "001";
         var appVerCa = "";   //存放版本号
         var intantN;
         for (var i = 0; i < temp.length; i++) {
             if (temp[i].appIntantStatusName == "尚未部署") {
                 message = message + temp[i].appIntantName + temp[i].appIntantStatusName + '<br>';
                 if(index_progress!=null) {
                     layer.close(index_progress);
                 }
             } else {
                 if (i > 0 && appVerCa != "" && appVerCa != temp[i].appIntantVer) {
                     message = message + temp[i].appIntantName + "与" + temp[intantN].appIntantName + "版本不一致，无法升级！" + '<br>';
                     isSuccess = "003";
                 }
                 if (appVerCa == "") {
                     intantN = i;
                     appVerCa = temp[i].appIntantVer;
                 }
             }
         }
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
