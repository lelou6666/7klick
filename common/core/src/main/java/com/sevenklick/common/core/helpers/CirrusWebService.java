package com.sevenklick.common.core.helpers;

import com.sevenklick.common.core.domain.Context;
import com.sevenklick.common.core.exception.TicketNotValidException;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * Created by pierre.petersson on 4/3/2014.
 * Baseclass for all Webservices with utility functions
 */
public abstract class CirrusWebService extends SpringBeanAutowiringSupport {
    /**
     * Validates authentication ticket by trying to create context.
     * @param ticket - Encrypted and Base64encoded json string containing User attributes
     * @throws TicketNotValidException - If invalid ticket this exception will be trown
     */
    public void validateTicket(String ticket) throws TicketNotValidException{
        Context context = new Context(ticket);
        ContextHandler.set(context);
    }
}
