package com.sevenklick.common.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.xml.ws.WebFault;

/**
 * Created by pierre.petersson on 4/3/2014.
 */
@WebFault(name="NotAuthenticatedException")
@ResponseStatus(value= HttpStatus.UNAUTHORIZED, reason="User not authenticated, invalid user credentials")
public class NotAuthenticatedException extends CirrusAbstractException {
    public final static String NOT_AUTHENTICATED ="not.authenticated";
    public final static String PASSWORD_BLANK="password.blank";
    public final static String USERNAME_BLOCKED="username.blocked";

    public NotAuthenticatedException(String messageCode) {
        super(messageCode);
    }

    public NotAuthenticatedException(String messageCode, Object errorObject) {
        super(messageCode, errorObject);
    }

    public NotAuthenticatedException(String messageCode, Throwable exception) {
        super(messageCode, exception);
    }

    public NotAuthenticatedException(String messageCode, String detailMessage) {
        super(messageCode, detailMessage);
    }
}
