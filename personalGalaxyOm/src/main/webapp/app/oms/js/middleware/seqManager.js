var layer_add_index ,layer_edit_index;
$(document).ready(function () {
    getPkList({
            url: contextPath + "/findMidwareCombox",
            id: "queryMidwareId",
            async: false,
            params: {midwareType: '0006001'}
        });

    var optredis = getDataTableOpt($("#redisKeyList"));

    optredis.ajax={
        url: contextPath+"/RedisKsy/findReqManager",
        data:{
            redisUrl:null,
            masterName:null
        },
        type:"POST"
    };
    optredis.columns=[
        { "data": "seqKey",
            "defaultContent" : ""},
        { "data": "seqValue",
            "defaultContent" : ""}
    ];
    //渲染tables
    setDataTableOpt($("#redisKeyList"),optredis);
    $("#redisKeyList").beautyUi({
                    tableId:"redisKeyList",
                    buttonName:["查询","添加", "修改","删除" ],
                    buttonId:["inquiry","add",  "edit","delete" ]
    });
      $("#inquiry").on("click",function(){
              readRedisKeyValues();
      });
      $("#add").on("click",function(){
             redisKey_add('添加','redisKeyAdd.jsp','600','400');
         });
      $("#edit").on("click",function(){
            redisKey_edit('修改','redisKeyEdit.jsp','600','400');
         });
      $("#delete").on("click",function(){
            redisKey_del();
         });
      $("#queryMidwareId").change(function(){
          if($("#queryMidwareId").val() == ""){
              showMsg('请先选择redis集群','warning');
          }else {
              $.post(contextPath+"/cache/findSentinelByMiddlewareId",{middlewareId:$("#queryMidwareId").val()},function(data){
                  var value=JSON.parse(data).masterName;
                  var values=[];
                  values=value.split(";");
                  $("#masterName").empty();
                  for(var i=0;i<values.length;i++){
                      $("#masterName").append("<option value='"+values[i]+"'>"+values[i]+"</option>");
                  }
                  $(".select2").select2();
                  $("#redisUrl").val(JSON.parse(data).sentinelUrl);
              });
          }
      });


    optcache = getDataTableOpt($("#cacheDataList"));
    optcache.ajax={
        url: contextPath+"/cache/findcache",
        data:{
            redisUrl:null,
            masterName:null,
            key:null,
            type:null,
            isRefresh:null
        },
        type:"POST"
    };
    optcache.columns=[
        { "data": "type" ,
            "defaultContent" : ""},
        { "data": "key" ,
            "defaultContent" : ""},
        { "data": "value" ,
            "defaultContent" : ""}
    ];
    //渲染tables
    setDataTableOpt($("#cacheDataList"),optcache);
    $("#cacheDataList").beautyUi({
                tableId:"cacheDataList",
                buttonName:["查询","刷新" ],
                buttonId:["query","refresh" ]
    });
     $("#query").on("click",function(){
               readRedisCatchKey(1);
                  });
     $("#refresh").on("click",function(){
                readRedisCatchKey(2);
                     });
    getPkList({
        url:contextPath+"/cache/findCacheType",
        id:"cacheType",
        async:false
    });

    $('.select2 ').select2();
    /*页面按钮根据权限实现隐藏*/
            pagePerm();
});


function readRedisCatchKey(type){
    if($("#redisUrl").val() == "" || $("#masterName").val() == "" ) {
        showMsg('请选择或者输入redis地址！', 'warning');
        return;
    }
    var isRefresh = "Y";
    if(type == 1){
        isRefresh = "N";
    }
    var redisCatchTab = $("#cacheDataList").dataTable();
    var apiCatch = redisCatchTab.api();
    var catchUrl = contextPath + "/cache/findcache?redisUrl=" + $("#redisUrl").val() + "&masterName=" + $("#masterName").val();

         var keyUrl="&cacheType=" + $("#cacheType").val()+"&cacheKey=" + $("#cacheKey").val();
         keyUrl +=  "&isRefresh=" + isRefresh;
         var redisUrl = catchUrl + keyUrl;
         apiCatch.ajax.url( redisUrl ).load();

}

function readRedisKeyValues() {
    if($("#redisUrl").val() == "" || $("#masterName").val() == "" ) {
        showMsg('请选择或者输入redis地址！', 'warning');
        return;
    }
    var redisTab = $("#redisKeyList").dataTable();
    var api = redisTab.api();
    var redisUrl = contextPath + "/RedisKsy/findReqManager?redisUrl=" + $("#redisUrl").val() + "&masterName=" + $("#masterName").val();
    api.ajax.url(redisUrl).load();
}

/*
 参数解释：
 title	标题
 url		请求的url
 id		需要操作的数据id
 w		弹出层宽度（缺省调默认值）
 h		弹出层高度（缺省调默认值）
 */
/*redis增加*/
function redisKey_add(title,url,w,h) {
    if($("#redisUrl").val() == "" || $("#masterName").val() == "" ) {
        showMsg('请选择或者输入redis地址！', 'warning');
        return;
    }

     layer_add_index =   layer.open({
            type: 2,
            content: url,
            area: [w+'px', h+'px'],
            end: function(){
                selectRedisKeyList();
            }
        });
}

function redisKey_del(){
    if ($("#redisKeyList").find(".selected").length!=1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    layer.confirm('确认要删除吗？', function () {
            redisKeyDel();
        });
}

function redisKeyDel(){
    var url = contextPath+"/RedisKsy/deleteReqManager";
    var rowData = $('#redisKeyList').DataTable().rows(".selected").data()[0];  //已经获取数据
    sendPostRequest(url, rowData, callback_redisKeyDel, "json");                //将获取数据发送给后台处理
}

function callback_redisKeyDel(json){
    if (json.success) {
        $('#redisKeyList').dataTable().api().row(".selected").remove().draw(false);
        showMsgDuringTime('删除成功！');
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

/*redis编辑*/
function redisKey_edit(title,url,w,h){
    if ($("#redisKeyList").find(".selected").length!=1){
        showMsg('请选择一行记录！','warning');
        return;
    }
    if(vaildTabData("redisKeyList")){
       layer_edit_index= layer.open({
            type: 2,
            content: url,
            area: [w+'px', h+'px'],
            end: function(){
                selectRedisKeyList();
            }
        });
    }
}

//异步获取规则查询信息
function selectRedisKeyList() {
    var redisTab = $("#redisKeyList").dataTable();
    var api = redisTab.api();
    api.ajax.reload();
}

function findRedisCatch(){
    if($("#redisUrl").val() == "" || $("#masterName").val() == "" ) {
        showMsg('请选择或者输入redis地址！', 'warning');
        return;
    }
    var url = contextPath+"/emptyCatch/findserverinfo";
    sendPostRequest(url, {
        redisUrl:$("#redisUrl").val(),
        masterName:$("#masterName").val()
    }, callback_findRedisCatch, "json");
   var url = contextPath+"/findRedisMemory";
    sendPostRequest(url, {
        midwareId:$("#queryMidwareId").val(),
        masterName:$("#masterName").val()
    }, callback_findRedisMemory, "json");
}

function callback_findRedisCatch(json){
        $("#keyNum").empty();
        $("#useMemory").empty();
        $("#keyNum").val(json.keyNum);
        $("#useMemory").val(json.useMemory);
}
function callback_findRedisMemory(json){
        $("#allMemory").empty();
        $("#allMemory").val(json.data.redisintMemory+"G");
}
function clearServerCatch(){
    if($("#redisUrl").val() == "" || $("#masterName").val() == "" ) {
        showMsg('请选择或者输入redis地址！', 'warning');
        return;
    }

    layer.confirm('确认要清空缓存吗？',function(){
        redisCatchempty();
    });
}

function redisCatchempty(){
    var url = contextPath+"/emptyCatch/clearserverinfo";
    sendPostRequest(url, {
        redisUrl:$("#redisUrl").val(),
        masterName:$("#masterName").val()
    }, callback_redisCatchempty, "json");                //将获取数据发送给后台处理
}

function callback_redisCatchempty(json){
    if (json.success) {
        showMsg(json.success, 'success');
        findRedisCatch();
    } else if (json.errorMsg) {
        showMsg(json.errorMsg, 'errorMsg');
    }
}

function showMsgDuringTime(msg)
{
    showMsgCloseLayer(msg,layer_add_index,layer_edit_index);
}