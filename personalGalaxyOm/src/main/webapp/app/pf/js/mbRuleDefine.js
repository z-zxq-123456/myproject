$(document).ready(function() {
	// 获取默认opt配置
	var opt = getDataTableOpt($("#ruleList"));
	opt.stateSave=true;
	opt.processing=true;
	opt.autoWidth=false;
	opt.ajax= {
			 "url": contextPath+"/ruleDefine/getAll",
			 "type": "POST"
       	 };
	opt.columns=[
			{ "data": "conditionId", "defaultContent":"" },
			{ "data": "conditionDesc", "defaultContent":"" },
			{ "data": "conditionRule", "defaultContent":""},
			{ "data": "status", "defaultContent":""}
		];
    opt.columnDefs=[{
        "targets":3,
         "render" :function(data ,type ,row){
             if(row.status == "A"){
                 return "有效";
             }else if(row.status =="F"){
                 return "无效";
             }
         }
    }];
	//渲染tables
	drawDataTable($("#ruleList"),opt);
    dataTableUi("ruleList",["查询","添加","修改","删除"],["select","add","edit","delete"]);
       	$("#add").on("click",function(){
                 mbRuleDefineAdd('添加','mbRuleDefineAdd.jsp','700','400');
             });
          $("#select").on("click",function(){selectAll()});
           $("#edit").on("click",function(){mbRuleDefineEdit('修改','mbRuleDefineEdit.jsp','700','400')});
          $("#delete").on("click",function(){mb_rule_del()});
 	$('#ruleList tbody').on('click', 'tr', function (e) {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        } else {
            $('#ruleList').find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
    })
});
/*增加*/
function mbRuleDefineAdd(title,url,w,h){
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
function mbRuleDefineEdit(title,url,w,h){
if ($("#ruleList").find(".selected").length!=1){
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
function mb_rule_del(){
if ($("#ruleList").find(".selected").length!=1){
            showMsg('请选择一行记录！','warning');
            return;
        }
        layer.confirm('确认要删除吗？',function(){
              ruledDel();
            });
}
function ruledDel(){
    	var url = contextPath+"/ruleDefine/delete";
    	var rowData = $('#ruleList').DataTable().rows(".selected").data()[0];  //已经获取数据
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
  	var prodTab = $("#ruleList").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}
