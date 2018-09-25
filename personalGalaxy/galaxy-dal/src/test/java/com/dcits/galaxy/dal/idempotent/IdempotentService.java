package com.dcits.galaxy.dal.idempotent;

import com.dcits.galaxy.dal.demo.entities.User;


public interface IdempotentService  {

	public void idempotent(User user);
}
