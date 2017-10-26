package com.gaonsoft.lqs.api.model.user;

import java.io.Serializable;

import lombok.Data;

@Data
public class AccessToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	private String Bearer;

	private String OAuth2;

	/**
	 * The access token issued by the authorization server. This value is REQUIRED.
	 */
	private String access_token;

	/**
	 * The type of the token issued as described in <a
	 * href="http://tools.ietf.org/html/draft-ietf-oauth-v2-22#section-7.1">Section 7.1</a>. Value is case insensitive.
	 * This value is REQUIRED.
	 */
	private String token_type;

	/**
	 * The lifetime in seconds of the access token. For example, the value "3600" denotes that the access token will
	 * expire in one hour from the time the response was generated. This value is OPTIONAL.
	 */
	private String expires_in;

	/**
	 * The refresh token which can be used to obtain new access tokens using the same authorization grant as described
	 * in <a href="http://tools.ietf.org/html/draft-ietf-oauth-v2-22#section-6">Section 6</a>. This value is OPTIONAL.
	 */
	private String refresh_token;

	/**
	 * The scope of the access token as described by <a
	 * href="http://tools.ietf.org/html/draft-ietf-oauth-v2-22#section-3.3">Section 3.3</a>
	 */
	private String scope;
	
	private String jti;
}
