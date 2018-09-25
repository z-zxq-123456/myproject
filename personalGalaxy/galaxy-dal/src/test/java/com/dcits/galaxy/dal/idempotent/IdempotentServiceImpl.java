package com.dcits.galaxy.dal.idempotent;

import org.springframework.context.ApplicationContext;

import com.alibaba.dubbo.rpc.RpcContext;
import com.dcits.galaxy.dal.demo.entities.User;
import com.dcits.galaxy.dal.demo.service.IUserService;
import com.dcits.galaxy.dal.mybatis.idempotent.IdempotentContext;


public class IdempotentServiceImpl implements IdempotentService {
	
	private ApplicationContext context = null;
	
	public IdempotentServiceImpl(ApplicationContext context){
		this.context = context;
	}
	
	@Override
	public void idempotent(User user) {
		IUserService userService = (IUserService)context.getBean("userService");
		String opId=RpcContext.getContext().getAttachment("opId");
		IdempotentContext.setIdempotentObj(opId);
		userService.insert(user);
	}

}
