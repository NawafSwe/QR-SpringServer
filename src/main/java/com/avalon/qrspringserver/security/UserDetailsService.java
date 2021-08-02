package com.avalon.qrspringserver.security;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {
    /**
     * This example is a service with a secure method. If Spring creates a @Bean of this type,
     * it is proxied and callers have to go through a security interceptor before the method is actually executed.
     * If access is denied, the caller gets an AccessDeniedException instead of the actual method result.
     */
    @Secured("Admin")
    public boolean secure() {
        return true;
    }


}
