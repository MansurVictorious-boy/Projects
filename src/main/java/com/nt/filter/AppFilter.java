package com.nt.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nt.serviceimpl.UserRegisterServiceimpl;
import com.nt.utility.JwtUtilService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AppFilter extends OncePerRequestFilter{

	@Autowired
    private JwtUtilService jwtUtilService;
	
	
	@Autowired
    private UserRegisterServiceimpl userRegisterServiceImpl;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header=request.getHeader("Authorization");
		String token=null;
		String username=null;
		
	//  Check if header contains "Bearer" token
		if(header!=null && header.startsWith("Bearer ")) {
			token= header.substring(7);
			try {
				username=jwtUtilService.extractUsername(token);
			} catch (Exception e) {
				System.out.println("Invalid JWT TOken: "+e.getMessage());
				
			}
		}
	//  If username exists & not already authenticated
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			
			 // Load user details from DB
			UserDetails userDetails=userRegisterServiceImpl.loadUserByUsername(username);
		
		//Validate JWT Token
			if (jwtUtilService.validateToken(token, userDetails)) {
				 //  Create Spring Security authentication object
				UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());
                //  Attach additional request details
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));

                //  Set the authentication in context
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			
			}
		}
		 //  Continue the filter chain
        filterChain.doFilter(request, response);
	}

}
