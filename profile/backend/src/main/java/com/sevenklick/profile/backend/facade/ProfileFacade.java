package com.sevenklick.profile.backend.facade;

import com.sevenklick.common.core.exception.NotAuthenticatedException;
import com.sevenklick.common.core.exception.TicketNotValidException;
import com.sevenklick.profile.backend.domain.UserEntity;

/**
 * Created by pierre.petersson on 2/5/14.
 */
public interface ProfileFacade {
    UserEntity updateProfile(byte[] content, String content_type, String name, Boolean visibility, String username, String password) throws NotAuthenticatedException, TicketNotValidException;
    boolean authenticateUser(String username, String password) throws NotAuthenticatedException;
}
