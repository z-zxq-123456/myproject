var form;
    $(document).ready(function () {
             getPkList({
               url: contextPath + "/pklist/getParameterPklist?tableName=FM_COMPANY&tableCol=COMPANY,COMPANY_NAME",
                              id: "company",
                              async: false
             });
         if(parent.parent.$("#operateType").val() === "add" || parent.parent.$("#operateType").val() === "copy"){
            $("#prodType").val(parent.parent.$("#newProdType").val()).attr("readonly", true);
         }else if(parent.parent.$("#operateType").val() === "update"){
            $("#prodType").val(parent.parent.$("#prodType").val()).attr("readonly", true);
         }

        $(".select2").select2();

             form = $("#irlProdTypeAdd").Validform({
                    tiptype: 2,
                    callback: function (form) {
                        irlProdTypeAdd();
                        return false;
                    }
                });

    function irlProdTypeAdd(){
        var url = contextPath+"/irlProdType/insert?reqNum="+parent.parent.$(".breadcrumb").data("reqNum");
        sendPostRequest(url,getFormData("irlProdTypeAdd"), callback_irlProdTypeAdd,"json");
    }

    function callback_irlProdTypeAdd(json){
        if (json.retStatus === 'F'){
            showMsg(json.retMsg,'info');
        } else if(json.retStatus === 'S'){

                  	var dataTable=parent.$('#irlProdType').DataTable();
                          dataTable.row.add({
                                         prodType:$("#prodType").val(),
                                         prodTypeDesc:$("#prodTypeDesc").val(),
                                         prodGrp:$("#prodGrp").val(),
                                         intDateType:$("#intDateType").val(),
                                         fixedCall:$("#fixedCall").val(),
                                         company:$("#company").val()
                                         }).draw(false);
                            		 parent.showMsgDuringTime("添加成功");

        }
        //form.resetForm();  JS表单清空
    }
        function irlProdTypeAddCancel() {
        var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
        parent.layer.close(index);  //关闭窗口
    }

     });
