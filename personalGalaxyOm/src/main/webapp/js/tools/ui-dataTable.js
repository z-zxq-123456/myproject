/*
@名称：ui-dataTable.js
@功能：对组件dataTable组件的美化扩展
@产权：神州信息 DCITS
@作者：zhongwh
@更改  201504221711  去除ID参数配置
*
*/
/*dataTables 美化  表格ID，按钮组依次名称，按钮依次对应的ID 20160301*/
/*美化现有三中需求，1.有按钮有分页、2.有按钮无分页、 3。无按钮有分页，故现在进行扩展在原基础上做成配置文件形式 20160312植树节*/
/*无分页处理应该保证原插件的分页配置也为false*/
/*参数
*
* needBtn     //是否需要按钮   取值布尔      默认    true
* buttonName  //按钮名称       取值数组     默认     添加 修改 删除
* buttonId    //按钮ID        取值数组      默认     add  edit  delete
* needPag     //是否有分页     取值布尔      默认    true
* tableId     //表格ID        取值字符串
*
*/
;(function($){
	var Ui=function(ele,opts){
		this.$this=ele;
		this.opts=opts;
		this.defaults={
			needBtn: true,
			buttonName:["添加", "修改" , "删除"],
			buttonId:["add",　"edit" , "delete"],
			needPag: true
		};
		this.settings=$.extend({},this.defaults,opts);
	};
	Ui.prototype= {
        init: function () {
            var _dataTableId = this.$this.attr("id"), _btnN = this.settings.buttonName, _btnId = this.settings.buttonId;
            if (this.settings.needBtn && this.settings.needPag) {
                if (this.settings.serverSide) {
                    _headerServerSide(_dataTableId);
                    _btnServerSide(_dataTableId, _btnN, _btnId);
                }
                else {
                    _header(_dataTableId);
                    _btn(_dataTableId, _btnN, _btnId);
                }
                _footer(_dataTableId);

            } else if (this.settings.needBtn && !this.settings.needPag) {
                _noPage(_dataTableId);
                _btn(_dataTableId, _btnN, _btnId);
                _footer(_dataTableId);
            } else if (!this.settings.needBtn && this.settings.needPag) {
                _header(_dataTableId);
                _footer(_dataTableId);
            } else if (!this.settings.needBtn && !this.settings.needPag) {
                _noPage(_dataTableId);
                _footer(_dataTableId);
            }
        }
    };
    function _header(_dataTableId){
        $("#"+_dataTableId+"_length").wrap('<div  class="cl table-header" id="'+_dataTableId+'-header"></div>');
        $("#"+_dataTableId+"_filter").appendTo("#"+_dataTableId+"-header");
        $("#"+_dataTableId+"_length").before($("#"+_dataTableId+"_filter"));
        //以下两行是兼容dm
        $("#"+_dataTableId+"_length").find("select").addClass("select");
        $("#"+_dataTableId+"_filter").find("input").addClass("input-text");
        //搜索框差个图片$(".input-text").addClass("img");
    }
    function _btn(_dataTableId,_btnN,_btnId){
        for(i in _btnN){
            $("#"+_dataTableId+"_length").before('<a  class="table-btn" id="'+_btnId[i]+'">'+_btnN[i]+'</a>');
        }
    }
	function _headerServerSide(_dataTableId){
		$("#"+_dataTableId+"_filter").wrap('<div  class="cl table-header" id="'+_dataTableId+'-header"></div>');
		//以下两行是兼容dm
		$("#"+_dataTableId+"_filter").find("input").addClass("input-text");
	}
	function _btnServerSide(_dataTableId,_btnN,_btnId){
		for(i in _btnN){
			$("#"+_dataTableId+"_filter").before('<a  class="table-btn" id="'+_btnId[i]+'">'+_btnN[i]+'</a>');
		}
	}
	function _footer(_dataTableId){
		$("#"+_dataTableId+"_paginate").wrap('<div  class="cl table-footer"  id="'+_dataTableId+'-footer"></div>');
		$("#"+_dataTableId+"_info").prependTo($("#"+_dataTableId+"-footer"));
	}
	function _noPage(_dataTableId){
		$("#"+_dataTableId+"_filter").wrap('<div  class="cl table-header"  id="'+_dataTableId+'-header"></div>');
		//以下两行是兼容dm
		$("#"+_dataTableId+"_filter").find("input").addClass("input-text");}
	$.fn.beautyUi = function(options){
		var tableUi=new Ui(this,options);
		return tableUi.init();
	}
})(jQuery);