             $(document).ready(function() {
                  $.galaxytab("#tab-system .tabBar span", "#tab-system .tabCon", "current", "click", "0");
                   getPkList({
                       url: contextPath + "/pklist/getParameterPklist?tableName=mb_prod_class&tableCol=PROD_CLASS,PROD_CLASS_DESC",
                        id: "prodClass",
                     async: false
                        });
                         var rowData;
                       // 获取默认opt配置
                       var opt = getDataTableOpt($("#prodAttrList"));
                       opt.stateSave=true;
                       opt.processing=true;
                       opt.autoWidth=false;

                       rowData = parent.$('#prodList').DataTable().rows(".selected").data()[0];
                       opt.ajax= {
                                "url": contextPath + "/prodDefine/getByProdType",
                                "type": "POST",
                                "data":{prodType: rowData.prodType}
                                };
                       opt.columns=[
                                   {
                                         "className":      'details-control',
                                         "orderable":      false,
                                         "data":           null,
                                         "defaultContent": ''
                                     },
                             { "data": "prodType", "defaultContent":"" },
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
                       drawDataTable($("#prodAttrList"),opt);
                       dataTableUi("prodAttrList",["查询","添加","修改","删除"],["select","add","edit","delete"]);
                          $("#add").hide();
                          $("#edit").hide();
                          $("#delete").hide();
                          $("#select").hide();
                       //子行数据展开收缩操作函数
                       $('#prodAttrList tbody').on('click', 'td.details-control', function () {
                           var tr = $(this).closest('tr');
                           var dataTable=$("#prodAttrList").DataTable();
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

                   if (parent.$("#prodList").find(".selected").length==1){
                       rowData = parent.$('#prodList').DataTable().rows(".selected").data()[0];
                   }
                   if (rowData){
                       $("#prodType").val(rowData.prodType);
                       $("#prodDesc").val(rowData.prodDesc);
                       $("#prodClass").val(rowData.prodClass);
                       $("#prodRange").val(rowData.prodRange);
                       $("#prodGroup").val(rowData.prodGroup);
                       $("#status").val(rowData.status);
                   }
                  $(".select2").select2();
                       //子行数据函数
             function format (d) {  //d是当前行的数据
                 var table;
                 var name;
                 if(d.assembleType == "ATTR"){
                     return false;
                 }else{
                     $.ajax({
                         url: contextPath+"/prodDefine/selectChildrenEvent",
                         data:{
                             "prodType" : d.prodType ,
                             "assembleId" : d.assembleId
                         },
                         type : "POST",
                         dataType : "json",
                         async : false,
                         success :function(resultGroup){
                             var result=resultGroup.data;
                             table='<table id="eventAttrList" cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;"><tr><td width=15px></td><td>事件</td><td>序号</td><td>类型</td><td>代码</td><td>参数值</td></tr>';
                             for(n in result){
                                 var num=Number(n)+1;
                                 if(result[n].assembleType == 'PART'){
                                       $.ajax({
                                           url: contextPath+"/eventAttr/selectChildrenPart",
                                           data:{
                                               "eventType" : result[n].eventType ,
                                               "assembleId" : result[n].assembleId
                                           },
                                           type : "POST",
                                           dataType : "json",
                                           async : false,
                                           success :function(partResult){
                                            var result1=partResult.data;
                                                  if(result[n].attrValue == undefined){
                                                      table += '<tr><td width=15px class="details-control sorting_1"></td>'+
                                                              '<td>'+result[n].eventType+'</td>'+
                                                              '<td>'+num+'</td>'+
                                                              '<td>指标</td>'+
                                                              '<td>'+result[n].assembleId+'</td>'+
                                                              '<td></td></tr>';
                                                         for(n in result1){
                                                             var num1=Number(n)+1;
                                                            table += '<tr><td width=15px></td>'+
                                                                    '<td>'+result1[n].assembleId+'</td>'+
                                                                    '<td>'+num1+'</td>'+
                                                                    '<td>参数</td>'+
                                                                    '<td>'+result1[n].attrKey+'</td>'+
                                                                    '<td>'+result1[n].attrValue+'</td>'+
                                                                    '<td></td></tr>';
                                                             }
                                                  }else {
                                                      table += '<tr><td width=15px class="details-control sorting_1"></td>'+
                                                              '<td>'+result[n].eventType+'</td>'+
                                                              '<td>'+num+'</td>'+
                                                              '<td>指标</td>'+
                                                              '<td>'+result[n].assembleId+'</td>'+
                                                              '<td>'+result[n].attrValue+'</td></tr>';
                                                              for(n in result1){
                                                                     var num1=Number(n)+1;
                                                                    table += '<tr><td width=15px></td>'+
                                                                            '<td>'+result1[n].assembleId+'</td>'+
                                                                            '<td>'+num1+'</td>'+
                                                                            '<td>参数</td>'+
                                                                            '<td>'+result1[n].attrKey+'</td>'+
                                                                            '<td>'+result1[n].attrValue+'</td>'+
                                                                            '<td></td></tr>';
                                                                     }
                                                  }
                                           }
                                           });
                                 }else{
                                      if(result[n].attrValue == undefined){
                                          table += '<tr><td width=15px class="details-control sorting_1"></td>'+
                                                  '<td>'+result[n].eventType+'</td>'+
                                                  '<td>'+num+'</td>'+
                                                  '<td>参数</td>'+
                                                  '<td>'+result[n].assembleId+'</td>'+
                                                  '<td></td></tr>';
                                      }else {
                                          table += '<tr><td width=15px class="details-control sorting_1"></td>'+
                                                  '<td>'+result[n].eventType+'</td>'+
                                                  '<td>'+num+'</td>'+
                                                  '<td>参数</td>'+
                                                  '<td>'+result[n].assembleId+'</td>'+
                                                  '<td>'+result[n].attrValue+'</td></tr>';
                                      }
                                 }

                             }
                             table += '</table>';
                         }
                     });
                 }
                 return table;
             }

        });