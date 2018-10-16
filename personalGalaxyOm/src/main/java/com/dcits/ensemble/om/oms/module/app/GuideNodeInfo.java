package com.dcits.ensemble.om.oms.module.app;

import com.dcits.galaxy.base.bean.AbstractBean;

import java.util.List;


/**
 * Created by oms on 2016/02/25 15:47:29.
 */
public class GuideNodeInfo extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    private EcmUpgflowNode upgflowNode;//节点对象
    
    private List<EcmUpgndBtn> btnList;//节点包含按钮--已排序
	

	public EcmUpgflowNode getUpgflowNode() {
		return upgflowNode;
	}

	public void setUpgflowNode(EcmUpgflowNode upgflowNode) {
		this.upgflowNode = upgflowNode;
	}

	public List<EcmUpgndBtn> getBtnList() {
		return btnList;
	}

	public void setBtnList(List<EcmUpgndBtn> btnList) {
		this.btnList = btnList;
	}
    
    
    
}