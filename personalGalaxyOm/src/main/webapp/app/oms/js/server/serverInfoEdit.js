var form;
var rowData;
$(document).ready(function () {
    if (parent.$("#EcmServerList").find(".selected").length == 1) {
        rowData = parent.$('#EcmServerList').DataTable().rows(".selected").data()[0];
    }
    getPkList({
        url: contextPath + "/findParaCombox?paraParentId=0030&&isDefault=false'",
        id: "serOs",
        async: false
    });
    if (rowData) {
        $("#serName").val(rowData.serName);
        $("#serIp").val(rowData.serIp);
        $("#serUser").val(rowData.serUser);
        $("#serPwd").val(rowData.serPwd);
        $("#serOs").val(rowData.serOs);
        form = $("#form-patternMetadata-edit").Validform({
            tiptype: 2,
            callback: function (form) {
                patternMetadataEdit();
                return false;
            }
        });
    }
    $(".select2").select2();
});

function patternMetadataEdit() {
    var url = contextPath + "/updateEcmServerInfo";
    sendPostRequest(url, {
        serId: rowData.serId,
        serName: $("#serName").val(),
        serIp: $("#serIp").val(),
        serUser: $("#serUser").val(),
        serPwd: $("#serPwd").val(),
        serOs: $("#serOs").val(),
    }, callback_patternMetadataEdit, "json");
}

function callback_patternMetadataEdit(json) {
    if (json.success) {
        var dataTable=parent.$("#EcmServerList").DataTable();
        dataTable.row(".selected").remove().draw(false);
        dataTable.row.add({
            serId: rowData.serId,
            serName: $("#serName").val(),
            serIp: $("#serIp").val(),
            serUser: $("#serUser").val(),
            serOs: $("#serOs").val(),
            serOsName:$("#serOs").find("option:selected").text(),
        }).draw(false);
        parent.showMsgDuringTime("编辑成功");
        //patternMetadataEditCancel();
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}
function patternMetadataEditCancel() {
    var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
    parent.layer.close(index);  //关闭窗口
}
function displayTestPwd(){
     $("#pwdConfDiv").show();
}