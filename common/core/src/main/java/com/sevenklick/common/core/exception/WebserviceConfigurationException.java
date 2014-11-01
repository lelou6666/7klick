package com.sevenklick.common.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.xml.ws.WebFault;

/**
 * Created by pierre.petersson on 4/3/2014.
 */

@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="WebserviceConfigurationException")
@WebFault(name="WebserviceConfigurationException")
public class WebserviceConfigurationException extends CirrusAbstractException {
    private static final long serialVersionUID = 1505477526692909759L;
    public final static String WSDL_EMPTY="wsdl.empty";

    public WebserviceConfigurationException(String messageCode) {
        super(messageCode);
    }

    public WebserviceConfigurationException(String messageCode, Object errorObject) {
        super(messageCode, errorObject);
    }

    public WebserviceConfigurationException(String messageCode, Throwable exception) {
        super(messageCode, exception);
    }

    public WebserviceConfigurationException(String messageCode, String detailMessage) {
        super(messageCode, detailMessage);
    }
}
