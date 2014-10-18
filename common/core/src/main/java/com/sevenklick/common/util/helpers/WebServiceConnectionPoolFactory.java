package com.sevenklick.common.util.helpers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Webservices connection pool factory. Used by Webservice factory to improve performance radically.
 * @author Pierre Petersson
 *
 */
public class WebServiceConnectionPoolFactory {
    private static Map<String, Service> webservicePool = new ConcurrentHashMap<String, Service>();
    private static WebServiceConnectionPoolFactory webServiceConnectionPoolFactory;
    private static final Logger logger = LoggerFactory.getLogger(WebServiceConnectionPoolFactory.class);
    protected static WebServiceConnectionPoolFactory getInstance(){
        if(webServiceConnectionPoolFactory==null){
            WebServiceConnectionPoolFactory.webServiceConnectionPoolFactory = new WebServiceConnectionPoolFactory();
        }
        return WebServiceConnectionPoolFactory.webServiceConnectionPoolFactory;
    }
    protected Service getService(String wsdLocation, QName qName, Constructor<Service> serviceConstructor) throws IllegalAccessException, InvocationTargetException, InstantiationException, MalformedURLException {
        if(WebServiceConnectionPoolFactory.webservicePool.containsKey(wsdLocation)){
            logger.debug("Accessing wsdl from WS-pool:"+wsdLocation);
            return WebServiceConnectionPoolFactory.webservicePool.get(wsdLocation);
        }else {
            logger.debug("Accessing wsdl at URL:"+wsdLocation);
            URL url = new URL(wsdLocation);
            Service serviceInstance = serviceConstructor.newInstance(url, qName);
            serviceInstance.setExecutor(java.util.concurrent.Executors.newFixedThreadPool(10));
            WebServiceConnectionPoolFactory.webservicePool.put(wsdLocation, serviceInstance);
            return serviceInstance;
        }
    }

}