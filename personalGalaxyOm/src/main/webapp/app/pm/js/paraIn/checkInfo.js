$(document).ready(function() {
    //给该界面绑定参数，以便差异展示界面判定不同的情况
    $(".breadcrumb").data("statusFlag","3");
	// 获取默认opt配置
	var opt = getDataTableOpt($("#checkInfoList"));
	opt.stateSave=true;
	opt.processing=true;
	opt.autoWidth=false;
    opt.deferRender=true;
	opt.ajax= {
         "url": contextPath + "/paraAcp/findCheckOrPublish",
         "data":{currentStatus:2},
         "type": "POST"
     };
	opt.columns=[
        { "data": "transactionDesc",
            "defaultContent" : "" },
        { "data": "reqNo",
            "defaultContent" : "" }

    ];
	//渲染tables
	drawDataTable($("#checkInfoList"),opt);
	//界面美化tables
	$("#checkInfoList").beautyUi({
	    tableId:"checkInfoList"
    });
 	$('#checkInfoList tbody').on('click', 'tr', function (e) {
        //对数据表格该行进行标记
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        } else {
            $('#checkInfoList').find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
        differentShow("待复核参数详细信息");
    });
    $('#checkInfoList').on('page.dt', function (e) {
        $('#checkInfoList').find("tr").removeClass('selected');
    });
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
});

//异步获取规则查询信息
function selectList(){
    var userTab = $("#checkInfoList").dataTable();
    var api = userTab.api();
    api.ajax.reload();
}
var checkInfo_index;
//差异信息展示页面弹出函数
function differentShow(title){
    var trValue=$('#checkInfoList tr:eq(1) td:eq(0)').html();
    if( trValue == "No data available in table"){
        $('#checkInfoList').find('tr').eq(1).removeClass('selected');
        showMsg("无待复核信息！");
        return;
    }
    checkInfo_index=layer.open({
        type: 2,
        content: "differentShow.jsp",
        title:title,
        end: function(){
        	var userTab = $("#checkInfoList").dataTable();
            var api = userTab.api();
            api.ajax.reload();
            selectList();
        }
    });
    layer.full(checkInfo_index);
}
function showMsgDuringTime(msg)
{
    layer.msg(msg);
    setTimeout(function(){
        layer.closeAll('dialog');
    }, 1000);
    layer.close(checkInfo_index);
}

