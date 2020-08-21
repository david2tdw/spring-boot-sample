package com.macro.mall.tiny.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.macro.mall.tiny.entity.User;
import com.macro.mall.tiny.mapper.UserMapper;
import com.macro.mall.tiny.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;

    public boolean checkUser() {
        return false;
    }

    @Override
    public String checkUser(String name, int age) {
        User user = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getId, 10086));
        return user.getId() + "";
    }

    public User getUserById() {
        return userMapper.selectUserById(123L);
    }

}
