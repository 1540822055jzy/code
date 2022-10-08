package com.gxa.mapper;

import com.gxa.entity.User;

import java.util.Set;

public interface UserMapperLogin {
    User queryByUsernameAndPassword(User user);
    User queryByName(String username);

    Set<String> queryPermsByUsername(String username);
}
