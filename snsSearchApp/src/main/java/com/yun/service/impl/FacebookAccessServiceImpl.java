package com.yun.service.impl;

import org.springframework.stereotype.Component;

import com.restfb.FacebookClient;
import com.yun.otherClass.FacebookClientFactory;
import com.yun.service.SnsAccessService;

@Component
public class FacebookAccessServiceImpl implements SnsAccessService {

	private String getAccessToken() throws Exception {
		String accessToken = "EAACEdEose0cBALfcUh7lis5KsmRwD7s7RCYhYg3hHV6iNTm957QhDH6fLZA3MNo9TFmv2nVC9oEv71OZBtOZCV8nm8CetPIXHw93T1NngLTGZBOGTZACJY5NyxfeWrmPxYQpYpuOcrajKKSnl8hziBpjAoqHxBN96mfM9jpQBZC6swP3Gjgm6AfnBJid1trXUVCGkxKJkvTAZDZD";
		
		return accessToken;
	}

	@Override
	public FacebookClient getClientObject() throws Exception {
		FacebookClientFactory factory = new FacebookClientFactory(getAccessToken());
		return factory.getFacebookClient();
	}

	

}
