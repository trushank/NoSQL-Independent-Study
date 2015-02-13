package com.cargarage.repository;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.cargarage.garage.Car;
import com.mongodb.WriteResult;

public class CarGarageImpl implements CarGarageBluePrint {

	MongoTemplate mongoTemplate;

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public List<Car> getAllCars() {
		return mongoTemplate.findAll(Car.class);
	}

	public void saveCar(Car Car) {
		mongoTemplate.insert(Car);
	}

	public Car getCar(String id) {
		return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)),
				Car.class);
		
	}

	
	public WriteResult updateCar(String id, String name) {
		return mongoTemplate.updateFirst(
				new Query(Criteria.where("id").is(id)),
				Update.update("name", name), Car.class);
	}

	
	public void deleteCar(String id) {
		mongoTemplate
				.remove(new Query(Criteria.where("id").is(id)), Car.class);
	}

	public void createCollection() {
		if (!mongoTemplate.collectionExists(Car.class)) {
			mongoTemplate.createCollection(Car.class);
		}
	}

	public void dropCollection() {
		if (mongoTemplate.collectionExists(Car.class)) {
			mongoTemplate.dropCollection(Car.class);
		}
	}
}
