$(document).ready(function() {
    var opt = getDataTableOpt($("#cmcCardNoRoleEx"));
    opt.stateSave=true;
    opt.processing=true;
    opt.autoWidth=false;
    opt.deferRender=true;
    opt.ajax = {
        "url": contextPath+"/baseComm/getAll",
        "type": "POST",
        "data":{
            "pk_value":$.session.get('infoBackUp_code'),
            "pk_name":"PARAM_NAME",
            "tableName":"CMC_CARD_NO_ROLE_EX"
        }
    };
    opt.fnRowCallback=function( nRow, aData) {
        var value = aData.OPERATE_TYPE;
        if ( value === "3" )
        {
            $('td', nRow).addClass('danger');
        }
        else if ( value === "2" )
        {
            $('td', nRow).addClass('highlight');
        }
        else if ( value === "1" )
        {
            $('td', nRow).addClass('newFace');
        }
    };

    opt.columnDefs=[
        {
            "targets": [ 0 ],
            "visible": false
        }
        ,{
            "width":"100px",
            "targets":1
        }
        ,{
            "width":"100px",
            "targets":2
        }
    ];

    opt.columns=[
        {"data": "OPERATE_TYPE", "defaultContent": ""}
        ,{ "data": "PARAM_NAME", "defaultContent":""}
        ,{ "data": "PARAM_VALUE", "defaultContent":""}
    ];

    opt.order = [[1, 'asc']];
    //渲染tables
    drawDataTable($("#cmcCardNoRoleEx"),opt);
    dataTableUi("cmcCardNoRoleEx", ["查看差异数据", "刷新","返回"], ["contrast", "refresh","return"]);

    if (parent. $(".flowStep").data("flag") !== "lookup") {
        $("#cmcCardNoRoleEx").beautyUi({
            tableId:"cmcCardNoRoleEx",
            buttonName:["添加","修改","删除" ],
            buttonId:["add","edit","delete"]
        });
    }
    $('#cmcCardNoRoleEx tbody').on('click', 'tr', function (e) {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        } else {
            $('#cmcCardNoRoleEx').find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
    });
    $('#cmcCardNoRoleEx').on('page.dt', function (e) {
        $('#cmcCardNoRoleEx').find("tr").removeClass('selected');
    });

    $("#contrast").on("click",function(){
        tran_mapping_contrast();
    });

    $("#return").on("click",function(){
        $("#refresh").show();
        initData_refresh();
    });

    $("#refresh").on("click",function(){
        selectAll_refresh();
    });
    $(".select2").select2();
});

function tran_mapping_contrast() {
    var attrTab = $("#cmcCardNoRoleEx").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=CMC_CARD_NO_ROLE_EX").load();
    $("#refresh").hide();
}

function initData_refresh() {
    selectAll_refresh();
}

function selectAll_refresh(){
    var attrTab = $("#cmcCardNoRoleEx").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/cardNoRoleEx/getAll?paramName="+parent.$("#newCardProductCode").val()).load();
}
