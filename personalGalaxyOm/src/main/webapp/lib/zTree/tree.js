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
			title:"fullPath"
		}
	},
	edit : {
		enable : false,
		showRemoveBtn : true,
		showRenameBtn : true
	},
	callback : {
		onClick : zTreeOnClick
	},

	async: {
		enable: true,
		url: "getTree",
		autoParam: ["fullPath=path"]
	}
		
};

//处理异步加载返回的节点属性信息
function ajaxDataFilter(treeId, parentNode, responseData) {
	if (responseData) {
		for ( var i = 0; i < responseData.length; i++) {
			if (responseData[i].parentId == 0) {
				responseData[i].isParent = "true";
			}
		}
	}
	return responseData;
}

function zTreeOnClick(event, treeId, treeNode) {
	var path = treeNode.fullPath;
	$('#data').val('');
	$('#path').text(path);
	$.ajax({
		url : contextPath + "/getNodeInfo",
		data:{"path": path},
		type : "post",
		dataType : "json",
		success : function(data) {
			$('#data').val(data.data);
			//alert(data.stat.mzxid)
			$('#czxid').text(data.stat.czxid);
			$('#mzxid').text(data.stat.mzxid);
			$('#ctime').text(getDateStr(data.stat.ctime,"yyyy-MM-dd h:m:s"));
			$('#mtime').text(getDateStr(data.stat.mtime,"yyyy-MM-dd h:m:s"));
			$('#version').text(data.stat.version);
			$('#cversion').text(data.stat.cversion);
			$('#aversion').text(data.stat.aversion);
			$('#ephemeralOwner').text(data.stat.ephemeralOwner);
			$('#dataLength').text(data.stat.dataLength);
			$('#numChildren').text(data.stat.numChildren);
			$('#pzxid').text(data.stat.pzxid);
		}
	});
}

function update() {
	var path = $('#path').text();
	if (path && path != '') {
		if (confirm("确定要修改么？")) {
			$.ajax({
				url : contextPath + "/updatePathData",
				type : "post",
				dataType : "json",
				data: {"path":path, "data": $('#data').val()},
				success : function(data) {
					if (data.isSuccess){
						alert(data.content);
						//刷新树
					} else {
						alert(data.content);
					} 
				}
			});
		}
	} else {
		showMsg('path不能为空！','warning');
	}
}

function add() {
	var path = $('#add_search').val();
	if (path && path != '') {
		if (confirm("确定要添加么？")) {
			$.ajax({
				url : contextPath + "/addPath",
				type : "post",
				dataType : "json",
				data: {"path":path, "data": $('#add_data').val(), "flag":$('#flag').val()},
				success : function(data) {
					if (data.isSuccess){
						alert(data.content);
						//刷新树
						loadTree();
					} else {
						alert(data.content);
					} 
				}
			});
		}
	} else {
		showMsg('path不能为空！','warning');
	}
}

function deletePath() {
    var path = $('#path').text();
	if (path && path != '') {
		if (confirm("确定要删除么？")) {
			$.ajax({
				url : contextPath + "/deletePath",
				type : "POST",
				dataType : "json",
				data: {
					"path":path
				},
				success : function(data) {
					alert(data.content);
					if (data.isSuccess){
						//清空节点及子节点
						var nodes = treeObj.getSelectedNodes();
						if (nodes && nodes.length>0) {
							treeObj.removeChildNodes(nodes[0]);
							treeObj.removeNode(nodes[0]);
						}
					}
				}
			});
		}
	} else {
		showMsg('path不能为空！','warning');
	}
}

function treeCollapseAll() {
    treeObj.expandAll(false);
}

function search() {
	var path = $('#add_search').val();
	if (path && path != '') {
		var nodes = treeObj.getNodesByParamFuzzy("name", path);
        if(nodes.length == 0)
        {
			showMsg('搜索的节点不存在！','warning');
			return;
        }
        if (confirm("搜索到的节点数量为：" + nodes.length + "，确定要展开节点吗？")) {
            for (var i = 0; i < nodes.length; i++) {
                if (nodes[i].check_Child_State == -1) {
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
		showMsg('搜索节点不能为空！','warning');
	}
}


var treeObj;
var tree;
function loadTree() {
	$.ajax({
		url : contextPath + "/readAddress",
		type : "get",
		dataType : "json",
		data: {
			cxnStr: ""
		},
		success : function(data) {
            if(data==null)
            {
                showMsg('连接ZK失败！','warning');
                return;
            }
            treeObj = $.fn.zTree.init($("#zkTree"), setting, data);
		}
	});
}

function readAddress() {
	if ($("#cxnStr").val() == "") {
		showMsg('请选择环境和ZK或输入链接地址！', 'warning');
		return;
	}
	var url = contextPath + "/readAddress";
	var index1;
	var reJson = null;
	$.ajax({
		url: url,
		type: "get",
		dataType: "json",
		data: {
			cxnStr: $("#cxnStr").val()
		},
		beforeSend: function () {
			index1 = layer.load(4, {
				shade: [0.4, '#777777'] //0.5透明度的白色背景
			});
		},
		success: function (json) {
			reJson = json;
			treeObj = $.fn.zTree.init($("#zkTree"), setting, json);
			$('#tab-treeManagement').show();
		},
		complete: function () {
			layer.close(index1);
			if (reJson == null) {
				showMsg('连接ZK失败！', 'warning');
				return;
			}
			reJson = null;
		}
	});
}

$(document).ready(function() {
    $('#tab-treeManagement').hide();
    getPkList({
        url:contextPath+"/pklist/getEnvId",
        id:"envId",
        async:false
    });
    getPkList({
        url:contextPath+"/pklist/getzkNameByEnvId?envId="+$("#envId").val(),
        id:"zkName",
        async:false,
		normalSelect:false
    });
	$(".select2").select2();
    // 初始化表单控件
    form = $("#readAddress").Validform({
        tiptype:2,
        callback:function(from){
            readAddress();
            return false;
        }
    });
    /*页面按钮根据权限实现隐藏*/
	pagePerm();
});

