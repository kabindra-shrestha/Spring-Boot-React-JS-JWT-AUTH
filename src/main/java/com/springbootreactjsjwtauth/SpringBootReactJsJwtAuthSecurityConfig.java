package com.springbootreactjsjwtauth;

import com.springbootreactjsjwtauth.jwt.JWTAuthenticationFailureHandler;
import com.springbootreactjsjwtauth.jwt.JWTAuthenticationFilter;
import com.springbootreactjsjwtauth.jwt.JWTAuthenticationSuccessHandler;
import com.springbootreactjsjwtauth.jwt.JWTAuthorizationFilter;
import com.springbootreactjsjwtauth.utils.LinkConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SpringBootReactJsJwtAuthSecurityConfig {

    @Autowired
    private MyAppUserDetailsService myAppUserDetailsService;

    @Configuration
    public class ShopkeepingAppSecurityConfig extends WebSecurityConfigurerAdapter {

        @Bean
        public HttpSessionEventPublisher httpSessionEventPublisher() {
            return new HttpSessionEventPublisher();
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/webjars/**", "/resources/**", "/images/**", "/css/**", "/js/**", "/less/**")
                    .permitAll().antMatchers("/login", "/register", "/validateOtp", "/resendOtp").permitAll().antMatchers("/app.trust/**")
                    .hasAnyRole("ADMIN", "USER", "CLIENT").anyRequest().authenticated().and().formLogin()
                    .defaultSuccessUrl("/").loginPage("/login").loginProcessingUrl("/login").failureUrl("/login?error")
                    .usernameParameter("user_email").passwordParameter("user_password").and().logout()
                    .logoutUrl("/logout").logoutSuccessUrl("/login").deleteCookies("JSESSIONID").permitAll().and()
                    .exceptionHandling().accessDeniedPage("/error").and().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.ALWAYS).sessionFixation().migrateSession()
                    .invalidSessionUrl("/invalidsession").maximumSessions(1).expiredUrl("/sessionexpired");
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            auth.userDetailsService(myAppUserDetailsService).passwordEncoder(passwordEncoder);
        }

    }

    @Configuration
    @Order(1)
    public class ShopkeepingApiSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private JWTAuthenticationSuccessHandler successHandler;
        @Autowired
        private JWTAuthenticationFailureHandler failureHandler;

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.cors().and().csrf().disable().authorizeRequests()
                    .antMatchers(HttpMethod.POST, LinkConfig.BASE_URL + "/login", LinkConfig.BASE_URL + "/register", LinkConfig.BASE_URL + "/validateOtp", LinkConfig.BASE_URL + "/resendOtp")
                    .permitAll().antMatchers("/api/**")
                    /* .hasAnyRole("ADMIN", "USER", "CLIENT").anyRequest().authenticated() */.permitAll().and()
                    .addFilter(jwtAuthenticationFilter()).addFilter(new JWTAuthorizationFilter(authenticationManager()))
                    // this disables session creation on Spring Security
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            auth.userDetailsService(myAppUserDetailsService).passwordEncoder(passwordEncoder);
        }

        @Bean
        CorsConfigurationSource corsConfigurationSource() {
            final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/api/**", new CorsConfiguration().applyPermitDefaultValues());
            return source;
        }

        public JWTAuthenticationFilter jwtAuthenticationFilter() throws Exception {
            JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter(authenticationManager(),
                    successHandler, failureHandler);
            jwtAuthenticationFilter.setFilterProcessesUrl("/api/login");
            return jwtAuthenticationFilter;
        }

    }

}
