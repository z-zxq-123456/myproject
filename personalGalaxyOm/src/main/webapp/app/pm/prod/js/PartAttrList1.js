      $(document).ready(function() {
                var rowData;
              	// 获取默认opt配置
              	var opt = getDataTableOpt($("#partList"));
              	opt.stateSave=true;
              	opt.processing=true;
              	opt.autoWidth=false;
              	rowData = parent.$('#partList').DataTable().rows(".selected").data()[0];
              	var url = contextPath + "/partCon/getByPartType";
              	if(parent.$("#contrast").data("constrastFlag")==true){
					url = contextPath + "/partCon/getByPartTypeContrast";
              	}
              	opt.ajax= {
              			 "url": url,
              			 "type": "POST",
              			 "data":{partType: rowData.partType,partClass: rowData.partClass}
                     	 };
              	opt.columns=[
              	      	    {
                                  "className":      'details-control',
                                  "orderable":      false,
                                  "data":           null,
                                  "defaultContent": ''
                              },
                      { "data": "attrKey", "defaultContent":"" },
                      { "data": "name", "defaultContent":"" }
                  ];
              	//渲染tables
              	drawDataTable($("#partList"),opt);
                dataTableUi("partList",["查询","添加","修改","删除"],["select","add","edit","delete"]);
                   $("#add").hide();
                   $("#edit").hide();
                   $("#delete").hide();
                   $("#select").hide();

                   //子行数据展开收缩操作函数
                   $('#partList tbody').on('click', 'td.details-control', function () {
                       var tr = $(this).closest('tr');
                       var dataTable=$("#partList").DataTable();
                       var row = dataTable.row( tr );
                       if ( row.child.isShown() ) {
                           row.child.hide();
                           tr.removeClass('shown');
                       }
                       else {
                           tr.addClass('shown');
                       }
                   });
            	getPkList({
            		url:contextPath+"/partType/getForPkList",
            		id:"partClass",
            		async:false
            	});
            	if (parent.$("#partList").find(".selected").length==1){
            		rowData = parent.$('#partList').DataTable().rows(".selected").data()[0];
            	}
            	if (rowData){
            		$("#partType").val(rowData.partType);
            		$("#partDesc").val(rowData.partDesc);
            		$("#partClass").val(rowData.partClass);
            		$("#status").val(rowData.status);
            		$("#isStandard").val(rowData.isStandard);
            		$("#processMethod").val(rowData.processMethod);
            	}
                   $(".select2").select2();
                });