package com.yun;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.restfb.Connection;
import com.yun.MockClass.MockPlace;
import com.yun.controller.SnsDataSearchController;
import com.yun.domain.SearchParams;
import com.yun.service.HelperService;

@RunWith(SpringRunner.class)
@WebMvcTest(SnsDataSearchController.class)
public class FacebookHelperServiceTest {

	@Autowired
	HelperService helperService;
	
	/*@Test
	public void storeResultToListTest() throws Exception {
		
		List<MockPlace> list = makeMockPlaceList();
		
		Connection<MockPlace> r = null;
		
		SearchParams params = new SearchParams();
		
		helperService.storeResultToList(list, result, params);
		
	}*/
	
	
	
	
	
	
	
	
	
	
	
	/*private List<MockPlace> makeMockPlaceList() throws Exception {
		
		List<MockPlace> list = new ArrayList<>();
		
		for(int i = 0; i < 5; i++) {
			MockPlace mockPlace = new MockPlace("name" + i, "country" + i, "city" + i);
			list.add(mockPlace);
		}
		
		return list;
		
	}*/
}
