$(document).ready(function() {
    //汉化预处理，从数据库拉数据
    var descMap;
    $.ajax({
        url:contextPath+"/event/getDesc",
        async:false,
        dataType: "json",
        type: "POST",
        success:function(json){
            descMap=json.data;
        }
    });
	// 获取默认opt配置
	var opt = getDataTableOpt($("#eventList"));
	opt.stateSave=true;
	opt.processing=true;
	opt.autoWidth=false;
	opt.ajax= {
			 "url": contextPath+"/eventType/getAll",
			 "type": "POST"
       	 };
	opt.columns=[
        { "data": "eventType", "defaultContent":"" },
        { "data": "eventDesc", "defaultContent":"" },
        { "data": "eventClass", "defaultContent":"" },
        { "data": "status", "defaultContent":""}
            ];
    opt.columnDefs=[{
        "targets":2,
        "render"  :function(data ,type,row){
            return descMap[row.eventClass];
        }
    },{
        "targets" :3,
        "render"  :function(data ,type ,row){
            if(row.status=="A"){
                return "有效";
            }else if(row.status=="F"){
                return "无效";
            }
        }
    }];
	//渲染tables
	drawDataTable($("#eventList"),opt);
    dataTableUi("eventList",["查询","添加","修改","删除","查看差异数据","刷新","返回"],["select","add","edit","delete","contrast","refresh","return"]);
           $("#add").hide();
           $("#edit").hide();
           $("#delete").hide();
           $("#select").hide();
           $("#return").hide();
           $("#contrast").data("constrastFlag", false);
      	$("#add").on("click",function(){
                mb_event_add('添加','mbEventTypeAdd.jsp','600','400');
            });
         $("#select").on("click",function(){selectAll()});
          $("#edit").on("click",function(){mb_event_edit('修改','mbEventTypeEdit.jsp','600','400')});
         $("#delete").on("click",function(){mb_event_del()});
 	$('#eventList tbody').on('click', 'tr', function (e) {
       mb_event_edit('修改','EventAttrList1.jsp','800','500');
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        } else {
            $('#eventList').find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
    });
    $("#refresh").on("click",function(){
        selectAll();
    });
    $("#contrast").on("click",function(){
        mb_event_contrast();
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
function mb_event_add(title,url,w,h){
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
function mb_event_edit(title,url,w,h){
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
function mb_event_del(){
if ($("#eventList").find(".selected").length!==1){
            showMsg('请选择一行记录！','warning');
            return;
        }

        layer.confirm('确认要删除吗？',function(){
              eventDel();
            });
}
function eventDel(){
    	var url = contextPath+"/eventType/delete";
    	var rowData = $('#eventList').DataTable().rows(".selected").data()[0];  //已经获取数据
    	sendPostRequest(url,rowData	, callback_eventDel,"json");                //将获取数据发送给后台处理
    }

    function callback_eventDel(json){
    	if (json.retStatus == 'F'){
    	   	showMsg(json.retMsg,'info');
    	} else if(json.retStatus == 'S'){
    		showMsg(json.retMsg,'info');
    	}
        selectAll();
    }

function selectAll(){
    $("#contrast").data("constrastFlag", false);
  	var eventTab = $("#eventList").dataTable();
    var api = eventTab.api();
    api.ajax.url(contextPath+"/eventType/getAll").load();
}

function mb_event_contrast(){
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();
    $("#contrast").data("constrastFlag", true);
    var eventTab = $("#eventList").dataTable();
    var api = eventTab.api();
    api.ajax.url(contextPath+"/eventType/getAllContrast").load();
}
