package com.dcits.galaxy.dtp.demo.service.money;

import java.io.Serializable;

/**
 * 取款服务
 * @author Yin.Weicai
 *
 */
public interface DrawService extends Serializable {
	
	public boolean check(String userId, int amount);
	
	public boolean drawMoney(String userId, int amount);
	
	public void doSubmit(String userId, int amount);

}
