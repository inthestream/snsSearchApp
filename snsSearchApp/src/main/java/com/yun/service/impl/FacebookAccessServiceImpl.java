package com.yun.service.impl;

import javax.servlet.http.HttpSession;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.restfb.FacebookClient;
import com.yun.otherClass.FacebookClientFactory;
import com.yun.service.SnsAccessService;

@Component
public class FacebookAccessServiceImpl implements SnsAccessService {

	@Autowired
	Environment env;
	
	public String returnLoginUrl(String appId) throws Exception {
		String loginDialogUrlString = "https://www.facebook.com/v2.12/dialog/oauth?" +
				"client_id=" + appId +
				"&redirect_uri=https://localhost:8443/getAccessToken" +
				"&scope=email";
		
		return loginDialogUrlString;
	}
	
	public String requesFaceBooktAccesToken(HttpSession session, String code, String appId, String appSecret) throws Exception {
		String facebookUrl = "https://graph.facebook.com/v2.12/oauth/access_token?"+
						 	"client_id=" + appId +
						 	"&redirect_uri=https://localhost:8443/getAccessToken"+
						 	"&client_secret=" + appSecret +
						 	"&code=" + code;
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet getRequest = new HttpGet(facebookUrl);
		String rawJsonString = client.execute(getRequest, new BasicResponseHandler());
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(rawJsonString);
		
		String facebookAccessToken = (String) jsonObject.get("access_token");
		Long facebookExpiresIn = (Long) jsonObject.get("expires_in");
		
		session.setAttribute("facebookAccessToken", facebookAccessToken);
		session.setAttribute("facebookExpiresIn", facebookExpiresIn);
		
		return facebookAccessToken;
	}

	@Override
	public FacebookClient getClientObject(String accessToken) throws Exception {
		FacebookClientFactory factory = new FacebookClientFactory(accessToken);
		return factory.getFacebookClient();
	}
	
}
