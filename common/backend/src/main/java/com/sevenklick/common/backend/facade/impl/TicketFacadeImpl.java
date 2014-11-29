package com.sevenklick.common.backend.facade.impl;

import com.sevenklick.common.backend.facade.TicketFacade;
import com.sevenklick.common.backend.service.TicketService;
import com.sevenklick.common.backend.service.UserService;
import com.sevenklick.common.configuration.security.domain.UserSecurity;
import com.sevenklick.common.core.exception.NotAuthenticatedException;
import com.sevenklick.common.core.exception.TicketNotValidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: piepet
 * Date: 10/25/13
 * Time: 12:06 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
@Transactional(rollbackFor = Exception.class)
public class TicketFacadeImpl implements TicketFacade {
    private final Logger logger = LoggerFactory.getLogger(TicketFacadeImpl.class);
    @Autowired
    TicketService ticketService;
    @Autowired
    UserService userService;

    @Override
    public UserSecurity findUserByUserName(String userName) {
        return userService.findUserByUserName(userName);
    }
    @Override
    public String authenticate(String userName, String password) throws NotAuthenticatedException, TicketNotValidException {
        logger.debug("Authenticating user:"+userName);
        throw new NotAuthenticatedException(NotAuthenticatedException.NOT_AUTHENTICATED);
        //return ticketService.createTicket(userName, password);
    }

}
