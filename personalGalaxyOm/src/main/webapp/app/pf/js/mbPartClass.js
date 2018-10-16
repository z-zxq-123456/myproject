$(document).ready(function() {
    //汉化预处理，从数据库拉数据
    var descMap;
    $.ajax({
        url:contextPath+"/part/getDesc",
        async:false,
        dataType: "json",
        type: "POST",
        success:function(json){
            descMap=json.data;
        }
    });
	// 获取默认opt配置
	var opt = getDataTableOpt($("#partList"));
	opt.stateSave=true;
	opt.processing=true;
	opt.autoWidth=false;
	opt.ajax= {
			 "url": contextPath+"/part/getAll",
			 "type": "POST"
       	 };
	opt.columns=[
			{ "data": "partClass", "defaultContent":"" },
			{ "data": "partClassDesc", "defaultContent":"" },
			{ "data": "partClassLevel", "defaultContent":"" },
			{ "data": "parentPartClass", "defaultContent":""}
		];
    opt.columnDefs=[{
        "targets" : 3,
        "render"  : function(data, type ,row){
            if(descMap[row.parentPartClass] != undefined){
                return descMap[row.parentPartClass];
            }else if(row.parentProdClass=="" || row.parentProdClass==null ||row.parentProdClass==undefined){
                return "未定义";
            }else if(row.parentPartClass == 0){
                return "无";
            }else{
                return row.parentPartClass;
            }
        }
    },{
        "targets" :2,
        "render" : function (data ,type ,row){
            return "第"+row.partClassLevel+"级";
        }
    }];
	//渲染tables
	drawDataTable($("#partList"),opt);

    dataTableUi("partList",["查询","添加","修改","删除"],["select","add","edit","delete"]);
 	$("#add").on("click",function(){
           mbPartClassAdd('添加','mbPartClassAdd.jsp','600','400');
       });
    $("#select").on("click",function(){selectAll()});
     $("#edit").on("click",function(){mbPartClassEdit('修改','mbPartClassEdit.jsp','600','400')});
    $("#delete").on("click",function(){mb_part_del()});
 	$('#partList tbody').on('click', 'tr', function (e) {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        } else {
            $('#partList').find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
    })
});
/*增加*/
function mbPartClassAdd(title,url,w,h){
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
function mbPartClassEdit(title,url,w,h){
if ($("#partList").find(".selected").length!=1){
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
function mb_part_del(){
if ($("#partList").find(".selected").length!=1){
            showMsg('请选择一行记录！','warning');
            return;
        }

        layer.confirm('确认要删除吗？',function(){
              partDel();
            });
}
function partDel(){
    	var url = contextPath+"/part/delete";
    	var rowData = $('#partList').DataTable().rows(".selected").data()[0];  //已经获取数据
    	sendPostRequest(url,rowData	, callback_partDel,"json");                //将获取数据发送给后台处理
    }

    function callback_partDel(json){
    	if (json.retStatus == 'F'){
    	   	showMsg(json.retMsg,'info');
    	} else if(json.retStatus == 'S'){
    		showMsg(json.retMsg,'info');
    	}
        selectAll();
    }

function selectAll(){
  	var partTab = $("#partList").dataTable();
    var api = partTab.api();
    api.ajax.reload();
}