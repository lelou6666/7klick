package com.sevenklick.common.backend.endpoint;

import com.sevenklick.common.core.exception.TicketNotValidException;
import com.sevenklick.common.core.helpers.CirrusWebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Webservice publishing all cirrus-common services
 * User: piepet
 * Date: 10/25/13
 * Time: 12:13 PM
 */

@WebService(serviceName = "common-web-webservice")
@Service
@Transactional(rollbackFor = Exception.class)
public class CommonWSImpl extends CirrusWebService {
    private static final Logger logger = LoggerFactory.getLogger(CommonWSImpl.class);
    @WebMethod
    @Deprecated
    public void echo(@WebParam(name = "message") String message, @WebParam(name = "userName") String userName, @WebParam(name = "plcId") Long plcId, @WebParam(name = "authenticationTicket") String authenticationTicket) throws TicketNotValidException {
        validateTicket(authenticationTicket);
    }

}
