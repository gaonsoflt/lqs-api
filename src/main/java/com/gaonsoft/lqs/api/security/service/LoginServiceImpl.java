package com.gaonsoft.lqs.api.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gaonsoft.lqs.api.common.PwdEncryptor;
import com.gaonsoft.lqs.api.security.model.ApiUser;
import com.gaonsoft.lqs.api.security.model.ApiUserRole;
import com.gaonsoft.lqs.api.security.model.LoginUser;
import com.gaonsoft.lqs.api.security.repository.ApiUserRepository;
import com.gaonsoft.lqs.api.security.repository.ApiUserRoleRepository;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private ApiUserRepository userRepository; 
	
	@Autowired
	private ApiUserRoleRepository userRoleRepository; 
	
	@Override
	public LoginUser loadUserByUsername(String username) throws UsernameNotFoundException {
		ApiUser user = userRepository.findOne(Long.valueOf(username));
		List<ApiUserRole> userRoles = userRoleRepository.findByUserSeq(Long.valueOf(username));
		List<String> roles = new ArrayList<>();
		for (ApiUserRole role : userRoles) {
			roles.add(role.getRoleId());
		}
		return new LoginUser(user.getUserSeq(), user.getPassword(), AuthorityUtils.createAuthorityList(roles.stream().toArray(String[]::new)));
//		return new LoginUser(user.getUserSeq(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
	}

	@Override
	public PasswordEncoder getPasswordEncoder() {
//		return new BCryptPasswordEncoder(); // spring 기본 제공
		return new PwdEncryptor();
	}

}
