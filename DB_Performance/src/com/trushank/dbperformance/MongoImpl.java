package com.trushank.dbperformance;


import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.WriteResult;

public class MongoImpl implements MongoInterface {

	MongoTemplate mongoTemplate;

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public List<Student> getAllStudents() {
		return mongoTemplate.findAll(Student.class);
	}

	public void saveStudent(Student student) {
		mongoTemplate.insert(student);
	}

	public Student getStudent(String id) {
		return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)),
				Student.class);
		
	}

	
	public WriteResult updateStudent(String id, String name) {
		return mongoTemplate.updateFirst(
				new Query(Criteria.where("id").is(id)),
				Update.update("name", name), Student.class);
	}

	
	public void deleteStudent(String id) {
		mongoTemplate
				.remove(new Query(Criteria.where("id").is(id)), Student.class);
	}

	public void createCollection() {
		if (!mongoTemplate.collectionExists(Student.class)) {
			mongoTemplate.createCollection(Student.class);
		}
	}

	public void dropCollection() {
		if (mongoTemplate.collectionExists(Student.class)) {
			mongoTemplate.dropCollection(Student.class);
		}
	}
}
