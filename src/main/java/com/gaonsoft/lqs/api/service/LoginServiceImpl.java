package com.gaonsoft.lqs.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gaonsoft.lqs.api.common.PwdEncryptor;
import com.gaonsoft.lqs.api.model.user.ApiUser;
import com.gaonsoft.lqs.api.model.user.ApiUserRole;
import com.gaonsoft.lqs.api.model.user.LoginUser;
import com.gaonsoft.lqs.api.repository.ApiUserRepository;
import com.gaonsoft.lqs.api.repository.ApiUserRoleRepository;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private ApiUserRepository userRepository; 
	
	@Autowired
	private ApiUserRoleRepository userRoleRepository; 
	
	@Override
	public LoginUser loadUserByUsername(String username) throws UsernameNotFoundException {
		ApiUser user = userRepository.findOne(username);
		List<ApiUserRole> userRoles = userRoleRepository.findByUserId(username);
		List<String> roles = new ArrayList<>();
		for (ApiUserRole role : userRoles) {
			roles.add(role.getRoleId());
		}
		return new LoginUser(user.getUserId(), user.getPassword(), AuthorityUtils.createAuthorityList(roles.stream().toArray(String[]::new)));
//		return new LoginUser(user.getUserSeq(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
	}

	@Override
	public PasswordEncoder getPasswordEncoder() {
//		return new BCryptPasswordEncoder(); // spring 기본 제공
		return new PwdEncryptor();
	}

}
