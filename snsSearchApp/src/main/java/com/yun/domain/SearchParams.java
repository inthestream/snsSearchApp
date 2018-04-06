package com.yun.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchParams {

	@NonNull
	private String country;
	
	//@NotNull
	private String city;
	
	//@NotNull
	private String searchWord;
	
	private String afterCursor;
}
