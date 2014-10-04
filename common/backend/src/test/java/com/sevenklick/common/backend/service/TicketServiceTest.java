package com.sevenklick.common.backend.service;

import com.sevenklick.common.util.exception.NotAuthenticatedException;
import com.sevenklick.common.util.exception.TicketNotValidException;
import com.sevenklick.common.util.helpers.Base64Util;
import com.sevenklick.common.api.domain.UserEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by pierre.petersson on 2014-04-06.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:junit-test-context.xml")
public class TicketServiceTest {
    private static final String USER = "sandvik-smc@primelog";
    private static final String PASSWORD_INCORRECT = "wrongpwassword";
    private static final String PASSWORD_ENCODED = "{SHA}JWS9YX46tIZWCdEYZC0yPJGUM94=";

    @Autowired
    TicketService ticketService;
    @Before
    public void init() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(USER);
        userEntity.setLangCode("SE");
        userEntity.setCountryCode("SE");
        userEntity.setRoleCode("ADMIN");

    }
    @Test
    public void createTicketIsSuccessful() throws NotAuthenticatedException, TicketNotValidException {
        String ticket = ticketService.createTicket(USER, PASSWORD_ENCODED);
        assertNotNull(ticket);
        assertTrue(Base64Util.isBase64(ticket));
    }

}
