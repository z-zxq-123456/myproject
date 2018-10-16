var isFinish = parent.getIsFinish();
$(document).ready(function () {
    start();
});
function start(){
    var file_url=contextPath + '/findProgressNotify';
    $.post(file_url,{},function(result){
        var result = eval('(' + result + ')');
        if (result.errorMsg){
            parent.showMsgDuringTime(result.errorMsg);
        } else {
           var deployProgress = result.progressNum;
           console.log(deployProgress);
           var deployMessage=result.progressMessage;
           if (deployProgress <=100){
                $("#progress").attr("style","width:"+deployProgress+"%");
                $("#progress").text(deployProgress+"%");
                $("#progress-animated").html(deployMessage);
           }
           if(deployProgress>=100 || deployProgress==0){
             setTimeout(close,1000);
           }
        }
    });
    if(isFinish==false){
        setTimeout('start()',1000);
    }
}
function close(){
    layer_close();
}