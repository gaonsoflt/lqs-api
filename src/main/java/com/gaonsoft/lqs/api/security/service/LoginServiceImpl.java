package com.gaonsoft.lqs.api.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gaonsoft.lqs.api.common.PwdEncryptor;
import com.gaonsoft.lqs.api.farm.model.Farm;
import com.gaonsoft.lqs.api.farm.repository.FarmRepository;
import com.gaonsoft.lqs.api.security.model.LoginUser;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private FarmRepository userRepository; 
	
	@Override
	public LoginUser loadUserByUsername(String username) throws UsernameNotFoundException {
		Farm farm = userRepository.findOne(Long.valueOf(username)); 
		LoginUser user = new LoginUser(farm.getFarmSeq(), farm.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
		user.setEnabled(farm.isRunnabled());
		return user;
	}

	@Override
	public PasswordEncoder getPasswordEncoder() {
//		return new BCryptPasswordEncoder(); // spring 기본 제공
		return new PwdEncryptor();
	}

}
