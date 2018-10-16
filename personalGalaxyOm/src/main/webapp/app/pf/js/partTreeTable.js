$(document).ready(function() {
           $.ajax({
              	url:  contextPath+"/part/getByBmodule?Bmodule="+parent.$("#Bmodule").val(),
              	type : "POST",
              	dataType : "json",
              	async : false,
              	success : function(dataGroup) {//动态创建treeTable 采用嵌套  treeTable不能自动识别层级，自动定位
              	    var data=dataGroup.data;   //用对象的值是个数组
              	    for(i in data){
                       $("#partTreeTable").append("<tr data-tt-id='"+i+"'><td><span class='folder'>"+data[i].partClass+"</td><td>"+data[i].partClassDesc+"</td></tr>");
                       $.ajax({
                               url: contextPath+"/partType/getByPartClass?partClass="+data[i].partClass,
                               type : "POST",
                               dataType : "json",
                               async : false,
                               success :function(itemsGroup){
                                   for(j in itemsGroup){
                                       $("#partTreeTable").append("<tr data-tt-id='"+i+"."+j+"' data-tt-parent-id='"+i+"'><td><span class='file' >"+itemsGroup[j].partType+"</td><td>"+itemsGroup[j].partDesc+"</td></tr>");  //添加ID方便后面更改样式
                                   }
                               }
                       });
              	    }
              	}
              });
    //初始化
    $("#part").treetable({ expandable: true });
    //行选择高亮事件
     $("#part tbody").on("mousedown", "tr", function() {
        $(".selected").not(this).removeClass("selected");
        $(this).toggleClass("selected");
    });
    //行双击事件 并获取行数据 prodType prodDesc  添加到父页面
    $('#part tbody').on('dblclick', '.leaf[data-tt-parent-id]', function () {
        $(".selected").not(this).removeClass("selected");
        $(this).toggleClass("selected"); //行高亮事件
        var partType=this.firstChild.textContent; //取值并操作
        var partDesc=this.lastChild.textContent;
        parent.$("#partType").val(partType);
        parent.$("#partDesc").val(partDesc);
        layer.confirm("指标【类型："+partType+"】【描述："+partDesc+"】",function(){
            layer_close();
        });

    });

});

