package com.pravin.spring.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import com.pravin.spring.batch.entity.Customer;
import com.pravin.spring.batch.repository.CustomerRepository;
import com.pravin.spring.batch.string.step.Processor;
import com.pravin.spring.batch.string.step.Reader;
import com.pravin.spring.batch.string.step.Writer;
import com.sun.org.apache.xalan.internal.xslt.Process;

import lombok.AllArgsConstructor;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
@EnableAutoConfiguration
public class SpringBatchConfig {
	
	private JobBuilderFactory jobBuilderFactory;
	private StepBuilderFactory stepBuilderFactory;
	private CustomerRepository customerRepository;
	
	@Bean
	public FlatFileItemReader<Customer> reader(){
		FlatFileItemReader<Customer> itemReader=new FlatFileItemReader<Customer>();
		itemReader.setResource(new FileSystemResource("src/main/resources/customers.csv"));
		itemReader.setName("csvReader");
		itemReader.setLinesToSkip(1);
		itemReader.setLineMapper(lineMapper());
		return itemReader;
		
	}
	
	private LineMapper<Customer> lineMapper() {
		DefaultLineMapper<Customer> lineMapper=new DefaultLineMapper<Customer>();
		
		DelimitedLineTokenizer lineTokenizer=new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames("id","firstName","lastName",	"email","gender","contactNo","country","dob");
		
		BeanWrapperFieldSetMapper<Customer> fieldSetMapper=new BeanWrapperFieldSetMapper<Customer>();
		fieldSetMapper.setTargetType(Customer.class);
		
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		
		return lineMapper;
	}
	
	@Bean
	public CustomerItemProcessor processor() {
		return new CustomerItemProcessor();
	}
	
	@Bean
	public RepositoryItemWriter<Customer> writer(){
		RepositoryItemWriter<Customer> repositoryItemWriter=new RepositoryItemWriter<Customer>();
		repositoryItemWriter.setRepository(customerRepository);
		repositoryItemWriter.setMethodName("save");
		return repositoryItemWriter;
	}
	
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("csv-step").<Customer,Customer>chunk(10)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.taskExecutor(taskExecutor())
				.build();
	}
	
	/*@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<String,String> chunk(1)
				.reader(new Reader())
				.writer(new Writer())
				.processor(new Processor())
				.build();
	}*/
	
	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2").<Customer,Customer> chunk(10)
				.reader(new com.pravin.spring.batch.file.step.Reader())
				.writer(new com.pravin.spring.batch.file.step.Writer())
				.processor(new com.pravin.spring.batch.file.step.Processor())
				.build();
	}
	
	@Bean
	public Job job() {
		return jobBuilderFactory.get("csv-job").flow(step1()).end().build();
	}
	
	@Bean
	public TaskExecutor taskExecutor() {
		SimpleAsyncTaskExecutor asyncTaskExecutor=new SimpleAsyncTaskExecutor();
		asyncTaskExecutor.setConcurrencyLimit(10);
		return asyncTaskExecutor;
	}
}
