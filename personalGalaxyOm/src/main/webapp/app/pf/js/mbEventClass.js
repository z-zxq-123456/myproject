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
			 "url": contextPath+"/event/getAll",
			 "type": "POST"
       	 };
	opt.columns=[
			{ "data": "eventClass",
			  "defaultContent":""},
			{ "data": "eventClassDesc",
			  "defaultContent":"" },
			{ "data": "eventClassLevel" ,
			 "defaultContent":""},
			{ "data": "parentEventClass",
			     "defaultContent":""
			}
		];
    opt.columnDefs=[{
         "targets":2,
         "render":function(data ,type ,row){
            return "第"+row.eventClassLevel+"级";
        }
    }]
	//渲染tables
	drawDataTable($("#eventList"),opt);
    dataTableUi("eventList",["查询","添加","修改","删除"],["select","add","edit","delete"]);
    	$("#add").on("click",function(){
              mbEventClassAdd('添加','mbEventClassAdd.jsp','600','400');
          });
       $("#select").on("click",function(){selectAll()});
        $("#edit").on("click",function(){mbEventClassEdit('修改','mbEventClassEdit.jsp','600','400')});
       $("#delete").on("click",function(){mb_event_del()});
 	$('#eventList tbody').on('click', 'tr', function (e) {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        } else {
            $('#eventList').find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
    })
});
/*增加*/
function mbEventClassAdd(title,url,w,h){
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
function mbEventClassEdit(title,url,w,h){
if ($("#eventList").find(".selected").length!=1){
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
function mb_event_del(){
if ($("#eventList").find(".selected").length!=1){
            showMsg('请选择一行记录！','warning');
            return;
        }

        layer.confirm('确认要删除吗？',function(){
              eventDel();
            });
}
function eventDel(){
    	var url = contextPath+"/event/delete";
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
  	var eventTab = $("#eventList").dataTable();
    var api = eventTab.api();
    api.ajax.reload();
}
