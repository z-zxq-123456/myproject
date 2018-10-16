$(document).ready(function() {
    //汉化预处理，从数据库拉数据
    var descMap;
    $.ajax({
        url:contextPath+"/attr/getDesc",
        async:false,
        dataType: "json",
        type: "POST",
        success:function(json){
            descMap=json.data;
        }
    });
	// 获取默认opt配置
	var opt = getDataTableOpt($("#attrList"));
	opt.stateSave=true;
	opt.processing=true;
	opt.autoWidth=false;
	opt.ajax= {
			 "url": contextPath+"/attrType/getAll",
			 "type": "POST"
       	 };
	opt.columns=[
			{ "data": "attrKey" , "defaultContent":""},
			{ "data": "attrDesc", "defaultContent":"" },
			{ "data": "attrClass", "defaultContent":"" },
			{ "data": "useMethod", "defaultContent":""},
			{ "data": "valueMethod", "defaultContent":""},
			{ "data": "status","defaultContent":""},
			{ "data": "busiCategory", "defaultContent":""}
    ];
    opt.columnDefs=[{
        "targets" : 2,
        "render"  : function(data, type ,row){
            return descMap[row.attrClass];
        }
    },{
        "targets" : 3,
        "render"  : function( data, type, row ) {
            if(row.useMethod=="CTR"){
                return "控制类";
            }else if(row.useMethod=="EVAL"){
                return "赋值类";
            }else if(row.useMethod=="IND"){
                return "独立逻辑";
            }else if(row.useMethod=="DESC"){
                return "描述类";
            }else if(row.useMethod== undefined || row.useMethod== "" ||row.useMethod== null){
                return "";
            }
        }
    },{
        "targets" :4,
        "render"  :function(data, type ,row){
            if(row.valueMethod=="FD"){
                return "取自固定值";
            }else if(row.valueMethod=="VL"){
                return "取值列表值";
            }else if(row.valueMethod=="RF"){
                return "参数数据表";
            }else if(row.valueMethod=="YN"){
                return "取值Y或N";
            }
        }
    },{
        "targets" :5,
        "render" :function(data ,type ,row){
            if(row.status == "A"){
                return "有效";
            }else if(row.status =="F"){
                return "无效";
            }
        }
    }];
	//渲染tables
	drawDataTable($("#attrList"),opt);
    dataTableUi("attrList",["查询","添加","修改","删除"],["select","add","edit","delete"]);
    	$("#add").on("click",function(){
               mb_attr_add('添加','mbAttrTypeAdd.jsp','600','400');
           });
        $("#select").on("click",function(){selectAll()});
         $("#edit").on("click",function(){mb_attr_edit('修改','mbAttrTypeEdit.jsp','600','400')});
        $("#delete").on("click",function(){mb_attr_del()});
 	$('#attrList tbody').on('click', 'tr', function (e) {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        } else {
            $('#attrList').find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
    })
});
/*增加*/
function mb_attr_add(title,url,w,h){
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
function mb_attr_edit(title,url,w,h){
if ($("#attrList").find(".selected").length!=1){
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
function mb_attr_del(){
if ($("#attrList").find(".selected").length!=1){
            showMsg('请选择一行记录！','warning');
            return;
        }

        layer.confirm('确认要删除吗？',function(){
              attrDel();
            });
}
function attrDel(){
    	var url = contextPath+"/attrType/delete";
    	var rowData = $('#attrList').DataTable().rows(".selected").data()[0];  //已经获取数据
    	sendPostRequest(url,rowData	, callback_attrDel,"json");                //将获取数据发送给后台处理
    }

    function callback_attrDel(json){
    	if (json.retStatus == 'F'){
    	   	showMsg(json.retMsg,'info');
    	} else if(json.retStatus == 'S'){
    		showMsg(json.retMsg,'info');
    	}
        selectAll();
    }

function selectAll(){


  	var attrTab = $("#attrList").dataTable();
    var api = attrTab.api();
    api.ajax.reload();
}

