$(document).ready(function () {
    var opt = getDataTableOpt($("#serverInfoList"));
	opt.stateSave = true;
    opt.processing = true;
    opt.autoWidth = false;
    opt.order = [[ 1, 'asc']];
    opt.orderMulti = false;
    opt.columns = [
        {
            "data": "Id",
            "defaultContent": ""
        },
        {
            "data": "zkSerName",
            "defaultContent": ""
        },
        {
            "data": "zkSerTypeName",
            "defaultContent": ""
        },
        {
            "data": "zkIpPort",
            "defaultContent": ""
        },
        {
            "data": "zkReversion",
            "defaultContent": ""
        },
        {
            "data": "zkSerGrop",
            "defaultContent": ""
        },
        {
            "data": "zkSerStatusName",
            "defaultContent": ""
        }
    ];
    opt.columnDefs=[
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
    drawDataTable($("#serverInfoList"), opt);
    //界面美化tables
    $("#serverInfoList").beautyUi({
        tableId: "serverInfoList",
        buttonName:[ "启用" , "禁用"],
        buttonId:["enable", "disable"]
    });
    //dataTable 实现多选，选中时同时选中复选框
    //$('#serverInfoList tbody tr').on('click', function (e) {
    $("#serverInfoList").find('tbody').on('click', 'tr', function (e) {

        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
            $(this).find(":checkbox :first").prop("checked",false);
        } else {
            $(this).addClass('selected');
            $(this).find(":checkbox :first").prop("checked",true);
        }

    });
//获取zk集群
	getPkList({
		url:contextPath+"/findMidwareDefaultCombox",
		id:"queryMidwareId",
		async:false,
		normalSelect:false,
	    params: {
                 midwareType: '0006002'

              }
	});
//获取dubbo类型
     getPkList({
        url: contextPath + "/findParaCombox",
        id: "queryZkSerType",
        async: false,
        params: {
            paraParentId: '0062',
            isDefault: false
        }
    });
    //获取ip地址
    getPkList({
        url: contextPath + "/findSerComboxIp",
        id: "queryZkId",
        async: false
    });
   // getPkListByDefault();
    $(".select2").select2();
        $("#select").click(function () {
               exection();
        });
        $("#enable").click(function () {
              var rows = $("#serverInfoList").DataTable().rows(".selected").data();
        	  var sub_url = contextPath + '/enableZkSers';
        	  var message = "该服务已经是有效的，不能启动!!";
        	  var type = "无效";
        	  enOrDisableZkSers(rows,sub_url,message,type);
         });
         $("#disable").click(function () {
              var rows = $("#serverInfoList").DataTable().rows(".selected").data();
         	  var sub_url= contextPath + '/disableZkSers';
         	  var message = "该服务已经是无效的，不能禁用!";
         	  var type = "有效";
         	  enOrDisableZkSers(rows,sub_url,message,type);
         });
});

function selecttable_data() {
    var userTab = $("#serverInfoList").dataTable();
    var api = userTab.api();
    api.ajax.reload();
}

function enOrDisableZkSers(rows,sub_url,message,type){
    var  flag = 2; //是否继续执行的标志 2.中断此次执行，1.继续执行
    var zkIpPortString = new Array();
    var zkSerNameString = new Array();
    var zkSerGropString = new Array();
    var zkSerTypeString = new Array();
    var zkVersionString = new Array();
     if(rows.length !=0){
           $.each(rows,function(i){
           if(rows[i].zkSerStatusName!=type){
              showMsg(message,'warning');
              $("#serverInfoList").find('tbody').find("tr").each(function(){
              if ($(this).hasClass('selected')) {
                    $(this).removeClass('selected');
                    $(this).find(":checkbox :first").prop("checked",false);
              }
              });
              return ;
          	}
          	flag=1;
            zkIpPortString[i]=rows[i].zkIpPort;
            zkSerNameString[i]=rows[i].zkSerName;
            zkSerGropString[i]=rows[i].zkSerGrop;
            zkSerTypeString[i]=rows[i].zkSerType;
            zkVersionString[i]=rows[i].zkVersion;
        });
         if (flag == 2){
            return ;
         }else{
              var jsonObj = "&midwareId="+$('#queryMidwareId').val()+"&zkIpPort="+JSON.stringify(zkIpPortString)+"&zkSerName="+JSON.stringify(zkSerNameString)+"&zkSerGrop="+JSON.stringify(zkSerGropString)+"&zkSerType="+JSON.stringify(zkSerTypeString)+"&zkVersion="+JSON.stringify(zkVersionString);
              $.post(sub_url,jsonObj,function(result){
                    selecttable_data();
              });
          }
    }else{
        showMsg('请选择一条记录!','warning');
    }
}


    function exection(){
            var midwareId = $("#queryMidwareId").val();
            var zkSerType = $("#queryZkSerType").val();
            if($("#queryZkId option:selected").text()=="请选择"){
                    var zkIp="";
                 }else{
                    // zkIp=$('#queryZkId').combobox('getText');
                    var  zkIp = $("#queryZkId option:selected").text();
                 }
            var zkSerName = $("#queryZkSerName").val();
            var userTab = $("#serverInfoList").dataTable();
            var api = userTab.api();
            api.ajax.url(contextPath + "/findZkSers?midwareId=" + midwareId + "&zkSerType=" + zkSerType + "&zkIp=" + zkIp + "&zkSerName=" + zkSerName).load();
    }