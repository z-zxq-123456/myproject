$(document).ready(function() {
     	var opt = getDataTableOpt($("#instantViewList"));
     	opt.stateSave=true;
     	opt.processing=true;
     	opt.autoWidth=false;

     	opt.ajax= {
              "url": contextPath + "/findFullAppIntant",
              "type": "POST"
          };
     	opt.columns=[
             { "data": "appName",
                 "defaultContent" : "" },
             { "data": "appIntantName",
                 "defaultContent" : ""  },
             { "data": "serIp",
                 "defaultContent" : ""  },
             { "data": "appIntantVer",
                 "defaultContent" : ""  },
              { "data": "appIntantStatusName",
                  "defaultContent" : "" },
              { "data": "currentAppIntantStatus",
                  "defaultContent" : ""  },
              { "data": "healthMessage",
                   "defaultContent" : "" },
              { "data": "appPath",
                  "defaultContent" : ""  },
              { "data": "appWork",
                   "defaultContent" : "" }
         ];
         opt.columnDefs=[
                 {
                     "render": function ( data, type, row ) {
                         if(row.appIntantName)
                            return data;
                         else
                             return "";
                     },
                     "targets": 1
                 },
                 {
                      "render": function ( data, type, row ) {
                          if(row.serIp)
                             return data;
                          else
                              return "";
                      },
                      "targets": 2
                 },{
                     "render": function ( data, type, row ) {
                         if(row.appIntantStatusName)
                            return data;
                         else
                             return "";
                     },
                     "targets": 4
                },{
                       "render": function ( data, type, row ) {
                           if(row.currentAppIntantStatus)
                              return data;
                           else
                               return "";
                       },
                       "targets": 5
                  },{
                       "render": function ( data, type, row ) {
                           if(row.healthMessage)
                              return data;
                           else
                               return "";
                       },
                       "targets": 6
                  },{
                       "render": function ( data, type, row ) {
                           if(row.appWork)
                              return data;
                           else
                               return "";
                       },
                       "targets": 8
                  },
         ];
     	//渲染tables
     	drawDataTable($("#instantViewList"),opt);
     	//界面美化tables
     	$("#instantViewList").beautyUi({
            tableId:"instantViewList",
              buttonName: ["刷新"],
              buttonId: ["reference"]
         });
      	$('#instantViewList tbody').on('click', 'tr', function (e) {
             if ( $(this).hasClass('selected') ) {
                 $(this).removeClass('selected');
             } else {
                 $('#instantViewList').find("tr").removeClass('selected');
                 $(this).addClass('selected');
             }
         });
         $(".select2").select2();
         $("#reference").on("click",function(){
                     reference();
                 });
});
function reference(){
      var userTab = $("#instantViewList").dataTable();
      var api = userTab.api();
      api.ajax.url(contextPath + "/findFullAppIntant").load();
}
