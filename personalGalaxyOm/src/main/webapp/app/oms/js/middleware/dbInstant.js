$(document).ready(function () {
    var opt = getDataTableOpt($("#dbInfoList"));
	opt.stateSave = true;
    opt.processing = true;
    opt.autoWidth = false;
    opt.order = [[ 1, 'asc']];
    opt.orderMulti = false;
    opt.columns = [
            {
                "data": "midwareName",
                "defaultContent": ""
            },
            {
                "data": "serIp",
                "defaultContent": ""
            },
            {
                "data": "dbTypeName",
                "defaultContent": ""
            },
            {
                "data": "dbintName",
                "defaultContent": ""
            },
            {
                "data": "dbintUserName",
                "defaultContent": ""
            },
            {
                "data": "dbintCurrentStatusName",
                "defaultContent": ""
            },
            {
                "data": "healthMessage",
                "defaultContent": ""
            },
            {
                "data": "dbintNodeNum",
                "defaultContent": ""
            },
            {
                "data": "dbintPort",
                "defaultContent": ""
            },
            {
                "data": "dbintServiceName",
                "defaultContent": ""
            }
        ];
    //渲染tables
    drawDataTable($("#dbInfoList"), opt);
    //界面美化tables
    $("#dbInfoList").beautyUi({
        tableId: "dbInfoList",
         needBtn: false
    });
//获取db集群
	getPkList({
		url:contextPath+"/findMidwareDefaultCombox",
		id:"queryMidwareId",
		async:false,
		normalSelect:false,
	    params: {
                 midwareType: '0006003'

              }
	});
});


    function exection(){
        var midwareId = $("#queryMidwareId").val();
        var userTab = $("#dbInfoList").dataTable();
        var api = userTab.api();
        api.ajax.url(contextPath + "/findDbIntByMidwareId?midwareId=" + midwareId).load();
    }