package com.vvopaa.universalsite.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vvopaa.universalsite.model.UserEntity;
import com.vvopaa.universalsite.model.UserRole;
import com.vvopaa.universalsite.service.UserService;

/**
 * Service for spring security concerning user roles
 * @author loptop
 *
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	
	@Qualifier("userServiceImpl")
	@Autowired
	private UserService userService;
	 
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity user = userService.getUserByEmail(email);
		
        if(user==null) {
            throw new UsernameNotFoundException("Username not found");
        }
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), 
                 true, true, true, true, getGrantedAuthorities(user));
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(UserEntity user){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		 
		for(UserRole userProfile : user.getUserRoles()){
		    authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getRole()));
		}
		
		return authorities;
	}
	 
}
