package com.dcits.galaxy.dtp.demo.service.money;

import java.io.Serializable;

/**
 * 存款服务
 * @author Yin.Weicai
 *
 */
public interface SaveService extends Serializable {

	public boolean check(String userId, int amount);
	
	public boolean saveMoney(String userId, int amount);
	
	public void doSubmit(String userId, int amount);

}
