function format ( d ) {
    // `d` is the original data object for the row
    var tempRows ="";
    if(d.providers){
         tempRows += formatChildData(d.providers);
    }
    if(d.consumers){
          tempRows +=  formatChildData(d.consumers);
    }
    if(d.routers){
          tempRows +=  formatChildData(d.routers);
    }
    return '<table class="childTable"  cellspacing="0" border="0"  style="background-color: #FDF7E6; padding-left:-2px; border-left: 1px solid #e6e6e6;border-collapse:separate;margin-bottom:-2px;margin-left:-2px;">'+
                                '<thead>'+
           '<tr>'+
           '<th>服务角色</th>'+
           '<th>URL地址</th>'+
           '<th>详细信息</th>'+
           '</tr>'+
           '</thead>'+tempRows+'</table>';
}
function formatChildData(children){
        var tempRows="";
        for(var i=0;i<children.length;i++){
            var child = children[i];
            var temp = '<tr>'+
                     '<th>'+child.id+'</th>'+
                     '<th>'+child.address+'</th>'+
                     '<th>'+child.info+'</th>'+
                     '</tr>';
            tempRows += temp;
        }
        return tempRows;
}
$(document).ready(function () {
    getPkList({
        url: contextPath + "/findMidwareDefaultCombox",
        id: "queryMidwareId",
        async: false,
        normalSelect:false,
        params: {
            midwareType : '0006002'
        }
    });
    var opt = getDataTableOpt($("#table_data"));
    opt.stateSave = true;
    opt.processing = true;
    opt.autoWidth = false;
    var table = $('#table_data').DataTable( {
            "columns": [
                {
                    "class":          'details-control',
                    "orderable":      false,
                    "data":           null,
                    "defaultContent": ''
                },
                { "data": "name" },
                { "data": "pNum" },
                { "data": "cNum" },
                { "data": "rNum" }
            ],
            "order": [[1, 'asc']],
            "processing":true,
            "autoWidth":false,
            "stateSave":true,
            "deferRender":true,
            "sPaginationType": "full_numbers",
            "oLanguage":{
            "sSearch": "",
            "sInfo": "<span>_START_</span> 到 <span>_END_</span> 共 <span>_TOTAL_</span> 条",
            "sLengthMenu": "_MENU_",
            "sInfoFiltered": "_MAX_",
            "sInfoEmpty": "<span>0</span> 到 <span>0</span> 共 <span>0</span> 条",
            "oPaginate": {
                "sFirst": "首页",
                "sLast": "尾页",
                "sNext": "下一页",
                "sPrevious": "上一页"
            }
            },
            'sDom': "lfrtip"
        } );
    //渲染tables
    drawDataTableWithChilds($("#table_data"));
    //界面美化tables
    $("#table_data").beautyUi({
        tableId: "table_data",
        needBtn: false
    });
    $('#table_data tbody').on('click', 'td.details-control', function () {
        var tr = $(this).closest('tr');
        var row = table.row( tr );
        if ( row.child.isShown() ) {
            row.child.hide();
            tr.removeClass('shown');
        }
        else {
            row.child( format(row.data()) );
            var childTable = row.child().find("table");
            //childTable.css({"border-collapse":"separate","margin-bottom":"-2px","margin-left":"-2px"});
            /*row.child().find("table,tr,td,th").css({ "background-color": "#FDF7E6" });*/
            row.child.show();
            tr.addClass('shown');
        }
    } );

    $(".select2").select2();
    searchRec();
     $("#queryMidwareId").change(function(){
       searchRec();
    });
});

var layer_add_index, layer_edit_index;

function showMsgDuringTime(msg)
{
    showMsgCloseLayer(msg,layer_add_index,layer_edit_index);
}

function searchRec() {
    var midwareId = $("#queryMidwareId").val();
    var userTab = $("#table_data").dataTable();
    var api = userTab.api();
    api.ajax.url(contextPath + "/findDubboInfoList?midwareId=" + midwareId).load();
}