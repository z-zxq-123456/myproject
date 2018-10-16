var selectIs = 0;
    $(document).ready(function () {
        // 获取默认opt配置
        var opt = getDataTableOpt($("#irlProdInt"));
        opt.stateSave = true;
        opt.processing = true;
        opt.autoWidth = false;
        if(parent.$("#operateType").val() === "add"){
              opt.ajax= {
                   "url": contextPath+"/irlProdInt/getAll?prodType=" + parent.$("#newProdType").val(),
                   "type": "POST"
               };
            }else if(parent.$("#operateType").val() === "update"){
                opt.ajax= {
                     "url": contextPath+"/irlProdInt/getAll?prodType=" + parent.$("#prodType").val(),
                     "type": "POST"
                 };
            }
            else if(parent.$("#operateType").val() === "copy"){
             if(parent.irlProdIntData===true){
                               opt.ajax= {
                                             "url": contextPath+"/irlProdInt/getAll?prodType=" + parent.$("#prodType").val(),
                                             "type": "POST"
                                         };
                               opt.columnDefs=[
                                       {
                                                    "width":"100px",
                                                    "targets":[ 0 ],
                                                    "render":function(data ,type ,row){
                                                            return parent.$("#newProdType").val();
                                                     }
                                         }
                               ];
              }else{
              opt.ajax= {
                      "url": contextPath+"/irlProdInt/getAllByNewProdType?prodType=" + parent.$("#prodType").val() + "&newProdType=" + parent.$("#newProdType").val()+"&reqNum="+parent.$(".breadcrumb").data("reqNum"),
                      "type": "POST"
                  };
              parent.irlProdIntData=true;
              }
            }
        opt.columns = [
            {"data": "prodType", "defaultContent": ""},
            {"data": "eventType", "defaultContent": ""},
            {"data": "intClass", "defaultContent": ""},
            {"data": "intAmtId", "defaultContent": ""},
            {"data": "intCalcBal", "defaultContent": ""},
            {"data": "intDaysType", "defaultContent": ""},
            {"data": "intStart", "defaultContent": ""},
            {"data": "intType", "defaultContent": ""},
            {"data": "rateAmtId", "defaultContent": ""},
            {"data": "recalMethod", "defaultContent": ""},
            {"data": "taxType", "defaultContent": ""},
            {"data": "company", "defaultContent": ""},
           {"data": "intApplType", "defaultContent": ""},
            {"data": "rollFreq", "defaultContent": ""},
            {"data": "rollDay", "defaultContent": ""},
            {"data": "maxRate", "defaultContent": ""},
            {"data": "minRate", "defaultContent": ""},
            {"data": "monthBasis", "defaultContent": ""},
            {"data": "groupRuleType","defaultContent":""},
            {"data": "splitId","defaultContent":""},
            {"data": "splitType","defaultContent":""},
            {"data": "ruleId","defaultContent":""},
            {"data": "intRateInd", "defaultContent": ""},
            {"data":"","defaultContent":""}
        ];
        $(".select2").select2();
        	  opt.order = [[0, 'asc']];
        //渲染tables
        drawDataTable($("#irlProdInt"), opt);
        dataTableUi("irlProdInt",["添加", "修改", "删除", "作废", "查看差异数据", "刷新", "返回查看"],["add", "edit", "delete", "nullify", "contrast", "reference", "reBack"]);
        $('#irlProdInt tbody').on('click', 'tr', function (e) {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
            } else {
                $('#irlProdInt').find("tr").removeClass('selected');
                $(this).addClass('selected');
            }
        });
        $("#nullify").hide();
        $("#reBack").hide();
        $("#contrast").hide();
    if(parent.$("#operateType").val() === "update"){
        $("#reference").on("click", function () {
            selectAll_refresh();
        });
        $("#add").on("click", function () {
            irl_prod_int_add('添加', 'add/irlProdIntTAdd.jsp', '600', '520');
        });
        $("#edit").on("click", function () {
            irl_prod_int_mod('修改', 'edit/irlProdIntTEdit.jsp', '600', '520');
        });
        $("#delete").on("click", function () {
            irl_prod_int_del();
        });
        }else if(parent.$("#operateType").val() === "add" || parent.$("#operateType").val() === "copy"){
        $("#reference").on("click", function () {
            selectNewAll_refresh();
        });
        $("#add").on("click", function () {
            irl_prod_int_addN('添加', 'add/irlProdIntTAdd.jsp', '600', '520');
        });
        $("#edit").on("click", function () {
            irl_prod_int_modN('修改', 'edit/irlProdIntTEdit.jsp', '600', '520');
        });
        $("#delete").on("click", function () {
            irl_prod_int_delN();
        });
        }
    });
    var layer_add_index, layer_edit_index;
    /*增加*/
    function irl_prod_int_add(title, url, w, h) {
        layer_add_index = layer.open({
            type: 2,
            content: url,
            area: [w + 'px', h + 'px'],
            end: function(){

                    }
        });
    }
    /*增加*/
    function irl_prod_int_addN(title, url, w, h) {
        layer_add_index = layer.open({
            type: 2,
            content: url,
            area: [w + 'px', h + 'px'],
            end: function(){

                    }
        });
    }
    /*修改*/
    function irl_prod_int_mod(title, url, w, h) {
        if ($("#irlProdInt").find(".selected").length !== 1) {
            showMsg('请选择一行记录！', 'warning');
            return;
        }
        layer_edit_index = layer.open({
            type: 2,
            content: url,
            area: [w + 'px', h + 'px'],
            end: function(){

                    }
        });
    }
    /*修改*/
    function irl_prod_int_modN(title, url, w, h) {
        if ($("#irlProdInt").find(".selected").length !== 1) {
            showMsg('请选择一行记录！', 'warning');
            return;
        }
        layer_edit_index = layer.open({
            type: 2,
            content: url,
            area: [w + 'px', h + 'px'],
            end: function(){

                    }
        });
    }
    /*删除*/
    function irl_prod_int_del() {
        if ($("#irlProdInt").find(".selected").length !== 1) {
            showMsg('请选择一行记录！', 'warning');
            return;
        }
        layer.confirm('确认要删除吗？', function () {
            irlProdIntDel();
        });
    }
    /*删除*/
    function irl_prod_int_delN() {
        if ($("#irlProdInt").find(".selected").length !== 1) {
            showMsg('请选择一行记录！', 'warning');
            return;
        }
        layer.confirm('确认要删除吗？', function () {
            irlProdIntDelN();
        });
    }

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

      function irlProdIntDel(){
          	var url = contextPath+"/irlProdInt/delete?reqNum="+parent.$(".breadcrumb").data("reqNum");
          	var rowData = $('#irlProdInt').DataTable().rows(".selected").data()[0];  //已经获取数据
          	sendPostRequest(url,rowData	, callback_eventDel,"json");                //将获取数据发送给后台处理
          }
      function irlProdIntDelN(){
          	var url = contextPath+"/irlProdInt/delete?reqNum="+parent.$(".breadcrumb").data("reqNum");
          	var rowData = $('#irlProdInt').DataTable().rows(".selected").data()[0];  //已经获取数据
          	sendPostRequest(url,rowData	, callback_eventDelN,"json");                //将获取数据发送给后台处理
          }

          function callback_eventDel(json){
          	if (json.retStatus === 'F'){
          	   	showMsg(json.retMsg,'info');
          	} else if(json.retStatus === 'S'){
          	  	        $('#irlProdInt').dataTable().api().row(".selected").remove().draw(false);
          		showMsg(json.retMsg,'info');
          	}

          }
             function callback_eventDelN(json){
             	if (json.retStatus === 'F'){
             	   	showMsg(json.retMsg,'info');
             	} else if(json.retStatus === 'S'){
             	  	        $('#irlProdInt').dataTable().api().row(".selected").remove().draw(false);
             		showMsg(json.retMsg,'info');
             	}

             }
    function selectAll_refresh() {
        var prodTab = $("#irlProdInt").dataTable();
        var api = prodTab.api();
        api.ajax.url(contextPath + "/irlProdInt/getAll?prodType=" + parent.$("#prodType").val()).load();
    }
    function selectNewAll_refresh() {
        var prodTab = $("#irlProdInt").dataTable();
        var api = prodTab.api();
        api.ajax.url(contextPath + "/irlProdInt/getAll?prodType=" + parent.$("#newProdType").val()).load();
    }
    function selectAll_close(type, msg) {
        afterSelectPara(type, msg);
    }
    $("#selectByPrimaryKey").click(function () {
        if (selectIs === 0) {
            var attrTab = $("#irlProdInt").dataTable();
            var api = attrTab.api();
            if ($("#PROD_TYPE").val() == "" && $("#EVENT_TYPE").val() == ""&& $("#INT_CLASS").val() == "") {
               api.ajax.url( contextPath + "/irlProdInt/getAll?prodType=" + parent.$("#prodType").val()).load();
            } else {
                api.ajax.url(contextPath + "/irlProdInt/selectBase?prodType=" + $("#PROD_TYPE").val() + "&eventType=" + $("#EVENT_TYPE").val() + "&intClass=" + $("#INT_CLASS").val()).load();
            }
        } else {
            alert("请退回查看页面");
        }
    });

