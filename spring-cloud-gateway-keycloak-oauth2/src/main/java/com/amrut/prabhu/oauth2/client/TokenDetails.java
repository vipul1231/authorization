package com.amrut.prabhu.oauth2.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

/**
 * @author Pankaj Chauhan
 *
 */

@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class TokenDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	private String accessToken;

	private String refreshToken;

	private String scope;

	private String tokenType;

	private Long expiresIn;
	
	private long refreshExpiresIn;

	private String sessionState;

}
