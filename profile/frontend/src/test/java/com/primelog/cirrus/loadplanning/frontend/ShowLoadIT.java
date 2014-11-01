package com.primelog.cirrus.loadplanning.frontend;


import com.sevenklick.common.core.test.CirrusTestExecutionListener;

import com.sevenklick.profile.backend.domain.CvEntity;
import com.sevenklick.profile.backend.domain.UserEntity;
import com.sevenklick.profile.backend.repository.UserRepository;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by pierre.petersson on 24/06/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@TestExecutionListeners( inheritListeners = false, listeners = { CirrusTestExecutionListener.class, TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = "classpath*:META-INF/spring/profile-backend-context.xml")
public class ShowLoadIT {

    private final static Long TEST_USER=1000010L;
    private final static Long TEST_CV=2000010L;
    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    public void createUser(){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test.m.petersson@gmail.com");
        userEntity.setPassword("pwdtest");
        UserEntity createdUserEntity = userRepository.save(userEntity);
        assertNotNull(createdUserEntity);
    }
    @Test
    @Transactional
    public void createUserWithCV() throws IOException {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("test.m.petersson@gmail.com");
        userEntity.setPassword("pwdtest");


        CvEntity cvEntity = new CvEntity();
        cvEntity.setContent(IOUtils.toByteArray(getClass().getResourceAsStream("/test-data/load-technicolor.xml")));
        cvEntity.setName("Test CV");
        cvEntity.setVisibility(false);
        userEntity.addCV(cvEntity);

        UserEntity createdEntityWithCV=userRepository.save(userEntity);

        assertNotNull(createdEntityWithCV);
        assertEquals(createdEntityWithCV.getCvEntities().size(), 1);
    }


    @Test
    @Transactional
    public void getUserTest() throws IOException, InterruptedException {
        createUser();
        UserEntity createdUserEntity = userRepository.findOne(1L);
        assertNotNull(createdUserEntity);
    }


}
