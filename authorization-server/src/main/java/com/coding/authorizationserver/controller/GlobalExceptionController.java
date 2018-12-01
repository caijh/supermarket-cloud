package com.coding.authorizationserver.controller;

import javax.servlet.http.HttpServletRequest;

import com.coding.commons.base.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理控制器.
 *
 * @author caijunhui
 **/
@ControllerAdvice
public class GlobalExceptionController {

    private static final String DEFAULT_ERROR_VIEW = "error";
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionController.class);

    /**
     * 全局异常处理.
     *
     * @param req HttpServletRequest
     * @param e   Exception
     * @return ModelAndView
     * @throws BizException if annotation ResponseStatus.
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws BizException {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw new BizException(e);
        }

        logger.error("defaultErrorHandler", e);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
}
