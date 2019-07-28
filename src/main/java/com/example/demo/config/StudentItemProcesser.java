package com.example.demo.config;


import org.springframework.batch.item.ItemProcessor;

import com.example.demo.model.Student;

public class StudentItemProcesser implements ItemProcessor<Student,Student> {


	@Override
	public Student process(Student item) throws Exception {
		
		return item;
	}

}
