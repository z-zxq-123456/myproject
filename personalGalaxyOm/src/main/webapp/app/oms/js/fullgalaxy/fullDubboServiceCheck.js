$(document).ready(function() {
        getPkList({
        			  url:contextPath + "/findMidwareDefaultCombox",
        			  id:"midwareId",
        			  params:{midwareType:'0006002'},
        			  normalSelect:false,
        			  async:false
        		});
     	var opt = getDataTableOpt($("#serviceList"));
     	opt.stateSave=true;
     	opt.processing=true;
     	opt.autoWidth=false;

     	opt.columns=[
             { "data": "dubboSide",
                 "defaultContent" : "" },
             { "data": "cosumerIP",
                 "defaultContent" : ""  },
             { "data": "dubboService",
                 "defaultContent" : ""  }
         ];
     	//渲染tables
     	drawDataTable($("#serviceList"),opt);
     	//界面美化tables
     	$("#serviceList").beautyUi({
            tableId:"serviceList",
            needBtn:false
         });
      	$('#serviceList tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#serviceList').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
         });
       $(".select2").select2();
       var midwareId = $("#midwareId").val();
        loadTable(midwareId);
        $("#midwareId").change(function(){
            var midwareId = $("#midwareId").val();
            loadTable(midwareId);
        });
});