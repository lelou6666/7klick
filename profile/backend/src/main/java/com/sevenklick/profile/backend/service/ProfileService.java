package com.sevenklick.profile.backend.service;


import com.sevenklick.common.core.exception.NotAuthenticatedException;
import com.sevenklick.profile.backend.domain.UserEntity;

/**
 * Created by pierre.petersson on 2/4/14.
 */
public interface ProfileService {
    boolean authenticateUser(String email, String password) throws NotAuthenticatedException;

    UserEntity isEmailUnique(String email);

    UserEntity updateProfile(UserEntity userEntity);

}