package com.primelog.cirrus.loadplanning.frontend;


import com.sevenklick.common.util.test.CirrusTestExecutionListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Date;

/**
 * Created by pierre.petersson on 24/06/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@TestExecutionListeners( inheritListeners = false, listeners = { CirrusTestExecutionListener.class, TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = "classpath*:META-INF/spring/profile-backend-context.xml")
public class ShowLoadIT {

    @Test
    @Transactional
    public void importTechnicolorLoadIsSuccessful() throws IOException, InterruptedException {
/*
        String originLoadXml = IOUtils.toString(getClass().getResourceAsStream("/test-data/load-technicolor.xml"), "UTF-8");
        String updatedLoadReferencesXML=updateReferences(originLoadXml);
        assertNotEquals(originLoadXml, updatedLoadReferencesXML);
        CirrusResponseStatus status=restTemplate.postForObject(getRestServiceBaseURL()+ LOAD_IMPORT +"?ticket=0Pd_qJcE5fTUA9B7KnfMawmHlAzKU6g_12aEdpXOtzQST9Q5w80cb_tb2mRW5vDQSDIKq1qd6jui5J6yk5jjOQOAIWj4x-_R2U0MqZn-pGuDY2cCD_Wba_SCJgQQteXK",updatedLoadReferencesXML, CirrusResponseStatus.class );
        System.out.println("Message:"+status.getMessage());
        System.out.println("Reference:"+status.getReference());
        System.out.println("Status:"+status.getStatus());
        assertEquals(status.getMessage().toString(),"OK",status.getStatus());
*/
    }
    private String getRestServiceBaseURL(){
        return serverUrl+contextPath;
    }
    private String updateReferences(String xml){
        long referenceSuffix=new Date().getTime();
        long customerShipmentSuffix1 = new Date().getTime();
        long customerShipmentSuffix2 = new Date().getTime();

        xml=xml.replace("[customer-shipment-reference-1]", "int-customer-shipment-reference-1"+ customerShipmentSuffix1);
        xml=xml.replace("[customer-shipment-reference-2]", "int-customer-shipment-reference-2"+ customerShipmentSuffix2);

        xml=xml.replace("[purchased-activity-1-1]", "int-purchased-activity-1-1"+ referenceSuffix);
        xml=xml.replace("[purchased-activity-1-2]", "int-purchased-activity-1-2"+ referenceSuffix);


        xml=xml.replace("[part-description-2-1-1]", "int-part-description-2-1-1"+ referenceSuffix);
        xml=xml.replace("[part-description-2-1-2]", "int-part-description-2-1-2"+ referenceSuffix);

        xml=xml.replace("[part-description-1-2-1]", "int-part-description-1-2-1"+ referenceSuffix);
        xml=xml.replace("[part-description-1-2-2]", "int-part-description-1-2-2"+ referenceSuffix);

        xml=xml.replace("[part-description-1-1-1]", "int-part-description-1-1-1"+ referenceSuffix);
        xml=xml.replace("[part-description-1-1-2]", "int-part-description-1-1-2"+ referenceSuffix);

        xml=xml.replace("[load-reference]", "int-load-reference"+ referenceSuffix);
        xml=xml.replace("[part-reference-1-1]", "int-part-reference-1-1"+ referenceSuffix);
        xml=xml.replace("[part-reference-1-2]", "int-part-reference-1-2"+ referenceSuffix);

        xml=xml.replace("[departure-1-1]", "int-departure-1-1"+ referenceSuffix);
        xml=xml.replace("[departure-1-2]", "int-departure-1-2"+ referenceSuffix);

        return xml;
    }
    private RestTemplate restTemplate = new RestTemplate();
    private final String serverUrl="http://localhost:7040";
    private final String contextPath="/load-planning";
    private final String LOAD_IMPORT ="/cirrus/v1/loadplanning/load.json";

}
