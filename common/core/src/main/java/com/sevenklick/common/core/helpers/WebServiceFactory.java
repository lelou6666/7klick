package com.sevenklick.common.core.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceClient;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Helper class to configure client to access configured server instance.
 * Created by pierre.petersson on 3/19/2014.
 */
@Component
public class WebServiceFactory {
    private static final Logger logger = LoggerFactory.getLogger(WebServiceFactory.class);

    /**
     * Initialiazes the webservice that will be invoked.
     * Sets the active server_url that will be used to invoke the webservices.
     * @param webserviceClass
     * @return
     */
    public Service init(Class webserviceClass)  {
        WebServiceClient ann = (WebServiceClient)webserviceClass.getAnnotation(WebServiceClient.class);
        Constructor<Service> c=null;
        Service webserviceInstance=null;
        String serverUrl=null;
        String integrationTestServerUrl=null;
        try {
            c = ((Class)webserviceClass).getDeclaredConstructor(URL.class, QName.class);
            c.setAccessible(true);
            serverUrl= System.getProperties().getProperty("server_url");
            integrationTestServerUrl = System.getProperties().getProperty("integrationTestServer");
            URI uri = new URI(ann.wsdlLocation());
            if(integrationTestServerUrl==null) {
                if (serverUrl == null) {
                    serverUrl = "http://localhost:7001" + uri.getPath();
                    logger.debug("****************System Property -Dserver_url is not SET!!!! will use :=" + serverUrl + "  *********************");
                } else if (serverUrl != null && serverUrl.indexOf("://") > 0) {
                    serverUrl = serverUrl + uri.getPath();
                    logger.debug("****************System Property -Dserver_url=" + serverUrl + " is set *********************");
                } else if (serverUrl != null && serverUrl.indexOf("://") < 0) {
                    serverUrl = "https://" + serverUrl + uri.getPath();
                    logger.debug("****************System Property -Dserver_url=" + serverUrl + " is set *********************");
                }
            }else{
                serverUrl = "https:" + integrationTestServerUrl + uri.getPath();
            }
            QName qName = new QName(ann.targetNamespace(), ann.name());
            webserviceInstance= WebServiceConnectionPoolFactory.getInstance().getService(serverUrl,qName, c);
        } catch (InstantiationException e) {
            logger.error("Not a valid webservice implementation, must implement Service interface", e);
        } catch (IllegalAccessException e) {
            logger.error("Not a valid webservice implementation, must implement Service interface", e);
        } catch (NoSuchMethodException e) {
            logger.error("Not a valid webservice implementation, must implement Service interface", e);
        } catch (MalformedURLException e) {
            logger.error("Server url not valid: "+serverUrl, e);
        } catch (InvocationTargetException e) {
            logger.error("Not a valid webservice implementation, must implement Service interface", e);
        } catch (URISyntaxException e) {
            logger.error("WSDL uri not correct: "+ann.wsdlLocation(), e);
        }

        return webserviceInstance;
    }
}
