package com.sevenklick.common.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.xml.ws.WebFault;

/**
 * Created by pierre.petersson on 4/3/2014.
 */

@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="Internal server error")
@WebFault
public abstract class CirrusAbstractException extends Exception {
    public final static String TECHNICAL_ERROR="technical.error";
    private static final long serialVersionUID = 7819847122749720915L;
    private String detailMessage;
    private Object errorObject;
    public CirrusAbstractException(String messageCode){
        super(messageCode);
    }
    public CirrusAbstractException(String messageCode, Object errorObject){
        super(messageCode);
        this.errorObject = errorObject;
    }

    public CirrusAbstractException(String messageCode, Throwable exception){
        super(messageCode, exception);
    }

    public CirrusAbstractException(String messageCode, String detailMessage){
        super(messageCode);
        this.detailMessage=detailMessage;
    }
    public String getDetailMessage(){
        return this.detailMessage;
    }

    public Object getErrorObject() {
        return errorObject;
    }
}
