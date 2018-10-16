var treeObj;
var setting = {
	data : {
		simpleData : {
			enable : true,
			idKey : "id",
			pIdKey : "parentId",
			rootpId : "-1"
		},
		key:{
			name: "name",
			title: "fullPath",
		}
	},
	 callback: {
             onClick: zTreeOnClick
     },
};
$(document).ready(function () {
    $('#tab-treeManagement').hide();
    $('#dataDiv').hide();
    var opt = getDataTableOpt($("#zookeeeperList"));
    opt.stateSave = true;
    opt.processing = true;
    opt.autoWidth = false;
    opt.ajax = {
        "url": contextPath + "/findZkIntant",
        "type": "POST"
    };
    //获取roleName
     	getPkList({
     		url:contextPath+"/findMidwareDefaultCombox",
     		id:"queryMidwareId",
     		async:false,
     		normalSelect:false,
     	    params: {
                      midwareType: '0006002'

                   }
     	});

     $(".select2").select2();

 $("#queryMidwareId").click(function () {
       if($('#queryMidwareId').val()==""){
          showMsg("请先选择zk集群",'warning');
       }else{
           $('#tab-treeManagement').show();
           $.post(contextPath + '/readAddress',{midwareId:$('#queryMidwareId').val()},function(result){
                 var result = eval('('+result+')');
                 if (result.errorMsg){
                     showMsg(result.errorMsg,'error');
                  }
                  $.fn.zTree.init($("#zkTree"), setting, result);
                    //$('#zkTree').tree('loadData',result);
                  $('#tab-treeManagement').show();
          });
        }
 });
});
 function  readZkUrl (){
     if ($('#queryMidwareId').val()==""){
           $("#cxnStr").val("");
     }else{
     $("#cxnStr").val("");
       var url =  contextPath + '/readZkUrl';
            $.post(url,{midwareId:$('#queryMidwareId').val()},function(result){
           		var result = eval('('+result+')');
           		if (result.errorMsg){
                      showMsg(result.errorMsg,'error');
                    }else{
           		       $("#cxnStr").val(result[0]);
                    }
            });
      $('#tab-treeManagement').show();
      $.post(contextPath + '/readAddress',{midwareId:$('#queryMidwareId').val()},function(result){
                var result = eval('('+result+')');
                if (result.errorMsg){
                    showMsg(result.errorMsg,'error');
                 }
               treeObj =  $.fn.zTree.init($("#zkTree"), setting, result);
                   //$('#zkTree').tree('loadData',result);
                 $('#tab-treeManagement').show();
         });
        }
    }

function zTreeOnClick(event, treeId, treeNode) {
    var path = treeNode.fullPath;
    $('#data').val('');
    $("#dataDiv").show();
//    $('#path').text(path);
     $.post(contextPath + '/getNodDate',{midwareId:$('#queryMidwareId').val(),path:path},function(result){
                    var result = eval('('+result+')');
                    if (result.errorMsg){
                        showMsg(result.errorMsg,'error');
                    }else{
                        if(result[0]==0){
                        alert("该路径下的节点数据为空");
                        }else{
                          showMsg(result[0],'info');
                        }
                    }
     });
}

function searchNode() {
    var path = $('#add_search').val();
    if (path && path !== '') {
        var nodes = treeObj.getNodesByParamFuzzy("name", path);
        if (nodes.length === 0) {
            showMsg('搜索的节点不存在！', 'warning');
            return;
        }
        if (confirm("搜索到的节点数量为：" + nodes.length + "，确定要展开节点吗？")) {
            for (var i = 0; i < nodes.length; i++) {
                if (nodes[i].check_Child_State === -1) {
                    var parentNode = nodes[i].getParentNode();
                    treeObj.expandNode(parentNode, true, null, true, null);
                }
                else {
                    treeObj.expandNode(nodes[i], true, null, true, null);
                }
            }
           treeObj.selectNode(nodes[0]);
        }
    } else {
        showMsg('搜索节点不能为空！', 'warning');
    }
}


