package com.yun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.restfb.Connection;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Place;
import com.yun.domain.SearchParams;
import com.yun.service.HelperService;
import com.yun.service.SnsSearchService;

@Component
public class FacebookSearchServiceImpl implements SnsSearchService {

	@Autowired
	HelperService helperService;
	
	@Override
	public Connection<Place> search(FacebookClient client, SearchParams params) throws Exception {
		Connection<Place> targetedSearch = client.fetchConnection("search", Place.class,
				Parameter.with("q", params.getSearchWord()), Parameter.with("type", "place"));
		return targetedSearch;
	}

	@Override
	public Connection<Place> searchWithPageCursor(FacebookClient client, SearchParams params, Connection<Place> morePlaces)
			throws Exception {
		Connection<Place> targetedSearch = client.fetchConnection("search", Place.class
				,Parameter.with("q", params.getSearchWord()), Parameter.with("type", "place")
				,Parameter.with("limit", 100), Parameter.with("after", params.getAfterCursor()));

		morePlaces = targetedSearch;
		params.setAfterCursor(morePlaces.getAfterCursor());
		
		return morePlaces;
	}
	
	@Override
	public List<Place> searchMore(FacebookClient fClient, List<Place> placeList, SearchParams params) throws Exception {
		
		Connection<Place> morePlaces = null;
		Boolean hasNextPage = true;
		
		while(hasNextPage) {
			morePlaces = searchWithPageCursor(fClient, params, morePlaces);
			placeList = helperService.storeResultToList(placeList, morePlaces, params);
			
			if(isSearchMorePage(morePlaces)) {
				params.setAfterCursor(morePlaces.getAfterCursor());
			} else {
				hasNextPage = false;
			}
		}
		
		return placeList;
	}

	@Override
	public Boolean isSearchMorePage(Connection<Place> result) throws Exception {
		return result.getAfterCursor() != null;
	}

}
