package com.example.demo.config;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.model.Student;

public class PersonRowMapper implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Student s=new Student();
		s.setId(rs.getInt("id"));
		s.setBranch(rs.getString("branch"));
		s.setName(rs.getString("name"));
		System.out.println("Name is"+s.getName());
		return s;
	}

}
