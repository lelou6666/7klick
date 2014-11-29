package com.sevenklick.common.backend.service;


import com.sevenklick.common.configuration.security.domain.UserSecurity;

public interface UserService {

    UserSecurity findUserByUserName(String userName);
    UserSecurity getAuthenticatedUser(String username, String password);

}
