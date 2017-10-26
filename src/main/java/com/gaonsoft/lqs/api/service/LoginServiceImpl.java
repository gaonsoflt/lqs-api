package com.gaonsoft.lqs.api.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gaonsoft.lqs.api.common.PwdEncryptor;
import com.gaonsoft.lqs.api.model.farm.AppFcm;
import com.gaonsoft.lqs.api.model.request.LoginVo;
import com.gaonsoft.lqs.api.model.user.AccessToken;
import com.gaonsoft.lqs.api.model.user.ApiUser;
import com.gaonsoft.lqs.api.model.user.ApiUserRole;
import com.gaonsoft.lqs.api.model.user.LoginUser;
import com.gaonsoft.lqs.api.repository.ApiUserRepository;
import com.gaonsoft.lqs.api.repository.ApiUserRoleRepository;
import com.gaonsoft.lqs.api.repository.AppFcmRepository;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private ApiUserRepository userRepository; 
	
	@Autowired
	private ApiUserRoleRepository userRoleRepository; 
	
	@Autowired
	private AppFcmRepository appFcmRepository; 

	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//
	private OkHttpClient client = new OkHttpClient();
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Value("${security.oauth2.client.id}")
	private String CLIENT_ID;
	
	@Value("${security.oauth2.resource.jwt.key-value}")
	private String CLIENT_SECRET;
	
	@Value("${server.port}")
	private int SERVER_PORT;
	
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

	@Override
	public ResponseEntity<AccessToken> doLogin(LoginVo vo) throws Exception {
		String clientCredentials = CLIENT_ID + ":" + CLIENT_SECRET;
		String base64ClientCredentials = new String(Base64.encodeBase64(clientCredentials.getBytes()));
		StringBuilder url = new StringBuilder();
		url.append("http://localhost:").append(SERVER_PORT)
			.append("/oauth/token")
			.append("?grant_type=").append("password")
			.append("&username=").append(vo.getId())
			.append("&password=").append(vo.getPassword());
		
		Request request = new Request.Builder()
		        .url(url.toString())
		        .addHeader("Authorization", "Basic " + base64ClientCredentials)
		        .post(RequestBody.create(JSON, ""))
		        .build();
		try {
			Response response = client.newCall(request).execute();
			HttpHeaders headers = new HttpHeaders();
			headers.set("Cache-Control", "no-store");
			headers.set("Pragma", "no-cache");
			headers.set("content-type", "application/json; charset=utf-8");
			if(response.isSuccessful()) {
				// save token for fcm
				appFcmRepository.save(new AppFcm(vo.getMobile_token(), vo.getId()));
				return new ResponseEntity<>(mapper.readValue(response.body().string(), AccessToken.class), headers, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new AccessToken(), headers, HttpStatus.UNAUTHORIZED);
			}
		} catch(Exception e) {
			throw e;
		}
	}
}
