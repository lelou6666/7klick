package com.primelog.cirrus.common.util;

import com.sevenklick.common.core.exception.TicketNotValidException;
import com.sevenklick.common.core.domain.Context;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by pierre petersson on 2014-04-06.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:junit-test-context.xml")
public class ContextTest extends TestCase {
    @Test
    public void createContextSuccessful() throws TicketNotValidException {
        String authenticationTicket = "ggXJZDbZHDcpl5g5O_S10F8UpPud0ySA89tHrO-QnhUW0aOYHo1ZPFlwReH7s-Uxe_urNKTotJj2qPm83b-Fl5a6oG738RcYPa1b1QHstY_EITCKvPNwvIHGlyPbcgWC4RAZRJrL3FSY-tFb0SlddO-valNADLXeesHyZJNYNIBYYs8ljuXQd9Z8Ftsb6QTYuUqSyv1KqxaeZSovgC6dAFhBwGp_Nw0qlp4Hsbfbzwum2v--xUatuqlMdFN-3n5PoULIjqm8lvxB9y60OZRnzNPuG3-rwR4ZVcJNSQCZFdDRbQYhVb3LLill5kPIMipTe6gjsnZhBKjekvjqkBrDWlJnirD15dBKsLfxOFnMHEMaLWdYjx309pz1YXANeOxm86Eb36UCIM_B_bMEbpWSQr5564va7nl0R-_4e02WcuPCf2amFsh6oW2HtH5XT6w5_Ep0sc6-e7E6IOKrpu-3tzSpvpV0r9NAFkxYJFFU4SMGRm1LMMpCgzAaW7ZgdzANh9lJY5D530mSIZdEBADgc0IPAPf89ZOaY2GvRNcmYAM";
        Context context = new Context(authenticationTicket);
        assertEquals("primelog@sandvik-smc",context.getUsername());

    }
    @Test(expected =TicketNotValidException.class )
    public void createContextUnsuccessful() throws TicketNotValidException {
        String manipulatedAuthenticationTicket = "Manipulated-Fl5a6oG738RcYPa1b1QHstY_EITCKvPNwvIHGlyPbcgWC4RAZRJrL3FSY-tFb0SlddO-valNADLXeesHyZJNYNIBYYs8ljuXQd9Z8Ftsb6QTYuUqSyv1KqxaeZSovgC6dAFhBwGp_Nw0qlp4Hsbfbzwum2v--xUatuqlMdFN-3n5PoULIjqm8lvxB9y60OZRnzNPuG3-rwR4ZVcJNSQCZFdDRbQYhVb3LLill5kPIMipTe6gjsnZhBKjekvjqkBrDWlJnirD15dBKsLfxOFnMHEMaLWdYjx309pz1YXANeOxm86Eb36UCIM_B_bMEbpWSQr5564va7nl0R-_4e02WcuPCf2amFsh6oW2HtH5XT6w5_Ep0sc6-e7E6IOKrpu-3tzSpvpV0r9NAFkxYJFFU4SMGRm1LMMpCgzAaW7ZgdzANh9lJY5D530mSIZdEBADgc0IPAPf89ZOaY2GvRNcmYAM";
        new Context(manipulatedAuthenticationTicket);
    }
}
