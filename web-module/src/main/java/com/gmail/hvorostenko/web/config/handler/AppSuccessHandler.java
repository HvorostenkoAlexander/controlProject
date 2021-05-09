package com.gmail.hvorostenko.web.config.handler;

import com.gmail.hvorostenko.service.model.RoleUserEnum;
import com.gmail.hvorostenko.service.model.UserLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

public class AppSuccessHandler implements AuthenticationSuccessHandler {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserLogin userLogin = (UserLogin) authentication.getPrincipal();
            logger.info("User with login: " + userLogin.getUsername());
            if (userLogin.getUser().getRole().getName().name().equals(RoleUserEnum.ADMINISTRATOR.name())) {
                httpServletResponse.sendRedirect("/administrator/users");
            }
            if (userLogin.getUser().getRole().getName().name().equals(RoleUserEnum.CUSTOMER_USER.name())) {
                httpServletResponse.sendRedirect("/customer/articles");
            }
        } else {
            httpServletResponse.sendRedirect("/denied-page");
        }
    }
}
