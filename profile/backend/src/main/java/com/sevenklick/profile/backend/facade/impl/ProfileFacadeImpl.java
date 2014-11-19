package com.sevenklick.profile.backend.facade.impl;

import com.google.gson.Gson;
import com.sevenklick.common.core.domain.Context;
import com.sevenklick.common.core.domain.UserContext;
import com.sevenklick.common.core.exception.NotAuthenticatedException;
import com.sevenklick.common.core.exception.TicketNotValidException;
import com.sevenklick.common.core.helpers.ContextHandler;
import com.sevenklick.common.core.helpers.EncryptionUtil;
import com.sevenklick.profile.backend.domain.CvEntity;
import com.sevenklick.profile.backend.domain.UserEntity;
import com.sevenklick.profile.backend.facade.ProfileFacade;
import com.sevenklick.profile.backend.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pierre.petersson
 */
@Component
@Transactional(rollbackFor = Exception.class)
public class ProfileFacadeImpl implements ProfileFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileFacadeImpl.class);

    @Autowired
    ProfileService profileService;

    @Override
    public boolean authenticateUser(String username, String password) throws NotAuthenticatedException {
        return profileService.authenticateUser(username, password);
    }

    @Override
    public UserEntity updateProfile(byte[] content, String contentType, String name, Boolean visibility, String email, String password) throws NotAuthenticatedException, TicketNotValidException {
        UserEntity userEntity = profileService.isEmailUnique(email);

        // If user does not exist create new user and store cv
        if (userEntity == null) {
            LOGGER.info("Username is unique creating user:" + email);
            userEntity = new UserEntity();
            userEntity.setEmail(email);
            userEntity.setPassword(password);
        } else {
            profileService.authenticateUser(userEntity.getEmail(), password);
        }

        ContextHandler.set(new Context(createTicket(email)));

        // Store uploaded cv
        CvEntity cvEntity = new CvEntity();
        cvEntity.setContent(content);
        cvEntity.setContentType(contentType);
        cvEntity.setName(name);
        cvEntity.setVisibility(visibility);
        userEntity.addCV(cvEntity);
        return profileService.updateProfile(userEntity);

    }
    private String createTicket(String userName){
        String ticket=null;
        UserContext userContext = new UserContext();
        userContext.setUserName(userName);
        ticket = EncryptionUtil.getInstance().encrypt((new Gson().toJson(userContext)));
        return ticket;

    }
}
