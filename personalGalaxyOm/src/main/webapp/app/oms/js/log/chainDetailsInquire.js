var layer_look_index;
var dataP=[];
$(document).ready(function () {

        $.ajax({
                url : contextPath + "/findCircleInfoByTraceId?traceId="+traceId,
                async : false, // 注意此处需要同步，因为返回完数据后，下面才能让结果的第一条selected
                type : "POST",
                success : function(result) {
                    var dataJson = JSON.parse(result);
                    dataP = dataJson.data;
                }
        });

        drawLogCircle(dataP,50);
    });


