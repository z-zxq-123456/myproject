 var localhref = location.href ;
    var flag;
    var nodeId;
    var step;
    var sub_url;
    var index_progress;
    var maxStep;
    $(document).ready(function () {
        var stepId;
        /*所有函数进行模块化整理*/
        /*页面初始化函数调用*/
        $.ajax({
            url: contextPath + "/unInterruptedUpdate?appId="+appId,
            type: "POST",
            dataType: "json",
            async: false,
            success: function (json) {
                stepId = json.currentNode.upgflowNodeSeq;
                maxStep = json.nodeList.length;
                var tempIndex;//获取当前节点的索引
                $.each(json.nodeList, function (i) {
                    var nodeSeq = json.nodeList[i].upgflowNodeSeq;
                    var nodeName = json.nodeList[i].upgflowNodeName;
                     if(nodeSeq == stepId){
                    	    tempIndex =i;
                      }
                    $("#myStep ul").append("<li><p><a  id='" + i + "'  " + "  onclick='selectStep(" + nodeSeq + ")'>" + nodeSeq + "  " + nodeName + "</a></p></li>");
                });

            }
        });
        //自定义控件
        step = $("#myStep").step({
            animate: true,
            initStep: stepId,
            speed: 1000,
            maxStepSize:maxStep
        });

        $("#stepMenu > li").each(function (index) {
            if ($(this).hasClass('step-current')) {
                nodeId = index + 1;
                selectStep(nodeId);
            }
        });
        $(".select2").select2();
    });
//加载url和按钮当SELECT选中时自动调用onSelectTab
    function selectStep(targetStep) {
        step.goStep(targetStep);
        $.post(contextPath + '/findNodeInfo', {appId: appId, upgflowNodeSeq: targetStep}, function (result) {
            var result = eval('(' + result + ')');
            refreshTabBtn(result.currentNode, result.selectNode);//根据TAB的upgflowNodeSeq去加载相应的按钮  入参currentNode为对应的按钮列表 ，selectNode为SELECT的当前节点信息
            refreshTabUrl(result.selectNode.upgflowNode);//刷新下一步的页面
            viewTab(result.currentNode);//根据当前节点序号比他小的节点默认可以打开的的选项卡面板，序号比他大的默认为禁用的选项卡面板 入参：currentNode为当前节点
        });

    }

    //根据TAB的upgflowNodeSeq去刷新相应的页面     入参 nodeInfo 为节点信息
     function  refreshTabUrl(nodeInfo){
    		   var url = contextPath + nodeInfo.upgflowNodeUrl;//调整url的格式
    		   document.getElementById("updateDivId").innerHTML =  createFrame(url,nodeInfo);
    		  //document.getElementById("test").src=url;
    }
    // 创建菜单项连接
        function createFrame(url,nodeInfo) {
        	var s = '<iframe scrolling="auto"  id="frame_' + nodeInfo.upgflowNodeSeq+'"  frameborder="0"  src="' + url+'?'+"id="+appId+"&name="+appName+"&flag="+flag
        	    + '" style="width:100%;height:99%;"></iframe>';
        	return s;
         }
    //加载每一步的按钮
    function refreshTabBtn(currentNode, selectNode) {
        var menulist = "";
        var btnId = "";
        var iframe = selectNode.upgflowNode.upgflowNodeSeq;
        $.each(selectNode.btnList, function (i, n) {
            btnId = createBtnId(n.upgndBtnName, n.upgndBtnId);
            menulist += "<a id='" + btnId + "' class='button-select L ml-20 mr-20'  onclick='openSelectTab(\"" + n.upgflowNodeId + "\",\"" + n.upgndBtnDirnodeid + "\",\"" + n.upgndBtnClass + "\",\"" + n.directNode.upgflowNodeSeq + "\",\"" + iframe + "\",\"" + n.upgndBtnFunc + "\")'>" + n.upgndBtnName + "</a>";
        });
        $("#addBtn").html(menulist);
        if (selectNode.upgflowNode.upgflowNodeSeq < currentNode.upgflowNodeSeq) {
            flag = 1;
            $.each(selectNode.btnList, function (i, n) {
                btnId = createBtnId(n.upgndBtnName, n.upgndBtnId);
                $("#" + btnId).attr("onclick", "");
                $("#" + btnId).attr("style", "background-color:#d4d4d4");
            });
        } else {
            flag = 0;//页面按钮是否置灰标志，等于0则不置灰
        }
    }
    // 根据当前节点序号比他小的节点默认可以打开的的选项卡面板，序号比他大的默认为禁用的选项卡面板 入参：currentNode为当前节点
    function viewTab(currentNode) {
        var currentSeq = currentNode.upgflowNodeSeq;
        $("#stepMenu > li").each(function (index) {
            if (index < currentSeq) {
                $("#" + index).attr("onclick", "selectStep(" + (index + 1) + ")");
            } else {
                $("#" + index).attr("onclick", "");
            }
        });
    }
    // 创建菜单项连接
    function createBtnId(BtnName, BtnId) {
        return BtnName + "" + BtnId;
    }
     //onclick事件函数，根据按钮跳转到相应的节点
     //入参upgflowNodeId：节点的Id,upgndBtnDirnodeid:跳转到下一个节点的Id,upgndBtnClass:按钮状态 directNode：节点序号frameId：frameId，upgndBtnFunc：按钮函数
    function openSelectTab(upgflowNodeId, upgndBtnDirnodeid, upgndBtnClass, directNode, frameId, upgndBtnFunc) {
        //当upgndBtnFunc字段不为空时，执行frameId对应的里面的函数，否则跳转到下一个节点
       if (null == upgndBtnFunc || upgndBtnFunc == "" || "undefined" == upgndBtnFunc) {
          exection(upgflowNodeId, upgndBtnDirnodeid, upgndBtnClass, directNode);
       } else {
          var frameId = "frame_" + frameId;
          var methodName = upgndBtnFunc + "()";
         // var test  = "document.getElementById("+frameId+")";
        //  var o = window.eval(frameId);
         document.getElementById(frameId).contentWindow.eval(methodName);
          ajxyExection(upgflowNodeId, upgndBtnDirnodeid, upgndBtnClass, directNode, frameId);
       }
    }
    //调用 对应的frameId里的函数入参upgflowNodeId：节点的Id,upgndBtnDirnodeid:跳转到下一个节点的Id,upgndBtnClass:按钮状态directNode：节点序号
    function ajxyExection(upgflowNodeId, upgndBtnDirnodeid, upgndBtnClass, directNode, frameId) {
     //  var tempFlag = window.eval(frameId).isSuccess;
       var tempFlag = document.getElementById(frameId).contentWindow.isSuccess;
        if (tempFlag == "001") {
            setTimeout("ajxyExection('" + upgflowNodeId + "','" + upgndBtnDirnodeid + "','" + upgndBtnClass + "','" + directNode + "','" + frameId + "')", 1000);
        }
        if (tempFlag == "002") {
            exection(upgflowNodeId, upgndBtnDirnodeid, upgndBtnClass, directNode);
        }
        if (tempFlag == "003") {
         return;
        }
    }
    //点击按钮，跳转到下一个节点，并打开相应的页面入参upgflowNodeId：节点的Id,upgndBtnDirnodeid:跳转到下一个节点的Id,upgndBtnClass:按钮状态directNode：节点序号
    function exection(upgflowNodeId, upgndBtnDirnodeid, upgndBtnClass, directNode) {
        $.post(contextPath + '/' + upgndBtnClass, {
            appId: appId,
            oldUpgflowNodeId: upgflowNodeId,
            upgflowNodeId: upgndBtnDirnodeid
        }, function (result) {
            var result = eval('(' + result + ')');
            if (result.errorMsg) {
                if(index_progress!=null) {
                    layer.close(index_progress);
                }
                showMsg(result.errorMsg, 'error');
            } else {//根据Tab的ID打开相应的URL
                if (upgflowNodeId != upgndBtnDirnodeid) {
                    selectStep(upgndBtnDirnodeid);
                    location.replace(location.href);

                }
            }
        });
    }

    function showMsgDuringTime(msg) {
        layer.msg(msg);
        setTimeout(function () {
            layer.closeAll('dialog');
        }, 1000);
        layer.close(index_progress);
    }