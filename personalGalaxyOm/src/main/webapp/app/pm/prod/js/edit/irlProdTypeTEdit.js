var form;
    $(document).ready(function () {
         getPkList({
           url: contextPath + "/pklist/getParameterPklist?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
                          id: "company",
                          async: false
         });
        var rowData;
        if (parent.$("#irlProdType").find(".selected").length === 1) {
            rowData = parent.$('#irlProdType').DataTable().rows(".selected").data()[0];
        }
        if (rowData) {
            if(parent.parent.$("#operateType").val()==="copy"){
            $("#prodType").val(parent.parent.$("#newProdType").val()).attr("disabled", true);
            }else{
            $("#prodType").val(rowData.prodType).attr("disabled", true);
            }
            $("#company").val(rowData.company);


            $("#intDateType").val(rowData.intDateType);



            $("#fixedCall").val(rowData.fixedCall);


            $("#prodGrp").val(rowData.prodGrp);


            $("#prodTypeDesc").val(rowData.prodTypeDesc);


        }
        $(".select2").select2();
        form = $("#irlProdTypeMod").Validform({
            tiptype: 2,
            callback: function (form) {
                irlProdTypeMod();
                return false;
            }
        });
     });
    function irlProdTypeMod(){
        var url = contextPath+"/irlProdType/update";
        sendPostRequest(url,{
            prodType:$("#prodType").val(),
            company:$("#company").val(),
            intDateType:$("#intDateType").val(),
            fixedCall:$("#fixedCall").val(),
            prodGrp:$("#prodGrp").val(),
            prodTypeDesc:$("#prodTypeDesc").val(),
            reqNum: parent.parent.$(".breadcrumb").data("reqNum")

        }, callback_irlProdTypeMod,"json");
    }
    function callback_irlProdTypeMod(json){
        if (json.retStatus === 'F'){
            showMsg(json.retMsg,'info');
        } else if(json.retStatus === 'S'){
        	var dataTable=parent.$('#irlProdType').DataTable();
           dataTable.row(".selected").remove().draw(false);
            dataTable.row.add({
                               prodType:$("#prodType").val(),
                               prodTypeDesc:$("#prodTypeDesc").val(),
                               prodGrp:$("#prodGrp").val(),
                               intDateType:$("#intDateType").val(),
                               fixedCall:$("#fixedCall").val(),
                               company:$("#company").val()
                               }).draw(false);
            parent.showMsgDuringTime("修改成功");
        }
    }
    function irlProdTypeModCancel() {
        var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
        parent.layer.close(index);  //关闭窗口
    }

