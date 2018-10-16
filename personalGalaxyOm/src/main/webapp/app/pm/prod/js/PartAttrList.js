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
			 "url": contextPath+"/partType/getAll",
			 "type": "POST"
       	 };
    opt.columnDefs=[{
        "targets" : 2,
        "render"  : function(data, type ,row){
            return descMap[row.partClass];
        }
    },{
        "targets" :3,
        "render" :function (data ,type ,row){
            if(row.status == "F"){
                return "无效";
            }else if(row.status == "A"){
                return "有效";
            }else{
                return data;
            }
        }
    },{
        "targets":4,
        "render" :function (data ,type ,row){
            if(row.isStandard == "Y"){
                return "是";
            }else if(row.isStandard == "N"){
                return "否";
            }
        }
    }];
	opt.columns=[
        { "data": "partType", "defaultContent":"" },
        { "data": "partDesc", "defaultContent":"" },
        { "data": "partClass", "defaultContent":"" },
        { "data": "status", "defaultContent":""},
        { "data" :"isStandard","defaultContent":""}
    ];
	//渲染tables
	drawDataTable($("#partList"),opt);
    dataTableUi("partList",["查询","添加","修改","删除","查看差异数据","刷新","返回"],["select","add","edit","delete","contrast","refresh","return"]);
       $("#add").hide();
       $("#edit").hide();
       $("#delete").hide();
       $("#select").hide();
       $("#return").hide();
       $("#contrast").data("constrastFlag", false);
  	$("#add").on("click",function(){
            mb_part_add('添加','mbPartTypeAdd.jsp','600','400');
        });
     $("#select").on("click",function(){selectAll()});
      $("#edit").on("click",function(){mb_part_edit('修改','mbPartTypeEdit.jsp','600','400')});
     $("#delete").on("click",function(){mb_part_del()});
 	$('#partList tbody').on('click', 'tr', function (e) {
        mb_part_edit('修改','PartAttrList1.jsp','800','450');
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        } else {
            $('#partList').find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
    })
    $("#refresh").on("click",function(){
        selectAll();
    });
    $("#contrast").on("click",function(){
        mb_part_contrast();
     });
    $("#return").on("click",function(){
         $("#contrast").show();
         $("#refresh").show();
         $("#return").hide();
         selectAll();
    });

    //buttonStatus();
});
/*增加*/
function mb_part_add(title,url,w,h){
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
function mb_part_edit(title,url,w,h){

	layer.open({
            type: 2,
            content: url,
            area: [w+'px', h+'px'],
            end: function(){
                $("#contrast").show();
                $("#refresh").show();
                $("#return").hide();
                selectAll();
               }
            });
}

/*删除*/
function mb_part_del(){
if ($("#partList").find(".selected").length!==1){
            showMsg('请选择一行记录！','warning');
            return;
        }

        layer.confirm('确认要删除吗？',function(){
              partDel();
            });
}
function partDel(){
    	var url = contextPath+"/partType/delete";
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

    $("#contrast").data("constrastFlag", false);
  	var partTab = $("#partList").dataTable();
    var api = partTab.api();
    api.ajax.url(contextPath+"/partType/getAll").load();
}
/*查看差异数据*/
function mb_part_contrast(){
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();
    $("#contrast").data("constrastFlag", true);
    var attrTab = $("#partList").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/partType/getAllContrast" ).load();

}




