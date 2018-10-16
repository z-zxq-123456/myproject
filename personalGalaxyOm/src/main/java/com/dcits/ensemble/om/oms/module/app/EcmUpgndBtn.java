package com.dcits.ensemble.om.oms.module.app;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by oms on 2016/02/25 15:47:29.
 */
public class EcmUpgndBtn extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;

    /**
     * This field is ECM_UPGND_BTN.UPGND_BTN_ID 
     */
    @TablePk(index = 1)
    private Integer upgndBtnId;

    /**
     * This field is ECM_UPGND_BTN.UPGFLOW_NODE_ID 
     */
    private Integer upgflowNodeId;

    /**
     * This field is ECM_UPGND_BTN.UPGND_BTN_NAME 
     */
    private String upgndBtnName;
    
    private String upgflowNodeName;

    /**
     * This field is ECM_UPGND_BTN.UPGND_BTN_SEQ 
     */
    private Integer upgndBtnSeq;

    /**
     * This field is ECM_UPGND_BTN.UPGND_BTN_CLASS 
     */
    private String upgndBtnClass;
    /**
     * This field is ECM_UPGND_BTN.UPGND_BTN_CLASS 
     */
    private String upgndBtnIsview;
    
    private String upgndBtnIsviewName;

    /**
     * This field is ECM_UPGND_BTN.UPGND_BTN_DIRNODEID 
     */
    private Integer upgndBtnDirnodeid;
    
    private String upgndBtnDirnodename;
    
    private EcmUpgflowNode directNode;//按钮跳转节点
    
    private String upgndBtnFunc;

    public EcmUpgflowNode getDirectNode() {
		return directNode;
	}

	public void setDirectNode(EcmUpgflowNode directNode) {
		this.directNode = directNode;
	}

	/**
     * @return the value of  ECM_UPGND_BTN.UPGND_BTN_ID 
     */
    public Integer getUpgndBtnId() {
        return upgndBtnId;
    }

    /**
     * @param upgndBtnId the value for ECM_UPGND_BTN.UPGND_BTN_ID 
     */
    public void setUpgndBtnId(Integer upgndBtnId) {
        this.upgndBtnId = upgndBtnId;
    }

    /**
     * @return the value of  ECM_UPGND_BTN.UPGFLOW_NODE_ID 
     */
    public Integer getUpgflowNodeId() {
        return upgflowNodeId;
    }

    /**
     * @param upgflowNodeId the value for ECM_UPGND_BTN.UPGFLOW_NODE_ID 
     */
    public void setUpgflowNodeId(Integer upgflowNodeId) {
        this.upgflowNodeId = upgflowNodeId;
    }

  
    /**
     * @return the value of  ECM_UPGND_BTN.UPGND_BTN_SEQ 
     */
    public Integer getUpgndBtnSeq() {
        return upgndBtnSeq;
    }

    /**
     * @param upgndBtnSeq the value for ECM_UPGND_BTN.UPGND_BTN_SEQ 
     */
    public void setUpgndBtnSeq(Integer upgndBtnSeq) {
        this.upgndBtnSeq = upgndBtnSeq;
    }

    /**
     * @return the value of  ECM_UPGND_BTN.UPGND_BTN_CLASS 
     */
    public String getUpgndBtnClass() {
        return upgndBtnClass;
    }

    /**
     * @param upgndBtnClass the value for ECM_UPGND_BTN.UPGND_BTN_CLASS 
     */
    public void setUpgndBtnClass(String upgndBtnClass) {
        this.upgndBtnClass = upgndBtnClass == null ? null : upgndBtnClass.trim();
    }

    /**
     * @return the value of  ECM_UPGND_BTN.UPGND_BTN_DIRNODEID 
     */
    public Integer getUpgndBtnDirnodeid() {
        return upgndBtnDirnodeid;
    }

    /**
     * @param upgndBtnDirnodeid the value for ECM_UPGND_BTN.UPGND_BTN_DIRNODEID 
     */
    public void setUpgndBtnDirnodeid(Integer upgndBtnDirnodeid) {
        this.upgndBtnDirnodeid = upgndBtnDirnodeid;
    }

	/**
	 * @return the upgndBtnDirnodename
	 */
	public String getUpgndBtnDirnodename() {
		return upgndBtnDirnodename;
	}

	/**
	 * @param upgndBtnDirnodename the upgndBtnDirnodename to set
	 */
	public void setUpgndBtnDirnodename(String upgndBtnDirnodename) {
		this.upgndBtnDirnodename = upgndBtnDirnodename;
	}

	/**
	 * @return the upgndBtnName
	 */
	public String getUpgndBtnName() {
		return upgndBtnName;
	}

	/**
	 * @param upgndBtnName the upgndBtnName to set
	 */
	public void setUpgndBtnName(String upgndBtnName) {
		this.upgndBtnName = upgndBtnName;
	}

	/**
	 * @return the upgflowNodeName
	 */
	public String getUpgflowNodeName() {
		return upgflowNodeName;
	}

	/**
	 * @param upgflowNodeName the upgflowNodeName to set
	 */
	public void setUpgflowNodeName(String upgflowNodeName) {
		this.upgflowNodeName = upgflowNodeName;
	}

	/**
	 * @return the upgndBtnFunc
	 */
	public String getUpgndBtnFunc() {
		return upgndBtnFunc;
	}

	/**
	 * @param upgndBtnFunc the upgndBtnFunc to set
	 */
	public void setUpgndBtnFunc(String upgndBtnFunc) {
		this.upgndBtnFunc = upgndBtnFunc;
	}
	/**
     * @return the value of  ECM_UPGND_BTN.UPGND_BTN_CLASS 
     */
    public String getUpgndBtnIsview() {
        return upgndBtnIsview;
    }

    /**
     * @param upgndBtnClass the value for ECM_UPGND_BTN.UPGND_BTN_CLASS 
     */
    public void setUpgndBtnIsview(String upgndBtnIsview) {
        this.upgndBtnIsview = upgndBtnIsview == null ? null : upgndBtnIsview.trim();
    }
    /**
     * @return the value of  ECM_UPGND_BTN.UPGND_BTN_CLASS 
     */
    public String getUpgndBtnIsviewName() {
        return upgndBtnIsviewName;
    }
    public void setUpgndBtnIsviewName(String upgndBtnIsviewName) {
        this.upgndBtnIsviewName = upgndBtnIsviewName == null ? null : upgndBtnIsviewName.trim();
    }
}