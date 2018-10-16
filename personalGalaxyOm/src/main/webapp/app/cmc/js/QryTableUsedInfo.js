
var datas;

$(document).ready(function() {
        $("#selectByProductRuleNo").click(function () {
            ABtable();
        });
    }
);


function  ABtable() {
    $.ajax({url:contextPath+ "/cardTableUsedInfo/getCardTableUsedInfo",
        data:{
            "productRuleNo":$("#productRuleNo").val(),
            "tableName":$("#tableName").val()
            },
        type:"post", dataType:"json",success: function(results) {
        datas = JSON.stringify(results.data);
            var obj = JSON.parse(datas);
            console.log("unUsedNum="+obj.totalNum-obj.used);
             getPieStatics(obj);
            getTableShow(obj);
    }
    });
}

function getTableShow(obj) {
    $("#totalNum").attr("value",obj.totalNum);
    $("#usedNum").attr("value",obj.usedNum);
}

//饼图
function getPieStatics(obj) {
    // 构建图表
    $('#container').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },

        title: {
            text: 'CARD ACOUNT'
        },

        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor)
                    },
                    connectorColor: 'silver'
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'precent',
            data: [
                ['unused',obj.totalNum-obj.usedNum],
                ['used',obj.usedNum]
            ]
        }]
    });
}


function getBarStatics(data){
    // ����ͼ��
    $('#container').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },

        title: {
            text: 'CARD ACOUNT'
        },

        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor)
                    },
                    connectorColor: 'silver'
                }
            }
        },
        series: [{
            type: 'bar',
            name: 'precent',
            data: [
                ['unused', data.used],
                ['used',   data.used]
            ]
        }]
    });
}