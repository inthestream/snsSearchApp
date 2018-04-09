package com.yun.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.restfb.FacebookClient;

@Service
public interface SnsAccessService {

	public String returnLoginUrl(String returnLoginUrl) throws Exception;
	
	public FacebookClient getClientObject(String accessToken) throws Exception;

	public String requesFaceBooktAccesToken(HttpSession session, String code, String appId, String appSecret) throws Exception;
}
