package com.sevenklick.common.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.xml.ws.WebFault;

/**
 * Created by pierre.petersson on 4/3/2014.
 */
@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="TechnicalException")
@WebFault(name="TechnicalException")
public class TechnicalException extends CirrusAbstractException {
    private static final long serialVersionUID = 1505477526692909759L;
    public final static String TECHNICAL_ERROR="technical.exception";

    public TechnicalException(String messageCode) {
        super(messageCode);
    }

    public TechnicalException(String messageCode, Object errorObject) {
        super(messageCode, errorObject);
    }

    public TechnicalException(String messageCode, Throwable exception) {
        super(messageCode, exception);
    }

    public TechnicalException(String messageCode, String detailMessage) {
        super(messageCode, detailMessage);
    }
}
