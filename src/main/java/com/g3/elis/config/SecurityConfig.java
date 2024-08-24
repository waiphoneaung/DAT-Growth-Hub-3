package com.g3.elis.config;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.g3.elis.model.User;
import com.g3.elis.security.LoginUserDetail;
import com.g3.elis.security.OurUserDetailService;
import com.g3.elis.service.UserLogService;
import com.g3.elis.service.UserService;
import com.g3.elis.util.CustomAccessDeniedHandler;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	@Autowired
    private OurUserDetailService ourUserDetailService;
    private  PersistentTokenRepository persistentTokenRepository;
    
    @Autowired
    private UserLogService userLogService;
    
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
    
    @Value("${remember.me.key}")
    private String rememberMeKey;

    @Bean
    static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/public/**", "/public/assets/**", "/public/assets/css/**",
                                "/public/assets/images/**","/public/assets/js/**",

                           "/public/assets/vendor/**","/fragments/**", "/private/profiles/**","/private/blog/**",
                                "/public/assets/vendor/**","/private/profile/blog-profile/**",
                                "/private/profile/course-profile/**","/private/profile/user-profile/**",
                                "/private/blog/blog-images/**","/private/blog/blog-files/**","/authenticated-user/**","/api/public/**").permitAll()

                        .requestMatchers("/user/**","/auth/**").permitAll()
                        .requestMatchers("/student/**").hasAuthority("ROLE_STUDENT")
                        .requestMatchers("/instructor/**").hasAuthority("ROLE_INSTRUCTOR")
                        .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/authenticated-user/**", "/private/blog/**", "/private/course/**",
                        				 "/private/course/course-image-file/**")
                        .permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(exceptionHandling -> 
                    exceptionHandling.accessDeniedHandler(accessDeniedHandler())
                                     .authenticationEntryPoint(authenticationEntryPoint()))
                .formLogin(form -> 
                    form
                        .loginPage("/auth/login").loginProcessingUrl("/signIn")
                        .successHandler(customerAuthenticationSuccessHandler())
                        .failureHandler(authenticationFailureHandler())
                        .permitAll())
                .rememberMe(rememberMe -> 
                    rememberMe
                        .key(rememberMeKey)
                        .rememberMeParameter("remember-me")
                        .tokenRepository(persistentTokenRepository)
                        .tokenValiditySeconds(7 * 24 * 60 * 60) 
                        .userDetailsService(ourUserDetailService))
                .sessionManagement(session -> 
                    session
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(false)
                        .expiredUrl("/auth/login?expired"))
                .logout(logout -> 
                    logout
                        .logoutUrl("/sign-out").logoutSuccessUrl("/auth/login")
                        .logoutSuccessHandler((request, response, authentication) -> {
                        	if(authentication != null) {
                        		//String username = authentication.getName();
                        		 User user = ((LoginUserDetail) authentication.getPrincipal()).getUser();
                                 int userId = user.getId();
                        		userLogService.logLogout(userId);
                        	}
                            response.sendRedirect("/auth/login");
                        }).invalidateHttpSession(true).deleteCookies("JSESSIONID", "remember-me")
                        .permitAll());
        
        return httpSecurity.build();
    }

    @Bean
    OurUserDetailService userDetailService() {
        return new OurUserDetailService();
    }

    @Bean
    AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            response.sendRedirect("/user/home");
        };
    }
    
    @Bean
    AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(ourUserDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, exception) -> {
            String errorMessageString = "Incorrect username or password.";
            request.getSession().setAttribute("errorMessage", errorMessageString);
            response.sendRedirect("/auth/login?error");
        };
    }

    @Bean
    AuthenticationSuccessHandler customerAuthenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                   Authentication authentication) throws IOException, ServletException {
            	//for log activity
                User user = ((LoginUserDetail) authentication.getPrincipal()).getUser();
                int userId = user.getId();
                userLogService.logLogin(userId); // Log login time
            	
            	
                Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
                if (authorities.contains("ROLE_ADMIN")) {
                    logger.info("Redirecting to admin dashboard for user: {}", authentication.getName());
                    response.sendRedirect("/admin/admin-dashboard");
                } else if (authorities.contains("ROLE_STUDENT")) {
                    logger.info("Redirecting to student dashboard for user: {}", authentication.getName());
                    response.sendRedirect("/student/student-dashboard");
                } else if (authorities.contains("ROLE_INSTRUCTOR")) {
                    logger.info("Redirecting to instructor dashboard for user: {}", authentication.getName());
                    response.sendRedirect("/instructor/instructor-dashboard");
                } else {
                    logger.info("Redirecting to home page for user: {}", authentication.getName());
                    response.sendRedirect("/user/home");
                }
            }

	        };
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        return new InMemoryTokenRepositoryImpl();
    }

    @PostConstruct
    public void init() {
          if (rememberMeKey == null || rememberMeKey.isEmpty()) {
            rememberMeKey = generateRememberMeKey();
        }
    }

    private String generateRememberMeKey() {
        try {
            SecureRandom secureRandom = SecureRandom.getInstanceStrong();
            byte[] keyBytes = new byte[32];  
            secureRandom.nextBytes(keyBytes);
            return Base64.getEncoder().encodeToString(keyBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to generate Remember Me key", e);
        }
    }
}
