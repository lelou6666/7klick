package com.primelog.cirrus.common.web;


import com.sevenklick.common.util.exception.NotAuthenticatedException;
import com.sevenklick.common.util.exception.TicketNotValidException;
import com.sevenklick.common.util.web.BaseController;
import com.sevenklick.common.backend.facade.TicketFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.NamingException;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/")
public class UtilController extends BaseController {

    @Autowired
    TicketFacade ticketFacade;
    @RequestMapping
    public String landingPage() {
    return "index";
    }
    @RequestMapping(value="/util/ticket/init")
    public String createTicket(ModelMap map){
        return "ticket";
    }
    @RequestMapping(value="/util/wsdl/init")
    public String wsdl(ModelMap map){
        return "wsdl";
    }

    @RequestMapping(value="/util/ticket", method = RequestMethod.POST)
    public String createTicket(@RequestParam(value="domain") String domain,@RequestParam(value="username") String username, @RequestParam(value="password") String password,ModelMap map) throws NotAuthenticatedException, TicketNotValidException, NamingException, NotAuthenticatedException, TicketNotValidException {
        map.put("ticket",ticketFacade.authenticate(username+"@"+domain, password));
        map.put("user", username);
        return "ticket";
    }

}
