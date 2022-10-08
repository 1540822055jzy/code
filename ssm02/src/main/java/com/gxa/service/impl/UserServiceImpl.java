package com.gxa.service.impl;

import com.gxa.entity.User;
import com.gxa.mapper.UserMapperLogin;
import com.gxa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapperLogin userMapperLogin;

    @Override
    public User login(User user) {
        User user1 = this.userMapperLogin.queryByUsernameAndPassword(user);

        return user1;
    }
}
