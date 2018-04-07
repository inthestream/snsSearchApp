package com.yun.MockClass;

import com.restfb.types.Place;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MockPlace extends Place {
	
	MockLocation location;
	
	String name;
	
	public MockPlace(String name, String country, String city) {
		this.name = name;
		
		this.location = new MockLocation(country, city);
	}

	
}
