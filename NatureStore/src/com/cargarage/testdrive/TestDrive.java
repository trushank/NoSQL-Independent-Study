package com.cargarage.testdrive;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cargarage.garage.Car;
import com.cargarage.repository.CarGarageBluePrint;
import com.cargarage.repository.CarGarageImpl;

public class TestDrive {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:/spring/applicationContext.xml");

		CarGarageBluePrint repository = context.getBean(CarGarageImpl.class);

		repository.dropCollection();
		
		repository.createCollection();

		repository.saveCar(new Car("1", "Jack", "Lamborgini","Gallardo", "Black",100000 ));

		System.out.println("1." + repository.getAllCars());
		repository.saveCar(new Car("2", "John", "Lamborgini","Aventador", "White",150000 ));
		

		System.out.println("2. " + repository.getAllCars());

		System.out.println(repository.getCar("1"));
		

		repository.updateCar("1", "LP560-4");

		System.out.println("3. " + repository.getAllCars());

		repository.deleteCar("2");

		System.out.println("4. " + repository.getAllCars());
	}
}
