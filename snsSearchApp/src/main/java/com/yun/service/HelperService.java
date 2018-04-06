package com.yun.service;

import java.util.List;

import org.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.restfb.Connection;
import com.restfb.types.Place;
import com.yun.domain.SearchParams;

@Service
public interface HelperService {

	public List<Place> storeResultToList(List<Place> list, Connection<Place> result, SearchParams params) throws Exception;
	
	public JSONArray convertListToJSONArray(List<Place> list, JSONArray jarr) throws Exception;
	
	public void setResultToModel(JSONArray jarr, ModelAndView modelAndView) throws Exception;

}
