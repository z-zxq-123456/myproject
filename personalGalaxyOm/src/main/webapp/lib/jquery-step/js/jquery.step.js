/**
 * jQuery step  Plug-in v1.0
 * created : 2015-05-26 10:30:26
 * last update : 2015-05-27 15:23:16
 * author:zhengyong
 * update: zwh
 */
(function (factory) {
    "use strict";
    if (typeof define === 'function') {
        // using CMD; register as anon module
      define.cmd&&define('jquery-step', ['jquery'], function (require, exports, moudles) {
            var $=require("jquery");
            factory($);
            return $;
        });
      define.amd&&define(['jquery'], factory);
    } else {
        // no CMD; invoke directly
        factory( (typeof(jQuery) != 'undefined') ? jQuery : window.Zepto );
    }
}

(function($){
  $.fn.step = function(options) { 
      var opts = $.extend({}, $.fn.step.defaults, options);
      //var size=this.find(".step-header li").length;
      /*var barWidth=opts.initStep<size?100/(2*size)+100*(opts.initStep-1)/size : 100;*/
      /*之前进度条长度和圆圈长度一样，故采用相同算法，现做更改分别采用不同算法*/      
      /*之前算法是没等份中间位置，现在是n等分n+1个树算法*/
      /*现在插件添加当前状态的CSS。 step-current*/
      /*改变插件原有初始化机制，不再是有多少li数据便初始化多少数据，而是根据AJAX获取的数据决定初始化个数*/
      var oldSize=this.find(".step-header li").length;
      var size=opts.maxStepSize;
      var barWidth=opts.initStep<size?100/(size-1)+100*(opts.initStep-1):100;

      var curPage=opts.initStep;

      this.find(".step-header").prepend("<div class=\"step-bar\"><div class=\"step-bar-active\"></div></div>");
      this.find(".step-list").eq(opts.initStep-1).show();
      if (size<opts.initStep) {
        opts.initStep=size;
      }
      if (opts.animate==false) {
        opts.speed=0;
      }

      this.find(".step-header li").each(function (i, li) {
        $(li).prepend("<span>"+(i+1)+"</span>");
        /*满足插件添加当前状态*/
        if (i<opts.initStep - 1){
          $(li).find("span").html("");//解决插件初始化样式混乱
          $(li).addClass("step-active");
        }
        if (i == opts.initStep - 1){
          $(li).addClass("step-current");         
        } 
        $(li).css({
          "position":"absolute",
          "left":100/(size-1)*i+"%",
          "margin-left": "-50px"
        });

         /*解决冗余html隐藏问题*/
         if(i >= opts.maxStepSize){
            $(li).hide();
         }
    });
    /*this.find(".step-header li").css({
      "width": 100/(size-1)+"%"
    });*/
    /*进度条长度初始化*/
    var initLength=opts.initStep<size?100/(size-1)*(opts.initStep-1):100;
    this.find(".step-header").show();   
    this.find(".step-bar-active").animate({
        "width": initLength+"%"},
        opts.speed, function() {
        
    });
    /*需要加完成函数*/
      this.nextStep=function() {
        if (curPage>=size) {
          return false;
        }
        return this.goStep(curPage+1);
      }

      this.preStep=function() {
        if (curPage<=1) {
          return false;
        }        
        return this.goStep(curPage-1);
      }
      /*N隔断N+1棵树必须出现一个完成函数*/
      this.complete=function(){
        this.find(".step-header li").each(function (i, li) {
            $li=$(li);
            if(i < size){
                if($li.hasClass('step-current')){
                    $li.removeClass('step-current');
                }
                $li.find("span").html('');
                $li.addClass('step-active');
            }
        });
        this.find(".step-list").hide();
        this.find(".step-list").eq(size-1).show();
        this.find(".step-bar-active").animate({
              "width": 100+"%"},
              opts.speed, function() {

        });
      }
      this.goStep=function(page) {
        if (page ==undefined || isNaN(page) || page<0) {
          if(window.console&&window.console.error){
            console.error('the method goStep has a error,page:'+page);
          }
        return false;
        }
        curPage=page;
        this.find(".step-list").hide();
        this.find(".step-list").eq(curPage-1).show();
        this.find(".step-header li").each(function (i, li) {
          $li=$(li);
          $li.removeClass('step-active');                  
          $li.removeClass('step-current');
          /*进度条初始化函数发生改变  分三种情况大于page 小于page*/   
          $li.find("span").html('');  
          if(i > page -1 ){
            $li.find("span").html(i+1);
          }  
                   
          if (i<page - 1){
            $li.addClass('step-active');          
            if(opts.scrollTop){
             $('html,body').animate({scrollTop:0}, 'slow');
            }          
          }
          if (i == page -1){
            $li.addClass('step-current');
            $li.find("span").html(page);  
          }
      });
      /*算法更改，去除原相加算法*/
      var barLength=page<size?100/(size-1)*(page-1):100;      
        this.find(".step-bar-active").animate({
          "width": barLength+"%"},
          opts.speed, function() {
            
        });
        return true;
    };
  return this;
};
   
  $.fn.step.defaults = {
      animate:true,
      speed:500,
    initStep:1,
    scrollTop:true
  };

}));  