$(document).ready(function() {
    $(".breadcrumb").data("needButton", "N");
	// 获取默认opt配置
	var opt = getDataTableOpt($("#flowInfoList"));
    opt.stateSave=true;
    opt.processing=true;
    opt.autoWidth=false;
    opt.deferRender=true;
	opt.ajax= {
         "url": contextPath + "/paraAcp/findList",
         "type": "POST"
     };
	opt.columns=[
        { "data": "CURRENTSYSTEM_TIME",
            "defaultContent" : "" },
        { "data": "TRANSACTION_DESC",
            "defaultContent" : "" },
        { "data": "CHECK_TEXT",
            "defaultContent" : "" },
        { "data": "OPERATOR_TYPE",
            "defaultContent" : "" },
        { "data": "APPROVE",
            "defaultContent" : "" },
        { "data": "OPERATOR_ID",
            "defaultContent" : "" },
        { "data": "CLIENT_IP",
            "defaultContent" : "" },
        { "data": "REQ_NO",
            "defaultContent" : "" },
        { "data": "TRANSACTION_ID",
            "defaultContent" : "" }
    ];
    opt.columnDefs=[
        {
            "render": function (data, type, row) {
                if (row.OPERATOR_TYPE == "A")
                    return '完成申请';
                else if (row.OPERATOR_TYPE == "C")
                    return '完成复核';
                else if (row.OPERATOR_TYPE == "P")
                    return '完成发布';
                else if (row.OPERATOR_TYPE == "R")
                    return '完成复核驳回';
                else if (row.OPERATOR_TYPE == "J")
                    return '完成发布驳回';
                else if (row.OPERATOR_TYPE == "E")
                    return '完成录入提交';
                else if (row.OPERATOR_TYPE == "F")
                    return '完成作废';
                else if (row.OPERATOR_TYPE == "S")
                    return '同主交易状态';
                else
                    return '不适用';
            },
            "targets": 3
        }, {
            "render": function (data, type, row) {
                if (row.APPROVE == "Y")
                    return '通过';
                else if (row.APPROVE == "N")
                    return '未通过';
                else if (row.APPROVE == "C")
                    return '作废';
                else if (row.APPROVE == "S")
                    return '子交易';
                else
                    return '不适用';
            },
            "targets": 4
        }
    ];
	//渲染tables
    drawDataTable($("#flowInfoList"),opt);
    $('#flowInfoList').beautyUi({
        tableId:"flowInfoList",
        buttonName:["查看差异"],
        buttonId:["compare"]
    });

    $("#flowInfoList").find('tbody').on('click', 'tr', function (e) {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        } else {
            $('#flowInfoList').find("tr").removeClass('selected');
            $(this).addClass('selected');
        }
        var rowData = $('#flowInfoList').DataTable().rows(".selected").data()[0];
        if(rowData==null)
        {
            $("#compare").hide();
            return;
        }
        if(rowData.OPERATOR_TYPE !== "C" && rowData.OPERATOR_TYPE !== "P")
        {
            $("#compare").hide();
            return;
        }
        $("#compare").show();
        $(".breadcrumb").data("reqNum",rowData.REQ_NO);
    });
    $('#flowInfoList').on('page.dt', function (e) {
        $('#flowInfoList').find("tr").removeClass('selected');
    });
    $("#compare").on("click",function(){
        differentShowButton();
    });
    today("#startDate");
    today("#endDate");
    $("#selectByPrimaryKey").click(function () {
        queryByDate();
    });
});

function differentShowButton() {
    if ($("#flowInfoList").find(".selected").length!==1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    var rowData = $('#flowInfoList').DataTable().rows(".selected").data()[0];
    if(rowData.OPERATOR_TYPE !== "C" && rowData.OPERATOR_TYPE !== "P")
    {
        showMsg('此历史数据，未完成复核或者发布','warning');
        return;
    }

    var transactionId = rowData.TRANSACTION_ID;
    var jspUrl;
    $.ajax({
        url: contextPath + "/paraFlowService/getFullInfoForTableOrg",
        type: "POST",
        dataType: "json",
        async: false,
        data: {
            transactionId: transactionId
        },
        success: function (json) {
            jspUrl = json.jspUrl;
            if(json.multiDB=="Y")
            {
                jspUrl = json.jspViewUrl;
            }
        }
    });
    var index = layer.open({
        type: 2,
        title: '查看差异数据',
        content: contextPath + "/" + jspUrl
    });
    layer.full(index);
}

function today(ele){
    var now=new Date();
    var m = now.getMonth()+1;
    var d = now.getDate();
    if (m<10) m = '0' + m;
    if (d<10) d = '0' + d;
    var time=now.getFullYear()+ m + d;
    $(ele).val(time);
}

function queryByDate()
{
    var attrTab = $("#flowInfoList").dataTable();
    var api = attrTab.api();
    if ( $("#startDate").val() == "" && $("#endDate").val() == "" ) {
        api.ajax.url(contextPath + "/paraAcp/findList").reload();
    } else if($("#startDate").val() == "" ||$("#endDate").val() == "") {
        alert('开始日期、终止日期不能只选一个！')
    }else{
        api.ajax.url(contextPath+"/paraAcp/findByDate?startDate="+$("#startDate").val() + "&endDate=" + $("#endDate").val()).load();
    }
}
