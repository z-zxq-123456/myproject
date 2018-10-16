 //汉化预处理，从数据库拉数据
    var descMap;
    $.ajax({
        url:contextPath+"/prod/getDesc",
        async:false,
        dataType: "json",
        type: "POST",
        success:function(json){
            descMap=json.data;
        }
    });
$(document).ready(function() {
	// 获取默认opt配置
	var opt = getDataTableOpt($("#prodList"));
	opt.stateSave=true;
	opt.processing=true;
	opt.autoWidth=false;
	opt.ajax= {
			 "url": contextPath+"/prodType/getComAll",
			 "type": "POST"
       	 };
	opt.columns=[
			{ "data": "prodType", "defaultContent":"" },
			{ "data": "prodDesc", "defaultContent":"" },
			{ "data": "prodClass", "defaultContent":"" },
			{ "data": "status", "defaultContent":""},
			{ "data": "prodGroup", "defaultContent":"" }

		];

    opt.columnDefs=[{
        "targets":2,
        "render" :function(data ,type ,row){
            if(descMap[row.prodClass] != undefined ){
                return descMap[row.prodClass];
            }else if(row.prodClass=="" || row.prodClass==null ||row.prodClass==undefined){
                return "";
            }else{
                return data;
            }
        }
    },{
        "targets":4,
        "render" :function(data ,type ,row){
            if(row.prodGroup=="Y"){
                return "是";
            }else if(row.prodGroup=="N"){
                return "否";
            }else if(row.prodGroup == null  || row.prodGroup == "" || row.prodGroup == undefined ){
                return "";
            }
        }
    },{
        "targets":3,
        "render" :function(data ,type ,row){
            if(row.status=="A"){
                return "有效";
            }else if(row.status=="F"){
                return "无效";
            }
        }
    }];
	//渲染tables
	drawDataTable($("#prodList"),opt);
     // dataTableUi("prodList",["查询","添加","修改","删除"],["select","add","edit","delete"]);
           	$("#add").on("click",function(){
                     mb_prod_add('添加','mbProdTypeAdd.jsp','600','400');
                 });
              $("#select").on("click",function(){selectAll()});
               $("#edit").on("click",function(){mb_prod_edit('修改','mbProdTypeEdit.jsp','600','400')});
              $("#delete").on("click",function(){mb_prod_del()});
 	$('#prodList tbody').on('click', 'tr', function (e) {
       mb_prod_edit('查看','comProdAttrList1.jsp','800','500');
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        } else {
            $('#prodList').find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
    })
});
/*增加*/
function mb_prod_add(title,url,w,h){
	layer.open({
            type: 2,
            content: url,
            area: [w+'px', h+'px'],
            end: function(){
                selectAll();
               }
            });
}

/*修改*/
function mb_prod_edit(title,url,w,h){
	layer.open({
            type: 2,
            content: url,
            area: [w+'px', h+'px'],
            end: function(){
                selectAll();
               }
            });
}

/*删除*/
function mb_prod_del(){
if ($("#prodList").find(".selected").length!=1){
            showMsg('请选择一行记录！','warning');
            return;
        }

        layer.confirm('确认要删除吗？',function(){
              prodDel();
            });
}
function prodDel(){
    	var url = contextPath+"/prodType/delete";
    	var rowData = $('#prodList').DataTable().rows(".selected").data()[0];  //已经获取数据
    	sendPostRequest(url,rowData	, callback_prodDel,"json");                //将获取数据发送给后台处理
    }

    function callback_prodDel(json){
    	if (json.retStatus == 'F'){
    	   	showMsg(json.retMsg,'info');
    	} else if(json.retStatus == 'S'){
    		showMsg(json.retMsg,'info');
    	}
        selectAll();
    }

function selectAll(){
  	var prodTab = $("#prodList").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

