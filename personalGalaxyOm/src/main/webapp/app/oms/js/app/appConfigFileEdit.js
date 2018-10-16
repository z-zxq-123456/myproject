var form;
var rowData;
    $(document).ready(function () {
        var rowData;
        if (parent.$("#appConfigList").find(".selected").length == 1) {
            rowData = parent.$('#appConfigList').DataTable().rows(".selected").data()[0];
            $("#fileName").val(rowData.fileName);
            $("#configKey").val(rowData.configKey);
            $("#configValue").val(rowData.configValue);
            $("#updateConfigValue").val(rowData.updateConfigValue);
            if (rowData.configKey.indexOf('port') > 0) {
                var dataA = [{"id": "0003001", "text": "应用单实例修改"}];
                $("#updateType").select2({
                    data: dataA
                });
            } else {
                var dataA = [{"id": "0003001", "text": "应用单实例修改"},{"id": "0003002", "text": "应用修改"}];
                $("#updateType").select2({
                    data: dataA
                });
            }
            form = $("#form-configFileInfo-edit").Validform({
                tiptype: 2,
                callback: function (form) {
                     dataEdit();
                    return false;
                }
            });
        }
    });
    function dataEdit() {
        var url = contextPath + "/updateEcmAppFileConfig";
        var rowData = parent.$('#appConfigList').DataTable().rows(".selected").data()[0];
        sendPostRequest(url, {
            appIntantId:parent.$("#queryAppIntantId").val(),
            appId:parent.getAppId(),
            fileName: $("#fileName").val(),
            configKey: $("#configKey").val(),
            configValue: $("#configValue").val(),
            updateConfigValue: $("#updateConfigValue").val(),
            updateType: $("#updateType").val()
        }, callback_dataEdit, "json");
    }

    function callback_dataEdit(json) {
        if (json.success) {
            parent.showMsgDuringTime("编辑成功");
        } else if (json.errorMsg) {
            showMsg(json.errorMsg, 'errorMsg');
        }
    }


    function checkPort() {
        var url = contextPath + "/findCheckPort";
        if($("#configKey").val().indexOf('port')>0){
        var checkValue = $("#updateConfigValue").val();
         if(isPositiveNum(checkValue)&&checkValue.length>3){
           sendPostRequest(url, {
           appIntantId: parent.$("#queryAppIntantId").val(),
           port: checkValue
           }, callback_PortEdit, "json");

         }else{
            $.messager.alert("提示","端口必须是整数且长度大于3位!","info");
         }
        }
    }

    function callback_PortEdit(result) {
        if (result.errorMsg) {
            showMsg(result.errorMsg, 'error');
        } else {
            if (result.isConnection == "true") {
                showMsg('该端口已被占用!', 'info');
                $("#updateConfigValue").val("");
            }
        }
    }

    function isPositiveNum(s){//判断端口是否为正整数
        var re = /^[0-9]*[1-9][0-9]*$/ ;
        return re.test(s)
    }