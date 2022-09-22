package com.example.mutation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.request.CreateStudentRequest;
import com.example.response.StudentResponse;
import com.example.service.StudentService;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Service
public class Mutation implements GraphQLMutationResolver {

	@Autowired
	StudentService studentService;
	
	public StudentResponse createStudent (CreateStudentRequest createStudentRequest) {
		return new StudentResponse(studentService.createStudent(createStudentRequest));
	}
}
