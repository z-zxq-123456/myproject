var layer_add_index, layer_edit_index;

function showMsgDuringTime(msg)
{
    showMsgCloseLayer(msg,layer_add_index,layer_edit_index);
    if(msg==="添加成功") {
        selectAll_refresh();
    }
          $("#nullifyAppNo").show();
              $("#contrast").show();
}

$(document).ready(function() {
 $(".breadcrumb").data("reqNum",parent.$(".breadcrumb").data("reqNum"));
	// 获取默认opt配置
            $.ajax({
                                                    url: contextPath + "/ProdToJson/Start",
                                                    data:{
                                                    reqNum:parent.$(".breadcrumb").data("reqNum"),
                                                    tableName:"PROD_MB_PROD_CLASS",
                                                        tableDesc:"产品分类"
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
    	var opt = getDataTableOpt($("#mbProdClass"));
    	opt.stateSave=true;
    	opt.processing=true;
        opt.autoWidth=false;
    	opt.deferRender=true;
    	opt.ajax= {
    			 "url": contextPath+"/baseCommon/getList",
    			 "type": "POST",
    			 "data":{
                    "tableName":"MB_PROD_CLASS",
                    "reqNum": parent.$(".breadcrumb").data("reqNum")
                 }
           	 };
        opt.fnRowCallback=function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
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
                      "targets":2
                }
                ,{
                      "width":"100px",
                      "targets":3,
                      "render" :function(data ,type ,row){
                          if(row.PARENT_PROD_CLASS==="CL"){
                              return "贷款类产品";
                          }else if(row.PARENT_PROD_CLASS==="RB"){
                              return "存款类产品";
                          }else if(row.PARENT_PROD_CLASS==="MM"){
                              return "货币市场类产品";
                          }else if(row.PARENT_PROD_CLASS==="GL"){
                              return "总账类产品";
                          }else if(row.PARENT_PROD_CLASS==="" || row.PARENT_PROD_CLASS==null ||row.PARENT_PROD_CLASS===undefined){
                              return "未定义";
                          }else if(row.PARENT_PROD_CLASS==="0" ){
                              return "无";
                          }else{
                              return data;
                          }
                      }
                }
                ,{
                      "width":"100px",
                      "targets":4,
                      "render":function(data ,type ,row){
                          return "第"+row.PROD_CLASS_LEVEL+"级";
                      }
                }
             ];

        opt.columns=[
            {"data": "OPERATE_TYPE", "defaultContent": ""}
                ,{ "data": "PROD_CLASS", "defaultContent":""}
                ,{ "data": "PROD_CLASS_DESC", "defaultContent":""}
                ,{ "data": "PARENT_PROD_CLASS", "defaultContent":""}
                ,{ "data": "PROD_CLASS_LEVEL", "defaultContent":""}
                ,{ "data": "COMPANY", "defaultContent":""}
            ];
    	//渲染tables
    	drawDataTable($("#mbProdClass"),opt);
        $("#mbProdClass").beautyUi({
            tableId:"mbProdClass",
            buttonName:["添加", "修改","删除","提交","查看差异数据","刷新","返回","作废" ],
            buttonId:["add",　"edit","delete","submit","contrast","refresh","return","nullifyAppNo" ]
            });
        $('#mbProdClass tbody').on('click', 'tr', function () {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#mbProdClass').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
            });
        $('#mbProdClass').on('page.dt', function () {
                $('#mbProdClass').find("tr").removeClass('selected');
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
function mb_prod_class_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function mb_prod_class_mod(title,url,w,h){
    if ($("#mbProdClass").find(".selected").length!==1){
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
function  mb_prod_class_del(){
    if ($("#mbProdClass").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
         mbProdClassDel();
    });
}

function  mbProdClassDel(){
    var paraJson,keyFieldsJson;
    paraJson={};
    keyFieldsJson={};
    var url = contextPath+"/baseCommon/updateForDelete";
    var rowData = $('#mbProdClass').DataTable().rows(".selected").data()[0];  //已经获取数据
    paraJson.tableName="MB_PROD_CLASS";
        keyFieldsJson.PROD_CLASS=rowData.PROD_CLASS;
    paraJson.key = keyFieldsJson;
    paraJson.status=rowData.COLUMN_STATUS;
    paraJson.reqNum = parent.$(".breadcrumb").data("reqNum");
    paraJson.status=rowData.COLUMN_STATUS;
    var params = {
        paraJson:JSON.stringify(paraJson)
    };
    sendPostRequest(url,params, callback_mbProdClassDel,"json");                //将获取数据发送给后台处理
}

function  callback_mbProdClassDel(json){
    if (json.success) {
        $("#mbProdClass").dataTable().api().row(".selected").remove().draw(false);
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
            mb_prod_class_contrast();
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
            mb_prod_class_add('添加','add/mbProdClassAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            mb_prod_class_mod('修改','edit/mbProdClassMod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                mb_prod_class_del();
            });
        }
        $("#submit").on("click",function(){
            mb_prod_class_submit();
        });
        $("#contrast").on("click",function(){
            mb_prod_class_contrast();
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
            mb_prod_class_contrast();
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
               mb_prod_class_add('添加','add/mbProdClassAdd.jsp','600','520');
           });
           $("#edit").on("click",function(){
               mb_prod_class_mod('修改','edit/mbProdClassMod.jsp','600','520');
           });
           $("#nullifyAppNo").click(function() {
                             nullifyAppNo();
           });
           $("#delete").on("click",function(){
               mb_prod_class_del();
           });
           $("#contrast").on("click",function(){
               mb_prod_class_contrast();
           });
           $("#submit").on("click",function(){
               mb_prod_class_submit();
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
function  mb_prod_class_contrast(){
    var attrTab = $("#mbProdClass").dataTable();
    var api = attrTab.api();
    api.ajax.url(contextPath+"/baseCommon/contrastBase?tableName=MB_PROD_CLASS" ).load();
    $("#queryPrimaryKey").hide();
    $("#add").hide();
    $("#edit").hide();
    $("#delete").hide();
    $("#submit").hide();
    $("#contrast").hide();
    $("#refresh").hide();
    $("#return").show();

}

function mb_prod_class_submit(){
    layer.confirm('确认要提交吗？',function(){
        var url = contextPath+"/baseCommon/submitParaData";
        sendPostRequest(url,{
            tableName:"MB_PROD_CLASS"
            }, callback_mbProdClassSubmit,"json");
    });
}

function  callback_mbProdClassSubmit(json){
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
            transactionId: "MB_PROD_CLASS",
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
  	var prodTab = $("#mbProdClass").dataTable();
    var api = prodTab.api();
    api.ajax.reload();
}

function initData_refresh(){
  	var prodTab = $("#mbProdClass").dataTable();
    var api = prodTab.api();
    api.ajax.url(contextPath+"/baseCommon/getList?tableName=MB_PROD_CLASS").load();
}



