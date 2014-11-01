package com.sevenklick.profile.backend.endpoint;


import com.sevenklick.profile.backend.exception.UploadException;
import com.sevenklick.common.core.helpers.CirrusWebService;
import com.sun.xml.ws.developer.SchemaValidation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by pierre.petersson on 04/09/2014.
 */

@Service
@WebService(name = "profileWS", serviceName = "profile-webservice")
@SchemaValidation
@Transactional(rollbackFor = Exception.class)
public class SearchWSImpl extends CirrusWebService {

    @WebMethod
    public void uploadCV(@WebParam(name = "uploadRequest") String document, @WebParam(name = "authenticationTicket") String authenticationTicket) throws UploadException {
    }
}
