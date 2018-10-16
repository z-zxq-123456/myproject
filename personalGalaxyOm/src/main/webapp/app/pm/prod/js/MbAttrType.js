var layer_add_index, layer_edit_index;

function showMsgDuringTime(msg)
{
    showMsgCloseLayer(msg,layer_add_index,layer_edit_index);
    if(msg==="添加成功") {
        selectAll_refresh();
    }
    if(msg==="编辑成功") {
        selectAll_refresh();
    }
       $("#nullifyAppNo").show();
       $("#contrast").show();
}

$(document).ready(function() {
$(".breadcrumb").data("reqNum",parent.$(".breadcrumb").data("reqNum"));
    //汉化预处理，从数据库拉数据
                            $.ajax({
                                                    url: contextPath + "/ProdToJson/Start",
                                                    data:{
                                                    reqNum:parent.$(".breadcrumb").data("reqNum"),
                                                    tableName:"PROD_MB_ATTR_TYPE",
                                                        tableDesc:"参数定义"
                                                    },
                                                      success: function(json) {
                                                      if(json.appNo!==null)
                                                      {
                                                      $(".breadcrumb").data("reqNum",json.appNo);
                                                       if(json.currentStatus==="2"||json.currentStatus==="3"){
                                                         $("#add").hide();
                                                         $("#edit").hide();
                                                         $("#delete").hide();
                                                         $("#submit").hide();
                                                         $(".breadcrumb").data("currentStatus","2");
                                                       }else if(json.currentStatus==="1"){
                                                         $("#contrast").hide();
                                                       }else if(json.currentStatus==="6"){
                                                         $("#nullifyAppNo").show();
                                                       }
                                                      }
                                                      },
                                                     dataType: "json",
                                                     type : "POST"
                            });
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
    	var opt = getDataTableOpt($("#mbAttrType"));
    	opt.stateSave=true;
    	opt.processing=true;
        opt.autoWidth=false;
    	opt.deferRender=true;
    	opt.ajax= {
    			 "url": contextPath+"/baseCommon/getList",
    			 "type": "POST",
    			 "data":{
                    "tableName":"MB_ATTR_TYPE",
                    "reqNum": parent.$(".breadcrumb").data("reqNum")
                 }
           	 };
        opt.fnRowCallback=function( nRow, aData ) {
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
                      "render"  : function(data, type ,row){
                          return descMap[row.ATTR_CLASS];
                      }
                }
                ,{
                      "width":"100px",
                      "targets":3
                }
                ,{
                      "width":"100px",
                      "targets":4,
                      "render" :function(data ,type ,row){
                        if(row.STATUS === "A"){
                            return "有效";
                        }else if(row.STATUS ==="F"){
                            return "无效";
                        }
                    }
                }
                ,{
                      "width":"100px",
                      "targets":5,
                      "render"  :function(data, type ,row){
                          if(row.VALUE_METHOD==="FD"){
                              return "取自固定值";
                          }else if(row.VALUE_METHOD==="VL"){
                              return "取值列表值";
                          }else if(row.VALUE_METHOD==="RF"){
                              return "参数数据表";
                          }else if(row.VALUE_METHOD==="YN"){
                              return "取值Y或N";
                          }
                      }
                }
                ,{
                      "width":"100px",
                      "targets":6,
                      "render"  : function( data, type, row ) {
                           if(row.ATTR_TYPE==="STRING"){
                               return "字符型";
                           }else if(row.ATTR_TYPE==="DOUBLE"){
                               return "数值型";
                           }else{
                                return data;
                           }
                       }
                }
                ,{
                      "width":"100px",
                      "targets":7
                }
                ,{
                      "width":"100px",
                      "targets":8,
                      "render"  : function( data, type, row ) {
                           if(row.SET_VALUE_FLAG==="M"){
                               return "多值";
                           }else if(row.SET_VALUE_FLAG==="S"){
                               return "单值";
                           }else{
                               return "未定义";
                           }
                       }
                }
                ,{
                      "width":"100px",
                      "targets":9,
                     "render"  : function( data, type, row ) {
                         if(row.USE_METHOD==="CTR"){
                             return "控制类";
                         }else if(row.USE_METHOD==="EVAL"){
                             return "赋值类";
                         }else if(row.USE_METHOD==="IND"){
                             return "独立逻辑";
                         }else if(row.USE_METHOD==="DESC"){
                             return "描述类";
                         }else if(row.USE_METHOD=== undefined || row.USE_METHOD=== "" ||row.USE_METHOD== null){
                             return "";
                         }
                     }
                }
             ];

        opt.columns=[
            {"data": "OPERATE_TYPE", "defaultContent": ""}
                ,{ "data": "ATTR_KEY", "defaultContent":""}
                ,{ "data": "ATTR_CLASS", "defaultContent":""}
                ,{ "data": "ATTR_DESC", "defaultContent":""}
                ,{ "data": "STATUS", "defaultContent":""}
                ,{ "data": "VALUE_METHOD", "defaultContent":""}
                ,{ "data": "ATTR_TYPE", "defaultContent":""}
                ,{ "data": "BUSI_CATEGORY", "defaultContent":""}
                ,{ "data": "SET_VALUE_FLAG", "defaultContent":""}
                ,{ "data": "USE_METHOD", "defaultContent":""}
                ,{ "data": "COMPANY", "defaultContent":""}
            ];
              opt.order = [[1, 'asc']];
    	//渲染tables
    	drawDataTable($("#mbAttrType"),opt);
        $("#mbAttrType").beautyUi({
            tableId:"mbAttrType",
            buttonName:["添加", "修改","删除","提交","查看差异数据","刷新","返回","作废" ],
            buttonId:["add",　"edit","delete","submit","contrast","refresh","return","nullifyAppNo" ]
            });
        $('#mbAttrType tbody').on('click', 'tr', function () {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#mbAttrType').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
            });
        $('#mbAttrType').on('page.dt', function () {
                $('#mbAttrType').find("tr").removeClass('selected');
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
function mb_attr_type_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function mb_attr_type_mod(title,url,w,h){
    if ($("#mbAttrType").find(".selected").length!==1){
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
function  mb_attr_type_del(){
    if ($("#mbAttrType").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
         mbAttrTypeDel();
    });
}

function  mbAttrTypeDel(){
    var paraJson,keyFieldsJson;
    paraJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateForDelete";
    var rowData = $('#mbAttrType').DataTable().rows(".selected").data()[0];  //已经获取数据
    paraJson.tableName="MB_ATTR_TYPE";
        keyFieldsJson.ATTR_KEY=rowData.ATTR_KEY;
    paraJson.key = keyFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
    paraJson.status=rowData.COLUMN_STATUS;
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params, callback_mbAttrTypeDel,"json");                //将获取数据发送给后台处理
}

function  callback_mbAttrTypeDel(json){
    if (json.success) {
        $("#mbAttrType").dataTable().api().row(".selected").remove().draw(false);
        showMsgDuringTime("删除成功！");
         $("#nullifyAppNo").show();
         $("#contrast").show();
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function buttonStatus()
{
    $("#nullifyAppNo").hide();
    $("#return").hide();
    if(parent.$(".breadcrumb").data("needButton") === "N"){
        $("#add").hide();
        $("#edit").hide();
        $("#delete").hide();
        $("#submit").hide();
        $("#contrast").on("click",function(){
            mb_attr_type_contrast();
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
            mb_attr_type_add('添加','add/mbAttrTypeAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            mb_attr_type_mod('修改','edit/mbAttrTypeMod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                mb_attr_type_del();
            });
        }
        $("#submit").on("click",function(){
            mb_attr_type_submit();
        });
        $("#contrast").on("click",function(){
            mb_attr_type_contrast();
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
            mb_attr_type_contrast();
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
               mb_attr_type_add('添加','add/mbAttrTypeAdd.jsp','600','520');
           });
           $("#edit").on("click",function(){
               mb_attr_type_mod('修改','edit/mbAttrTypeMod.jsp','600','520');
           });
             $("#nullifyAppNo").click(function() {
                             nullifyAppNo();
             });
           $("#delete").on("click",function(){
               mb_attr_type_del();
           });
           $("#contrast").on("click",function(){
               mb_attr_type_contrast();
           });
           $("#submit").on("click",function(){
               mb_attr_type_submit();
           });
           $("#return").on("click",function(){
            initData_refresh();
            if($(".breadcrumb").data("currentStatus") !== "2")
            {
                 $("#add").show();
                 $("#edit").show();
                 $("#delete").show();
                 $("#submit").show();
            }
            $("#contrast").show();
            $("#refresh").show();
            $("#return").hide();
            $("#queryPrimaryKey").show();
           });
       }
    if(parent.$(".breadcrumb").data("deleteButton") === "N") {
        $("#delete").hide();
    }
}

/*查看差异数据*/
function  mb_attr_type_contrast(){
    var attrTab = $("#mbAttrType").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=MB_ATTR_TYPE" ).load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();

}

function mb_attr_type_submit(){
    layer.confirm('确认要提交吗？',function(){
        var url = contextPath+"/baseCommon/submitParaData";
        sendPostRequest(url,{
            tableName:"MB_ATTR_TYPE"
            }, callback_mbAttrTypeSubmit,"json");
    });
}

function  callback_mbAttrTypeSubmit(json){
    if (json.success) {
           if($(".breadcrumb").data("needButton") === "windowShow" )
                {
                    showMsgDuringTime("提交成功");
                     $("#add").hide();
                     $("#edit").hide();
                     $("#delete").hide();
                     $("#submit").hide();
                     $("#contrast").show();
                     $("#nullifyAppNo").hide();
                     $("#refresh").hide();
                     $("#return").hide();
                }
                else
                {
                    parent.afterSelectPara("view","提交成功");
                }
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }

}
function nullifyAppNo() {
    layer.confirm('确认要作废吗？', function () {
        var url = contextPath + "/baseCommon/nullifyParaData";
        sendPostRequest(url, {
            transactionId: "MB_ATTR_TYPE",
            reqNum:$("#appSeriesNumber").val()
        }, callback_fmCompanyNullify, "json");
    });
}

function  callback_fmCompanyNullify(json) {
    if (json.success) {
     showMsgDuringTime("作废成功");
               initData_refresh();
         $("#nullifyAppNo").hide();
         $("#add").hide();
         $("#edit").hide();
         $("#delete").hide();
         $("#submit").hide();
         $("#contrast").hide();
         $("#refresh").hide();
         $("#return").hide();
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}
function selectAll_refresh(){
  	var prodTab = $("#mbAttrType").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh(){
  	var prodTab = $("#mbAttrType").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=MB_ATTR_TYPE").load();
}



