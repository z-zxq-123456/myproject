<!DOCTYPE HTML>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/form.jsp"%>
<html>
<head>
<script type="text/javascript" src="${ContextPath}/app/pm/prod/js/edit/glProdAccountingModPf.js"></script>
<title>参数表GL_PROD_ACCOUNTING修改</title>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="glProdAccountingMod">
	 <div class="row cl">
             	<label class="form-label span4"><span class="c-red">*</span>产品类型：</label>
             	<div class="formControls span6">
             		<input type="text" class="input-text grid" value=""  id="prodType" name="prodType" >
             	</div>
             	<div class="span2"> </div>
     </div>
	 <div class="row cl">
             	<label class="form-label span4"><span class="c-red">*</span>核算状态：</label>
             	<div class="formControls span6">
             		<input type="text" class="input-text grid" value=""  id="accountingStatus" name="accountingStatus" >
             	</div>
             	<div class="span2"> </div>
     </div>
          <div class="row cl">
          <label class="form-label span4"><span class="c-red"></span>利润中心：</label>
          <div class="formControls span6">

                            		  	  	<select id="profitCentre" name="profitCentre"  class="select2" tipmsg="格式错误!" >
                                                  <option value="" selected="selected">空</option>
                                                      <option value="01" >01-会计结算部</option>
                                                      <option value="02" >02-计划财务部</option>
                                                      <option value="03" >03-个人业务部</option>
                                                      <option value="04" >04-电子银行部</option>
                                                      <option value="05" >05-国际业务部</option>
                                                      <option value="06" >06-资金运营部</option>
                                                      <option value="07" >07-公司业务部</option>
                                                      <option value="08" >08-审计部</option>
                                                      <option value="09" >09-授信部</option>
                                                      <option value="10" >10-合规风险部</option>
                                                      <option value="99" >99-缺省</option>
                                                           	</select>
    </div>
          <div class="span2"> </div>
          </div>
          <div class="row cl">
          <label class="form-label span4"><span class="c-red"></span>账套：</label>
          <div class="formControls span6">
                                                   <input type="text" class="input-text grid" value="" placeholder="businessUnit" datatype="*0-50" id="businessUnit" name="businessUnit" tipmsg="格式错误!" >
                                               	    	    </div>
          <div class="span2"> </div>
          </div>
          <div class="row cl">
          <label class="form-label span4"><span class="c-red"></span>资产科目代码：</label>
          <div class="formControls span6">
                                                     	<select id="glCodeA" name="glCodeA"  class="select2" tipmsg="格式错误!"  >
                                                           	<option value="" selected="selected">空</option>
                                                                        </select>
                                               	    	    </div>
          <div class="span2"> </div>
          </div>
          <div class="row cl">
          <label class="form-label span4"><span class="c-red"></span>负债科目代码：</label>
          <div class="formControls span6">
                                                     	<select id="glCodeL" name="glCodeL"  class="select2" tipmsg="格式错误!"  >
                                                           	<option value="" selected="selected">空</option>
                                                                        </select>
                                               	    	    </div>
          <div class="span2"> </div>
          </div>
          <div class="row cl">
          <label class="form-label span4"><span class="c-red"></span>利息支出科目代码：</label>
          <div class="formControls span6">
                                                     	<select id="glCodeIntE" name="glCodeIntE"  class="select2" tipmsg="格式错误!"  >
                                                           	<option value="" selected="selected">空</option>
                                                                        </select>
                                               	    	    </div>
          <div class="span2"> </div>
          </div>
          <div class="row cl">
          <label class="form-label span4"><span class="c-red"></span>应付利息科目代码：</label>
          <div class="formControls span6">
                                                     	<select id="glCodeIntPay" name="glCodeIntPay"  class="select2" tipmsg="格式错误!"  >
                                                           	<option value="" selected="selected">空</option>
                                                                        </select>
                                               	    	    </div>
          <div class="span2"> </div>
          </div>
          <div class="row cl">
          <label class="form-label span4"><span class="c-red"></span>利息收入科目代码：</label>
          <div class="formControls span6">
                                                     	<select id="glCodeIntI" name="glCodeIntI"  class="select2" tipmsg="格式错误!"  >
                                                           	<option value="" selected="selected">空</option>
                                                                        </select>
                                               	    	    </div>
          <div class="span2"> </div>
          </div>
          <div class="row cl">
          <label class="form-label span4"><span class="c-red"></span>应收利息科目代码：</label>
          <div class="formControls span6">
                                                     	<select id="glCodeIntRec" name="glCodeIntRec"  class="select2" tipmsg="格式错误!"  >
                                                           	<option value="" selected="selected">空</option>
                                                                        </select>
                                               	    	    </div>
          <div class="span2"> </div>
          </div>
          <div class="row cl">
          <label class="form-label span4"><span class="c-red"></span>应计利息科目代码：</label>
          <div class="formControls span6">
                                                     	<select id="glCodeIntAcr" name="glCodeIntAcr"  class="select2" tipmsg="格式错误!"  >
                                                           	<option value="" selected="selected">空</option>
                                                                        </select>
                                               	    	    </div>
          <div class="span2"> </div>
          </div>
          <div class="row cl">
          <label class="form-label span4"><span class="c-red"></span>罚息收入科目代码：</label>
          <div class="formControls span6">
                                                     	<select id="glCodeOdpI" name="glCodeOdpI"  class="select2" tipmsg="格式错误!"  >
                                                           	<option value="" selected="selected">空</option>
                                                                        </select>
                                               	    	    </div>
          <div class="span2"> </div>
          </div>
          <div class="row cl">
          <label class="form-label span4"><span class="c-red"></span>应收罚息科目代码：</label>
          <div class="formControls span6">
                                                     	<select id="glCodeOdpRec" name="glCodeOdpRec"  class="select2" tipmsg="格式错误!"  >
                                                           	<option value="" selected="selected">空</option>
                                                                        </select>
                                               	    	    </div>
          <div class="span2"> </div>
          </div>
          <div class="row cl">
          <label class="form-label span4"><span class="c-red"></span>应计罚息科目代码：</label>
          <div class="formControls span6">
                                                     	<select id="glCodeOdpAcr" name="glCodeOdpAcr"  class="select2" tipmsg="格式错误!"  >
                                                           	<option value="" selected="selected">空</option>
                                                                        </select>
                                               	    	    </div>
          <div class="span2"> </div>
          </div>
          <div class="row cl">
          <label class="form-label span4"><span class="c-red"></span>复利收入科目代码：</label>
          <div class="formControls span6">
                                                     	<select id="glCodeOdiI" name="glCodeOdiI"  class="select2" tipmsg="格式错误!"  >
                                                           	<option value="" selected="selected">空</option>
                                                                        </select>
                                               	    	    </div>
          <div class="span2"> </div>
          </div>
          <div class="row cl">
          <label class="form-label span4"><span class="c-red"></span>应收利息科目代码：</label>
          <div class="formControls span6">
                                                     	<select id="glCodeOdiRec" name="glCodeOdiRec"  class="select2" tipmsg="格式错误!"  >
                                                           	<option value="" selected="selected">空</option>
                                                                        </select>
                                               	    	    </div>
          <div class="span2"> </div>
          </div>
          <div class="row cl">
          <label class="form-label span4"><span class="c-red"></span>应计利息科目代码：</label>
          <div class="formControls span6">
                                                     	<select id="glCodeOdiAcr" name="glCodeOdiAcr"  class="select2" tipmsg="格式错误!"  >
                                                           	<option value="" selected="selected">空</option>
                                                                        </select>
                                               	    	    </div>
          <div class="span2"> </div>
          </div>
        <div class="row cl">
        <label class="form-label span4"><span class="c-red"></span>资产损失科目：</label>
        <div class="formControls span6">
            <select id="glCodeALoss" name="glCodeALoss"   class="select2" tipmsg="格式错误!"  >
            </select>
        </div>
        <div class="span2"> </div>
    </div>
        <div class="row cl">
            <label class="form-label span4"><span class="c-red"></span>科目调整：</label>
            <div class="formControls span6">
                <select id="glCodeAdjust" name="glCodeAdjust"   class="select2" tipmsg="格式错误!"  >
                </select>
            </div>
            <div class="span2"> </div>
        </div>
   <div class="row">
               			<div class="span-offset-1 span10 span-offset-1 mt-10">
               				<input type="submit" class="button-select L smartButton" title="修改"  value="修改">
               			</div>
               		</div>
    </form>
</div>
</body>
</html>