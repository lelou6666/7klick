package com.sevenklick.common.backend.service.impl;

import com.google.gson.Gson;

import com.sevenklick.common.backend.domain.UserEntity;
import com.sevenklick.common.backend.service.TicketService;
import com.sevenklick.common.backend.service.UserService;
import com.sevenklick.common.core.exception.NotAuthenticatedException;
import com.sevenklick.common.core.exception.TicketNotValidException;
import com.sevenklick.common.core.helpers.EncryptionUtil;
import com.sevenklick.common.core.domain.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pierre.petersson on 4/5/2014.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TicketServiceImpl implements TicketService {
    @Autowired
    UserService userService;

    /**
     * Creates a authentication ticket to be used between all cirrus modules.
     * Once a ticket is created it will be reused between all interactions between modules.
     *
     * The User will be the bases to create the ticket and a timestamp.
     *
     * All relations to User will not be used to create ticket, such as Customer relation
     *
     * @param username - The users username
     * @param password - The user password
     * @return A JSON string which is Base64 encoded ticket and also cryptographically encrypted
     * @throws TicketNotValidException - If ticket has been manipulated or in any other case not correct
     * @throws NotAuthenticatedException - User not authenticated
     */
    @Override
    public String createTicket(String username, String password) throws NotAuthenticatedException {
        String ticket=null;
        UserEntity userEntity = userService.getAuthenticatedUser(username, password);
        if(userEntity!=null){
            UserContext userContext = new UserContext();
            userContext.setCountryCode(userEntity.getCountryCode());
            userContext.setLangCode(userEntity.getLangCode());
            userContext.setRoleCode(userEntity.getRoleCode());
            userContext.setUserName(userEntity.getUserName());
            ticket = EncryptionUtil.getInstance().encrypt((new Gson().toJson(userContext)));
        }
        return ticket;
    }
}
