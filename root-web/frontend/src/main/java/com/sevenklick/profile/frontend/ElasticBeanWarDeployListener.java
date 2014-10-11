package com.sevenklick.profile.frontend;

/**
 * Created by pierre.petersson on 12/10/2014.
 */

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;


public class ElasticBeanWarDeployListener implements ServletContextListener {

        ServletContext context;
        public void contextInitialized(ServletContextEvent contextEvent) {
            File warFile = new File("/var/lib/tomcat7/webapps/ROOT/common-web.war");
            File moveToDir = new File ("/var/lib/tomcat7/webapps");
            boolean success = warFile.renameTo (new File (moveToDir, warFile.getName ()));
            System.out.println("common-web :"+success);
            File warProfile = new File("/var/lib/tomcat7/webapps/ROOT/profile.war");
            success = warProfile.renameTo (new File (moveToDir, warProfile.getName ()));
            System.out.println("profile :"+success);

        }
        public void contextDestroyed(ServletContextEvent contextEvent) {
        }
    }