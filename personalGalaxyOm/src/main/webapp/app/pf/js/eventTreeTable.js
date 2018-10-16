$(document).ready(function() {
      if(parent.clickFlag===1){
        $.ajax({
         url:  contextPath+"/eventType/getIsStandardAllY",
         dataType : "json",
         async : false,
         success : function(json) {
          var data =json.data;
                 $("#prodTreeTable").append("<tr data-tt-id='1'><td><span class='folder'>IS_STANDARD</td><td>基础事件</td></tr>");
          for(i in data)
          {
              $("#prodTreeTable").append("<tr data-tt-id='1."+i+"' data-tt-parent-id='1'><td><span class='file' >"+data[i].eventType+"</td><td>"+data[i].eventDesc+"</td></tr>");
          }
         }
        });
      }else{
        $.ajax({
        url:  contextPath+"/prodType/selectByProdType?prodType="+parent.$("#prodType").val(),
       	type : "POST",
       	dataType : "json",
       	async : false,
       	success : function(dataGroup) {//动态创建treeTable 采用嵌套  treeTable不能自动识别层级，自动定位
       	    var data=dataGroup.data;   //用对象的值是个数组
       	    for(i in data){
                $("#prodTreeTable").append("<tr data-tt-id='"+i+"'><td><span class='folder'>"+data[i].prodType+"</td><td>"+data[i].prodDesc+"</td></tr>");
                $.ajax({
                        url: contextPath+"/eventType/getByBmodule?prodType="+data[i].prodType,
                        type : "POST",
                        dataType : "json",
                        async : false,
                        success :function(itemsGroup){
                            var items=itemsGroup.data;
                            for(j in items){
                                $("#prodTreeTable").append("<tr data-tt-id='"+i+"."+j+"' data-tt-parent-id='"+i+"'><td><span class='file' >"+items[j].eventType+"</td><td>"+items[j].eventDesc+"</td></tr>");  //添加ID方便后面更改样式
                            }
                        }
                });
       	    }
       	}
       });
       }
    //初始化
    $("#event").treetable({ expandable: true });
    //行选择高亮事件
     $("#event tbody").on("mousedown", "tr", function() {
        $(".selected").not(this).removeClass("selected");
        $(this).toggleClass("selected");
    });
    //行双击事件 并获取行数据 prodType prodDesc  添加到父页面
    $('#event tbody').on('dblclick', '.leaf[data-tt-parent-id]', function () {
        $(".selected").not(this).removeClass("selected");
        $(this).toggleClass("selected"); //行高亮事件
        var eventType=this.firstChild.textContent; //取值并操作
        var eventDesc=this.lastChild.textContent;
        parent.$("#eventType").val(eventType);
        parent.$("#eventDesc").val(eventDesc);
        if(parent.clickFlag===1){
               parent.$("#prodType").attr("disabled",true);
               parent.$("#prodDesc").attr("disabled",true);
               parent.$("#Bmodule").attr("disabled",true);
               parent.$("#eventClass").attr("disabled",false);
               parent.$("#processMethod").attr("disabled",false);
               parent.$("#status").attr("disabled",false);
        }
        layer.confirm("事件【类型："+eventType+"】【描述："+eventDesc+"】",function(){
            layer_close();
        });

    });
    
});

