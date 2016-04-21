package com.plantilla.springmvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import  com.plantilla.springmvc.model.User;
import  com.plantilla.springmvc.model.Role;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

//	@Autowired
//	private UserService userService;
	private UserServiceImpl userService=new UserServiceImpl();

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {		
		User user = userService.findByEmail(email);
		System.out.println("User : " + user);
		if (user == null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("Username not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
//				user.getState().equals("Active"), true, true, true, getGrantedAuthorities(user));
				true, true, true, true, getGrantedAuthorities(user));
	}

	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (Role userProfile : user.getRoles()) {
			System.out.println("UserProfile : " + userProfile);
			authorities.add(new SimpleGrantedAuthority("ROLE_" + userProfile.getName()));
		}
		System.out.print("authorities :" + authorities);
		return authorities;
	}

}
