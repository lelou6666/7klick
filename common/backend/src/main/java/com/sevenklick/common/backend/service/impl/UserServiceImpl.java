package com.sevenklick.common.backend.service.impl;

import com.sevenklick.common.util.exception.NotAuthenticatedException;
import com.sevenklick.common.api.domain.UserEntity;
import com.sevenklick.common.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserEntity getAuthenticatedUser(String userName, String providedPassword) throws NotAuthenticatedException {
        /**
         * Hardcoded values needs to be looked up from database
         */
        UserEntity userEntity = new UserEntity();
        userEntity.setRoleCode("admin");
        userEntity.setCountryCode("SE");
        userEntity.setLangCode("SV");
        userEntity.setUserName("test");
        return userEntity;

    }

}
