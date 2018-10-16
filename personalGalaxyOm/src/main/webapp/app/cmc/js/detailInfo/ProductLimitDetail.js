$(document).ready(function() {
    	var opt = getDataTableOpt($("#cmcProductLimit"));
    	opt.stateSave=true;
    	opt.processing=true;
    	opt.deferRender=true;
        opt.scrollX=true;
        opt.ajax = {
            "url": contextPath + "/baseComm/getAll",
            "type": "POST",
            "data": {
                "pk_value": $.session.get('infoBackUp_code'),
                "pk_name": "CARD_PRODUCT_CODE",
                "tableName": "CMC_PRODUCT_LIMIT"
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
                ,{
                    "width":"100px",
                    "targets":4
                }
                ,{
                    "width":"100px",
                    "targets":5
                }
                ,{
                    "width":"100px",
                    "targets":6
                }
                ,{
                    "width":"100px",
                    "targets":7
                }
                ,{
                    "width":"100px",
                    "targets":8
                }
                ,{
                    "width":"100px",
                    "targets":9
                }
                ,{
                    "width":"100px",
                    "targets":10
                }
                ];

        opt.columns=[
                {"data": "OPERATE_TYPE", "defaultContent": ""}
                ,{ "data": "CARD_PRODUCT_CODE", "defaultContent":""}
                ,{ "data": "CHANNEL_TYPE", "defaultContent":""}
                ,{ "data": "CCY", "defaultContent":""}
                ,{ "data": "PERIOD", "defaultContent":""}
                ,{ "data": "CON_LIMIT_AMT", "defaultContent":""}
                ,{ "data": "TRAN_IN_LIMIT_AMT", "defaultContent":""}
                ,{ "data": "TRAN_OUT_LIMIT_AMT", "defaultContent":""}
                ,{ "data": "CON_LIMIT_TIME", "defaultContent":""}
                ,{ "data": "TRAN_IN_LIMIT_TIME", "defaultContent":""}
                ,{ "data": "TRAN_OUT_LIMIT_TIME", "defaultContent":""}
            ];

        opt.order = [[1, 'asc']];
    	//渲染tables
    	drawDataTable($("#cmcProductLimit"),opt);
        dataTableUi("cmcProductLimit", ["查看差异数据", "刷新","返回"], ["contrast", "refresh","return"]);

    $('#cmcProductLimit tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#cmcProductLimit').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
             });
    $('#cmcProductLimit').on('page.dt', function (e) {
            $('#cmcProductLimit').find("tr").removeClass('selected');
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
    var attrTab = $("#cmcProductLimit").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=CMC_PRODUCT_LIMIT").load();
    $("#refresh").hide();
}

function initData_refresh() {
    selectAll_refresh();
}

function selectAll_refresh(){
    var attrTab = $("#cmcProductLimit").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/cardProductLimit/getAll?cardProductCode="+parent.$("#newCardProductCode").val()).load();
}


