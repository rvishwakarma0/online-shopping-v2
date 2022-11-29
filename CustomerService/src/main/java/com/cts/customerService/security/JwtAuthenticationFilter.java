package com.cts.customerService.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;
@Configuration
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    
	@Autowired
    private JwtUtil tokenProvider;
    
    @Autowired
    private UserAuthDetailsService userDetailsService;
    
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("inside doFilter");
    	String jwt = getJwtFromRequest(request);
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            String userNameFromToken = tokenProvider.extractUsername(jwt);
            UserDetails userDetails = userDetailsService.loadUserByUsername(userNameFromToken);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
	
}