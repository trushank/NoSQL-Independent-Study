package com.cargarage.repository;

import java.util.List;

import com.cargarage.garage.Car;
import com.mongodb.WriteResult;

public interface CarGarageBluePrint {

	public List<Car> getAllCars();

	public void saveCar(Car Car);

	public Car getCar(String id);

	public WriteResult updateCar(String id, String name);

	public void deleteCar(String id);

	public void createCollection();

	public void dropCollection();
}
