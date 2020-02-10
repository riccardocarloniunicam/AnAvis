package com.example.java.configuration;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.sql.DriverManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    private final String SEDE_QUERY = "select email,password, active from utente_sedi where email=?";
   // private final String ROLES_SEDI_QUERY = " select u.email, r.role from utente_sedi u inner join utente_sede_role ur on (u.id = ur.user_sede_id) inner join role r on (ur.role_id=r.role_id) where u.email=?";
   private final String ROLES_SEDI_QUERY = "select email,role  from utente_sedi where email=?";
    private final String USERS_QUERY = "select email, password, active from user where email=?";
    private final String ROLES_QUERY = "select u.email, r.role from user u inner join user_role ur on (u.id = ur.user_id) inner join role r on (ur.role_id=r.role_id) where u.email=?";


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //utenti
        auth.jdbcAuthentication()
                //RUOLO NON FUNZIONA
                .usersByUsernameQuery(SEDE_QUERY).authoritiesByUsernameQuery(ROLES_SEDI_QUERY).dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);


        auth.jdbcAuthentication()
                .usersByUsernameQuery(USERS_QUERY)
                .authoritiesByUsernameQuery(ROLES_QUERY)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
        //sedi
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/sede/**").hasAuthority("SEDE")
                .antMatchers("/home/nuovo-modulo").hasAuthority("USER")
                .antMatchers("/home/nuova-analisi").hasAuthority("DONATORE")
                .antMatchers("/home/**").hasAnyAuthority("USER","DONATORE")
                .anyRequest()
                .authenticated().and().csrf().disable()
                .formLogin().loginPage("/login")
                .failureUrl("/login?error=true")
                .successHandler(myAuthenticationSuccessHandler())
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .and().rememberMe().rememberMeParameter("my-remember-me")
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60*60)
                .and().exceptionHandling().accessDeniedPage("/access_denied");

    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MySimpleUrlAuthenticationSuccessHandler();
    }

    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);

        return db;
    }



}
