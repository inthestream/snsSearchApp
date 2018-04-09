package com.yun.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.restfb.Connection;
import com.restfb.FacebookClient;
import com.restfb.types.Place;
import com.yun.domain.SearchParams;
import com.yun.service.HelperService;
import com.yun.service.SnsAccessService;
import com.yun.service.SnsSearchService;

@RestController
public class SnsDataSearchController {

	
	@Autowired
	SnsAccessService accessService;
	
	@Autowired
	SnsSearchService searchService;
	
	@Autowired
	HelperService helperService;
	
	@Autowired
	Environment enviroment;
	
	final Logger logger = LoggerFactory.getLogger(SnsDataSearchController.class);
	
	
	@GetMapping("/login")
	public void loginRequest(HttpServletResponse response) throws Exception {
		String appId = enviroment.getProperty("facebook.appId");
		response.sendRedirect(accessService.returnLoginUrl(appId));
	}
	
	@GetMapping("/getAccessToken")
	public void accessTokenRequest(HttpServletResponse response, String code, HttpSession session, String state) throws Exception {
		logger.debug("facebookAccessToken / code : " + code);
		
		String appId = enviroment.getProperty("facebook.appId");
		String appSecret = enviroment.getProperty("facebook.appSecret");
		
		accessService.requesFaceBooktAccesToken(session, code, appId, appSecret);
		response.sendRedirect("/poland/poznan/egnyte");
	}
	
	@GetMapping(value = "/{country}/{city}/{searchWord}")
	public ModelAndView searchRequest(HttpSession session, HttpServletResponse response, SearchParams params) throws Exception {

		if(session.getAttribute("facebookAccessToken") == null) {
			response.sendRedirect("/login");
			return null;
		}
		
		FacebookClient fClient = accessService.getClientObject(session.getAttribute("facebookAccessToken").toString());
		
		Connection<Place> searchPlaces = searchService.search(fClient, params);
		
		List<Place> placeList = new ArrayList<>();
		placeList = helperService.storeResultToList(placeList, searchPlaces, params);
		
		if(searchService.isSearchMorePage(searchPlaces)) {
			params.setAfterCursor(searchPlaces.getAfterCursor());
			placeList = searchService.searchMore(fClient, placeList, params);
		}

		JSONArray jarr = new JSONArray();
		jarr = helperService.convertListToJSONArray(placeList, jarr);
		
		if(jarr.length() == 0) throw new Exception();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("infoView");
		
		helperService.setResultToModel(jarr, modelAndView);
		
		return modelAndView;
	}
	
			
	@RequestMapping
	public String exceptionRequest() {
		return "Bad Request<br/>" + "Valid Request Form : GET /{country}/{city}/{searchWord}";
	}
	
	
}
