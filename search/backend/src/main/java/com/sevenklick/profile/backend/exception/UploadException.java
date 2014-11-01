package com.sevenklick.profile.backend.exception;


import com.sevenklick.common.core.exception.CirrusAbstractException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.xml.ws.WebFault;

/**
 * Created by pierre.petersson on 4/3/2014.
 */
@WebFault(name="LoadImportException")
@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="LoadImportException")
public class UploadException extends CirrusAbstractException {
    private static final long serialVersionUID = -5416909635216083634L;
    public final static String LOAD_IMPORT_EXCEPTION="load.import.exception";
    private Object errorObject;

    public UploadException(String messageCode, Object errorObject) {
        super(messageCode, errorObject);
    }

    public UploadException(String messageCode, Throwable exception) {
        super(messageCode, exception);
    }

    public UploadException(String messageCode, String detailMessage) {
        super(messageCode, detailMessage);
    }
}
