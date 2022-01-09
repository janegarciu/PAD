package com.flightapp.security;


import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
//@EnableWebSecurity
public class SecurityConfig {

//    private final SecurityFilter securityFilter;
//
//    public SecurityConfig(final SecurityFilter securityFilter) {
//        this.securityFilter = securityFilter;
//    }
//
//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.httpBasic().disable();
//        http.formLogin().disable();
////        http.
//        http.authorizeRequests().antMatchers("/flight-booking-app/authorisation/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
//    }

    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager("flightCache");

        return cacheManager;
    }

    @CacheEvict(allEntries = true, value = {"flightCache"})
    @Scheduled(fixedDelay =  10000 ,  initialDelay = 10000)
    public void reportCacheEvict() {

    }
}
