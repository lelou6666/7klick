package com.sevenklick.common.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.xml.ws.WebFault;

/**
 * Created by pierre.petersson on 4/3/2014.
 */

@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="ViewStateException")
@WebFault(name="ViewStateException")
public class ViewStateException extends CirrusAbstractException {
    private static final long serialVersionUID = -5416909635216083634L;
    public final static String COULD_NOT_SAVE="viewstate.could.not.save";
    public final static String COULD_NOT_LOAD="viewstate.could.not.load";

    public ViewStateException(String messageCode) {
        super(messageCode);
    }

    public ViewStateException(String messageCode, Object errorObject) {
        super(messageCode, errorObject);
    }

    public ViewStateException(String messageCode, Throwable exception) {
        super(messageCode, exception);
    }

    public ViewStateException(String messageCode, String detailMessage) {
        super(messageCode, detailMessage);
    }
}
