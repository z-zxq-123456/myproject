package com.qxz.learn.dao;

import java.util.List;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-03-01 19:57
 **/
public interface UserMapper {

    public List<User> getUser(String name);

}
