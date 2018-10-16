
$(document).ready(function() {
    var opt = getDataTableOpt($("#cmcProductChannel"));
    opt.stateSave=true;
    opt.processing=true;
    opt.autoWidth=false;
    opt.deferRender=true;
    opt.ajax= {
        "url": contextPath + "/baseComm/getAll",
        "type": "POST",
        "data": {
            "pk_value": $.session.get('infoBackUp_code'),
            "pk_name": "CARD_PRODUCT_CODE",
            "tableName": "CMC_CARD_PRODUCT_CHANNEL"
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
        ,{
            "width":"100px",
            "targets":3
        }
    ];

    opt.columns=[
          {"data": "OPERATE_TYPE", "defaultContent": ""}
        ,{ "data": "CARD_PRODUCT_CODE", "defaultContent":""}
        ,{ "data": "LIMIT_CHANNEL", "defaultContent":""}
        ,{ "data": "AUTH_TRAN_TYPE", "defaultContent":""}
    ];

    opt.order = [[1, 'asc']];
    //渲染tables
    drawDataTable($("#cmcProductChannel"),opt);
    dataTableUi("cmcProductChannel", ["查看差异数据", "刷新","返回"], ["contrast", "refresh","return"]);

    $('#cmcProductChannel tbody').on('click', 'tr', function (e) {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        } else {
            $('#cmcProductChannel').find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
    });
    $('#cmcProductChannel').on('page.dt', function (e) {
        $('#cmcProductChannel').find("tr").removeClass('selected');
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
    var attrTab = $("#cmcProductChannel").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=CMC_CARD_PRODUCT_CHANNEL").load();
    $("#refresh").hide();
}

function initData_refresh() {
    selectAll_refresh();
}

function selectAll_refresh(){
    var attrTab = $("#cmcProductChannel").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/cardProductChannel/getAll?cardProductCode="+parent.$("#newCardProductCode").val()).load();
}




   