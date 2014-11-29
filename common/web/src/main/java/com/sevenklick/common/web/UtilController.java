package com.sevenklick.common.web;


import com.sevenklick.common.backend.service.UserService;
import com.sevenklick.common.configuration.security.domain.UserSecurity;
import com.sevenklick.common.core.domain.UserContext;
import com.sevenklick.common.core.exception.NotAuthenticatedException;
import com.sevenklick.common.core.exception.TicketNotValidException;
import com.sevenklick.common.core.helpers.TokenUtils;
import com.sevenklick.common.core.web.BaseController;
import com.sevenklick.common.backend.facade.TicketFacade;
import com.sevenklick.common.core.web.TokenTransfer;
import com.sevenklick.common.core.web.UserTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.NamingException;
import javax.ws.rs.FormParam;
import javax.ws.rs.WebApplicationException;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/")
public class UtilController extends BaseController {
    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;

    @Autowired
    TicketFacade ticketFacade;
    @RequestMapping
    public String landingPage(ModelMap modelMap) {
/*
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Object principal = authentication.getPrincipal();
            if (principal instanceof String && ((String) principal).equals("anonymousUser")) {
                throw new WebApplicationException(401);
            }
            UserDetails userDetails = (UserDetails) principal;

            modelMap.addAttribute(new UserTransfer(userDetails.getUsername(), this.createRoleMap(userDetails)));
*/

        return "index";
    }
    private Map<String, Boolean> createRoleMap(UserDetails userDetails)
    {
        Map<String, Boolean> roles = new HashMap<String, Boolean>();
        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            roles.put(authority.getAuthority(), Boolean.TRUE);
        }

        return roles;
    }

    @RequestMapping(value="/util/ticket/init")
    public String createTicket(ModelMap map){
        return "ticket";
    }
    @RequestMapping(value="/util/wsdl/init")
    public String wsdl(ModelMap map){
        return "wsdl";
    }

    @RequestMapping(value="/authenticate", method = RequestMethod.POST)
    public String createTicket(@RequestParam(value="username") String username, @RequestParam(value="password") String password,ModelMap map) throws NotAuthenticatedException, TicketNotValidException, NamingException, NotAuthenticatedException, TicketNotValidException {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = this.authManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
		/*
		 * Reload user as password of authentication principal will be null after authorization and
		 * password is needed for token generation
		 */

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        map.addAttribute("token", new TokenTransfer(TokenUtils.createToken(userDetails)).getToken());
        return "ticket";
    }
    @RequestMapping(value="/lookup", method = RequestMethod.POST, produces = "application/json")
    public UserSecurity loadUserByUsername(@RequestParam(value="username") String username) {
        return ticketFacade.findUserByUserName(username);
    }
    @RequestMapping(value="/test" )
    public String test() {
        return "index";
    }

    @RequestMapping(value="/util/ticket", method = RequestMethod.GET)
    public String createTicket() throws NotAuthenticatedException, TicketNotValidException, NamingException, NotAuthenticatedException, TicketNotValidException {
        return "ticket";
    }

}
