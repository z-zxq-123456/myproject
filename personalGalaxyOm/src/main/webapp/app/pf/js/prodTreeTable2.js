$(document).ready(function() {
    $.ajax({
       	url: contextPath+"/prod/getByLevel?prodClassLevel=1&Bmodule="+parent.$("#Bmodule").val(),
       	type : "POST",
       	dataType : "json",
       	async : false,
       	success : function(dataGroup) {//动态创建treeTable 采用嵌套  treeTable不能自动识别层级，自动定位
       	    var data=dataGroup.data;   //用对象的值是个数组
       	    for(i in data){
                   $("#prodTreeTable").append("<tr data-tt-id='"+i+"'><td><span class='folder'>"+data[i].prodClass+"</td><td>"+data[i].prodClassDesc+"</td></tr>");
                    $.ajax({
                           	url: contextPath+"/prod/getByParP?prodClassLevel=2&parentProdClass="+data[i].prodClass,
                           	type : "POST",
                           	dataType : "json",
                           	async : false,
                           	success :function(itemsGroup){
                           	    var items=itemsGroup.data;
                           	    for(j in items){
                                    $("#prodTreeTable").append("<tr data-tt-id='"+i+"."+j+"' data-tt-parent-id='"+i+"'><td><span class='file' id='second"+i+"_"+j+"'>"+items[j].prodClass+"</td><td>"+items[j].prodClassDesc+"</td></tr>");  //添加ID方便后面更改样式
                           	        $.ajax({
                                        url: contextPath+"/prodType/selectByProdClass2?prodClass="+items[j].prodClass,
                                        type : "POST",
                                        dataType : "json",
                                        async : false,
                                        success :function(resultGroup){
                                            var result=resultGroup.data;
                                            for(n in result){
                                                $("#prodTreeTable").append("<tr data-tt-id='"+i+"."+j+"."+n+"' data-tt-parent-id='"+i+"."+j+"'><td><span class='file'>"+result[n].prodType+"</td><td>"+result[n].prodDesc+"</td></tr>");
                                                $("#second"+i+"_"+j).attr("class","folder"); //如果是有子节点，样式变更为文件夹
                                            }
                                        }
                                    });

                           	    }
                           	}
                       });
       	    }
       	}
       });
    //初始化
    $("#prod").treetable({ expandable: true });
    //行选择高亮事件
    $("#prod tbody").on("mousedown", "tr", function() {
        $(".selected").not(this).removeClass("selected");
        $(this).toggleClass("selected");
    });
    //行双击事件 并获取行数据 prodType prodDesc  添加到父页面
    $('#prod tbody').on('dblclick', '.leaf[style]', function () {
        $(".selected").not(this).removeClass("selected");
        $(this).toggleClass("selected"); //行高亮事件
        var prodType=this.firstChild.textContent;
        var prodDesc=this.lastChild.textContent;
         parent.$("#prodType").val(prodType);
          parent.$("#prodDesc").val(prodDesc);
        layer.confirm("预制卡参数表【类型："+prodType+"】【描述："+prodDesc+"】",function(){
            layer_close();
        });

    });
    
});

