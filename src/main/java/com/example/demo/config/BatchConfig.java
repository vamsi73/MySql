package com.example.demo.config;



import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.example.demo.model.Student;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private DataSource dataSource;


	@Bean
   public JdbcCursorItemReader<Student> reader(){
     JdbcCursorItemReader<Student> reader1=new JdbcCursorItemReader();
	 reader1.setDataSource(dataSource);
	 reader1.setSql("select * from employee.student");
	 reader1.setRowMapper(new PersonRowMapper());
     return reader1;
     }
	
	@Bean 
	public StudentItemProcesser process() {
		return new StudentItemProcesser();
	}



@Bean
public FlatFileItemWriter<Student> writer(){
FlatFileItemWriter<Student> writer=new FlatFileItemWriter<Student>();
writer.setResource(new ClassPathResource("student.csv"));
DelimitedLineAggregator<Student> lineAggregrator=new DelimitedLineAggregator<Student>();
lineAggregrator.setDelimiter(",");
BeanWrapperFieldExtractor<Student> fieldExtractor=new BeanWrapperFieldExtractor<Student>();
fieldExtractor.setNames(new String[]{"id","branch","name"});
lineAggregrator.setFieldExtractor(fieldExtractor);
writer.setLineAggregator(lineAggregrator);
return writer;
}

@Bean

public Step step1(){
return stepBuilderFactory.get("step1").<Student,Student>chunk(100) .reader(reader()).processor(process()).writer(writer()).build();
}

@Bean
public Job exportPersonJob(){
return jobBuilderFactory.get("export data into csv").incrementer(new RunIdIncrementer())
.flow(step1()).end().build();
}
}
