package com.yun.MockClass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MockLocation {

	String country;
	
	String city;
	
	public MockLocation(String country, String city) {
		this.country = country;
		this.city = city;
	}
}
