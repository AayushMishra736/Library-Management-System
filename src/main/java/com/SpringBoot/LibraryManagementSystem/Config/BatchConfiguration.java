package com.SpringBoot.LibraryManagementSystem.Config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.SpringBoot.LibraryManagementSystem.Batch.BookItemProcessor;
import com.SpringBoot.LibraryManagementSystem.Entity.Book;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	private static final Logger log = LoggerFactory.getLogger(BatchConfiguration.class);

	@Bean
	FlatFileItemReader<Book> itemReader(@Value("${input}") Resource resource) {
		log.info("File Reading Started.");
		FlatFileItemReader<Book> flatFileItemReader = new FlatFileItemReader<>();
		flatFileItemReader.setResource(new ClassPathResource("Books.csv"));
		flatFileItemReader.setLineMapper(getlineMapper());
		flatFileItemReader.setName("CSV-Reader");
		flatFileItemReader.setLinesToSkip(1);
		log.info("File Reading Ended.");
		return flatFileItemReader;
	}

	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
			ItemReader<Book> itemReader, ItemProcessor<Book, Book> itemProcessor, ItemWriter<Book> itemWriter) {
		log.info("Batch job started.");
		Step step = stepBuilderFactory.get("ETL-file-load").<Book, Book>chunk(200).reader(itemReader)
				.processor(itemProcessor).writer(itemWriter).build();
		log.info("Batch job ended.");
		return jobBuilderFactory.get("ETL-Load").incrementer(new RunIdIncrementer()).start(step).build();
	}

	@Bean
	public LineMapper<Book> getlineMapper() {
		log.info("Mapping with CSV File Started.");
		DefaultLineMapper<Book> defaultLineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames(new String[] { "book_id", "author", "book_price", "name", "no_of_copies", "publication"});

		BeanWrapperFieldSetMapper<Book> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Book.class);
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		log.info("Mapping with CSV File Ended.");
		return defaultLineMapper;

	}

}
