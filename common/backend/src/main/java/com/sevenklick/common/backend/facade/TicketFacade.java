package com.sevenklick.common.backend.facade;

import com.sevenklick.common.util.exception.NotAuthenticatedException;
import com.sevenklick.common.util.exception.TicketNotValidException;

/**
 * Created by pierre.petersson on 3/10/14.
 */
public interface TicketFacade {
    public String authenticate(String user, String password) throws NotAuthenticatedException, TicketNotValidException;
}
