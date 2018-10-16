$(document).ready(function() {
	// 获取默认opt配置
	var opt = getDataTableOpt($("#prodList"));
	opt.stateSave=true;
	opt.processing=true;
	opt.autoWidth=false;
	opt.ajax= {
			 "url": contextPath+"/prod/getAll",
			 "type": "POST"
       	 };
	opt.columns=[
			{ "data": "prodClass", "defaultContent":"" },
			{ "data": "prodClassDesc", "defaultContent":"" },
			{ "data": "prodClassLevel", "defaultContent":""},
			{ "data": "parentProdClass", "defaultContent":""}
		];
    opt.columnDefs=[{
        "targets":3,
        "render" :function(data ,type ,row){
            if(row.parentProdClass=="CL"){
                return "贷款类产品";
            }else if(row.parentProdClass=="RB"){
                return "存款类产品";
            }else if(row.parentProdClass=="MM"){
                return "货币市场类产品";
            }else if(row.parentProdClass=="GL"){
                return "总账类产品";
            }else if(row.parentProdClass=="" || row.parentProdClass==null ||row.parentProdClass==undefined){
                return "未定义";
            }else if(row.parentProdClass=="0" ){
                return "无";
            }else{
                return data;
            }
        }
    },{
        "targets":2,
         "render":function(data ,type ,row){
            return "第"+row.prodClassLevel+"级";
        }
    }];
	//渲染tables
	drawDataTable($("#prodList"),opt);
   dataTableUi("prodList",["查询","添加","修改","删除"],["select","add","edit","delete"]);
       	$("#add").on("click",function(){
                 mbProdClassAdd('添加','mbProdClassAdd.jsp','600','400');
             });
          $("#select").on("click",function(){selectAll()});
           $("#edit").on("click",function(){mbProdClassEdit('修改','mbProdClassEdit.jsp','600','400')});
          $("#delete").on("click",function(){mb_prod_del()});
 	$('#prodList tbody').on('click', 'tr', function (e) {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        } else {
            $('#prodList').find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
    })
});
/*增加*/
function mbProdClassAdd(title,url,w,h){
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
function mbProdClassEdit(title,url,w,h){
if ($("#prodList").find(".selected").length!=1){
        showMsg('请选择一行记录！','warning');
        return;
    }
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
    	var url = contextPath+"/prod/delete";
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