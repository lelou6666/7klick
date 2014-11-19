package com.sevenklick.common.core.web;

import com.sevenklick.common.core.exception.TicketNotValidException;
import com.sevenklick.common.core.helpers.ContextHandler;
import com.sevenklick.common.core.domain.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import sun.security.krb5.internal.Ticket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Session interceptor to exctract ticket, lookup chain for ticket is
 * 1. Http Request Header - ticket
 * 2. Http Session
 * 3. Http request post or querystring
 */
@Component
public class SessionInterceptor implements HandlerInterceptor  {
    private final Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

    @Override
    public  boolean preHandle(HttpServletRequest request,
                                 HttpServletResponse response, Object handler) throws Exception {
        try {
            String ticket = null;
            String ticketFromHeader=request.getHeader("Authority");
            if (request.getParameter("ticket") != null) {
                ticket = request.getParameter("ticket").toString();
            } else {
                throw new TicketNotValidException(TicketNotValidException.NOT_VALID);
            }
            logger.debug("Prehandle: ticket=" + ticket);
            Context context = new Context(ticket);
            ContextHandler.set(context);
        }catch (TicketNotValidException ex){
            throw new TicketNotValidException(ex.getMessage(), ex.getDetailMessage());
        }
            return true;

        }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        logger.debug("Post-handle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        logger.debug("After completion handle");
    }
}