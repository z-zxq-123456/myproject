$(document).ready(function() {
    $.ajax({
       	url: contextPath+"/cardProdType/getAll?publishChannel="+parent.$("#publishChannel").val(),
       	type : "POST",
       	dataType : "json",
       	async : false,
       	success : function(json) {//动态创建treeTable 采用嵌套  treeTable不能自动识别层级，自动定位
       	    var data=JSON.parse(json.data);   //用对象的值是个数组
            var index;
       	    for(index in data){
       	        var prodType = data[index].cardProductType;
       	        var temp;
       	        if (prodType==="0"){
       	            temp = "虚户类";
                }else {
       	            temp = "实户类";
                }
                $("#prodTreeTable").append("<tr data-tt-id='"+index+"' class='leaf collapsed selected' style='display: table-row;'>" +
                    "<td><span class='file'>"+data[index].cardProductCode+"</td>" +
                    "<td>"+temp+"</td>"+
                    "<td>"+data[index].cardProductName+"</td></tr>");
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
        parent.clickFlag=2;
        layer.confirm("选择卡产品【产品编号："+prodType+"】【描述："+prodDesc+"】",function(){
            layer_close();
        });
    });
    
});
