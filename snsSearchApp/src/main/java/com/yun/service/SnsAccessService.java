package com.yun.service;

import org.springframework.stereotype.Service;

import com.restfb.FacebookClient;

@Service
public interface SnsAccessService {

	public FacebookClient getClientObject() throws Exception;
	
}
