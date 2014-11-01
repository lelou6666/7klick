package com.sevenklick.common.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.xml.ws.WebFault;

/**
 * Created by pierre.petersson on 4/3/2014.
 */

@ResponseStatus(value= HttpStatus.UNAUTHORIZED, reason="TicketNotValidException")
@WebFault(name="TicketNotValidException")
public class TicketNotValidException extends CirrusAbstractException {
    private static final long serialVersionUID = 7002413436092375909L;
    public final static String NOT_AUHTHENTICATED="not.authenticated";
    public final static String NOT_VALID="ticket.not.valid";

    public TicketNotValidException(String messageCode) {
        super(messageCode);
    }

    public TicketNotValidException(String messageCode, Object errorObject) {
        super(messageCode, errorObject);
    }

    public TicketNotValidException(String messageCode, Throwable exception) {
        super(messageCode, exception);
    }

    public TicketNotValidException(String messageCode, String detailMessage) {
        super(messageCode, detailMessage);
    }
}
