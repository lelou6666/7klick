package com.sevenklick.common.core.exception;

import com.sevenklick.common.core.domain.ErrorInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created by pierre.petersson on 23/08/2014.
 */
@ControllerAdvice
class GlobalDefaultExceptionHandler {
    @Autowired
    private MessageSource messageSource;
    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e, HttpServletRequest req, HttpServletResponse response) {
        ErrorInfo errorInfo;
        String userLocalizedMessage;
        if(AnnotationUtils.getAnnotation(e.getClass(), ResponseStatus.class)==null){
            logger.error("Technical error" ,e.getStackTrace());
            userLocalizedMessage = messageSource.getMessage(CirrusAbstractException.TECHNICAL_ERROR, null, Locale.getDefault());
            errorInfo = new ErrorInfo(req.getRequestURL().toString(),userLocalizedMessage+"\n[["+e.toString()+"]]", e.toString());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        }else {
            HttpStatus httpStatus = AnnotationUtils.getAnnotation(e.getClass(), ResponseStatus.class).value();
            response.setStatus(httpStatus.value());
            try {
                userLocalizedMessage = messageSource.getMessage(e.getMessage(), null, Locale.getDefault());
                String technicalMessage = ((CirrusAbstractException)e).getDetailMessage();
                logger.error(technicalMessage, e);
                errorInfo = new ErrorInfo(req.getRequestURL().toString(), userLocalizedMessage, technicalMessage);
            } catch (Exception ex) {
                logger.error("Technical error", e.getStackTrace());
                userLocalizedMessage = messageSource.getMessage(CirrusAbstractException.TECHNICAL_ERROR, null, Locale.getDefault());
                errorInfo = new ErrorInfo(req.getRequestURL().toString(), userLocalizedMessage+"\n[["+e.toString()+"]]", e.toString());
            }
        }

        ModelMap model = new ModelMap();
        model.addAttribute("cirrusError", errorInfo);
        return new ModelAndView("error", model);
    }

}