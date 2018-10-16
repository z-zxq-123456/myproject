var layer_add_index, layer_edit_index;

function showMsgDuringTime(msg)
{
    showMsgCloseLayer(msg,layer_add_index,layer_edit_index);
    if(msg==="添加成功") {
        selectAll_refresh();
    }
}

$(document).ready(function() {
      $(".breadcrumb").data("reqNum",parent.$(".breadcrumb").data("reqNum"));
	// 获取默认opt配置
    	var opt = getDataTableOpt($("#mbPartType"));
    	opt.stateSave=true;
    	opt.processing=true;
        opt.autoWidth=false;
    	opt.deferRender=true;
    	opt.ajax= {
    			 "url": contextPath+"/baseCommon/getList",
    			 "type": "POST",
    			 "data":{
                    "tableName":"MB_PART_TYPE",
                    "reqNum": parent.$(".breadcrumb").data("reqNum")
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
                     "targets":2,
                     "render":function( data, type, row ) {
                         return row.IS_STANDARD === "Y"?"是":"否";
                     }
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
             ];

        opt.columns=[
            {"data": "OPERATE_TYPE", "defaultContent": ""}
                ,{ "data": "PART_TYPE", "defaultContent":""}
                ,{ "data": "IS_STANDARD", "defaultContent":""}
                ,{ "data": "PART_CLASS", "defaultContent":""}
                ,{ "data": "PART_DESC", "defaultContent":""}
                ,{ "data": "BUSI_CATEGORY", "defaultContent":""}
                ,{ "data": "DEFAULT_PART", "defaultContent":""}
                ,{ "data": "PROCESS_METHOD", "defaultContent":""}
                ,{ "data": "STATUS", "defaultContent":""}
                ,{ "data": "COMPANY", "defaultContent":""}
            ];
    	//渲染tables
    	drawDataTable($("#mbPartType"),opt);
        $("#mbPartType").beautyUi({
            tableId:"mbPartType",
            buttonName:["添加", "修改","删除","提交","查看差异数据","刷新","返回" ],
            buttonId:["add",　"edit","delete","submit","contrast","refresh","return" ]
            });
        $('#mbPartType tbody').on('click', 'tr', function () {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#mbPartType').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
            });
        $('#mbPartType').on('page.dt', function () {
                $('#mbPartType').find("tr").removeClass('selected');
            });
        $("#refresh").on("click",function(){
            selectAll_refresh();
        });
        $(".select2").select2();
        buttonStatus();
    $("#selectByPrimaryKey").click(function(){
            initData_refresh();
    });

});

/*增加*/
function mb_part_type_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function mb_part_type_mod(title,url,w,h){
    if ($("#mbPartType").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer_edit_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*删除*/
function  mb_part_type_del(){
    if ($("#mbPartType").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
         mbPartTypeDel();
    });
}

function  mbPartTypeDel(){
    var paraJson,keyFieldsJson;
    paraJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateForDelete";
    var rowData = $('#mbPartType').DataTable().rows(".selected").data()[0];  //已经获取数据
    paraJson.tableName="MB_PART_TYPE";
        keyFieldsJson.PART_TYPE=rowData.PART_TYPE;
    paraJson.key = keyFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
    paraJson.status=rowData.COLUMN_STATUS;
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params, callback_mbPartTypeDel,"json");                //将获取数据发送给后台处理
}

function  callback_mbPartTypeDel(json){
    if (json.success) {
        $("#mbPartType").dataTable().api().row(".selected").remove().draw(false);
        showMsgDuringTime("删除成功！");
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function buttonStatus()
{
    $("#return").hide();
    if(parent.$(".breadcrumb").data("needButton") === "N"){
        $("#add").hide();
        $("#edit").hide();
        $("#delete").hide();
        $("#submit").hide();
        $("#contrast").on("click",function(){
            mb_part_type_contrast();
        });
        $("#return").on("click",function(){
            initData_refresh();
            $("#contrast").show();
            $("#refresh").show();
            $("#return").hide();
            $("#queryPrimaryKey").show();
            $("#add").hide();
            $("#edit").hide();
            $("#delete").hide();
            $("#submit").hide();
        });
    }
    else if(parent.$(".breadcrumb").data("needButton") === "已申请" || parent.$(".breadcrumb").data("needButton") === "已驳回"){
        $("#add").on("click",function(){
            mb_part_type_add('添加','add/mbPartTypeAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            mb_part_type_mod('修改','edit/mbPartTypeMod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                mb_part_type_del();
            });
        }
        $("#submit").on("click",function(){
            mb_part_type_submit();
        });
        $("#contrast").on("click",function(){
            mb_part_type_contrast();
        });
        $("#return").on("click",function(){
            initData_refresh();
            $("#contrast").show();
            $("#refresh").show();
            $("#return").hide();
            $("#add").show();
            $("#edit").show();
            $("#delete").show();
            $("#submit").show();
            $("#queryPrimaryKey").show();
        });
    }
    else if(parent.$(".breadcrumb").data("needButton") === "已录入" ) {
        $("#add").hide();
        $("#edit").hide();
        $("#delete").hide();
        $("#submit").hide();
        $("#contrast").on("click",function(){
            mb_part_type_contrast();
        });
        $("#return").on("click",function(){
            initData_refresh();
            $("#contrast").show();
            $("#refresh").show();
            $("#return").hide();
            $("#add").show();
            $("#edit").show();
            $("#delete").show();
            $("#submit").show();
            $("#queryPrimaryKey").show();
        });
    }
     else if(parent.$(".breadcrumb").data("needButton") === "NoEdit" ){
          $("#add").hide();
          $("#edit").hide();
          $("#delete").hide();
          $("#submit").hide();
          $("#contrast").hide();
      }
      else
      {
          $(".breadcrumb").data("needButton","windowShow");
          $("#add").on("click",function(){
              mb_part_type_add('添加','add/mbPartTypeAdd.jsp','600','520');
          });
          $("#edit").on("click",function(){
              mb_part_type_mod('修改','edit/mbPartTypeMod.jsp','600','520');
          });
          $("#delete").on("click",function(){
              mb_part_type_del();
          });
          $("#contrast").on("click",function(){
              mb_part_type_contrast();
          });
          $("#submit").on("click",function(){
              mb_part_type_submit();
          });
          $("#return").on("click",function(){
              initData_refresh();
              $("#contrast").show();
              $("#refresh").show();
              $("#return").hide();
              $("#add").show();
              $("#edit").show();
              $("#delete").show();
              $("#submit").show();
              $("#queryPrimaryKey").show();
          });
      }
    if(parent.$(".breadcrumb").data("deleteButton") === "N") {
        $("#delete").hide();
    }
}

/*查看差异数据*/
function  mb_part_type_contrast(){
    var attrTab = $("#mbPartType").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=MB_PART_TYPE" ).load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();

}

function mb_part_type_submit(){
    layer.confirm('确认要提交吗？',function(){
        var url = contextPath+"/baseCommon/submitParaData";
        sendPostRequest(url,{
            tableName:"MB_PART_TYPE"
            }, callback_mbPartTypeSubmit,"json");
    });
}

function  callback_mbPartTypeSubmit(json){
    if (json.success) {
         if($(".breadcrumb").data("needButton") === "windowShow" )
              {
                  showMsgDuringTime("提交成功");
              }
              else
              {
                  parent.afterSelectPara("view","提交成功");
              }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }

}

function selectAll_refresh(){
  	var prodTab = $("#mbPartType").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh(){
  	var prodTab = $("#mbPartType").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=MB_PART_TYPE").load();
}



