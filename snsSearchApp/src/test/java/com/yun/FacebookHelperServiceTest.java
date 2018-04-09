package com.yun;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.restfb.Connection;
import com.restfb.types.Location;
import com.restfb.types.Place;
import com.yun.domain.SearchParams;
import com.yun.service.HelperService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FacebookHelperServiceTest {

	@Autowired
	HelperService helperService;
	
	@Test
	public void storeResultToListTest() throws Exception {
		List<Place> list = new ArrayList<>();
		
		IntStream.range(0, 2).forEach(i -> {
			Place mockPlace = mock(Place.class);
			when(mockPlace.getName()).thenReturn("egnyte" + i);
			
			Location mockLocation = mock(Location.class);
			when(mockLocation.getCity()).thenReturn("poznan");
			when(mockLocation.getCountry()).thenReturn("poland");
			
			when(mockPlace.getLocation()).thenReturn(mockLocation);
			
			list.add(mockPlace);
		});
		
		Connection<Place> result = mock(Connection.class);
		when(result.getData()).thenReturn(list);
		
		SearchParams params = new SearchParams();
		params.setCity("poznan");
		params.setCountry("poland");
		
		assertTrue(helperService.storeResultToList(new ArrayList<Place>(), result, params).size() == 2);
		assertTrue(helperService.storeResultToList(new ArrayList<Place>(), result, params).get(0).getName().equals("egnyte0"));
	}
	
	@Test
	public void convertListToJSONArrayTest() throws Exception {
		List<Place> list = new ArrayList<>();
		
		IntStream.range(0, 2).forEach(i -> {
			Place mockPlace = mock(Place.class);
			when(mockPlace.getName()).thenReturn("egnyte" + i);
			
			Location mockLocation = mock(Location.class);
			when(mockLocation.getCity()).thenReturn("poznan");
			when(mockLocation.getCountry()).thenReturn("poland");
			
			when(mockPlace.getLocation()).thenReturn(mockLocation);
			
			list.add(mockPlace);
		});
		
		assertTrue(helperService.convertListToJSONArray(list, new JSONArray()).length() == 2);
	}
	
}
