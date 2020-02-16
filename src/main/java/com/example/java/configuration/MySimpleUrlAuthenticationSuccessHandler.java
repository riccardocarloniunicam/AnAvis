package com.example.java.configuration;

import com.example.java.model.UtenteSedi;
import com.example.java.service.UtenteSediService;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    private UtenteSedi utenteSedi;

    @Autowired
    private UtenteSediService utenteSediService;
    public  UtenteSedi getUtenteSedi(){
        return utenteSedi;
    }
    public UtenteSedi fillInfo(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UtenteSedi u = utenteSediService.findUserByEmail(auth.getName());
        utenteSedi.setEmail(u.getEmail());
        utenteSedi.setId(u.getId());
        utenteSedi.setSede_id(u.getSede_id());
        return utenteSedi;
    }


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
                fillInfo();
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
