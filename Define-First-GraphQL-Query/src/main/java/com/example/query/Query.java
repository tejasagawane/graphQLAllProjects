package com.example.query;

import com.example.query.request.SampleRequest;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component
public class Query implements GraphQLQueryResolver {

	public String firstQuery () {
		return "First Query";
	}
	public String secondQuery () { return "Second Query"; }
	public String fullName(SampleRequest sampleRequest) {
		return sampleRequest.getFirstName() + " " + sampleRequest.getLastName();
	}
}
