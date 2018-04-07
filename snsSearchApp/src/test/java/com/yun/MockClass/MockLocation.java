package com.yun.MockClass;

import com.restfb.types.Location;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MockLocation extends Location{

	String country;
	
	String city;
	
	public MockLocation(String country, String city) {
		this.country = country;
		this.city = city;
	}
}
