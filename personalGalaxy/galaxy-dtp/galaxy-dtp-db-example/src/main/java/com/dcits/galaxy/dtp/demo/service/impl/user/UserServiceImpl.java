package com.dcits.galaxy.dtp.demo.service.impl.user;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dcits.galaxy.dtp.DtpContext;
import com.dcits.galaxy.dtp.branch.BranchManagerHelper;
import com.dcits.galaxy.dtp.branch.BranchTransaction;
import com.dcits.galaxy.dtp.demo.dao.UserDao;
import com.dcits.galaxy.dtp.demo.entities.User;
import com.dcits.galaxy.dtp.demo.service.user.UserService;
import com.dcits.galaxy.dtp.demo.util.SequenceHelper;
import com.dcits.galaxy.dtp.resource.DtpResource;

public class UserServiceImpl implements UserService {

	private static final String TABLE_NAME = "dtp_user";

	private static final String COLUMN_NAME = "userId";

	@Resource
	private UserDao dao;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean lockUser(String userId, String txid) {
		User user = null;
		try {
			user = dao.getUser(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (user == null) {
			throw new RuntimeException("账户：" + userId + " 不存在");
		}
		return DtpResource.lock(TABLE_NAME, COLUMN_NAME, userId, txid);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean unLockUser(String userId, String txid) {
		return DtpResource.unLock(TABLE_NAME, COLUMN_NAME, userId, txid);
	}

}
