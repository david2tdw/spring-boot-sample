package com.macro.mall.tiny.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.macro.mall.tiny.entity.User;

public interface UserService extends IService<User> {
    public String checkUser (String name, int  age);
    public User getUserById ();
}
