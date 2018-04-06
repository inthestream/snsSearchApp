package com.yun.service.impl;

import org.springframework.stereotype.Component;

import com.restfb.FacebookClient;
import com.yun.otherClass.FacebookClientFactory;
import com.yun.service.SnsAccessService;

@Component
public class FacebookAccessServiceImpl implements SnsAccessService {

	private String getAccessToken() throws Exception {
		String accessToken = "EAACEdEose0cBAEVi3PMWB6fTFOXvj0vDhYek3L75FSVsWqO6duZBfvAsitrmHxBrM61dRug34dgE6hEtzlAs8VqdVyiMdJuq3sIZA0RwhmScasujF7QMfGCaXcC2nU72T7eSlXlpUAEMnMNtSiP0AWD2dsMIdFvMPAdaro2dZCd0Gal50S6B4TuyeCtjeHseUMhL5jC0wZDZD";
		
		return accessToken;
	}

	@Override
	public FacebookClient getClientObject() throws Exception {
		FacebookClientFactory factory = new FacebookClientFactory(getAccessToken());
		return factory.getFacebookClient();
	}

	

}
