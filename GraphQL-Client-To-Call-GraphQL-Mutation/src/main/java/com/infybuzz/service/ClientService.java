package com.infybuzz.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infybuzz.request.CreateStudentRequest;
import com.infybuzz.response.StudentResponse;

import graphql.kickstart.spring.webclient.boot.GraphQLRequest;
import graphql.kickstart.spring.webclient.boot.GraphQLResponse;
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;

@Service
public class ClientService {
	
	@Autowired
	GraphQLWebClient graphQLWebClient;

	public StudentResponse getStudent (Integer id) {
		Map<String, Object> variables = new HashMap<>();
		variables.put("id", id);
		String queryStr = "query student($id : Int){\n" +
				"  student(id : $id) {\n" +
				"     id\n" +
				"    firstName\n" +
				"    lastName\n" +
				"    city\n" +
				"    learningSubjects (subjectNameFilter : Java) {\n" +
				"      subjectName\n" +
				"      marksObtained\n" +
				"      \n" +
				"    }\n" +
				"  }\n" +
				"}";
		
//		String queryStr = "query student($id : Int) {\r\n"
//				+ "  student(id : $id) {\r\n"
//				+ "    id\r\n"
//				+ "    firstName\r\n"
//				+ "    lastName\r\n"
//				+ "    email\r\n"
//				+ "    street\r\n"
//				+ "    city\r\n"
//				+ "    learningSubjects (subjectNameFilter : Java) {\r\n"
//				+ "      id\r\n"
//				+ "      subjectName\r\n"
//				+ "      marksObtained\r\n"
//				+ "    }\r\n"
//				+ "    fullName\r\n"
//				+ "  } \r\n"
//				+ "}";
		
		GraphQLRequest request = GraphQLRequest.builder()
				.query(queryStr).variables(variables).build();
		
		GraphQLResponse graphQLResponse = graphQLWebClient.post(request).block();
		
		return graphQLResponse.get("student", StudentResponse.class);
	}
	
	public StudentResponse createStudent(
			CreateStudentRequest createStudentRequest) {
		
		Map<String, Object> variables = new HashMap<>();
		variables.put("createStudentRequest", createStudentRequest);
		
		String mutationStr = "mutation createStudent ($createStudentRequest : CreateStudentRequest) {\r\n"
				+ "  createStudent (createStudentRequest : $createStudentRequest) {\r\n"
				+ "    id\r\n"
				+ "    firstName\r\n"
				+ "    lastName\r\n"
				+ "    email\r\n"
				+ "    street\r\n"
				+ "    city\r\n"
				+ "    learningSubjects(subjectNameFilter : Java) {\r\n"
				+ "      id\r\n"
				+ "      subjectName\r\n"
				+ "      marksObtained\r\n"
				+ "    }\r\n"
				+ "    fullName\r\n"
				+ "  }\r\n"
				+ "}";
		
		GraphQLRequest request = GraphQLRequest.builder()
				.query(mutationStr).variables(variables).build();
		
		GraphQLResponse graphQLResponse = graphQLWebClient.post(request).block();
		
		return graphQLResponse.get("createStudent", StudentResponse.class);
	}
}
