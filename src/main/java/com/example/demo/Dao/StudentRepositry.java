package com.example.demo.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Student;

public interface StudentRepositry extends CrudRepository<Student,Integer> {
	
	@Query(value="select * from employee.student ", nativeQuery=true)
	public List<Student> getStudetList();
	


}
