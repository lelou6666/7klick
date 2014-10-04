package com.sevenklick.common.backend.service;

        import com.sevenklick.common.util.exception.NotAuthenticatedException;

/**
 * Created by pierre.petersson on 4/5/2014.
 */
public interface TicketService {
    String createTicket(String username, String password) throws NotAuthenticatedException;
}
