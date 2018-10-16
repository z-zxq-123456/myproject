 var selectIs = 0;
    $(document).ready(function () {
        // 获取默认opt配置
        var opt = getDataTableOpt($("#irlProdType"));
        opt.stateSave = true;
        opt.processing = true;
        opt.autoWidth = false;
        if(parent.$("#operateType").val() == "add"){
          opt.ajax= {
                   "url": contextPath+"/irlProdType/getAll?prodType=" + parent.$("#newProdType").val(),
                   "type": "POST"
               };
        }else if(parent.$("#operateType").val() == "update"){
          opt.ajax= {
                   "url": contextPath+"/irlProdType/getAll?prodType=" + parent.$("#prodType").val(),
                   "type": "POST"
               };
        }else if(parent.$("#operateType").val() == "copy"){
          opt.ajax= {
                  "url": contextPath+"/irlProdType/getAllByNewProdType?prodType=" + parent.$("#prodType").val() + "&newProdType=" + parent.$("#newProdType").val(),
                  "type": "POST"
              };
        }
        opt.columns = [
            {"data": "prodType", "defaultContent": ""},
            {"data": "prodTypeDesc", "defaultContent": ""},
            {"data": "prodGrp", "defaultContent": ""},
            {"data": "intDateType", "defaultContent": ""},
            {"data": "fixedCall", "defaultContent": ""},
            {"data": "company", "defaultContent": ""}
        ];
        //渲染tables
        drawDataTable($("#irlProdType"), opt);
        $(".select2").select2();
        dataTableUi("irlProdType",["添加", "修改", "删除", "作废", "查看差异数据", "刷新", "返回查看"],["add", "edit", "delete", "nullify", "contrast", "reference", "reBack"]);
        $('#irlProdType tbody').on('click', 'tr', function (e) {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
            } else {
                $('#irlProdType').find("tr").removeClass('selected');
                $(this).addClass('selected');
            }
        });
        $("#nullify").hide();
        $("#reBack").hide();
        $("#contrast").hide();

        if(parent.$("#operateType").val() == "update"){
        $("#reference").on("click", function () {
            selectAll_refresh();
        });
        $("#add").on("click", function () {
            irl_prod_type_add('添加', 'irlProdTypeTAdd.jsp', '630', '400');
        });
        $("#edit").on("click", function () {
            irl_prod_type_mod('修改', 'irlProdTypeTEdit.jsp', '630', '400');
        });
        $("#delete").on("click", function () {
            irl_prod_type_del();
        });
        }else if(parent.$("#operateType").val() == "add" || parent.$("#operateType").val() == "copy"){
       $("#reference").on("click", function () {
           selectNewAll_refresh();
       });
       $("#add").on("click", function () {
           irl_prod_type_addN('添加', 'irlProdTypeTAdd.jsp', '630', '400');
       });
       $("#edit").on("click", function () {
           irl_prod_type_modN('修改', 'irlProdTypeTEdit.jsp', '630', '400');
       });
       $("#delete").on("click", function () {
           irl_prod_type_delN();
       });
      }
    });

    var layer_add_index, layer_edit_index;
    /*增加*/
    function irl_prod_type_addN(title, url, w, h) {
        layer_add_index = layer.open({
            type: 2,
            content: url,
            area: [w + 'px', h + 'px'],
            end: function(){
                    selectNewAll_refresh();
                    }
        });
    }
    /*增加*/
    function irl_prod_type_add(title, url, w, h) {
        layer_add_index = layer.open({
            type: 2,
            content: url,
            area: [w + 'px', h + 'px'],
            end: function(){
                    selectAll_refresh();
                    }
        });
    }

    /*修改*/
    function irl_prod_type_mod(title, url, w, h) {
        if ($("#irlProdType").find(".selected").length != 1) {
            showMsg('请选择一行记录！', 'warning');
            return;
        }
        layer_edit_index = layer.open({
            type: 2,
            content: url,
            area: [w + 'px', h + 'px'],
            end: function(){
                    selectAll_refresh();
                    }
        });
    }

    /*修改*/
    function irl_prod_type_modN(title, url, w, h) {
        if ($("#irlProdType").find(".selected").length != 1) {
            showMsg('请选择一行记录！', 'warning');
            return;
        }
        layer_edit_index = layer.open({
            type: 2,
            content: url,
            area: [w + 'px', h + 'px'],
            end: function(){
                    selectNewAll_refresh();
                    }
        });
    }
    /*删除*/
    function irl_prod_type_del() {
        if ($("#irlProdType").find(".selected").length != 1) {
            showMsg('请选择一行记录！', 'warning');
            return;
        }
        layer.confirm('确认要删除吗？', function () {
            irlProdTypeDel();
        });
    }
    /*删除*/
    function irl_prod_type_delN() {
        if ($("#irlProdType").find(".selected").length != 1) {
            showMsg('请选择一行记录！', 'warning');
            return;
        }
        layer.confirm('确认要删除吗？', function () {
            irlProdTypeDelN();
        });
    }
    function irlProdTypeDel(){
        	var url = contextPath+"/irlProdType/delete1";
        	var rowData = $('#irlProdType').DataTable().rows(".selected").data()[0];  //已经获取数据
        	sendPostRequest(url,rowData	, callback_eventDel,"json");                //将获取数据发送给后台处理
        }

    function irlProdTypeDelN(){
            var url = contextPath+"/irlProdType/delete1";
            var rowData = $('#irlProdType').DataTable().rows(".selected").data()[0];  //已经获取数据
            sendPostRequest(url,rowData	, callback_eventDelN,"json");                //将获取数据发送给后台处理
        }

        function callback_eventDel(json){
        	if (json.retStatus == 'F'){
        	   	showMsg(json.retMsg,'info');
        	} else if(json.retStatus == 'S'){
        		showMsg(json.retMsg,'info');
        	}
            selectAll_refresh();
        }
        function callback_eventDelN(json){
            if (json.retStatus == 'F'){
                showMsg(json.retMsg,'info');
            } else if(json.retStatus == 'S'){
                showMsg(json.retMsg,'info');
            }
            selectNewAll_refresh();
        }
        function showMsgDuringTime(msg)
        {
            layer.msg(msg);
            setTimeout(function(){
                layer.closeAll('dialog');
            }, 1000);
            if(msg=="添加成功") {
                layer.close(layer_add_index);
            }
            if(msg=="修改成功") {
                layer.close(layer_edit_index);
            }
        }


    function selectAll_refresh() {
        var prodTab = $("#irlProdType").dataTable();
        var api = prodTab.api();
        api.ajax.url(contextPath + "/irlProdType/getAll?prodType=" + parent.$("#prodType").val()).load();
    }
    function selectNewAll_refresh() {
        var prodTab = $("#irlProdType").dataTable();
        var api = prodTab.api();
        api.ajax.url(contextPath+"/irlProdType/getAll?prodType=" + parent.$("#newProdType").val()).load();
    }

    function selectAll_close(type, msg) {
         afterSelectPara(type, msg);
    }
    $("#selectByPrimaryKey").click(function () {
        if (selectIs == 0) {
            var attrTab = $("#irlProdType").dataTable();
            var api = attrTab.api();
            if (1 == 1
                    && $("#PROD_TYPE").val() == ""
                    && $("#PROD_GRP").val() == ""
                    && $("#INT_APPL_TYPE").val() == ""
            ) {
               api.ajax.url( contextPath + "/irlProdType/getAll?prodType=" + parent.$("#prodType").val()).load();
            } else {
                api.ajax.url(contextPath + "/irlProdType/selectBase?prodType=" + $("#PROD_TYPE").val() + "&prodGrp=" + $("#PROD_GRP").val() + "&intApplType=" + $("#INT_APPL_TYPE").val()).load();
            }
        } else {
            alert("请退回查看页面");
        }
    });

