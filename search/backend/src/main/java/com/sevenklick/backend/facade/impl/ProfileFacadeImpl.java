package com.sevenklick.backend.facade.impl;

import com.sevenklick.backend.facade.ProfileFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lars.vateman on 2/5/14.
 */
@Component
@Transactional(rollbackFor = Exception.class)
public class ProfileFacadeImpl implements ProfileFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileFacadeImpl.class);

    @Override
    public void upload() {

    }
}
