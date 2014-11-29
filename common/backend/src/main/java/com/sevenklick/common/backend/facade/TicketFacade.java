package com.sevenklick.common.backend.facade;

import com.sevenklick.common.configuration.security.domain.UserSecurity;
import com.sevenklick.common.core.domain.UserContext;
import com.sevenklick.common.core.exception.NotAuthenticatedException;
import com.sevenklick.common.core.exception.TicketNotValidException;

/**
 * Created by pierre.petersson on 3/10/14.
 */
public interface TicketFacade {
    public String authenticate(String user, String password) throws NotAuthenticatedException, TicketNotValidException;
    public UserSecurity findUserByUserName(String name);
}
