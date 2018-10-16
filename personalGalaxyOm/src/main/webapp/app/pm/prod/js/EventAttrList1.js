  $(document).ready(function() {
   $.galaxytab("#tab-system .tabBar span", "#tab-system .tabCon", "current", "click", "0");
    getPkList({
        url:contextPath+"/eventType/getForPkList",
        id:"eventClass",
        async:false
    });
        var contrastFlag = parent.$("#contrast").data("constrastFlag");
          var rowData;
        // 获取默认opt配置
        var opt = getDataTableOpt($("#eventAttrList"));
        opt.stateSave=true;
        opt.processing=true;
        opt.autoWidth=false;

        rowData = parent.$('#eventList').DataTable().rows(".selected").data()[0];
        var url = contextPath + "/eventAttr/getByEventType";
        if(contrastFlag){
            url = contextPath + "/eventAttr/getByEventTypeContrast";
        }
        opt.ajax= {
                 "url": url,
                 "type": "POST",
                 "data":{eventType: rowData.eventType}
                 };
        opt.columns=[
                    {
                          "className":      'details-control',
                          "orderable":      false,
                          "data":           null,
                          "defaultContent": ''
                      },
              { "data": "eventType", "defaultContent":"" },
              { "data": "assembleType", "defaultContent":"" },
              { "data": "assembleId", "defaultContent":"" },
              { "data": "attrValue", "defaultContent":"" }
          ];
         opt.columnDefs=[{
             "targets":2,
             "render" :function(data ,type ,row){
                 if(row.assembleType == "EVENT"){
                     return "事件";
                 }else if(row.assembleType == "PART"){
                     return "指标";
                 }else if(row.assembleType == "ATTR"){
                     return "参数";
                 }
             }
         }];
        //渲染tables
        drawDataTable($("#eventAttrList"),opt);
        dataTableUi("eventAttrList",["查询","添加","修改","删除"],["select","add","edit","delete"]);
           $("#add").hide();
           $("#edit").hide();
           $("#delete").hide();
           $("#select").hide();
        //子行数据展开收缩操作函数
        $('#eventAttrList tbody').on('click', 'td.details-control', function () {
            var tr = $(this).closest('tr');
            var dataTable=$("#eventAttrList").DataTable();
            var row = dataTable.row( tr );
            if ( row.child.isShown() ) {
                row.child.hide();
                tr.removeClass('shown');
            }
            else {
                row.child( format(row.data()) ).show();
                tr.addClass('shown');
            }
        });
        if (parent.$("#eventList").find(".selected").length==1){
            rowData = parent.$('#eventList').DataTable().rows(".selected").data()[0];
        }
        if (rowData){
            $("#eventType").val(rowData.eventType);
            $("#eventDesc").val(rowData.eventDesc);
            $("#eventClass").val(rowData.eventClass);
            $("#status").val(rowData.status);
        }
         $(".select2").select2();
    //子行数据函数
    function format (d) {  //d是当前行的数据
        var table;
        var name;
        var childUrl = contextPath+"/eventAttr/selectChildrenPart";
        if(contrastFlag){
            childUrl = contextPath+"/eventAttr/selectChildrenPartContrast";
        }
        if(d.assembleType == "ATTR"){
            return false;
        }else{
            $.ajax({
                url: childUrl,
                data:{
                    "eventType" : d.eventType ,
                    "assembleId" : d.assembleId
                },
                type : "POST",
                dataType : "json",
                async : false,
                success :function(resultGroup){
                    var result=resultGroup.data;
                    table='<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;"><tr><td width=15px></td><td>指标</td><td>序号</td><td>参数KEY</td><td>参数值</td></tr>';
                    for(n in result){
                        var num=Number(n)+1;
                        if(result[n].attrValue == undefined){
                            table += '<tr><td width=15px></td>'+
                                    '<td>'+result[n].assembleId+'</td>'+
                                    '<td>'+num+'</td>'+
                                    '<td>'+result[n].attrKey+'</td>'+
                                    '<td></td></tr>';
                        }else {
                            table += '<tr><td width=15px></td>'+
                                    '<td>'+result[n].assembleId+'</td>'+
                                    '<td>'+num+'</td>'+
                                    '<td>'+result[n].attrKey+'</td>'+
                                    '<td>'+result[n].attrValue+'</td></tr>';
                        }
                    }
                    table += '</table>';
                }
            });
        }
        return table;
    }
});