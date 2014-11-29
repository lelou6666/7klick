package com.sevenklick.common.backend.service;


import com.sevenklick.common.backend.facade.TicketFacade;
import com.sevenklick.common.configuration.security.domain.UserSecurity;
import com.sevenklick.common.configuration.security.repository.AuthenticationRepository;
import com.sevenklick.common.core.exception.NotAuthenticatedException;
import com.sevenklick.common.core.exception.TicketNotValidException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertNotNull;

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
    @Autowired
    TicketFacade ticketFacade;
    @Autowired
    AuthenticationRepository authenticationRepository;
    @Before
    public void init() {
        UserSecurity userEntity = new UserSecurity();
        userEntity.setUsername(USER);

    }
    @Test
    public void createTicketIsSuccessful() throws NotAuthenticatedException, TicketNotValidException {
        StandardPasswordEncoder standardPasswordEncoder = new StandardPasswordEncoder("xYz131--12145.1");
        System.out.println(standardPasswordEncoder.encode("admin"));
        UserSecurity userContext=authenticationRepository.find("admin");
        assertNotNull(userContext);
    }

}
