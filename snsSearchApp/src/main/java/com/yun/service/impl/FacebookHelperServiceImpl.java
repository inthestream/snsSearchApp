package com.yun.service.impl;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restfb.Connection;
import com.restfb.types.Place;
import com.yun.domain.SearchParams;
import com.yun.service.HelperService;

@Component
public class FacebookHelperServiceImpl implements HelperService {

	@Override
	public List<Place> storeResultToList(List<Place> resultList, Connection<Place> result, SearchParams params) throws Exception {
		/*resultList = result.getData();
		
		resultList.stream().filter(
				p -> !(p.getLocation().getCountry() == null || p.getLocation().getCity() == null) 
	    		&& p.getLocation().getCountry().toLowerCase().equals(params.getCountry().toLowerCase())
	    		&& p.getLocation().getCity().toLowerCase().equals(params.getCity().toLowerCase())
	    		);*/
			
		Iterator<Place> it = result.getData().iterator();
	    
	    while(it.hasNext()) {
	    	Place p = (Place) it.next();
	    	
	    	if(!(p.getLocation().getCountry() == null || p.getLocation().getCity() == null) 
	    			&& p.getLocation().getCountry().toLowerCase().equals(params.getCountry().toLowerCase())
		    		&& p.getLocation().getCity().toLowerCase().equals(params.getCity().toLowerCase())) {
	    		resultList.add(p);
	    	}
	    }
	    
	    return resultList;
		
	}
	
	@Override
	public JSONArray convertListToJSONArray(List<Place> list, JSONArray jarr) throws Exception {
		/* Map<String, Object> resultMap = new LinkedHashMap<>();
		
		list.stream().forEach(i ->
					{
						resultMap.put("name", i.getName());
						resultMap.put("latitude", i.getLocation().getLatitude());
						resultMap.put("longitude", i.getLocation().getLongitude());
						
						JSONObject job = new JSONObject(resultMap);
						
						jarr.put(job);
					}
				);*/
		
		
		for(Place pl : list) {
			LinkedHashMap<String, Object> tmap = new LinkedHashMap<>();
			
			tmap.put("name", pl.getName());
	    	tmap.put("latitude", pl.getLocation().getLatitude());
	    	tmap.put("longitude", pl.getLocation().getLongitude());
	    	
	    	JSONObject job = new JSONObject(tmap);
			
			jarr.put(job);
		}
		
		return jarr;
		
	}
	

	@Override
	public void setResultToModel(JSONArray jarr, ModelAndView modelAndView) throws Exception {
		
		String jsonString = null;
		
		//first list item
		jsonString = jarr.get(0).toString();
		
		//transform jsonObject into requested form to print on html
		ObjectMapper mapper = new ObjectMapper();
		Object firstJsonObject = mapper.readValue(jsonString, Object.class);
		
		modelAndView.addObject("output", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(firstJsonObject));
		
		if(jarr.length() > 1) {
			//other list item
			jarr.remove(0);
			jsonString = jarr.toString();
			
			//transform jsonObject into requested form to print on html
			Object otherJsonObject = mapper.readValue(jsonString, Object.class);
			
			modelAndView.addObject("otheroutput", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(otherJsonObject));
		}
	}

	
}
