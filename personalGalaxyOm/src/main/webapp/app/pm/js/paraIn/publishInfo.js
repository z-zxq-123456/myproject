
$(document).ready(function() {
    //给该界面绑定参数，以便差异展示界面判定不同的情况
    $(".breadcrumb").data("statusFlag","4");
	// 获取默认opt配置
	var opt = getDataTableOpt($("#publishInfoList"));
	opt.stateSave=true;
	opt.processing=true;
	opt.autoWidth=false;
    opt.deferRender=true;
	opt.ajax= {
         "url": contextPath + "/paraAcp/findCheckOrPublish",
          "data":{currentStatus:3},
          "type": "POST"
     };
	opt.columns=[
        { "data": "transactionDesc",
            "defaultContent" : "" },
        // { "data": "transactionId",
        //     "defaultContent" : "" },
        { "data": "reqNo",
            "defaultContent" : "" }
    ];
	//渲染tables
	drawDataTable($("#publishInfoList"),opt);
	//界面美化tables
	$("#publishInfoList").beautyUi({
	    tableId:"publishInfoList"
    });
 	$('#publishInfoList tbody').on('click', 'tr', function (e) {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        } else {
            $('#publishInfoList').find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
        differentShow("待发布参数详细信息");
    });
    $('#publishInfoList').on('page.dt', function (e) {
        $('#publishInfoList').find("tr").removeClass('selected');
    });
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
});

//异步获取规则查询信息
function selectUserList(){
	var userTab = $("#userList").dataTable();
    var api = userTab.api();
    api.ajax.reload();
}
var publishInfo_index;
//差异信息展示页面弹出函数
function differentShow(title){
    var trValue=$('#publishInfoList tr:eq(1) td:eq(0)').html();
    if( trValue == "No data available in table"){
        $('#publishInfoList').find('tr').eq(1).removeClass('selected');
        showMsg("无待发布信息！");
        return;
    }
    publishInfo_index=layer.open({
        type: 2,
        content: "differentShow.jsp",
        title:title,
        end: function(){
            var userTab = $("#publishInfoList").dataTable();
            var api = userTab.api();
            api.ajax.reload();
            selectList();
        }
    });
    layer.full(publishInfo_index);
}

function showMsgDuringTime(msg)
{
    layer.msg(msg);
    setTimeout(function(){
        layer.closeAll('dialog');
    }, 1000);
    layer.close(publishInfo_index);
}


//异步获取规则查询信息
function selectList(){
    var userTab = $("#publishInfoList").dataTable();
    var api = userTab.api();
    api.ajax.reload();
}