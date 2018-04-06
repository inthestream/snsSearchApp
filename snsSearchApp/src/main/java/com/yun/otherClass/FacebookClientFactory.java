package com.yun.otherClass;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;

public class FacebookClientFactory {

private final FacebookClient facebookClient;
	
	public FacebookClientFactory(String accessToken) throws Exception {
		
		facebookClient = new DefaultFacebookClient(accessToken, Version.VERSION_2_5);
	}
	
	public FacebookClient getFacebookClient() {
		return facebookClient;
	}
}
