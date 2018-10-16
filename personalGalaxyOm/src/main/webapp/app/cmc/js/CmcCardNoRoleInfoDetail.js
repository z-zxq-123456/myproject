var layer_add_index, layer_edit_index;

function showMsgDuringTime(msg)
{
    layer.msg(msg);
    setTimeout(function(){
        layer.closeAll('dialog');
    }, 1000);
    if(msg==="添加成功") {
        layer.close(layer_add_index);
    }
    if(msg==="修改成功") {
        layer.close(layer_edit_index);
    }
}

$(document).ready(function() {
        $(".breadcrumb").data("reqNum",parent.$(".breadcrumb").data("reqNum"));
	// 获取默认opt配置
    	var opt = getDataTableOpt($("#cmcCardNoRoleInfo"));
    	opt.stateSave=true;
    	opt.processing=true;
        opt.autoWidth=false;
    	opt.deferRender=true;
        opt.scrollX=true;
        opt.ajax= {
            "url": contextPath+"/baseComm/getAll",
            "type": "POST",
            "data":{
                "pk_value":$.session.get('cardNoType'),
                "pk_name":"CARD_NO_ROLE_CODE",
                "tableName":"CMC_CARD_NO_ROLE_INFO"
            }
        };
        opt.columnDefs=[
            {
                "targets": [ 0 ],
                "visible": false
            }
            ,{
                "width":"60px",
                "targets":1
            }
            ,{
                "width":"60px",
                "targets":2
            }
            ,{
                "width":"60px",
                "targets":3
            }
            ,{
                "width":"60px",
                "targets":4
            }
            ,{
                "width":"60px",
                "targets":5
            }
            ,{
                "width":"110px",
                "targets":6
            }
            ,{
                "width":"110px",
                "targets":7
            }
            ,{
                "width":"110px",
                "targets":8
            }
            ,{
                "width":"110px",
                "targets":9
            }
            ,{
                "width":"110px",
                "targets":10
            }
            ,{
                "width":"110px",
                "targets":11
            }
        ];

        opt.columns=[
             {"data": "OPERATE_TYPE", "defaultContent": ""}
            ,{"data": "CARD_NO_ROLE_CODE", "defaultContent": ""}
            ,{ "data": "LEN_OF_CARD_NO", "defaultContent":""}
            ,{ "data": "CARD_GEN_ID", "defaultContent":""}
            ,{ "data": "SEQ_NO_LEN", "defaultContent":""}
            ,{ "data": "FLDNUM1", "defaultContent":""}
            ,{ "data": "FLDSRCTBL1", "defaultContent":""}
            ,{ "data": "FLDSRCFLD1", "defaultContent":""}
            ,{ "data": "FLDLEN1", "defaultContent":""}
            ,{ "data": "FLDWHR1", "defaultContent":""}
            ,{ "data": "FLDSIGN1", "defaultContent":""}
            ,{ "data": "FLDSRCTBL2", "defaultContent":""}
            ,{ "data": "FLDSRCFLD2", "defaultContent":""}
            ,{ "data": "FLDLEN2", "defaultContent":""}
            ,{ "data": "FLDWHR2", "defaultContent":""}
            ,{ "data": "FLDSIGN2", "defaultContent":""}
            ,{ "data": "FLDSRCTBL3", "defaultContent":""}
            ,{ "data": "FLDSRCFLD3", "defaultContent":""}
            ,{ "data": "FLDLEN3", "defaultContent":""}
            ,{ "data": "FLDWHR3", "defaultContent":""}
            ,{ "data": "FLDSIGN3", "defaultContent":""}
            ,{ "data": "FLDSRCTBL4", "defaultContent":""}
            ,{ "data": "FLDSRCFLD4", "defaultContent":""}
            ,{ "data": "FLDLEN4", "defaultContent":""}
            ,{ "data": "FLDWHR4", "defaultContent":""}
            ,{ "data": "FLDSIGN4", "defaultContent":""}
            ,{ "data": "FLDSRCTBL5", "defaultContent":""}
            ,{ "data": "FLDSRCFLD5", "defaultContent":""}
            ,{ "data": "FLDLEN5", "defaultContent":""}
            ,{ "data": "FLDWHR5", "defaultContent":""}
            ,{ "data": "FLDSIGN5", "defaultContent":""}
            ,{ "data": "FLDNUM2", "defaultContent":""}
            ,{ "data": "FLDSRCTBL6", "defaultContent":""}
            ,{ "data": "FLDSRCFLD6", "defaultContent":""}
            ,{ "data": "FLDLEN6", "defaultContent":""}
            ,{ "data": "FLDWHR6", "defaultContent":""}
            ,{ "data": "FLDSIGN6", "defaultContent":""}
            ,{ "data": "FLDSRCTBL7", "defaultContent":""}
            ,{ "data": "FLDSRCFLD7", "defaultContent":""}
            ,{ "data": "FLDLEN7", "defaultContent":""}
            ,{ "data": "FLDWHR7", "defaultContent":""}
            ,{ "data": "FLDSIGN7", "defaultContent":""}
            ,{ "data": "FLDSRCTBL8", "defaultContent":""}
            ,{ "data": "FLDSRCFLD8", "defaultContent":""}
            ,{ "data": "FLDLEN8", "defaultContent":""}
            ,{ "data": "FLDWHR8", "defaultContent":""}
            ,{ "data": "FLDSIGN8", "defaultContent":""}
            ,{ "data": "VRY_BIT_METH", "defaultContent":""}
            ,{ "data": "VRY_BIT_LEN", "defaultContent":""}
            ,{ "data": "VRY_GEN_FUNC", "defaultContent":""}
            ];

        opt.order = [[1, 'asc']];
    	//渲染tables
    	drawDataTable($("#cmcCardNoRoleInfo"),opt);
        $("#cmcCardNoRoleInfo").beautyUi({
            tableId:"cmcCardNoRoleInfo",
            buttonName:["添加", "修改","删除"],
            buttonId:["add",　"edit","delete"]
            });
        $('#cmcCardNoRoleInfo tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#cmcCardNoRoleInfo').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
             });
        $('#cmcCardNoRoleInfo').on('page.dt', function (e) {
                $('#cmcCardNoRoleInfo').find("tr").removeClass('selected');
            });
        $(".select2").select2();
        buttonStatus();
});

/*增加*/
function cmc_card_no_role_info_add(title,url,w,h){
    layer_add_index = layer.open({
        type: 2,
        content: url,
        area: [w+'px', h+'px']
    });
}

/*修改*/
function cmc_card_no_role_info_mod(title,url,w,h){
    if ($("#cmcCardNoRoleInfo").find(".selected").length!==1){
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
function  cmc_card_no_role_info_del(){
    if ($("#cmcCardNoRoleInfo").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？',function(){
        cmcCardNoRoleInfoDel();
    });
}

function  cmcCardNoRoleInfoDel(){
    var rowData = $('#cmcCardNoRoleInfo').DataTable().rows(".selected").data()[0];
    var generalFieldsJson ={
        cardNoRoleCode:rowData.CARD_NO_ROLE_CODE,
        reqNum:parent.$(".breadcrumb").data("reqNum")
    };
    var url = contextPath+"/cardNoRoleInfo/delete";
    sendPostRequest(url,generalFieldsJson, callback_cmcCardNoRoleInfoDel,"json");
}

function  callback_cmcCardNoRoleInfoDel(json){
    if (json.retStatus === 'F'){
        showMsg(json.retMsg,'error');
    } else if(json.retStatus === 'S') {
        $('#cmcCardNoRoleInfo').dataTable().api().row(".selected").remove().draw(false);
        showMsg(json.retMsg, 'info');
    }
}

function buttonStatus()
{
    if(parent.$(".breadcrumb").data("needButton") === "N") {
        $("#add").hide();
        $("#edit").hide();
        $("#delete").hide();
    } else if(parent.$(".breadcrumb").data("needButton") === "已申请" || parent.$(".breadcrumb").data("needButton") === "已驳回"){
        $("#add").on("click",function(){
            cmc_card_no_role_info_add('添加','add/cmcCardNoRoleInfoAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            cmc_card_no_role_info_mod('修改','edit/cmcCardNoRoleInfoMod.jsp','600','520');
        });
        if(parent.$(".breadcrumb").data("deleteButton") === "N") {
            $("#delete").hide();
        }
        else
        {
            $("#delete").on("click",function(){
                cmc_card_no_role_info_del();
            });
        }
    }
    else if(parent.$(".breadcrumb").data("needButton") === "已录入" ) {
        $("#add").hide();
        $("#edit").hide();
        $("#delete").hide();
    }
    else if(parent.$(".breadcrumb").data("needButton") === "NoEdit" ){
        $("#add").hide();
        $("#edit").hide();
        $("#delete").hide();
    }
    else
    {
        $(".breadcrumb").data("needButton","windowShow");
        $("#add").on("click",function(){
            cmc_card_no_role_info_add('添加','add/cmcCardNoRoleInfoAdd.jsp','600','520');
        });
        $("#edit").on("click",function(){
            cmc_card_no_role_info_mod('修改','edit/cmcCardNoRoleInfoMod.jsp','600','520');
        });
        $("#delete").on("click",function(){
            cmc_card_no_role_info_del();
        });
    }
    if(parent.$(".breadcrumb").data("deleteButton") === "N") {
        $("#delete").hide();
    }
    if( $('#cmcCardNoRoleInfo').DataTable().data().length > 0){
        $("#add").attr("href","javascript:;");
        $("#add").css("color","gray");
        $("#delete").attr("href","javascript:;");
        $("#delete").css("color","gray");
        $("#edit").attr("href","javascript:;");
        $("#edit").css("color","gray");
    }
}