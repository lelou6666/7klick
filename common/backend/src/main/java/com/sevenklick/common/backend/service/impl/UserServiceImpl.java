package com.sevenklick.common.backend.service.impl;


import com.sevenklick.common.backend.service.UserService;
import com.sevenklick.common.configuration.security.domain.UserSecurity;
import com.sevenklick.common.configuration.security.repository.AuthenticationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: larvat
 * Date: 10/23/13
 * Time: 4:27 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    AuthenticationRepository authenticationRepository;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserSecurity findUserByUserName(String userName) {
        return authenticationRepository.find(userName);

    }

    @Override
    public UserSecurity getAuthenticatedUser(String username, String password) {
        return null;
    }

}
