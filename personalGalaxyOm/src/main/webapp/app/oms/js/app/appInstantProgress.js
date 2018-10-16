    var sub_url = parent.getSubUrl();
    var row = parent.getRow();
    var isFinish;
    var interval;
    $(document).ready(function () {
        interval=setInterval(start, 1000);
        startOrStop(row);
    });
    function startOrStop(row) {
        $.post(sub_url, {
            appId: parent.$("#appId").val(),
            serIp: row.serIp,
            serUser: row.serUser,
            appIntantId: row.appIntantId,
            serOs: row.serOs,
            serPwd: row.serPwd,
            appPath: row.appPath,
            appWork: row.appWork,
            appIntantName: row.appIntantName,
            appIntantVer: row.appIntantVer,
            appSimpenNm: row.appSimpenNm
        }, function (result) {
            var result = eval('(' + result + ')');
            isFinish = true;
            if (result.errorMsg) {
                parent.showMsg(result.errorMsg, 'error');
                close();
            } else {

                stopInterval(interval);
                setTimeout(function(){
                    $("#progress").attr("style", "width:100%");
                    $("#progress").text("100%");
                    $("#progress-animated").html("已完成");
                    layer.msg("五秒后关闭此页面！");
                    setTimeout(close,5000);
                },500);

                parent.refresh();
            }
        });
    }
    function start() {
        var file_url = contextPath + '/findProgressNotify';
        $.post(file_url, function (result) {
            var result = eval('(' + result + ')');
            if (result.errorMsg) {
                showMsg(result.errorMsg, 'error');
            } else {
                var deployProgress = result.progressNum;
                var deployMessage = result.progressMessage;
                if (deployProgress <= 100) {
                    $("#progress").attr("style", "width:" + deployProgress + "%");
                    $("#progress").text(deployProgress + "%");
                    $("#progress-animated").html(deployMessage);
                }
            }
        });
    }
    function stopInterval(interval){
        clearInterval(interval);
    }
    function close() {
        var index = parent.layer.getFrameIndex(window.name);  //获取窗口索引
        parent.layer.close(index);  //关闭窗口
    }

function test(obj){
      searchRec(row);//查找日志
      var div1=document.getElementById("dlg");
      if(div1.style.display=="block"){
        div1.style.display="none";
       // obj.src="${ContextPath}/images/icon-jia.jpg";
       // document.getElementById('#stateBut_Id').innerText = "&#xe602";
        $("#stateBut_Id").html("查看日志：   &#xe609;");
      }else{
        div1.style.display="block";
        $("#stateBut_Id").html("查看日志：   &#xe607;");
      }
}
function searchRec(row){
       url = contextPath + "/checkLog";
       var  lineNumber= "10";//每次获取20条日志信息
       sendPostRequest(url,{
      		appIntantId:row.appIntantId,
      		lineNumber:lineNumber
       }, callback_dataEdit,"json");
}
function callback_dataEdit(json){
    $('#dlg').html('');
   // document.getElementById("dlg").innerHTML = "";//原始的js清空div的内容
	if (json.success){
      var nowTime = new Date().toLocaleTimeString();
      var divShow = $("#dlg");
      var length = json.data.length;
      for(var i=length-1;i>=0;i--){
	    var log = json.data[i];
			if(i==0) {
				log = "<font color='blue'>访问时间:"+nowTime+'</font><br><br>'+log;
			}
			if(i==length-1){
				log = log+'<br><br><br>';
			}
			$("#dlg").prepend(log+'<br>');
	  }
   }else if (json.errorMsg) {
		showMsg(json.errorMsg, 'errorMsg');
   }
}