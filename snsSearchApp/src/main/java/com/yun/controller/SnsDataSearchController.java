package com.yun.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello World";
	}
	
	@GetMapping(value = "/{country}/{city}/{searchWord}")
	public ModelAndView searchRequest(SearchParams params) throws Exception {
		
		FacebookClient fClient = accessService.getClientObject();
		
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


/*while(hasNextPage) {
morePlaces = searchService.searchWithPageCursor(fClient, params, morePlaces);
placeList = helperService.storeResultToList(placeList, morePlaces, params);

if(searchService.isSearchMorePage(morePlaces)) {
	params.setAfterCursor(morePlaces.getAfterCursor());
} else {
	hasNextPage = false;
}
}*/

/*while(searchService.isSearchMore(searchService.searchMore(fClient, params, morePlaces))) {
placeList = helperService.storeResultToList(placeList, morePlaces, params);
}*/
