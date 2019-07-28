package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.StudentRepositry;
import com.example.demo.model.Student;

@Service
public class StudentService {
	@Autowired
	StudentRepositry studentRepositry;
	@Cacheable(value="getStudent")
	public List<Student> getStudent() {
		List<Student> responde=studentRepositry.getStudetList();
        return responde;
	}
	@CacheEvict(value="getStudent")
	public void clearCache() {
		System.out.println("cache cleared");
		
	}

}
