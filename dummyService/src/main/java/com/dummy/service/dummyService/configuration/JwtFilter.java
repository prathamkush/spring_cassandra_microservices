package com.dummy.service.dummyService.configuration;

import com.dummy.service.dummyService.controller.AuthenticationConsumer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class JwtFilter extends GenericFilterBean {

    private AuthenticationConsumer tokenConsumerService;

    public JwtFilter(AuthenticationConsumer tokenConsumerService
    ){
        this.tokenConsumerService = tokenConsumerService;
    }


    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse res,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) res;

        String token = httpServletRequest.getHeader("Authorization");
        if("OPTIONS".equalsIgnoreCase(httpServletRequest.getMethod())) {
            httpServletResponse.sendError(HttpServletResponse.SC_OK, "success");
            return;
        }
        if(allowRequestWithoutToken(httpServletRequest)){
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(req,res);
        }
        else{
            String userId = tokenConsumerService.getUserIdFromToken(token);
            System.out.println(userId+"----------------------------------------------------------");
            httpServletRequest.setAttribute("userId",userId);
            filterChain.doFilter(req,res);
        }
    }

    public boolean allowRequestWithoutToken(HttpServletRequest httpServletRequest){
        System.out.println(httpServletRequest.getRequestURI());
        if(!httpServletRequest.getRequestURI().contains("/employee"))
            return true;

        return false;
    }
}
