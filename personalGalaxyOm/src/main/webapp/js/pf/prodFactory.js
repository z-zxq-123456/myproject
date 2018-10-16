/**
prodFactory base functions ddd
**/
var a = 0;
$.getScript('${ContextPath}/lib/select2-4.0.2/dist/js/select2.min.js');
function showAttrDiv(valueMethod, setValueFlag, attrDesc,seqNo, assembleId, optionFlag) {
    var div = "";
    if (valueMethod === "FD") {
        div = '<div class="span2 attr" id="' + assembleId + '" optionFlag="' + optionFlag + '" setValueFlag="' + setValueFlag + '" seqNo="' + seqNo + '"><div class="portlet box green" ><div class="portlet-title"><div class="caption" showDes="' + attrDesc + '">' + attrDesc + '</div><div class="tools"><a href="javascript:;" class="collapse"></a><a href="javascript:;" class="remove"></a></div></div><div class="portlet-body"><div class="controls scroller"><input id="s_' + assembleId + '" name="s_' + assembleId + '" type="text" class="m-wrap" placeholder="属性值"  datatype="*1-40" ></div></div></div></div>';
    } else if (valueMethod === "YN") {
        div = '<div class="span2 attr" id="' + assembleId + '" optionFlag="' + optionFlag + '" setValueFlag="' + setValueFlag + '" seqNo="' + seqNo + '"><div class="portlet box green" ><div class="portlet-title"><div class="caption" showDes="' + attrDesc + '">' + attrDesc + '</div><div class="tools"><a href="javascript:;" class="collapse"></a><a href="javascript:;" class="remove"></a></div></div><div class="portlet-body"><div class="controls scroller"><select id="s_' + assembleId + '" name="s_' + assembleId + '" class="m-wrap" tabindex="1"><option value="">请选择</option><option value="Y">Y-是</option><option value="N">N-否</option></select></div></div></div></div>';
    } else if (valueMethod === "VL") {
      if(setValueFlag === "M"){
              div = '<div class="span2 attr" id="' + assembleId + '" optionFlag="' + optionFlag + '" setValueFlag="' + setValueFlag + '" seqNo="' + seqNo + '"><div class="portlet box green"><div class="portlet-title"><div class="caption" showDes="' + attrDesc + '">' + attrDesc + '</div><div class="tools"><a href="javascript:;" class="collapse"></a><a href="javascript:;" class="remove"></a></div></div><div class="portlet-body"><div class="controls scroller"><select id="s_' + assembleId + '" name="s_' + assembleId + '" class="m-wrap select2" multiple="multiple" tabindex="1"><option value="">请选择</option>';
      }else{
              div = '<div class="span2 attr" id="' + assembleId + '" optionFlag="' + optionFlag + '" setValueFlag="' + setValueFlag + '" seqNo="' + seqNo + '"><div class="portlet box green"><div class="portlet-title"><div class="caption" showDes="' + attrDesc + '">' + attrDesc + '</div><div class="tools"><a href="javascript:;" class="collapse"></a><a href="javascript:;" class="remove"></a></div></div><div class="portlet-body"><div class="controls scroller"><select id="s_' + assembleId + '" name="s_' + assembleId + '" class="m-wrap" tabindex="1"><option value="">请选择</option>';
      }
       var url = contextPath + "/partCon/getValueList";
        $.ajax({
            url: url,
            data: "attrKey=" + assembleId,
            success: function(attrValRet) {
                attrValRet = eval(attrValRet);
                var str = "";
                for (var j = 0; j < attrValRet.length; j++) {
                    str += '<option  value="' + attrValRet[j].attrValue + '" >' + attrValRet[j].attrValue + '-' + attrValRet[j].valueDesc + '</option>';
                }
                div += str;
                div += '</select></div></div></div></div>';
            },
            dataType: "json",
            async: false
        });

    } else if (valueMethod === "RF") {
      if(setValueFlag === "M"){
              div = '<div class="span2 attr" id="' + assembleId + '" optionFlag="' + optionFlag + '" setValueFlag="' + setValueFlag + '" seqNo="' + seqNo + '"><div class="portlet box green"><div class="portlet-title"><div class="caption" showDes="' + attrDesc + '">' + attrDesc + '</div><div class="tools"><a href="javascript:;" class="collapse"></a><a href="javascript:;" class="remove"></a></div></div><div class="portlet-body"><div class="controls scroller"><select id="s_' + assembleId + '" name="s_' + assembleId + '" class="m-wrap select2" multiple="multiple" tabindex="1"><option value="">请选择</option>';
      }else{
              div = '<div class="span2 attr" id="' + assembleId + '" optionFlag="' + optionFlag + '" setValueFlag="' + setValueFlag + '" seqNo="' + seqNo + '"><div class="portlet box green" ><div class="portlet-title"><div class="caption" showDes="' + attrDesc + '">' + attrDesc + '</div><div class="tools"><a href="javascript:;" class="collapse"></a><a href="javascript:;" class="remove"></a></div></div><div class="portlet-body"><div class="controls scroller"><select id="s_' + assembleId + '" name="s_' + assembleId + '" class="m-wrap" tabindex="1"><option value="">请选择</option>';
      }
        url = contextPath + "/partCon/getRfValueList";
        $.ajax({
            url: url,
            data: "attrKey=" + assembleId,
            success: function(attrValRet) {
                attrValRet = eval(attrValRet);
                var str = "";
                for (var j = 0; j < attrValRet.length; j++) {
                    str += '<option  value="' + attrValRet[j].attrValue + '" >' + attrValRet[j].attrValue + '-' + attrValRet[j].valueDesc + '</option>';
                }
                div += str;
                div += '</select></div></div></div></div>';
            },
            dataType: "json",
            async: false
        });
    }
    return div;
}

function showAttrDiv1(assembleId, attrDesc, optionFlag ,seqNo) {
    var div = '<div class="span2" id="' + assembleId + '" optionFlag="' + optionFlag + '" seqNo="' + seqNo + '"><div class="portlet box green"><div class="portlet-title"><div class="tools" ><a href="javascript:;" class="remove"></a></div><div class="m-wrap" >' + attrDesc + '</div></div></div></div>';
    return div;
}

function showAttrDivSimple(assembleType, assembleId, attrDesc, attrValue) {
    var div = '<div class="span2" style="width:120px"><div class="portlet box green"><div class="portlet-title" >' + attrDesc + '</div><div class="portlet-body">' + attrValue + '</div></div></div>';
    return div;
}
function showAttrDivAll(assembleType, assembleId, attrDesc, attrValue) {
    var div = '<div class="span2" style="width:120px"><div class="portlet box green"><div class="portlet-title" >' + assembleType + '</div><div class="portlet-body">' + attrDesc + '</div><div class="portlet-body">' + attrValue + '</div></div></div>';
    return div;
}

function showEventDiv(assembleId, eventDesc, eventClass, seqNo, prodData ,optionFlag) {
  var div = "";
  var attrDiv = "<div><form><table><tr>";
  var partDiv = '<div><form><table><tr>';
  var attrStr = "";
  var partStr = "";
  var m = 0;
  var n = 0;
   div = '<div class="span20 event" id="' + assembleId + '" eventClass="' + eventClass + '" optionFlag="' + optionFlag + '" seqNo="' + seqNo + '"><div class="portlet event box brown" ><div class="portlet-title"><div class="caption" showDes="' + eventDesc + '">' + eventDesc + '</div><div class="tools"><a href="javascript:;" class="expand"></a><a href="javascript:;" class="remove event"></a></div></div><div class="portlet-body event"><div class="controls scroller">';
   for (var j = 0; j < prodData.length; j++){
      if(prodData[j].assembleType === "ATTR"){

      attrStr = '<td>'+showAttrDiv11(assembleId,prodData[j].valueMethod,prodData[j].attrDesc,prodData[j].assembleId,prodData[j].assembleRule,prodData[j].seqNo,prodData[j].setValueFlag, "O")+'</td>';
       attrDiv +=attrStr;
       m++;
      }else if(prodData[j].assembleType === "PART"){
       partStr = '<td style="vertical-align:top">'+showPartDiv11(assembleId,prodData[j].assembleId, prodData[j].partDesc,prodData[j].assembleRule,prodData[j].seqNo,prodData[j].data,"O")+'</td>';
       partDiv +=partStr;
       n++;
      }
       if( m % 5 === 0){
        attrDiv +='</tr><tr>';
       }
       if((n + 1)%5 === 0){
         partDiv +='</tr><tr>';
       }
   }
     attrDiv +='</tr></table></form></div>'
     partDiv +='</tr></table></form></div>'
     div += attrDiv;
     div += partDiv;
     div += '</div></div></div></div>';
     return div;
}
function showAttrDiv11(eventType,valueMethod, attrDesc, assembleId, assembleRule, seqNo,setValueFlag, optionFlag) {
    var div = "";
    var url;
    if (valueMethod === "FD") {
        div = '<div class="span20 attr" style="width:105px;" id="' + assembleId + '" optionFlag="' + optionFlag + '" assembleRule="' + assembleRule + '" setValueFlag="' + setValueFlag + '" seqNo="' + seqNo + '"><div class="portlet attrE box green"><div class="portlet-title"><div class="caption" showDes="' + attrDesc + '">' + attrDesc + '</div><div class="tools"><a href="javascript:;" class="collapse"></a><a href="javascript:;" class="remove"></a></div></div><div class="portlet-body"><div class="controls scroller"><input id="q_' + eventType + assembleId + '" name="q_' + eventType + assembleId + '" type="text" class="m-wrap" placeholder="属性值"  datatype="*1-40" ></div></div></div></div>';
    } else if (valueMethod === "YN") {
        div = '<div class="span20 attr" style="width:105px;" id="' + assembleId + '" optionFlag="' + optionFlag + '" assembleRule="' + assembleRule + '" setValueFlag="' + setValueFlag + '" seqNo="' + seqNo + '"><div class="portlet attrE box green"><div class="portlet-title"><div class="caption" showDes="' + attrDesc + '">' + attrDesc + '</div><div class="tools"><a href="javascript:;" class="collapse"></a><a href="javascript:;" class="remove"></a></div></div><div class="portlet-body"><div class="controls scroller"><select id="q_' + eventType + assembleId + '" name="q_' + eventType + assembleId + '" class="m-wrap" tabindex="1"><option value="">请选择</option><option value="Y">Y-是</option><option value="N">N-否</option></select></div></div></div></div>';
    } else if (valueMethod === "VL") {
          if(setValueFlag === "M"){
                 div = '<div class="span20 attr" style="width:105px;" id="' + assembleId + '" optionFlag="' + optionFlag + '" assembleRule="' + assembleRule + '" setValueFlag="' + setValueFlag + '" seqNo="' + seqNo + '"><div class="portlet attrE box green"><div class="portlet-title"><div class="caption" showDes="' + attrDesc + '">' + attrDesc + '</div><div class="tools"><a href="javascript:;" class="collapse"></a><a href="javascript:;" class="remove"></a></div></div><div class="portlet-body"><div class="controls scroller"><select id="q_' + eventType + assembleId + '" name="q_' + eventType + assembleId + '" class="m-wrap  select2" multiple="multiple" tabindex="1"><option value="">请选择</option>';
          }else{
                 div = '<div class="span20 attr" style="width:105px;" id="' + assembleId + '" optionFlag="' + optionFlag + '" assembleRule="' + assembleRule + '" setValueFlag="' + setValueFlag + '" seqNo="' + seqNo + '"><div class="portlet attrE box green"><div class="portlet-title"><div class="caption" showDes="' + attrDesc + '">' + attrDesc + '</div><div class="tools"><a href="javascript:;" class="collapse"></a><a href="javascript:;" class="remove"></a></div></div><div class="portlet-body"><div class="controls scroller"><select id="q_' + eventType + assembleId + '" name="q_' + eventType + assembleId + '" class="m-wrap" tabindex="1"><option value="">请选择</option>';
          }
        url = contextPath + "/partCon/getValueList";
        $.ajax({
            url: url,
            data: "attrKey=" + assembleId,
            success: function(attrValRet) {
                attrValRet = eval(attrValRet);
                var str = "";
                for (var j = 0; j < attrValRet.length; j++) {
                    str += '<option  value="' + attrValRet[j].attrValue + '" >' + attrValRet[j].attrValue + '-' + attrValRet[j].valueDesc + '</option>';
                }
                div += str;
                div += '</select></div></div></div></div>';
            },
            dataType: "json",
            async: false
        });
    } else if (valueMethod === "RF") {
      if(setValueFlag === "M"){
      div = '<div class="span20 attr" style="width:105px;" id="' + assembleId + '" optionFlag="' + optionFlag + '" assembleRule="' + assembleRule + '" setValueFlag="' + setValueFlag + '" seqNo="' + seqNo + '"><div class="portlet attrE box green" ><div class="portlet-title"><div class="caption" showDes="' + attrDesc + '">' + attrDesc + '</div><div class="tools"><a href="javascript:;" class="collapse"></a><a href="javascript:;" class="remove"></a></div></div><div class="portlet-body"><div class="controls scroller"><select id="q_' + eventType + assembleId + '" name="q_' + eventType + assembleId + '" class="m-wrap select2" multiple="multiple" tabindex="1"><option value="">请选择</option>';
      }else{
        div = '<div class="span20 attr" style="width:105px;" id="' + assembleId + '" optionFlag="' + optionFlag + '" assembleRule="' + assembleRule + '" setValueFlag="' + setValueFlag + '" seqNo="' + seqNo + '"><div class="portlet attrE box green" ><div class="portlet-title"><div class="caption" showDes="' + attrDesc + '">' + attrDesc + '</div><div class="tools"><a href="javascript:;" class="collapse"></a><a href="javascript:;" class="remove"></a></div></div><div class="portlet-body"><div class="controls scroller"><select id="q_' + eventType + assembleId + '" name="q_' + eventType + assembleId + '" class="m-wrap" tabindex="1"><option value="">请选择</option>';
      }
        url = contextPath + "/partCon/getRfValueList";
        $.ajax({
            url: url,
            data: "attrKey=" + assembleId,
            success: function(attrValRet) {
                attrValRet = eval(attrValRet);
                var str = "";
                for (var j = 0; j < attrValRet.length; j++) {
                    str += '<option  value="' + attrValRet[j].attrValue + '" >' + attrValRet[j].attrValue + '-' + attrValRet[j].valueDesc + '</option>';
                }
                div += str;
                div += '</select></div></div></div></div>';
            },
            dataType: "json",
            async: false
        });
    }
    return div;
}
function showPartDiv11(eventType,assembleId, partDesc,assembleRule,seqNo,data,optionFlag) {
   var div = "";
          div = '<div class="span20 part" style="width:120px;" id="' + assembleId + '" optionFlag="' + optionFlag + '" assembleRule="' + assembleRule + '" seqNo="' + seqNo + '"><div class="portlet partE box purple" ><div class="portlet-title"><div class="caption" showDes="' + partDesc + '">' + partDesc + '</div><div class="tools"><a href="javascript:;" class="collapse"></a><a href="javascript:;" class="remove"></a></div></div><div class="portlet-body"><div class="controls scroller"><form id="attr-form"><table>';
       for(var i=0;i<data.length;i++){
          var str = "";
          var id='p_' + eventType + data[i].attrKey ;
         if (data[i].valueMethod === "YN"){
          str = '<tr><td style="font-size:10px">'+data[i].attrDesc+'：</td></tr><tr><td><select id='+id+' name="' + data[i].attrKey + '" partId="' + assembleId + '" setValueFlag="' + data[i].setValueFlag + '" class="pf-wrap attrs" tabindex="1"><option value="">请选择</option><option value="Y">Y-是</option><option value="N">N-否</option></select></td></tr>';

         }else if (data[i].valueMethod === "FD") {
           str = '<tr><td style="font-size:10px">'+data[i].attrDesc+'：</td></tr><tr><td><input id='+id+' name="' + data[i].attrKey + '" partId="' + assembleId + '" setValueFlag="' + data[i].setValueFlag + '" type="text" class="pf-wrap attrs" placeholder="属性值"  datatype="*1-40" ></td></tr>';
         }else if (data[i].valueMethod === "VL") {
           if(data[i].setValueFlag === "M"){
                   str = '<tr><td style="font-size:10px">'+data[i].attrDesc+'：</td></tr><tr><td><select id='+id+' name="' + data[i].attrKey + '" partId="' + assembleId + '" setValueFlag="' + data[i].setValueFlag + '" class="pf-wrap attrs select2" multiple="multiple" tabindex="1"><option value="">请选择</option>';
           }else{
                  str = '<tr><td style="font-size:10px">'+data[i].attrDesc+'：</td></tr><tr><td><select id='+id+' name="' + data[i].attrKey + '" partId="' + assembleId + '" setValueFlag="' + data[i].setValueFlag + '" class="pf-wrap attrs" tabindex="1"><option value="">请选择</option>';
           }
                  url = contextPath + "/partCon/getValueList";
                  $.ajax({
                      url: url,
                      data: "attrKey=" + data[i].attrKey,
                      success: function(attrValRet) {
                          attrValRet = eval(attrValRet);
                          var belong = "";
                          for (var j = 0; j < attrValRet.length; j++) {
                              belong += '<option  value="' + attrValRet[j].attrValue + '" >' + attrValRet[j].attrValue + '-' + attrValRet[j].valueDesc + '</option>';
                          }
                          str += belong;
                          str += '</td></tr></select>';
                      },
                      dataType: "json",
                      async: false
                  });
       } else if (data[i].valueMethod === "RF") {
          if(data[i].setValueFlag === "M"){
                  str = '<tr><td style="font-size:10px">'+data[i].attrDesc+'：</td></tr><tr><td><select id='+id+' name="' + data[i].attrKey + '" partId="' + assembleId + '" setValueFlag="' + data[i].setValueFlag + '" class="pf-wrap attrs select2" multiple="multiple" tabindex="1"><option value="">请选择</option>';
          }else{
                  str = '<tr><td style="font-size:10px">'+data[i].attrDesc+'：</td></tr><tr><td><select id='+id+' name="' + data[i].attrKey + '" partId="' + assembleId + '" setValueFlag="' + data[i].setValueFlag + '" class="pf-wrap attrs" tabindex="1"><option value="">请选择</option>';
          }
                 url = contextPath + "/partCon/getRfValueList";
                 $.ajax({
                     url: url,
                     data: "attrKey=" + data[i].attrKey,
                     success: function(attrValRet) {
                         attrValRet = eval(attrValRet);
                         var bong = "";
                         for (var j = 0; j < attrValRet.length; j++) {
                            bong += '<option  value="' + attrValRet[j].attrValue + '" >' + attrValRet[j].attrValue + '-' + attrValRet[j].valueDesc + '</option>';
                         }
                         str += bong;
                         str += '</select></td></tr>';
                     },
                     dataType: "json",
                     async: false
                 });
             }

         div +=str;
        }
        div += '</table></form></div></div></div></div>';
   return div;
}
function showPartDiv(assembleId, partDesc, optionFlag) {
    var url = "partAttrList.jsp";
    var div = '<div class="span2" id="' + assembleId + '" optionFlag="' + optionFlag + '" ><div class="portlet box purple" ><div class="portlet-title"><div class="tools" ><a href="javascript:;" class="remove"></a></div><div class="m-wrap" onclick="mbPartAttrAll1(\'' + url + '\',\'' + assembleId + '\',\'' + optionFlag + '\')">' + partDesc + '</div></div></div></div>';
    return div;
}
function showPartDiv1(assembleId, partDesc,seqNo,data,optionFlag) {
   var div = "";
          div = '<div class="span2 part" id="' + assembleId + '" optionFlag="' + optionFlag + '" seqNo="' + seqNo + '"><div class="portlet box purple" ><div class="portlet-title"><div class="caption" showDes="' + partDesc + '">' + partDesc + '</div><div class="tools"><a href="javascript:;" class="collapse"></a><a href="javascript:;" class="remove"></a></div></div><div class="portlet-body"><div class="controls scroller"><form id="attr-form"><table>';
       for(var i=0;i<data.length;i++){
          var str = "";
         if (data[i].valueMethod === "YN"){
          str = '<tr><td style="font-size:10px">'+data[i].attrDesc+'：</td></tr><tr><td><select id="s_' + data[i].attrKey + '" name="' + data[i].attrKey + '" partId="' + assembleId + '" setValueFlag="' + data[i].setValueFlag + '" class="pf-wrap attrs" tabindex="1"><option value="">请选择</option><option value="Y">Y-是</option><option value="N">N-否</option></select></td></tr>';
         }else if (data[i].valueMethod === "FD") {
           str = '<tr><td style="font-size:10px">'+data[i].attrDesc+'：</td></tr><tr><td><input id="s_' + data[i].attrKey + '" name="' + data[i].attrKey + '" partId="' + assembleId + '" setValueFlag="' + data[i].setValueFlag + '" type="text" class="pf-wrap attrs" placeholder="属性值"  datatype="*1-40" ></td></tr>';
         }else if (data[i].valueMethod === "VL") {
              if(data[i].setValueFlag === "M"){
                      str = '<tr><td style="font-size:10px">'+data[i].attrDesc+'：</td></tr><tr><td><select id="s_' + data[i].attrKey + '" name="' + data[i].attrKey + '" partId="' + assembleId + '" setValueFlag="' + data[i].setValueFlag + '" class="pf-wrap attrs select2" multiple="multiple" tabindex="1"><option value="">请选择</option>';
              }else{
                     str = '<tr><td style="font-size:10px">'+data[i].attrDesc+'：</td></tr><tr><td><select id="s_' + data[i].attrKey + '" name="' + data[i].attrKey + '" partId="' + assembleId + '" setValueFlag="' + data[i].setValueFlag + '" class="pf-wrap attrs" tabindex="1"><option value="">请选择</option>';
              }
                  url = contextPath + "/partCon/getValueList";
                  $.ajax({
                      url: url,
                      data: "attrKey=" + data[i].attrKey,
                      success: function(attrValRet) {
                          attrValRet = eval(attrValRet);
                          var belong = "";
                          for (var j = 0; j < attrValRet.length; j++) {
                              belong += '<option  value="' + attrValRet[j].attrValue + '" >' + attrValRet[j].attrValue + '-' + attrValRet[j].valueDesc + '</option>';
                          }
                          str += belong;
                          str += '</td></tr></select>';
                      },
                      dataType: "json",
                      async: false
                  });
       } else if (data[i].valueMethod === "RF") {
              if(data[i].setValueFlag === "M"){
                      str = '<tr><td style="font-size:10px">'+data[i].attrDesc+'：</td></tr><tr><td><select id="s_' + data[i].attrKey + '" name="' + data[i].attrKey + '" partId="' + assembleId + '" setValueFlag="' + data[i].setValueFlag + '" class="pf-wrap attrs select2" multiple="multiple" tabindex="1"><option value="">请选择</option>';
              }else{
                      str = '<tr><td style="font-size:10px">'+data[i].attrDesc+'：</td></tr><tr><td><select id="s_' + data[i].attrKey + '" name="' + data[i].attrKey + '" partId="' + assembleId + '" setValueFlag="' + data[i].setValueFlag + '" class="pf-wrap attrs" tabindex="1"><option value="">请选择</option>';
              }
                 url = contextPath + "/partCon/getRfValueList";
                 $.ajax({
                     url: url,
                     data: "attrKey=" + data[i].attrKey,
                     success: function(attrValRet) {
                         attrValRet = eval(attrValRet);
                         var bong = "";
                         for (var j = 0; j < attrValRet.length; j++) {
                            bong += '<option  value="' + attrValRet[j].attrValue + '" >' + attrValRet[j].attrValue + '-' + attrValRet[j].valueDesc + '</option>';
                         }
                         str += bong;
                         str += '</select></td></tr>';
                     },
                     dataType: "json",
                     async: false
                 });
             }

         div +=str;
        }
        div += '</table></form></div></div></div></div>';
   return div;
}

function mbPartAttrAll1(url, assembleId, optionFlag) {
    var w = "400px";
    var h = "300px";
     $("#partType1").val(assembleId);
     $("#optionFlag").val(optionFlag);
    layer.open({
        type: 2,
        content: url,
        area: [w, h],
        success: function(layero, index) {
            var body = layer.getChildFrame('body', index);
            var iframeWin = window[layero.find('iframe')[0]['name']];
            body.find('#partType').val(assembleId);
            iframeWin.selectByCondition();
            body.find('.breadcrumb').remove();
            body.find('#partAttrForm').empty();
        }
    });
}
function mbPartAttrAll(url, assembleId) {
    var w = "400px";
    var h = "300px";
    layer.open({
        type: 2,
        content: url,
        area: [w, h],
        success: function(layero, index) {
            var body = layer.getChildFrame('body', index);
            var iframeWin = window[layero.find('iframe')[0]['name']];
            body.find('#partType').val(assembleId);
            iframeWin.selectByCondition();
            body.find('.breadcrumb').remove();
            body.find('#partAttrForm').empty();
        }
    });
}

function mbEventAttrAll(url, assembleId) {
    var w = "800px";
    var h = "500px";
    layer.open({
        type: 2,
        content: url,
        area: [w, h],
        success: function(layero, index) {
            var body = layer.getChildFrame('body', index);
            var iframeWin = window[layero.find('iframe')[0]['name']];
            body.find('#eventType').val(assembleId);
            body.find('#assembleType').val("");
            iframeWin.selectByCondition();
            body.find('.breadcrumb').remove();
            body.find('#eventAttrForm').empty();
        }
    });
}
var handlePortletTools3 = function() {
    jQuery('body').on('click', '.portlet.event .tools a.remove.event',
    function() {
       if ($(this).parents(".span20.event").attr("optionFlag") === "I") {
           $(this).parents(".span20.event").remove();
       } else if ($(this).parents(".span20.event").attr("optionFlag") === "O"||$(this).parents(".span20.event").attr("optionFlag") === "U") {
           $(this).parents(".span20.event").attr("optionFlag", "D");
           $(this).parents('.portlet.event').removeClass("blue").addClass("grey");
       } else if ($(this).parents(".span20.event").attr("optionFlag") === "D") {
           $(this).parents(".span20.event").attr("optionFlag", "O");
           $(this).parents('.portlet.event').removeClass("grey");
       }
    });
    jQuery('body').on('change', '.portlet.event',
    function(e) {
        e.preventDefault();
    if ($(this).parents(".span20.event").attr("optionFlag") !== "I") {
        $(this).parents(".span20.event").attr("optionFlag", "U");
    }
    });
}
var handlePortletTools = function() {
    jQuery('body').on('click', '.portlet .tools a.remove',
    function() {
        if ($(this).parents(".span2").attr("optionFlag") === "I") {
            $(this).parents(".span2").remove();
        } else if ($(this).parents(".span2").attr("optionFlag") === "O") {
            $(this).parents(".span2").attr("optionFlag", "D");
            $(this).parents('.portlet').removeClass("blue").addClass("grey");
        } else if ($(this).parents(".span2").attr("optionFlag") === "D") {
            $(this).parents(".span2").attr("optionFlag", "O");
            $(this).parents('.portlet').removeClass("grey");
        } else if ($(this).parents(".span2").attr("optionFlag") === "U") {
          $(this).parents(".span2").attr("optionFlag", "D");
          $(this).parents('.portlet').removeClass("blue").addClass("grey");
      }
    });
    jQuery('body').on('change', '.portlet',
    function(e) {
        e.preventDefault();
        if ($(this).parents(".span2").attr("optionFlag") !== "I") {
            $(this).parents(".span2").attr("optionFlag", "U");
        }
    });

    jQuery('body').on('click', '.portlet.attrE .tools a.remove',
    function(e) {
       if ($(this).parents(".span20.attr").attr("optionFlag") === "I") {
           $(this).parents(".span20.attr").remove();
       } else if ($(this).parents(".span20.attr").attr("optionFlag") === "O"||$(this).parents(".span20.attr").attr("optionFlag") === "U") {
           $(this).parents(".span20.attr").attr("optionFlag", "D");
           $(this).parents('.portlet.attrE').removeClass("blue").addClass("grey");
       } else if ($(this).parents(".span20.attr").attr("optionFlag") === "D") {
           $(this).parents(".span20.attr").attr("optionFlag", "O");
           $(this).parents('.portlet.attrE').removeClass("grey");
       }
    });
    jQuery('body').on('change', '.portlet.attrE',
    function(e) {
        e.preventDefault();
    if ($(this).parents(".span20.attr").attr("optionFlag") === "I") {

    } else {
        $(this).parents(".span20.attr").attr("optionFlag", "U");
    }
    });

    jQuery('body').on('click', '.portlet.partE .tools a.remove',
    function() {
       if ($(this).parents(".span20.part").attr("optionFlag") === "I") {
           $(this).parents(".span20.part").remove();
       } else if ($(this).parents(".span20.part").attr("optionFlag") === "O"||$(this).parents(".span20.part").attr("optionFlag") === "U") {
           $(this).parents(".span20.part").attr("optionFlag", "D");
           $(this).parents('.portlet.partE').removeClass("blue").addClass("grey");
       } else if ($(this).parents(".span20.part").attr("optionFlag") === "D") {
           $(this).parents(".span20.part").attr("optionFlag", "O");
           $(this).parents('.portlet.partE').removeClass("grey");
       }
    });
    jQuery('body').on('change', '.portlet.partE',
    function(e) {
        e.preventDefault();
    if ($(this).parents(".span20.part").attr("optionFlag") === "I") {

} else {
        $(this).parents(".span20.part").attr("optionFlag", "U");
    }
    });

    jQuery('body').on('click', '.portlet .tools .collapse, .portlet .tools .expand',
    function(e) {
        e.preventDefault();
        var el = jQuery(this).closest(".portlet").children(".portlet-body");
        if (jQuery(this).hasClass("collapse")) {
            jQuery(this).removeClass("collapse").addClass("expand");
            el.slideUp(200);
        } else {
            jQuery(this).removeClass("expand").addClass("collapse");
            el.slideDown(200);
        }
    });
}
function getSetting1(compontentId, treeId, treeType) {

    var moveTree = {
        curTarget: null,
        curTmpTarget: null,
        noSel: function() {
                window.getSelection ? window.getSelection().removeAllRanges() : document.selection.empty();
        },
        dragTree2Dom: function(treeId, treeNodes) {
            return ! treeNodes[0].isParent;
        },
       dragTree2Domc: function(treeId, treeNode) {
                return ! treeNode.isParent;
            },
        prevTree: function(treeId, treeNodes, targetNode) {
            return ! targetNode.isParent && targetNode.parentTId === treeNodes[0].parentTId;
        },
        nextTree: function(treeId, treeNodes, targetNode) {
            return ! targetNode.isParent && targetNode.parentTId === treeNodes[0].parentTId;
        },
        innerTree: function(treeId, treeNodes, targetNode) {
            return targetNode != null && targetNode.isParent && targetNode.tId === treeNodes[0].parentTId;
        },
        dragMove: function(e, treeId, treeNodes) {
            var p = null,
            pId = treeNodes[0].pId;
            if (e.target.id === pId) {
                p = $(e.target);
            } else {
                p = $(e.target).parent('#' + pId);
                if (!p.get(0)) {
                    p = null;
                }
            }
        },
        dropTree2Dom: function(e, treeId, treeNodes, targetNode, moveType) {
            if(treeNodes[0].pId === "RB" || treeNodes[0].pId === "CL" || treeNodes[0].pId === "MM" || treeNodes[0].pId === "GL"){
                alert("级别为一的数据不能被添加！");
            }else{
                if(!($(compontentId).children().is("#"+treeNodes[0].id))){
                    if (treeType === "attr") {
                        $(compontentId).append(showAttrDiv1(treeNodes[0].id, treeNodes[0].name, "I",""));
                    }
                     if (treeType === "part") {
                             var url=contextPath+"/partType/forCheck";
                             var length=$(compontentId).children().size();
                             if(length === undefined){
                                     url = contextPath + "/partCon/getByPartType1";
                                    $.ajax({
                                        url: url,
                                       data: "partType=" + treeNodes[0].id,
                                        success: function(partRet) {
                                            json = eval(partRet);
                                          $(compontentId).append(showPartDiv1(treeNodes[0].id, treeNodes[0].name,'',partRet[0].data,"I"));
                                          $(".select2").select2();
                                        },
                                        dataType: "json"
                                    });


                             }else{
                                 var dataAry={};
                                 for(var i=0 ;i<length ;i++)
                                 {
                                     var contentId=$(compontentId).children()[i].id;
                                     $.ajax({
                                         url: url,
                                         data: {"eventType": contentId },
                                         success: function(json) {
                                             dataAry[json.data]=json.data;
                                         },
                                         dataType: "json",
                                         type: "POST",
                                         async: false
                                     });
                                 }
                                 var contentDesc=treeNodes[0].pId;
                                if(dataAry[contentDesc] !== undefined){ //存在同类
                                     alert("不能添加同一类型指标！");
                                 }else if(dataAry[contentDesc] === undefined){

                                    url = contextPath + "/partCon/getByPartType1";
                                    $.ajax({
                                        url: url,
                                       data: "partType=" + treeNodes[0].id,
                                        success: function(partRet) {
                                            json = eval(partRet);
                                          $(compontentId).append(showPartDiv1(treeNodes[0].id, treeNodes[0].name,'',partRet[0].data,"I"));
                                          $(".select2").select2();
                                        },
                                        dataType: "json"
                                    });

                                 }
                             }
                     }
                }
         else{
             alert("已存在！");
         }
        }
      },
        dropTree2Domc: function(e, treeId, treeNode) {
                if(treeNode.pId === "RB" || treeNode.pId === "CL" || treeNode.pId === "MM" || treeNode.pId === "GL"){
                    alert("级别为一的数据不能被添加！");
                }else{
                    if(!($(compontentId).children().is("#"+treeNode.id))){
                    if (treeType === "attr") {
                        $(compontentId).append(showAttrDiv1(treeNode.id, treeNode.name, "I",""));
                    }
                     var url;
                    if (treeType === "part") {
                         url=contextPath+"/partType/forCheck";
                        var length=$(compontentId).children().size();
                        if(length === undefined){
                                    url = contextPath + "/partCon/getByPartType1";
                                    $.ajax({
                                        url: url,
                                       data: "partType=" + treeNode.id,
                                        success: function(partRet) {
                                            json = eval(partRet);
                                          $(compontentId).append(showPartDiv1(treeNode.id, treeNode.name,'',partRet[0].data,"I"));
                                          $(".select2").select2();
                                        },
                                        dataType: "json"
                                    });

                        }else{
                            var dataAry={};
                            for(var i=0 ;i<length ;i++)
                            {
                                var contentId=$(compontentId).children()[i].id;
                                $.ajax({
                                    url: url,
                                    data: {"eventType": contentId },
                                    success: function(json) {
                                        dataAry[json.data]=json.data;
                                    },
                                    dataType: "json",
                                    type: "POST",
                                    async: false
                                });
                            }
                            var contentDesc=treeNode.pId;
                           if(dataAry[contentDesc] !== undefined){ //存在同类
                                alert("不能添加同一类型指标！");
                            }else if(dataAry[contentDesc] === undefined){

                                    url = contextPath + "/partCon/getByPartType1";
                                    $.ajax({
                                        url: url,
                                       data: "partType=" + treeNode.id,
                                        success: function(partRet) {
                                            json = eval(partRet);
                                          $(compontentId).append(showPartDiv1(treeNode.id, treeNode.name,'',partRet[0].data,"I"));
                                          $(".select2").select2();
                                        },
                                        dataType: "json"
                                    });

                            }
                        }
                    }
                    }
                    else{
                        alert("已存在！");
                    }
                }
                },
    };

     function addDiyDom(treeId, treeNode) {
            var spaceWidth = 10;
            var switchObj = $("#" + treeNode.tId + "_switch"),
            icoObj = $("#" + treeNode.tId + "_ico");
            switchObj.remove();
            icoObj.before(switchObj);

            if (treeNode.level > 0) {
                var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level)+ "px'></span>";
                switchObj.before(spaceStr);
            }
        }

        function beforeClick(treeId, treeNode) {
                var zTree = $.fn.zTree.getZTreeObj(treeId);
                zTree.expandNode(treeNode);
                return false;
        }

    var setting = {
        view: {
    				showLine: false,
    				showIcon: false,
    				selectedMulti: false,
    				dblClickExpand: true,
    				addDiyDom: addDiyDom
    			},
        edit: {
            enable: true,
            showRemoveBtn: false,
            showRenameBtn: false,
            drag: {
                prev: moveTree.prevTree,
                next: moveTree.nextTree,
                inner: moveTree.innerTree
            }
        },
        data: {
            keep: {
                parent: true,
                leaf: true
            },
            simpleData: {
                enable: true
            }
        },
        callback: {
            beforeClick: beforeClick,
            beforeDblClick: moveTree.dragTree2Domc,
            beforeDrag: moveTree.dragTree2Dom,
            onDrop: moveTree.dropTree2Dom,
            onDblClick: moveTree.dropTree2Domc,
            onDragMove: moveTree.dragMove
        },
    };

    return setting;
}
function getSetting(compontentId, treeId, treeType) {

    var moveTree = {
        curTarget: null,
        curTmpTarget: null,
        noSel: function() {
            try {
                window.getSelection ? window.getSelection().removeAllRanges() : document.selection.empty();
            } catch(e) {}
        },
        dragTree2Dom: function(treeId, treeNodes) {
            return ! treeNodes[0].isParent;
        },
       dragTree2Domc: function(treeId, treeNode) {
                return ! treeNode.isParent;
            },
        prevTree: function(treeId, treeNodes, targetNode) {
            return ! targetNode.isParent && targetNode.parentTId === treeNodes[0].parentTId;
        },
        nextTree: function(treeId, treeNodes, targetNode) {
            return ! targetNode.isParent && targetNode.parentTId === treeNodes[0].parentTId;
        },
        innerTree: function(treeId, treeNodes, targetNode) {
            return targetNode != null && targetNode.isParent && targetNode.tId === treeNodes[0].parentTId;
        },
        dragMove: function(e, treeId, treeNodes) {
            var p = null,
            pId = treeNodes[0].pId;
            if (e.target.id === pId) {
                p = $(e.target);
            } else {
                p = $(e.target).parent('#' + pId);
                if (!p.get(0)) {
                    p = null;
                }
            }
        },
        dropTree2Dom: function(e, treeId, treeNodes, targetNode, moveType) {
            var zTree = $.fn.zTree.getZTreeObj(treeId);
            if(treeNodes[0].pId === "RB" || treeNodes[0].pId === "CL" || treeNodes[0].pId === "MM" || treeNodes[0].pId === "GL"){
                alert("级别为一的数据不能被添加！");
            }else{
                if(!($(compontentId).children().is("#"+treeNodes[0].id))){
                    if (treeType === "attr") {
                     $(compontentId).append(showAttrDiv(treeNodes[0].valueMethod, treeNodes[0].setValueFlag, treeNodes[0].name,"", treeNodes[0].id, "I"));
                     $(".select2").select2();
                    }
                     var url;
                      var length=$(compontentId).children().size();
                        var dataAry={};
                    if (treeType === "part") {
                        url=contextPath+"/partType/forCheck";
                            if(length === undefined){
                                $(compontentId).append(showPartDiv(treeNodes[0].id, treeNodes[0].name, "I"));
                            }else{
                                for(var i=0 ;i<length ;i++)
                                {
                                    var contentId=$(compontentId).children()[i].id;
                                    $.ajax({
                                        url: url,
                                        data: {"eventType": contentId },
                                        success: function(json) {
                                            dataAry[json.data]=json.data;
                                        },
                                        dataType: "json",
                                        type: "POST",
                                        async: false
                                    });
                                }
                                var contentDesc=treeNodes[0].pId;
                               if(dataAry[contentDesc] !== undefined){ //存在同类
                                    alert("不能添加同一类型指标！");
                                }else if(dataAry[contentDesc] === undefined){
                                     $(compontentId).append(showPartDiv(treeNodes[0].id, treeNodes[0].name, "I"));
                                }
                            }
                    }
                    if (treeType === "event") {
                         url=contextPath+"/eventType/forCheck";
                        if(length == undefined){
                             url = contextPath + "/eventAttr/getByEvent";
                            $.ajax({
                                url: url,
                               data: "eventType=" + treeNodes[0].id,
                                success: function(partRet) {
                                    json = eval(partRet);
                                  $(compontentId).append(showEventDiv(treeNodes[0].id, treeNodes[0].name,partRet[0].eventClass,"",partRet[0].prodData ,"I"));
                                   $('#'+treeNodes[0].id).find(".portlet-body.event").slideUp(200);
                                },
                                dataType: "json"
                            });

                        }else{
                            for(var i=0 ;i<length ;i++)
                            {
                                var contentId=$(compontentId).children()[i].id;
                                $.ajax({
                                    url: url,
                                    data: {"eventType": contentId },
                                    success: function(json) {
                                        dataAry[json.data]=json.data;
                                    },
                                    dataType: "json",
                                    type: "POST",
                                    async: false
                                });
                            }
                            var contentDesc=treeNodes[0].pId;
                           if(dataAry[contentDesc] !== undefined){ //存在同类
                                alert("不能添加同一类型事件！");
                            }else if(dataAry[contentDesc] === undefined){
                            url = contextPath + "/eventAttr/getByEvent";
                            $.ajax({
                                url: url,
                               data: "eventType=" + treeNodes[0].id,
                                success: function(partRet) {
                                    json = eval(partRet);
                                  $(compontentId).append(showEventDiv(treeNodes[0].id, treeNodes[0].name,partRet[0].eventClass,"",partRet[0].prodData ,"I"));
                                  $('#'+treeNodes[0].id).find(".portlet-body.event").slideUp(200);
                                },
                                dataType: "json"
                            });

                            }
                        }
                    }
                }

         else{
             alert("已存在！");
         }
        }
      },
        dropTree2Domc: function(e, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj(treeId);
                if(treeNode.pId === "RB" || treeNode.pId === "CL" || treeNode.pId === "MM" || treeNode.pId === "GL"){
                    alert("级别为一的数据不能被添加！");
                }else{
                    if(!($(compontentId).children().is("#"+treeNode.id))){
                    if (treeType === "attr") {
                    $(compontentId).append(showAttrDiv(treeNode.valueMethod, treeNode.setValueFlag, treeNode.name, "",treeNode.id, "I"));
                    $(".select2").select2();
                    }
                       var url;
                       var length=$(compontentId).children().size();
                        var dataAry={};
                    if (treeType === "part") {
                        url=contextPath+"/partType/forCheck";
                        if(length == undefined){
                            $(compontentId).append(showPartDiv(treeNode.id, treeNode.name, "I"));
                        }else{
                            for(var i=0 ;i<length ;i++)
                            {
                                var contentId=$(compontentId).children()[i].id;
                                $.ajax({
                                    url: url,
                                    data: {"eventType": contentId },
                                    success: function(json) {
                                        dataAry[json.data]=json.data;
                                    },
                                    dataType: "json",
                                    type: "POST",
                                    async: false
                                });
                            }
                            var contentDesc=treeNode.pId;
                           if(dataAry[contentDesc] !== undefined){ //存在同类
                                alert("不能添加同一类型指标！");
                            }else if(dataAry[contentDesc] === undefined){
                                 $(compontentId).append(showPartDiv(treeNode.id, treeNode.name, "I"));
                            }
                        }
                    }
                    if (treeType === "event") {
                    url=contextPath+"/eventType/forCheck";
                        if(length == undefined){
                            url = contextPath + "/eventAttr/getByEvent";
                            $.ajax({
                                url: url,
                               data: "eventType=" + treeNode.id,
                                success: function(partRet) {
                                    json = eval(partRet);
                                  $(compontentId).append(showEventDiv(treeNode.id, treeNode.name,partRet[0].eventClass,"",partRet[0].prodData ,"I"));
                                  $('#'+treeNode.id).find(".portlet-body.event").slideUp(200);
                                },
                                dataType: "json"
                            });

                        }else{
                            for(var i=0 ;i<length ;i++)
                            {
                                var contentId=$(compontentId).children()[i].id;
                                $.ajax({
                                    url: url,
                                    data: {"eventType": contentId },
                                    success: function(json) {
                                        dataAry[json.data]=json.data;
                                    },
                                    dataType: "json",
                                    type: "POST",
                                    async: false
                                });
                            }
                            var contentDesc=treeNode.pId;
                           if(dataAry[contentDesc] !== undefined){ //存在同类
                                alert("不能添加同一类型事件！");
                            }else if(dataAry[contentDesc] === undefined){
                             url = contextPath + "/eventAttr/getByEvent";
                            $.ajax({
                                url: url,
                               data: "eventType=" + treeNode.id,
                                success: function(partRet) {
                                    json = eval(partRet);
                                  $(compontentId).append(showEventDiv(treeNode.id, treeNode.name,partRet[0].eventClass,"",partRet[0].prodData ,"I"));
                            for (var i = 0; i < json[0].prodData.length; i++) {
                                     if(json[0].prodData[i].assembleType === "ATTR"){
                                        if(json[0].prodData[i].attrValue.contains(',')){
                                               $('#q_' + json[0].prodData[i].eventType + json[0].prodData[i].assembleId).val(json[0].prodData[i].attrValue.split(","));
                                               $('#q_' + json[0].prodData[i].eventType + json[0].prodData[i].assembleId).select2();
                                        }else{
                                             $('#q_' + json[0].prodData[i].eventType + json[0].prodData[i].assembleId).val(json[0].prodData[i].attrValue);
                                        }
                                     }
                                     else if(json[0].prodData[i].assembleType === "PART"){
                                       for (var k = 0; k < json[0].prodData[i].data.length; k++) {
                                              if(json[0].prodData[i].data[k].attrValue.contains(';')){
                                                   $('#p_' + json[0].prodData[i].eventType +json[0].prodData[i].data[k].attrKey).val(json[0].prodData[i].data[k].attrValue.split(";"));
                                                   $('#p_' + json[0].prodData[i].eventType +json[0].prodData[i].data[k].attrKey).select2();
                                              }else{
                                                $('#p_' + json[0].prodData[i].eventType +json[0].prodData[i].data[k].attrKey).val(json[0].prodData[i].data[k].attrValue);
                                              }
                                       }
                                     }
                                   }
                                  $('#'+treeNode.id).find(".portlet-body.event").slideUp(200);
                                },
                                dataType: "json"
                            });

                            }
                        }
                    }

                    }
                    else{
                        alert("已存在！");
                    }
                }
                },



    };

     function addDiyDom(treeId, treeNode) {
            var spaceWidth = 10;
            var switchObj = $("#" + treeNode.tId + "_switch"),
            icoObj = $("#" + treeNode.tId + "_ico");
            switchObj.remove();
            icoObj.before(switchObj);

            if (treeNode.level > 0) {
                var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level)+ "px'></span>";
                switchObj.before(spaceStr);
            }
        }

        function beforeClick(treeId, treeNode) {
                var zTree = $.fn.zTree.getZTreeObj(treeId);
                zTree.expandNode(treeNode);
                return false;
        }

    var setting = {
        view: {
    				showLine: false,
    				showIcon: false,
    				selectedMulti: false,
    				dblClickExpand: true,
    				addDiyDom: addDiyDom
    			},
        edit: {
            enable: true,
            showRemoveBtn: false,
            showRenameBtn: false,
            drag: {
                prev: moveTree.prevTree,
                next: moveTree.nextTree,
                inner: moveTree.innerTree
            }
        },
        data: {
            keep: {
                parent: true,
                leaf: true
            },
            simpleData: {
                enable: true
            }
        },
        callback: {
            beforeClick: beforeClick,
            beforeDblClick: moveTree.dragTree2Domc,
            beforeDrag: moveTree.dragTree2Dom,
            onDrop: moveTree.dropTree2Dom,
            onDblClick: moveTree.dropTree2Domc,
            onDragMove: moveTree.dragMove
        }
    };

    return setting;
}

function clearAll() {
    if ($("#compontent")) {
        $("#compontent").empty();
    }
    if ($("#compontentP")) {
        $("#compontentP").empty();
    }
    if ($("#compontentE")) {
        $("#compontentE").empty();
    }
    if($("#ACCTD")){
        $("#ACCTD").empty();
    }
    if($("#AGREEMENTD")){
        $("#AGREEMENTD").empty();
    }
    if($("#BALANCED")){
        $("#BALANCED").empty();
     }
    if($("#CLIENTD")){
        $("#CLIENTD").empty();
    }
    if($("#FREQUCYD")){
        $("#FREQUCYD").empty();
    }
    if($("#MEDIAD")){
        $("#MEDIAD").empty();
    }
    if($("#PRICED")){
        $("#PRICED").empty();
    }
    if($("#RISKD")){
        $("#RISKD").empty();
    }
    if($("#prodTableM")){
        $("#prodTableM").empty();
    }
    if($("#childProdList")){
        $("#childProdList").empty();
    }
}