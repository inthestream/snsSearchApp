package com.yun;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yun.MockClass.MockPlace;
import com.yun.service.HelperService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FacebookHelperServiceTest {

	@Autowired
	HelperService helerService;
	
	
	public void storeResultToListTest() throws Exception {
		
		List<MockPlace> list = makeMockPlaceList();
		
	}
	
	
	
	
	
	
	
	
	private List<MockPlace> makeMockPlaceList() throws Exception {
		
		List<MockPlace> list = new ArrayList<>();
		
		for(int i = 0; i < 5; i++) {
			MockPlace mockPlace = new MockPlace("name" + i, "country" + i, "city" + i);
			list.add(mockPlace);
		}
		
		return list;
		
	}
}
