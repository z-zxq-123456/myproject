package com.dcits.galaxy.dal.idempotent;

import com.alibaba.dubbo.rpc.RpcContext;
import com.dcits.galaxy.dal.demo.entities.User;


public class UserClient {

	public static void main(String[] args)throws Exception {
		
		String opId = "test_idempotent";
		IdempotentService service = IdempotentServiceHelper.getIdempotentService();
		
		User user = new User();
		user.setSid( 118L );
		user.setUserName("a1");
		user.setUserName("g1");
		user.setAge(6);
		user.setRemark("ttt");
		
		RpcContext.getContext().setAttachment("opId", opId);
		service.idempotent(user);
		
		System.in.read();
	}
}
