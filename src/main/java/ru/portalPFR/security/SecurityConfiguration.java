package ru.portalPFR.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import ru.portalPFR.enums.RoleType;
//import ru.portalPFR.service.CustomPersistentTokenBasedRememberMeServices;
//import ru.portalPFR.service.PersistentLoginService;
import ru.portalPFR.util.Constants;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * Created by 048ChubakovaEL on 15.08.2016.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    public static final int TOKEN_VALIDITY_SECONDS = 86400;

    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;


//    @Autowired
//    private PersistentLoginService persistentLoginService;
//
    @Autowired
    PersistentTokenRepository tokenRepository;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//                CharacterEncodingFilter filter = new CharacterEncodingFilter();
//        filter.setEncoding(StandardCharsets.UTF_8.displayName());
//        filter.setForceEncoding(true);
//        http.addFilterBefore(filter, CsrfFilter.class);
        http.authorizeRequests()
//                .antMatchers("/", "/list")
//                .access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
                .antMatchers(Constants.Url.RESOURCES + "/**").permitAll()
                .antMatchers(Constants.Url.ROOT).permitAll()
//                .antMatchers(Constants.Url.LOGIN + "/**").permitAll()
//                .antMatchers(Constants.Url.ADMIN + "/**").hasAnyAuthority(RoleType.ADMIN.getName())
//                .antMatchers(Constants.Url.USER + "/**").hasAnyAuthority(RoleType.ADMIN.getName(), RoleType.USER.getName())
//                .and().formLogin().defaultSuccessUrl(Constants.Url.ROOT).loginPage(Constants.Url.LOGIN).failureUrl(Constants.Url.LOGIN + "?error=true")
//                .and().rememberMe().rememberMeServices(rememberMeServices()).key(rememberMeServices().getKey()).tokenValiditySeconds(TOKEN_VALIDITY_SECONDS)
//                .and().logout().permitAll().logoutSuccessUrl(Constants.Url.LOGIN + "?logout=true")
//                .and().exceptionHandling().accessDeniedPage(Constants.Url.ERROR_403)
//                .and().csrf()
//                .and()
//                .userDetailsService(userDetailsService());



//                .antMatchers("/newuser/**", "/delete-user-*").access("hasRole('ADMIN')").antMatchers("/edit-user-*")
//                .access("hasRole('ADMIN') or hasRole('DBA')").and().formLogin().loginPage("/login")
//                .loginProcessingUrl("/login").usernameParameter("ssoId").passwordParameter("password")
 .and()
                .rememberMe().rememberMeParameter("remember-me")
                .tokenRepository(tokenRepository)
                .tokenValiditySeconds(86400).and().csrf().and().exceptionHandling().accessDeniedPage("/Access_Denied")
                .and().userDetailsService(userDetailsService);
    }



//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        CharacterEncodingFilter filter = new CharacterEncodingFilter();
//        filter.setEncoding(StandardCharsets.UTF_8.displayName());
//        filter.setForceEncoding(true);
//        http.addFilterBefore(filter, CsrfFilter.class);
//        http.authorizeRequests()
//                .antMatchers(Constants.Url.RESOURCES + "/**").permitAll()
//                .antMatchers(Constants.Url.ROOT).permitAll()
//                .antMatchers(Constants.Url.LOGIN + "/**").permitAll()
//                .antMatchers(Constants.Url.ADMIN + "/**").hasAnyAuthority(RoleType.ADMIN.getName())
//                .antMatchers(Constants.Url.USER + "/**").hasAnyAuthority(RoleType.ADMIN.getName(), RoleType.USER.getName())
//                .and().formLogin().defaultSuccessUrl(Constants.Url.ROOT).loginPage(Constants.Url.LOGIN).failureUrl(Constants.Url.LOGIN + "?error=true")
//                .and().rememberMe().rememberMeServices(rememberMeServices()).key(rememberMeServices().getKey()).tokenValiditySeconds(TOKEN_VALIDITY_SECONDS)
//                .and().logout().permitAll().logoutSuccessUrl(Constants.Url.LOGIN + "?logout=true")
//                .and().exceptionHandling().accessDeniedPage(Constants.Url.ERROR_403)
//                .and().csrf()
//                .and()
//                .userDetailsService(userDetailsService());
//    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
        PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
                "remember-me", userDetailsService, tokenRepository);
        return tokenBasedservice;
    }
//
//    @Bean
//    public CustomPersistentTokenBasedRememberMeServices rememberMeServices() {
//        String key = UUID.randomUUID().toString();
//        return new CustomPersistentTokenBasedRememberMeServices(key, userDetailsService(), persistentLoginService);
//    }

    @Bean
    public AuthenticationTrustResolver getAuthenticationTrustResolver() {
        return new AuthenticationTrustResolverImpl();
    }

}