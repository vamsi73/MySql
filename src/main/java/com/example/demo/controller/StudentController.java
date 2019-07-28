package com.example.demo.controller;

import java.util.List;

import javax.persistence.Cacheable;

import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dao.StudentRepositry;
import com.example.demo.core.InjectableLogger;
import com.example.demo.model.Student;
import com.example.demo.model.TestReq;
import com.example.demo.service.StudentService;


@RestController
@RequestMapping("/student")
public class StudentController {
	
	Logger logger=LoggerFactory.getLogger("StudentController.class");
	@Autowired
	StudentRepositry studentRepositry;
	@Autowired
	StudentService studentService;
	@PostMapping("/saveStudent")
	public void saveStudent(@RequestBody Student stu) {
	     logger.info("saving daat into db{}",stu.getName());
	     logger.debug("saving daat into db{}",stu.getName());
		studentRepositry.save(stu);
	}
	
	@PostMapping("/getStudent")
	public List<Student> getStudent() {
	    
		List<Student> responde=studentService.getStudent();
		return responde;
	}
	

	@RequestMapping(value ="/testValue", method = RequestMethod.POST)
	public void backUpAllocationDetails(@RequestBody TestReq test) {

		System.out.println("name is"+test.getName());
		System.out.println("age is"+test.getAge());
		System.out.println("student is"+test.isStudent());

	}
	
	@RequestMapping(value ="/clearCache", method = RequestMethod.GET)
	public void clear() {
            studentService.clearCache();

	}
	
	
	
	

}
