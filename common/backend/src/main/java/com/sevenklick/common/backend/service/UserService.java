package com.sevenklick.common.backend.service;


import com.sevenklick.common.util.exception.NotAuthenticatedException;
import com.sevenklick.common.api.domain.UserEntity;

public interface UserService {

    public UserEntity getAuthenticatedUser(String userName, String providedPassword) throws NotAuthenticatedException;

}
