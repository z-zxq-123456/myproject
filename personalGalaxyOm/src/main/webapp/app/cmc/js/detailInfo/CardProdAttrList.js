$(document).ready(function() {
	// 获取默认opt配置
	var opt = getDataTableOpt($("#prodList"));
	opt.stateSave=true;
	opt.processing=true;
	opt.autoWidth=false;
	opt.ajax= {
			 "url": contextPath+"/cardProdType/getAllStatus?tableName=CMC_PRODUCT_TYPE",
			 "type": "POST",
             "async":"true",
             "dataType":"json"
    };
    opt.fnRowCallback=function( nRow, aData) {
        var value = aData.COLUMN_STATUS;
        if ( value === "N" )
        {
            $('td', nRow).addClass('danger');/*red作废/删除*/
        }
        else if ( value === "W" )
        {
            $('td', nRow).addClass('highlight');/*blue复核*/
        }
        else if ( value === "C" )
        {
            $('td', nRow).addClass('newFace');/*green录入*/
        }
    };
	opt.columns=[
			{ "data": "CARD_PRODUCT_CODE", "defaultContent":"" },
			{ "data": "CARD_PRODUCT_NAME", "defaultContent":"" },
			{ "data": "CARD_PRODUCT_TYPE", "defaultContent":"" },
			{ "data": "PUBLISH_CHANNEL", "defaultContent":""},
			{ "data": "STATUS", "defaultContent":"" },
			{ "data": "COLUMN_STATUS", "defaultContent":"" }
		];

    opt.columnDefs=[
        {
        "targets":2,
         "render" :function(data ,type ,row){
          if (row.CARD_PRODUCT_TYPE === "0"){
                return "虚户类";
            }else if (row.CARD_PRODUCT_TYPE === "1"){
               return "实户类"
           }else {
               return "";
          }
        }
    },{
        "targets":3,
        "render" :function(data ,type ,row){
            if(row.PUBLISH_CHANNEL==="1"){
                return "本行渠道";
            }else if(row.PUBLISH_CHANNEL==="2"){
                return "渠道发卡";
            }else{
                return "";
            }
        }
    },{
        "targets":4,
        "render" :function(data ,type ,row){
            if(row.STATUS === "A"){
                return "有效";
            }else if(row.STATUS === "F"){
                return "无效";
            }else{
                return "";
            }
        }
    },
     {
        "targets":5,
         "render" :function(data ,type ,row){
             if(row.COLUMN_STATUS === "Y"){
                 return "已发布";
             } else if(row.COLUMN_STATUS === "C"){
                 return "已录入";
             }else if(row.COLUMN_STATUS === "W"){
                 return "已复核";
             }else if(row.COLUMN_STATUS === "N"){
                 return "已作废";
             }else {
                 return "";
             }
         }
        }
    ];
	//渲染tables
	drawDataTable($("#prodList"),opt);
    dataTableUi("prodList",["查看差异数据","刷新","返回"],
                           ["contrast","refresh","return"]);

    $('#prodList tbody').on('click', 'tr', function (e) {
        mb_prod_edit('查看','CardProdAttrListDetail.jsp','1000','600');
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        } else {
            $('#prodList').find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
    });
    $('#prodList').on('page.dt', function (e) {
        $('#prodList').find("tr").removeClass('selected');
    });

    $("#refresh").on("click",function(){
        selectAll();
    });
    $("#contrast").on("click",function(){
        mb_prod_contrast();
     });
     $("#return").on("click",function(){
        $("#contrast").show();
        $("#refresh").show();
        $("#return").hide();
        selectAll();
     });
});

/*修改*/
function mb_prod_edit(title,url,w,h){
    layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px'],
        end: function(){
            $("#contrast").show();
            $("#refresh").show();
            $("#return").hide();
        }
    });
}
function selectAll(){
    $("#contrast").data("constrastFlag", false);
  	var prodTab = $("#prodList").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/cardProdType/getAllStatus?tableName=CMC_PRODUCT_TYPE").load();
}

function mb_prod_contrast(){
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();
    $("#contrast").data("constrastFlag", true);
    var eventTab = $("#prodList").dataTable();
    var api = eventTab.api();
    api.ajax.url(contextPath+"/cardProdType/getAllContrast?tableName=CMC_PRODUCT_TYPE").load();
}

