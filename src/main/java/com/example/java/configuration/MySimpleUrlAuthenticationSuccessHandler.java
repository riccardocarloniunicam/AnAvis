package com.example.java.configuration;

import org.apache.juli.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse arg1,
                                        Authentication authentication) throws IOException, ServletException {


        boolean hasUserRole = false;
        boolean hasDonatoreRole = false;
        boolean hasSedeRole = false;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("USER")) {
                hasUserRole = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("DONATORE")) {
                hasDonatoreRole = true;
                break;
            }else if (grantedAuthority.getAuthority().equals("SEDE")){
                hasSedeRole = true;
                break;
            }
        }

        if (hasUserRole) {
            redirectStrategy.sendRedirect(arg0, arg1, "/home/home");
        } else if (hasDonatoreRole){
            redirectStrategy.sendRedirect(arg0,arg1,"/home/home");
        }

        else if (hasSedeRole) {
            redirectStrategy.sendRedirect(arg0, arg1, "/sede/home");
        } else {
            throw new IllegalStateException();
        }
    }


}
