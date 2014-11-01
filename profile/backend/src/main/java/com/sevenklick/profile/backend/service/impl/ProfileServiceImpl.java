package com.sevenklick.profile.backend.service.impl;

import com.sevenklick.common.core.exception.NotAuthenticatedException;
import com.sevenklick.profile.backend.domain.UserEntity;
import com.sevenklick.profile.backend.repository.UserRepository;
import com.sevenklick.profile.backend.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pierre.petersson
 */
@Service
public class ProfileServiceImpl implements ProfileService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileService.class);
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean authenticateUser(String email, String password) throws NotAuthenticatedException {
        UserEntity userEntity = userRepository.validateUserCredentials(email, password);
        if(userEntity==null){
         throw new NotAuthenticatedException(NotAuthenticatedException.NOT_AUTHENTICATED, "Invalid usercredentials for user:"+ email);
        }else{
            return true;
        }
    }

    @Override
    public UserEntity isEmailUnique(String email) {
        return userRepository.isEmailUnique(email);
    }

    @Override
    public UserEntity updateProfile(UserEntity userEntity) {
        LOGGER.info("Updating profile for user:" + userEntity.getEmail() + " with <<" + userEntity.getCvEntities().size() + ">> CV:s");
        return userRepository.save(userEntity);
    }

}
