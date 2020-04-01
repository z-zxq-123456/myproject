package com.exampl.zxq.handwirte.dubbo.Interface;

import org.springframework.stereotype.Service;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-04-01 16:39
 **/
@Service
public class UserServiceImpl implements UserService {

    @Override
    public void testUser() {
        System.out.println("===============UserServiceImpl===========");
    }
}
