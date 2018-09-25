package com.dcits.galaxy.dtp.demo.service.user;


/**
 * 用户服务
 * @author Yin.weicai
 *
 */
public interface UserService{

	public boolean lockUser(String userId ,String txid);
	
	public boolean unLockUser(String userId ,String txid);
}
