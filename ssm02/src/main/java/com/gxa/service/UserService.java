package com.gxa.service;

import com.gxa.entity.User;
import org.springframework.stereotype.Service;


public interface UserService {
    User login(User user);
}
