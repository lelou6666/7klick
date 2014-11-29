package com.sevenklick.common.core.web;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Set;
@Component
public class RestTemplateUtil {
    private String baseUrl;

    public URI buildTemplateUrl(HttpServletRequest request,  String resourcePath, String ticket) {
        String basePath=getBaseURL(request);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(basePath + resourcePath)
                .queryParam("ticket",ticket );
        return builder.build().toUri();
    }
    private String 	getBaseURL(HttpServletRequest httpRequest){
        String serverUrl= System.getProperties().getProperty("server_url");
        String restServiceBaseUrl="";
        if(serverUrl!=null && !serverUrl.contains("://")){
            restServiceBaseUrl = "https://"+serverUrl+httpRequest.getContextPath();
        }else if(serverUrl!=null && serverUrl.indexOf("://")>0){
            restServiceBaseUrl = serverUrl+httpRequest.getContextPath();
        }
        // Default always weblogic localhost:8080
        else if(serverUrl==null ){
            restServiceBaseUrl = "http://localhost:8080"+httpRequest.getContextPath();
        }
        return restServiceBaseUrl;

    }

}
