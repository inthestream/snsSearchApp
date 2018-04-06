package com.yun.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.restfb.Connection;
import com.restfb.FacebookClient;
import com.restfb.types.Place;
import com.yun.domain.SearchParams;

@Service
public interface SnsSearchService {

	public Connection<Place> search(FacebookClient client, SearchParams params) throws Exception;
	
	public Connection<Place> searchWithPageCursor(FacebookClient client, SearchParams params, Connection<Place> result) throws Exception;
	
	public List<Place> searchMore(FacebookClient fClient, List<Place> placeList, SearchParams params) throws Exception;
	
	public Boolean isSearchMorePage(Connection<Place> result) throws Exception;
	
}
