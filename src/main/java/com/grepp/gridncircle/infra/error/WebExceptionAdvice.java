package com.grepp.gridncircle.infra.error;

import com.grepp.gridncircle.infra.error.exceptions.CommonException;
import com.grepp.gridncircle.infra.response.ResponseCode;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "com.grepp.gridncircle.app.controller.web")
public class WebExceptionAdvice {
    
    @ExceptionHandler(CommonException.class)
    public String webExceptionHandler(CommonException ex, Model model) {
        model.addAttribute("message", ex.code().message());
        return "error/redirect";
    }
    
    @ExceptionHandler(AuthorizationDeniedException.class)
    public String authorizationDeniedHandler(AuthorizationDeniedException ex, Model model){
        throw new CommonException(ResponseCode.UNAUTHORIZED);
    }
}
